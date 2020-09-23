/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf.model;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author gail
 */
public class MiniHole {
    // all the data that defines a hole
    private final int number;
    private final int par;
    private final double imagewidth;
    private final double imageheight;
    private final double startpadwidth;
    private final double startpadheight;
    private final double startpadx;
    private final double startpady;
    private final double cupy;
    private final double cupxstartrange;
    private final double cupxendrange;
    private final Pos powerMeterPosition;
    // barriers is a list of rectangles that marks where on the hole's image
    // barrierrs exist that the shooter must go around or bank off of
    private final List<Rectangle> barriers = new ArrayList<>();

    public MiniHole(int number,
            int par,
            double imagewidth, 
            double imageheight, 
            double startpadwidth, 
            double startpadheight, 
            double startpadx, 
            double startpady, 
            double cupy, 
            double cupxstartrange, 
            double cupxendrange,
            Pos pos) {
        this.number = number;
        this.par = par;
        this.imagewidth = imagewidth;
        this.imageheight = imageheight;
        this.startpadwidth = startpadwidth;
        this.startpadheight = startpadheight;
        this.startpadx = startpadx;
        this.startpady = startpady;
        this.cupy = cupy;
        this.cupxstartrange = cupxstartrange;
        this.cupxendrange = cupxendrange;
        this.powerMeterPosition = pos;
    }

    public int getNumber() {
        return number;
    }
    
    public int getPar() {
        return par;
    }

    public double getImagewidth() {
        return imagewidth;
    }

    public double getImageheight() {
        return imageheight;
    }

    public double getStartpadwidth() {
        return startpadwidth;
    }

    public double getStartpadheight() {
        return startpadheight;
    }

    public double getStartpadx() {
        return startpadx;
    }

    public double getStartpady() {
        return startpady;
    }

    public double getCupy() {
        return cupy;
    }

    public double getCupxstartrange() {
        return cupxstartrange;
    }

    public double getCupxendrange() {
        return cupxendrange;
    }

    public List<Rectangle> getBarriers() {
        return barriers;
    }

    public Pos getPowerMeterPosition() {
        return powerMeterPosition;
    }    
    
}
