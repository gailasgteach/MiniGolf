/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author gail
 */
public class Flag extends Group {
    
    private Text t1;

    public Flag() {
        DropShadow d = new DropShadow();
        d.setOffsetX(2.0);
        d.setOffsetY(2.0);
        setEffect(d);
        Stop[] stops = new Stop[]{new Stop(0, Color.LIGHTGRAY), new Stop(1, Color.GRAY)};
        Stop[] stops2 = new Stop[]{new Stop(0, Color.GRAY), new Stop(1, Color.LIGHTGRAY)};

        Polygon p1 = new Polygon();
        p1.getPoints().addAll(new Double[]{
            // x, y
            10.0, 10.0,
            50.0, 25.0,
            10.0, 50.0});
        p1.setFill(Color.DODGERBLUE);

        Rectangle r1 = new Rectangle(10, 80);
        r1.setX(2.0f);
        r1.setY(5.0f);
        Ellipse e1 = new Ellipse(5, 3);
        e1.setCenterX(7.0f);
        e1.setCenterY(7.0f);
        Ellipse e2 = new Ellipse(5, 3);
        e2.setCenterX(7.0f);
        e2.setCenterY(85.0f);
        r1.setFill(Color.BURLYWOOD);
        e1.setFill(Color.BURLYWOOD);
        e2.setFill(Color.BURLYWOOD);
        t1 = new Text("1");
        t1.setX(17.0);
        t1.setY(33.0);
        t1.setStroke(Color.WHITE);
        t1.setFill(Color.WHITE);

        getChildren().addAll(r1, e1, e2, p1, t1);
//        setEffect(lighting);

    }
    
    public void setHoleNumber(int num) {
        t1.setText(num + "");
    }

}
