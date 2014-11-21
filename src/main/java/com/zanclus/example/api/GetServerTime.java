/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.example.api;

import com.zanclus.example.entities.ServerTime;
import java.util.Calendar;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import lombok.Setter;

/**
 *
 * @author <a href="mailto: deven.phillips@gmail.com">Deven Phillips</a>
 */
@Path("/rest")
@RequestScoped
public class GetServerTime {
    private static final String INJECTION_NPE = 
            "serverTime Calendar object not injected!";
    
    @Setter(onMethod = @__({@Inject, @Named("serverTime")}))
    private Calendar serverTime;

    @GET
    @Path("/time")
    @Produces(value = {"application/json"})
    public String getServerTime() {
        if (serverTime==null) {
            throw new NullPointerException(INJECTION_NPE);
        } else {
            ServerTime time = new ServerTime()
                                    .day(serverTime.get(Calendar.DAY_OF_MONTH))
                                    .month(serverTime.get(Calendar.MONTH))
                                    .year(serverTime.get(Calendar.YEAR))
                                    .hours(serverTime.get(Calendar.HOUR))
                                    .minutes(serverTime.get(Calendar.MINUTE))
                                    .seconds(serverTime.get(Calendar.SECOND));
            return time.toString();
        }
    }
}
