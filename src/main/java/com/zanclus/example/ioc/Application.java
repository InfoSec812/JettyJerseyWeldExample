/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.example.ioc;

import com.zanclus.example.entities.Configuration;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import org.apache.deltaspike.core.api.common.DeltaSpike;
import org.eclipse.jetty.server.Server;

/**
 * Dependency Injection Application Module
 * @author <a href="mailto: deven.phillips@gmail.com">Deven Phillips</a>
 */
@ApplicationScoped @DeltaSpike
public class Application {

    private Configuration config;

    private Server server;

    public void observe(@Observes Configuration config) {
        this.config = config;
    }

    public void observe(@Observes Server server) {
        this.server = server;
    }


    @Produces @DeltaSpike
    public Calendar getGalendar() {
        return new GregorianCalendar();
    }
}
