package org.simreal.annotation.user;

import lombok.Getter;
import lombok.Setter;
import org.simreal.annotation.SimAgent;
import org.simreal.annotation.SimAgentVisual;
import org.simreal.annotation.SimField;

@Getter
@Setter
@SimAgent
public class Agent {
    @SimField
    int wealth;

    @SimField
    int step;

    public Agent(){
        // set the step at each tick

    }

    @SimAgentVisual
    // update the return type to SimUI in the future
    public String agentUI()
    {
        return null;
    }
}
