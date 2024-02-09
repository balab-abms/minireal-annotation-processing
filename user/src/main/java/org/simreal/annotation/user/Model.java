package org.simreal.annotation.user;

import org.simreal.annotation.*;
import org.simreal.processor.DTO.ModelDTO;

import java.util.ArrayList;

@SimModel
public class Model {
    public Model(@SimParam(value = "50") int popln,
                 @SimParam(value = "true") boolean wealth){
        System.out.println("Hello Simulation");
    }

    @SimDB(name = "controllers_data")
    public ArrayList<Agent> controllersToDB()
    {
        return null;

    }

    @SimDB(name = "oracles_data")
    public ArrayList<Agent> oraclesToDB()
    {
        return null;

    }

    @SimChart(name = "line_chart")
    public double toChart(){
        return 0;
    }

    @SimVisual
    public ArrayList<Agent> toVisual(){
        return null;
    }
}
