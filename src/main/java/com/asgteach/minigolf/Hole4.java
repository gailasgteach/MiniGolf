/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;

/**
 *
 * @author gail
 */
public class Hole4 extends StackPane {

    private final Rectangle vert = new Rectangle(180, 650, Color.rgb(22, 127, 45));
    private final Rectangle hort = new Rectangle(375, 150, Color.rgb(22, 127, 45));
    private final Rectangle barrier = new Rectangle(200, 20, Color.rgb(174, 53, 57));
    private final Path borderPath = new Path();
    private final Rectangle startPad = new Rectangle(103, 61, Color.DARKGREEN);
    

    public Hole4() {
        barrier.setArcHeight(20);
        barrier.setArcWidth(20);

        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);
        light.setElevation(30);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);
        
        barrier.setEffect(lighting);
        
        MoveTo moveTo = new MoveTo(vert.getX() + vert.getWidth()/2, vert.getY());

        HLineTo hLineTo1 = new HLineTo();
        hLineTo1.setX(vert.getWidth() + vert.getWidth()/2);
        VLineTo vLineTo1 = new VLineTo();
        vLineTo1.setY(vert.getHeight()- (vert.getHeight()/2 + hort.getHeight()/2));
        
        HLineTo hLineTo2 = new HLineTo();
        hLineTo2.setX(hort.getWidth());
        VLineTo vLineTo2 = new VLineTo();
        vLineTo2.setY(vert.getHeight() - (vert.getHeight()/2 - hort.getHeight()/2));
        HLineTo hLineTo3 = new HLineTo();
        hLineTo3.setX(hort.getWidth()-(hort.getWidth()/2 - vert.getWidth()/2));
        VLineTo vLineTo3 = new VLineTo();
        vLineTo3.setY(vert.getHeight());       
        HLineTo hLineTo4 = new HLineTo();
        hLineTo4.setX(vert.getX() + vert.getWidth()/2);
        VLineTo vLineTo4 = new VLineTo();
        vLineTo4.setY(vert.getHeight() - (vert.getHeight()/2 - hort.getHeight()/2));
        HLineTo hLineTo5 = new HLineTo();
        hLineTo5.setX(hort.getX());
        VLineTo vLineTo5 = new VLineTo();
        vLineTo5.setY(vert.getHeight()- (vert.getHeight()/2 + hort.getHeight()/2));
        HLineTo hLineTo6 = new HLineTo();
        hLineTo6.setX(vert.getX() + vert.getWidth()/2);
        VLineTo vLineTo6 = new VLineTo();
        vLineTo6.setY(vert.getY());
        
        borderPath.setStrokeWidth(20);
        borderPath.setStroke(Color.BROWN);
        borderPath.setEffect(lighting);        
 
        borderPath.getElements().addAll(moveTo, hLineTo1, vLineTo1, hLineTo2, vLineTo2,
                hLineTo3, vLineTo3, hLineTo4, vLineTo4,
                hLineTo5, vLineTo5, hLineTo6, vLineTo6);
        startPad.setTranslateY(235);     
        getChildren().addAll(vert, hort, barrier, borderPath, startPad);
    }

}
