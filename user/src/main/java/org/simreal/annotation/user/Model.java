//package org.simreal.annotation.user;
//
//import org.simreal.annotation.SimModel;
//import org.simreal.annotation.SimChart;
//import org.simreal.annotation.SimDB;
//import org.simreal.annotation.SimParam;
//import sim.engine.SimState;
//
//import java.util.ArrayList;
//
//@SimModel
//public class Model extends SimState {
//    public Model(@SimParam(value = "50") int popln,
//                 @SimParam(value = "true") boolean wealth){
//        super(System.currentTimeMillis());
//        System.out.println("Hello Simulation");
//    }
//
//    @SimDB(name = "controllers_data")
//    public ArrayList<Agent> controllersToDB()
//    {
//        return null;
//
//    }
//
//    @SimDB(name = "oracles_data")
//    public ArrayList<Agent> oraclesToDB()
//    {
//        return null;
//
//    }
//
//    @SimChart(name = "line_chart")
//    public double toRoundChart(){
//        return 0;
//    }
//
//    @SimChart(name = "avg_chart")
//    public double toAvgChart(){
//        return 0;
//    }
//}
