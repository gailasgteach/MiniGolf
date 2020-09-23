/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf.model;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author gail
 */
public class MiniGolfCourse {

    /**
     * MiniGolfCourse Static data for each hole as defined in MiniHole
     */
    private List<MiniHole> course = new ArrayList<>();

    public MiniGolfCourse() {
        /*
            int hole-number,             
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
            Pos powerMeterPosition
         */
        course.add(new MiniHole(1, 1, 193, 742, 103, 61, 0, 301, -310,
                -35, 35, Pos.TOP_LEFT));
        course.add(new MiniHole(2, 2, 333, 558, 115, 56, 0, 214, -235,
                -70, 70, Pos.CENTER));
        course.add(new MiniHole(3, 2, 195, 745, 100, 60, 0, 300, -310,
                -35, 35, Pos.TOP_LEFT));
        course.add(new MiniHole(4, 3, 796, 1346, 200, 120, 0, 470, -550,
                -120, 85, Pos.TOP_LEFT));
        course.add(new MiniHole(5, 3, 445, 745, 63, 101, -156, 280, 
                300, 135, 170, Pos.CENTER));
        course.add(new MiniHole(6, 3, 397, 547, 62, 102, -125, -176, 
                215, 117, 152, Pos.TOP_LEFT));
        course.add(new MiniHole(7, 3, 397, 547, 60, 100, -127, 179, 
                200, 105, 145, Pos.TOP_LEFT));
        course.add(new MiniHole(8, 2, 394, 545, 62, 102, -126, -171, 
                -215, 107, 142, Pos.TOP_LEFT));
        course.add(new MiniHole(9, 2, 396, 545, 60, 100, -125, 160, 
                -200, 67, 133, Pos.TOP_LEFT));
        /*
        # hole9 properties file
imagewidth=396
imageheight=545
startpadwidth=60
startpadheight=100
startpadx=-125
startpady=160
cupy=-200
cupxstartrange=67
cupxendrange=133
         */

 /*
        Numbers for barrier rectangles:
        based on stackpane Pos.CENTER
        width, height, translateX, translateY, arcHeight, arcWidth 
         */
        course.get(0).getBarriers().add(buildRectangleBarrier(193 - 40, 742 - 40, 0, 0, 20, 20));
        course.get(1).getBarriers().add(buildRectangleBarrier(333 - 33, 558 - 33, 0, 0, 20, 20));
        course.get(1).getBarriers().add(buildRectangleBarrier(180, 180, 0, -112, 10, 10));
        course.get(2).getBarriers().add(buildRectangleBarrier(195 - 43, 745 - 42, 0, 0, 20, 20));
        course.get(2).getBarriers().add(buildRectangleBarrier(66, 19, 43, -140, 0, 0));
        course.get(2).getBarriers().add(buildRectangleBarrier(66, 19, -43, 109, 0, 0));
        course.get(3).getBarriers().add(buildRectangleBarrier(796 - 77, 1346 - 77, 0, 0, 20, 20));
        course.get(3).getBarriers().add(buildRectangleBarrier(400, 40, 0, 0, 20, 20));
        course.get(3).getBarriers().add(buildRectangleBarrier(180, 500, -270, -386, 0, 0));
        course.get(3).getBarriers().add(buildRectangleBarrier(210, 500, 255, -386, 0, 0));
        course.get(3).getBarriers().add(buildRectangleBarrier(180, 500, -270, 385, 0, 0));
        course.get(3).getBarriers().add(buildRectangleBarrier(200, 500, 260, 385, 0, 0));
        course.get(4).getBarriers().add(buildRectangleBarrier(445 - 45, 745 - 44, 0, 0, 20, 20));
        course.get(4).getBarriers().add(buildRectangleBarrier(18, 600, -102, 50, 0, 0));
        course.get(4).getBarriers().add(buildRectangleBarrier(18, 600, -2, -50, 0, 0));
        course.get(4).getBarriers().add(buildRectangleBarrier(18, 600, 98, 50, 0, 0));
        course.get(5).getBarriers().add(buildRectangleBarrier(397 - 44, 547 - 44, 0, 0, 20, 20));
        course.get(5).getBarriers().add(buildRectangleBarrier(18, 200, -55, -151, 0, 0));
        course.get(5).getBarriers().add(buildRectangleBarrier(18, 200, -55, 151, 0, 0));
        course.get(5).getBarriers().add(buildRectangleBarrier(18, 200, 15, -151, 0, 0));
        course.get(5).getBarriers().add(buildRectangleBarrier(18, 200, 15, 151, 0, 0));
        course.get(5).getBarriers().add(buildRectangleBarrier(18, 200, 85, -151, 0, 0));
        course.get(5).getBarriers().add(buildRectangleBarrier(18, 200, 85, 151, 0, 0));
        course.get(6).getBarriers().add(buildRectangleBarrier(397 - 44, 547 - 44, 0, 0, 20, 20));
        course.get(6).getBarriers().add(buildRectangleBarrier(100, 100, 25, -202, 0, 0));
        course.get(6).getBarriers().add(buildRectangleBarrier(100, 370, 25, 66, 0, 0));
        course.get(7).getBarriers().add(buildRectangleBarrier(394 - 42, 545 - 42, 0, 0, 20, 20));
        course.get(7).getBarriers().add(buildRectangleBarrier(150, 420, 0, -42, 0, 0));
        course.get(8).getBarriers().add(buildRectangleBarrier(396 - 42, 545 - 42, 0, 0, 20, 20));
        course.get(8).getBarriers().add(buildRectangleBarrier(18, 100, 15, -202, 0, 0));
        course.get(8).getBarriers().add(buildRectangleBarrier(18, 200, 15, 20, 0, 0));
    }

    private Rectangle buildRectangleBarrier(double width, double height,
            double translateX, double translateY, double arcWidth, double arcHeight) {
        Rectangle rec = new Rectangle(width, height);
        rec.setFill(Color.TRANSPARENT);
        rec.setStroke(Color.WHITE);
        rec.setStrokeWidth(1);
        rec.setArcHeight(arcHeight);
        rec.setArcWidth(arcWidth);
        rec.setTranslateX(translateX);
        rec.setTranslateY(translateY);
        return rec;
    }

    public List<MiniHole> getCourse() {
        return course;
    }

}
