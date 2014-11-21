/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.example.ioc;

import com.zanclus.example.api.GetServerTime;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author dphillips
 */
public class CdiBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(BeanProvider.injectFields(new GetServerTime())).to(GetServerTime.class);
    }
}
