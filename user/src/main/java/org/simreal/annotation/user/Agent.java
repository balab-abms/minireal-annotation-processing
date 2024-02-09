package org.simreal.annotation.user;

import lombok.Getter;
import lombok.Setter;
import org.simreal.annotation.SimAgentUI;
import org.simreal.annotation.SimField;

@Getter
@Setter
public class Agent {
    @SimField
    int wealth;

    @SimField
    int step;

    public Agent(){

    }

    @SimAgentUI
    // update the return type to SimUI in the future
    public void agentUI()
    {
        return;
    }
}
