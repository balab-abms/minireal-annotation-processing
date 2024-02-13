package com.example.application.sim_ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimUI implements Serializable
{
    private String color;
    private String avatar;
    private Integer[] pos;


}
