/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.example.jaxrs;

import com.google.common.collect.ImmutableSet;
import com.zanclus.example.api.GetServerTime;
import com.zanclus.example.ioc.Application;
import java.util.Set;

/**
 *
 * @author dphillips
 */
public class WeldApplication extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        return ImmutableSet.of(GetServerTime.class, Application.class);
    }
}
