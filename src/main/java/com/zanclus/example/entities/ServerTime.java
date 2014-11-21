/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.example.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author <a href="mailto: deven.phillips@gmail.com">Deven Phillips</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServerTime extends AbstractDocument {

    private Integer hours;

    private Integer minutes;

    private Integer seconds;

    private Integer day;

    private Integer month;

    private Integer year;
}
