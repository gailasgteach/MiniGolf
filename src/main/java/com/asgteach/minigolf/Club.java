/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author gail
 */
public class Club extends Group {

    public Club() {

//        Light.Distant light = new Light.Distant();
//        light.setAzimuth(-135.0);
//        light.setElevation(30);
//
//        Lighting lighting = new Lighting();
//        lighting.setLight(light);
//        lighting.setSurfaceScale(5.0);
//        Rectangle r1 = new Rectangle(40, 15, Color.GRAY);
        DropShadow d = new DropShadow();
        d.setOffsetX(2.0);
        d.setOffsetY(2.0);
        setEffect(d);
        Stop[] stops = new Stop[]{new Stop(0, Color.LIGHTGRAY), new Stop(1, Color.GRAY)};
        Stop[] stops2 = new Stop[]{new Stop(0, Color.GRAY), new Stop(1, Color.LIGHTGRAY)};

        Rectangle r1 = new Rectangle(40, 15);
        r1.setFill(
                new LinearGradient(0.0, 0.0, 1.0, 1.0, true, CycleMethod.NO_CYCLE, stops2));
        r1.setArcHeight(10);
        r1.setArcWidth(10);
        r1.setTranslateX(-20);
        r1.setTranslateY(-7.5);

        Rectangle r2 = new Rectangle(8, 45);
        r2.setFill(
                new LinearGradient(0.0, 0.0, 1.0, 1.0, true, CycleMethod.NO_CYCLE, stops));
        r2.setArcHeight(8);
        r2.setArcWidth(8);
        r2.setTranslateX(-20);
        r2.setTranslateY(-47.5);

        getChildren().addAll(r1, r2);
//        setEffect(lighting);

    }

}
