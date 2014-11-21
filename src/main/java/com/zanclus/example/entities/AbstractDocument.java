/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.example.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * An abstract entity document which can be serialized to JSON
 * @author <a href="mailto: deven.phillips@gmail.com">Deven Phillips</a>
 */
public class AbstractDocument {

    /**
     * Retrieve an instance of {@link Gson} which has some standard configurations
     * @return An instance of {@link Gson}
     */
    protected Gson getGson() {
        return (new GsonBuilder()).serializeNulls().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    }

    /**
     * Serialize the object using Gson to convert to JSON
     * @return A {@link String} containing the JSON serialized representation
     * of this object.
     */
    @Override
    public String toString() {
        return getGson().toJson(this);
    }
}
