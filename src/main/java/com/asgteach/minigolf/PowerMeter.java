/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import static com.asgteach.minigolf.Config.MAX_POWER;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author gail
 */
public class PowerMeter extends StackPane {

    private final Text topTitle = new Text("POWER");
    private final Text bottomTitle = new Text("METER");
    private final int REC_WIDTH = 110;
    private final int REC_HEIGHT = 200;
    private final int PATH_WIDTH = 70;
    private final Rectangle background
            = new Rectangle(REC_WIDTH, REC_HEIGHT);
    private final Rectangle powerWash = new Rectangle();
    private final Path meterPath = new Path();
    private final StackPane powerBarPane = new StackPane();

    private final DoubleProperty powerHeight = new SimpleDoubleProperty(MAX_POWER);

    private final Timeline powerTimeline = new Timeline();

    public PowerMeter() {
        getStyleClass().add("power-meter"); 
        
        
//        Light.Distant light = new Light.Distant();
//        light.setAzimuth(-135.0);
//        light.setElevation(30);
//
//        Lighting lighting = new Lighting();
//        lighting.setLight(light);
//        lighting.setSurfaceScale(5.0);

        // configure text blocks
        topTitle.setFill(Color.DARKORANGE);
        topTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        //topTitle.setEffect(lighting);
        bottomTitle.setFill(Color.DARKORANGE);
        bottomTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        //bottomTitle.setEffect(lighting);

        // do background
        Stop[] stops = new Stop[]{new Stop(0, Color.GREY), new Stop(1, Color.LIGHTGREY)};
        background.setFill(
                new LinearGradient(0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, stops));
        background.setArcHeight(20);
        background.setArcWidth(20);
        background.setStroke(Color.GREY);

//        background.setEffect(lighting);

        // do meter
        Stop[] barstops = new Stop[]{new Stop(0, Color.YELLOW), new Stop(.5, Color.RED), new Stop(1.0, Color.BLUE)};
        meterPath.setFill(
                new LinearGradient(
                        0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, barstops));

        MoveTo moveTo = new MoveTo(0.0f, 0.0f);

        HLineTo hLineTo = new HLineTo();
        hLineTo.setX(PATH_WIDTH);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(45.0f);
        arcTo.setY(140.0f);
        arcTo.setRadiusX(35.0f);
        arcTo.setRadiusY(200.0f);

        HLineTo hLineTo2 = new HLineTo();
        hLineTo2.setX(25.0f);

        ArcTo arcTo2 = new ArcTo();
        arcTo2.setX(0.0f);
        arcTo2.setY(0.0f);
        arcTo2.setRadiusX(35.0f);
        arcTo2.setRadiusY(200.0f);

        meterPath.getElements().addAll(moveTo, hLineTo, arcTo, hLineTo2, arcTo2);
        powerWash.setWidth(PATH_WIDTH);
        powerWash.heightProperty().bind(powerHeight);
        powerWash.setFill(Color.WHITE);

        Path clipPath = new Path(meterPath.getElements());
        clipPath.setOpacity(0.5);
        clipPath.setFill(Color.WHITE);
        powerWash.setClip(clipPath);
        
        HBox holder = new HBox();
        holder.setAlignment(Pos.BASELINE_CENTER);        
        holder.getChildren().add(powerWash);
        holder.setTranslateY(30);       
        

        powerBarPane.getChildren().addAll(meterPath, holder);
        getChildren().addAll(background, topTitle, bottomTitle, powerBarPane);
        topTitle.setTranslateY(-85);
        bottomTitle.setTranslateY(85);

        //setAlignment(Pos.TOP_CENTER);

        powerTimeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.seconds(0.0),
                                new KeyValue(powerHeight, 140.0)),
                        new KeyFrame(Duration.seconds(1.5),
                                new KeyValue(powerHeight, 0.0))
                );
        powerTimeline.setCycleCount(Timeline.INDEFINITE);
        powerTimeline.setAutoReverse(true);
        setMaxWidth(getBoundsInLocal().getWidth());
        setMaxHeight(getBoundsInLocal().getHeight());        
    }

    public Timeline getPowerTimeline() {
        return powerTimeline;
    }
    
    public DoubleProperty getPowerHeightProperty() {
        return powerHeight;
    }
    

}
