package com.locadoraveiculo.locadoraveiculosapp.stepdefinitions;

import io.cucumber.java.ParameterType;

public class ParameterTypeDefinitions {

    @ParameterType(".*")
    public String login(String login){
        return login;
    }

    @ParameterType(".*")
    public String password(String password){
        return password;
    }

    @ParameterType(".*")
    public String role(String role){
        return role;
    }

}
