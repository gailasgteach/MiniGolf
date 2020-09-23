/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 *
 * @author gail
 */
public class Config {

    public static final double WIDTH = 414;
    public static final double HEIGHT = 716;
    public static final double MAX_POWER = 140;
    public static final double GOLFBALL_RADIUS = 8;
    public static final boolean IS_TOUCH = Platform.isSupported(ConditionalFeature.INPUT_TOUCH);
    
    public static final Rectangle2D SCREEN_BOUNDS = IS_TOUCH ?
            Screen.getPrimary().getVisualBounds() : new Rectangle2D(0, 0, WIDTH, HEIGHT) ;

    public static double ScaleFactor = 1;
    public static final double LINE_DISTANCE = SCREEN_BOUNDS.getHeight()-175;
    public static final double MAX_DISTANCE = 3000.0;
    
    public static final int MAX_HOLE_NUM = 9;
    public static final String MG_VERSION = "MiniGolf 1.03";
    

}
