# MiniReal Annotation Processing

## Background on Anntation Processing
### Introduction
Annotation processing plays a key role in the utilization of the MiniReal system.
It helps to generate the `Simulation Launcher` class when the project is compiled
based on the respective simulation code implementation of the User. This class is:

* Used an an entry point for running the simulaiton jar file. The MiniReal system
  looks for this class when trying to run the simulation jar file.
* Accepts the values for simulation parameters at run time from the MiniReal system
  UI.
* Sends the charting values to the Message broker by invoking the correct method.

### Details of MiniReal Annotations
The custome Annotations and the respective processor built for the MiniReal system
is published on Maven Central. ([MiniReal Annotation Processing Library](
https://central.sonatype.com/artifact/io.github.panderior/minireal-annotation))

The following table shows the purpose and scope of the annotations.

| Annotation | Parameters | Target     | Retention | Purpose      | Return Type |
|-----------|------------|------------|-----------|---------------|----------------|
| SimModel  | none       | Class      | Source    | To designate the Simulation Model class for annotation processing. Only one class can have this annotation. | Not applicable |
| SimAgent  | none       | Class      | Source    | To designate the Simulation Agent class for annotation processing. | Not applicable |
| SimChart  | name       | Method     | Source    | To identify a method for visualizing simulation data on a chart. | An integer summarizing the cumulative data of agents for a single simulation cycle. |
| SimParam  | value      | Parameter  | Source    | To define the parameters that the simulation model uses during execution.  | Not applicable |

## Model and Agent Annotation usage
The `@SimModel` and `@SimAgent` annotations are used to mark the simulation `Model`
and `Agent` classes. This information is used by the MiniReal Annotation Processor
library in the generation of the `Simulation Launcher` class.

```java title="Model.java"
import org.simreal.annotation.*;
import sim.engine.SimState;

@SimModel
public class Model extends SimState  {
	
    // rest of code ...
}
```

```java title="Agent.java"
import org.simreal.annotation.SimAgent;
import sim.engine.Steppable;
import sim.engine.SimState;

@SimAgent
public class Agent implements Steppable {

    // rest of code ...
	
}
```

## Simluation parameter Annotation usage
The `@SimParam` annotation can be used to specify the simulation model parameters
that will be recieved at runtime. The MiniReal system will pickup the list of
simulation parameters and show them on the UI. The values inserted by the User on
the UI will be fed to the simulation on runtime.

This annotation should be defined for arguments placed in the constructor of the
`Model` class.

The `@SimParam` annotation takes one argument for its self, which is the default value
for the parameter it is binded to. If no value is provided to the simulation model at
runtime, this default value is used to initialize the model parameter.

* The key for this argument is named `value` and it takes a `String` object.
* Later on the `String` value is casted to the correct type with respect to the simulation
  parameter.

```java title="Model.java"
import org.simreal.annotation.*;
import sim.engine.SimState;

@SimModel
public class Model extends SimState  {
	
    // rest of code

	private int population;

    // class constructor
	public Model(
        @SimParam(value = "500") int population,
        @SimParam(value = "100") int wealth
    ) {
		super(System.currentTimeMillis());
		this.population = population;
		createAgents(wealth);
	}

    // rest of code
}
```

## Simulation Chart Annotation usage
The MiniReal system gives realtime visualization (insight) of simulation models through
a line chart on the browser. This line chart module receives an integer value from the
simulation on each tick and displays it accordingly.

* The simulation model must have one or more methods that calculate some aggregate behaviour
  and return an integer value for each tick.
* Then users have to annotate such methods with the `@SimChart` annotation. This annotation takes
  argument with the keyword called `name` for the chart name. The value for this argument should be
  passed as `String`.

```java title="Model.java"
import org.simreal.annotation.*;
import sim.engine.SimState;

@SimModel
public class Model extends SimState  {

    // rest of code

    @SimChart(name="chart_name")
	public int chartingMethod(){
        int result = 0;

        // perform oprtation to calculate and obtain integer representing ~
        // ~ aggregate simulation behaviour (update result)

        return result;
    }
}
```

## License
This opensource project is licensed under Apache 2.0 license.