package org.simreal.processor;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.squareup.javapoet.*;
import org.checkerframework.checker.units.qual.A;
import org.simreal.annotation.*;
import org.simreal.processor.DTO.*;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

@AutoService(Processor.class)
public class SimRealAnnotationProcessor  extends AbstractProcessor
{
    // define memebre variables
    private int round=0;
    private ModelDTO modelDTO;
    private ArrayList<ParamDTO> paramDTOList;
    private ArrayList<DatabaseDTO> databaseDTOList;
    private ArrayList<ChartDTO> chartDTOList;

    private VisualDTO visualDTO;

    // define generated simulation launcher methods related variables
    private ArrayList<MethodSpec> dbMethodsList;
    private ArrayList<MethodSpec> chartMethodsList;
    private MethodSpec visualMethod;
    private CodeBlock paramsCodeblock;
    private ArrayList<MethodSpec> agentDataMethodsList;
    private TypeSpec simLauncherCode;

    // define annotation processing related variables
    private ProcessingEnvironment processingEnv;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.processingEnv = processingEnv;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes()
    {
        Set<String> set = new HashSet<>();
        set.add(SimModel.class.getName());
        set.add(SimAgent.class.getName());
        set.add(SimDB.class.getName());
        set.add(SimChart.class.getName());
        set.add(SimVisual.class.getName());
        set.add(SimField.class.getName());
        set.add(SimParam.class.getName());

        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion()
    {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(round == 0)
        {
            // generate meta-data
            try {
                makeModelMetaInfo(roundEnv);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // process database
            dbMethodsList = new ArrayList<>();
            agentDataMethodsList = new ArrayList<>();
            generateDBMethod(roundEnv);

            // process chart
            chartMethodsList = new ArrayList<>();

            // process visual
            // visualMethod =

            // process model parameters
            // paramsCodeblock =

            // generate code
            generateSimLauncherCode();
        }

        round ++;

        return true;
    }

    public boolean makeModelMetaInfo(RoundEnvironment roundEnv) throws Exception
    {
        Gson gson = new Gson();

        // Extract model annotation data
        modelDTO = new ModelDTO();
        Set<? extends Element> modelElementSet = roundEnv.getElementsAnnotatedWith(SimModel.class);
        // the usage of the SimModel annotation should be only one
        if(modelElementSet.size() > 0)
        {
            // Element element = modelElementSet.stream().collect(Collectors.toList()).get(0);
            Element element = modelElementSet.stream().findFirst().get();
            modelDTO.setName(element.getSimpleName().toString());
            modelDTO.setClassName(ClassName.get((TypeElement) element));
        } else {
            throw new Exception("SimModel Annotation should be only 1");
        }

        System.out.println(gson.toJson(modelDTO));

        // Extract model parameters annotation data
        Set<? extends Element> paramElementSet = roundEnv.getElementsAnnotatedWith(SimParam.class);
        paramDTOList = new ArrayList<>();
        for(Element elt: paramElementSet)
        {
            ParamDTO paramDTO = new ParamDTO();
            paramDTO.setName(elt.getSimpleName().toString());
            paramDTO.setValue(elt.getAnnotation(SimParam.class).value());
            paramDTO.setType(elt.asType().toString());
            paramDTOList.add(paramDTO);
        }

        System.out.println(gson.toJson(paramDTOList,ArrayList.class));

        // Extract database annotation data, with agents field level annotations
        databaseDTOList = new ArrayList<>();
        Set<? extends Element> toDBElementSet = roundEnv.getElementsAnnotatedWith(SimDB.class);
        for(Element elt: toDBElementSet) {
            DatabaseDTO databaseDTO = new DatabaseDTO();

            databaseDTO.setTableName(elt.getAnnotation(SimDB.class).name());
            databaseDTO.setMethodName(elt.getSimpleName().toString());
            // get the Agent class related to the database persisting method
            TypeMirror type = ((ExecutableElement) elt).getReturnType();
            TypeMirror agentGenericType = ((DeclaredType) type).getTypeArguments().get(0);
            Element agentType = ((DeclaredType) agentGenericType).asElement();
            databaseDTO.setBoundedAgentName(ClassName.get((TypeElement)agentType));

            List<FieldDTO> simLogFields = agentType.getEnclosedElements().stream()
                    .filter(x -> x.getKind() == ElementKind.FIELD)
                    .filter(x -> x.getAnnotation(SimField.class) != null)
                    .map(x->{
                        FieldDTO simLogField = new FieldDTO();
                        simLogField.setName(x.getSimpleName().toString());
                        simLogField.setType(x.asType().toString());
                        return simLogField;
                    }).collect(Collectors.toList());
            databaseDTO.setBoundedAgentFields(simLogFields);

            databaseDTOList.add(databaseDTO);
        }
        System.out.println(gson.toJson(databaseDTOList,ArrayList.class));

        // Extract Charting annotation data
        chartDTOList = new ArrayList<>();
        Set<? extends Element> toChartElementSet = roundEnv.getElementsAnnotatedWith(SimChart.class);
        for(Element elt: toChartElementSet) {
            ChartDTO chartDTO = new ChartDTO();

            chartDTO.setChartName(elt.getAnnotation(SimChart.class).name());
            chartDTO.setMethodName(elt.getSimpleName().toString());
            chartDTOList.add(chartDTO);
        }
        System.out.println(gson.toJson(chartDTOList, ArrayList.class));

        // Extract Visualizing annotation data
        visualDTO = new VisualDTO();
        Set<? extends Element> visualElementSet = roundEnv.getElementsAnnotatedWith(SimVisual.class);
        // the usage of the SimModel annotation should be only one
        if(visualElementSet.size() > 0)
        {
            Element element = visualElementSet.stream().findFirst().get();
            visualDTO.setModelMethodName(element.getSimpleName().toString());
            // get the Agent class related to the visual method
            TypeMirror type = ((ExecutableElement) element).getReturnType();
            TypeMirror agentGenericType = ((DeclaredType) type).getTypeArguments().get(0);
            Element agentType = ((DeclaredType) agentGenericType).asElement();
            visualDTO.setBoundedAgentName(ClassName.get((TypeElement)agentType));
            // get the Agent UI method name
            String agentMethodName = agentType.getEnclosedElements().stream()
                    .filter(x -> x.getKind() == ElementKind.METHOD)
                    .filter(x -> x.getAnnotation(SimAgentUI.class) != null)
                    .map(x -> {
                        return x.getSimpleName().toString();
                    })
                    .collect(Collectors.toList()).get(0);
            visualDTO.setAgentMethodName(agentMethodName);
        } else {
            throw new Exception("SimVisual Annotation should be only 1");
        }
        System.out.println(gson.toJson(visualDTO));

        // Write metadata to file
        MetaModelDTO metaModelDTO = new MetaModelDTO();
        metaModelDTO.setModelDTO(modelDTO);
        metaModelDTO.setDbDTOList(databaseDTOList);
        metaModelDTO.setParamDTOList(paramDTOList);
        metaModelDTO.setChartDTOList(chartDTOList);
        metaModelDTO.setVisualDTO(visualDTO);

        FileObject object = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT,"","metaModel.json");
        Writer writer = object.openWriter();
        gson.toJson(metaModelDTO, writer);
        writer.close();

        System.out.println(object.toUri().toString());

        return true;
    }

    public boolean generateDBMethod(RoundEnvironment roundEnv)
    {
        for(DatabaseDTO dbDTO: databaseDTOList)
        {
            // generate get agent data method
            CodeBlock.Builder getAgentCode = CodeBlock.builder();
            getAgentCode.addStatement("$T<$T, $T> sim_data = new $T<>()", Map.class, String.class, Object.class, LinkedHashMap.class);
//            getAgentCode.addStatement("sim_data.put(\"step\", model.schedule.getSteps())");
            ArrayList<FieldDTO> agt_db_fields = (ArrayList<FieldDTO>) dbDTO.getBoundedAgentFields();
            // define  parametrized map type for agent data method
            ParameterizedTypeName parameterized_map_type = ParameterizedTypeName.get(
                    ClassName.get(Map.class),
                    ClassName.get(String.class),
                    ClassName.get(Object.class));
            for(FieldDTO temp_agt_field: agt_db_fields)
            {
                //TODO find better ways to capitalize field name
                String field_name_capitalized = temp_agt_field.getName().substring(0, 1).toUpperCase() + temp_agt_field.getName().substring(1);
                getAgentCode.addStatement("sim_data.put(\"$L\", agent.get$L())", temp_agt_field.getName(), field_name_capitalized);
            }
            getAgentCode.addStatement("return sim_data");

            String agt_data_method_name = String.format("%sData", dbDTO.getBoundedAgentName().simpleName().toString());
            MethodSpec getAgentDataMethod = MethodSpec.methodBuilder(agt_data_method_name)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(parameterized_map_type)
                    .addParameter(modelDTO.getClassName(), "model")
                    .addParameter(dbDTO.getBoundedAgentName(), "agent")
                    .addCode(getAgentCode.build())
                    .build();
            agentDataMethodsList.add(getAgentDataMethod);

            // loop through db agent method and send data to kafka
            CodeBlock.Builder send_agt_data_code = CodeBlock.builder();
            send_agt_data_code.beginControlFlow("for ($T temp_agt: model.$L())", dbDTO.getBoundedAgentName(), dbDTO.getMethodName());
            send_agt_data_code.addStatement("kafkaTemplate.send(\"db\", \"$L\", $N(model, temp_agt))", dbDTO.getTableName(), getAgentDataMethod);
            send_agt_data_code.endControlFlow();

            String send_agt_data_method_name = String.format("%sSendData", dbDTO.getBoundedAgentName().simpleName().toString());
            MethodSpec sendAgentDataMethod = MethodSpec.methodBuilder(send_agt_data_method_name)
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(modelDTO.getClassName(), "model")
                    .addParameter(dbDTO.getBoundedAgentName(), "agent")
                    .addCode(send_agt_data_code.build())
                    .build();

            dbMethodsList.add(sendAgentDataMethod);
        }
        return true;
    }

    public boolean generateSimLauncherCode()
    {
        // define Kafka template parametrized type
        ParameterizedTypeName kafka_template_type = ParameterizedTypeName.get(
                ClassName.get(KafkaTemplate.class),
                ClassName.get(String.class),
                ClassName.get(Object.class));
        // define the member variables for the simulation launcher class
        FieldSpec kafka_template_field = FieldSpec.builder(kafka_template_type, "kafkaTemplate")
                .addModifiers(Modifier.PRIVATE)
                .build();
        // define the simulation launcher constructor
        MethodSpec simConstructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(kafka_template_type, "kafkaTemplate")
                .addStatement("this.kafkaTemplate = kafkaTemplate")
                .build();


        simLauncherCode = TypeSpec.classBuilder("SimulationLauncher")
                .addModifiers(Modifier.PUBLIC)
                .addField(kafka_template_field)
                .addMethod(simConstructor)
                .addMethods(agentDataMethodsList)
                .addMethods(dbMethodsList)
                .build();

        try
        {
            JavaFile.builder(modelDTO.getClassName().packageName(), simLauncherCode)
                    .build()
                    .writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "ERROR : " + e);
            throw new RuntimeException(e);
        }

        return true;
    }
}
