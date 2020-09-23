/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import static com.asgteach.minigolf.Config.GOLFBALL_RADIUS;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

/**
 *
 * @author gail
 */
public class GolfBall extends Circle {

    public GolfBall() {
        setRadius(GOLFBALL_RADIUS);
        setFill(new RadialGradient(
                0, 0, .2, .2, .5,
                true, CycleMethod.NO_CYCLE,
                new Stop[]{
                    new Stop(0, Color.WHITESMOKE),
                    new Stop(1, Color.GREY)
                }));
        DropShadow d = new DropShadow();
        d.setOffsetX(2.0);
        d.setOffsetY(2.0);
        setEffect(d);       
    }
    
    

}
