/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.example.entities;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author <a href="mailto: deven.phillips@gmail.com">Deven Phillips</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Parameters(separators = "= ")
public class Configuration extends AbstractDocument {

    @Expose
    @Parameter(names = {"-l", "--listen"}, description = "The address on which the Jetty server engine should listen")
    private String listen = "127.0.0.1";

    @Expose
    @Parameter(names = {"-p", "--port"}, description = "The port on which the Jetty server engine should bind.")
    private Integer port = 6080;
}
