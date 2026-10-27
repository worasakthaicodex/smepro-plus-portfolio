package com.smepro.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.smepro.models.Transition;



public class DefinitionBuilder {

    private String[] places;
    private Map<String, Transition> transitions = new HashMap<>();

    // Update this method to return DefinitionBuilder instead of String[]
    public DefinitionBuilder addPlaces(String[] places) {
        this.places = places;
        return this; // Return 'this' to allow method chaining
    }

    public DefinitionBuilder addTransition(Transition transition) {
        transitions.put(transition.getName(), transition);
        return this; // Return 'this' to allow method chaining
    }

    public DefinitionBuilder build() {
        return this;
    }

    public Transition getTransition(String transitionName, String currentStep) {
        if (transitions.containsKey(transitionName)) {
            Transition transition = transitions.get(transitionName);
            if (transition.getFrom().equals(currentStep)) {
                return transition;
            }
        }
        return null;
    }
}
