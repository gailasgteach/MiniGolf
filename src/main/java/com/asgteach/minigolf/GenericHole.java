/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import static com.asgteach.minigolf.Config.ScaleFactor;
import static com.asgteach.minigolf.Config.SCREEN_BOUNDS;
import com.asgteach.minigolf.model.MiniGolfCourse;
import com.asgteach.minigolf.model.MiniHole;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author gail
 */
public class GenericHole extends StackPane {

    private final Rectangle startpad = new Rectangle(10, 10);
    private final Circle cup = new Circle(12);
//    private final Circle cup2 = new Circle(12);
    private String imageName = "startscreen.png";
    ImageView holeImageView = new ImageView();
    private final Text mytext = new Text();
    private int holenum = 1;

    /* hole configuration properties */
    private int par = 1;
    private double imagewidth = 0.0;
    private double imageheight = 0.0;
    private double startpadheight = 61.0;
    private double startpadwidth = 103.0;
    private double startpadx = 0.0;
    private double startpady = 301.0;

    /* cup config properties */
    private double cupy = -310;
    private double cupxstartrange = -35;
    private double cupxendrange = 35;
    
    /* power meter position */
    private Pos powerMeterPosition = Pos.TOP_LEFT;

    private final MiniGolfCourse minigolfcourse = new MiniGolfCourse();

    public GenericHole() {
        getStyleClass().add("generic-hole");

        getChildren().add(holeImageView);
        mytext.setTranslateY(-100);
        mytext.setFill(Color.WHITE);

        //startpad.setStroke(Color.BLACK);
        startpad.setFill(Color.TRANSPARENT);
        startpad.setVisible(false);

        holeImageView.setImage(new Image(getClass().getResource(this.imageName).toExternalForm()));
        holeImageView.setFitHeight(SCREEN_BOUNDS.getHeight() - 20);
        holeImageView.setFitWidth(SCREEN_BOUNDS.getWidth() - 20);
        holeImageView.setPreserveRatio(true);
        maxWidthProperty().bind(holeImageView.getImage().widthProperty());
        maxHeightProperty().bind(holeImageView.getImage().heightProperty());
        cup.setFill(new RadialGradient(
                0, 0, .5, .5, .5,
                true, CycleMethod.NO_CYCLE,
                new Stop[]{
                    new Stop(0, Color.GRAY),
                    new Stop(1, Color.BLACK)
                }));

//        Light.Distant light = new Light.Distant();
//        light.setAzimuth(-135.0);
//        light.setElevation(30);
//
//        Lighting lighting = new Lighting();
//        lighting.setLight(light);
//        lighting.setSurfaceScale(5.0);
//        
//        cup.setEffect(lighting);
    }

    public List<Rectangle> getBarriers() {
        return minigolfcourse.getCourse().get(holenum - 1).getBarriers();
    }

    public Rectangle getStartpad() {
        return startpad;
    }

    public Circle getCup() {
        return cup;
    }
    
    public int getPar() {
        return par;
    }

    public double getImageWidth() {
        return (imagewidth - 39) * ScaleFactor;
    }

    public double getImageHeight() {
        return (imageheight - 39) * ScaleFactor;
    }

    public ImageView getHoleImageView() {
        return holeImageView;
    }
    
    public Pos getPowerMeterPosition() {
        return powerMeterPosition;
    }

    // configure the image, set the scaling factor,
    // and configure the startpad size and position
    public final void configureImage(String imageName) {
        if (!getChildren().contains(cup)) {
//            getChildren().addAll(cup, mytext);
            getChildren().add(cup);
        }

        this.imageName = imageName;
        holeImageView.setImage(new Image(getClass().getResource(this.imageName).toExternalForm()));
        holenum = Integer.parseInt(String.valueOf(imageName.charAt(4)));
        holeImageView.setFitHeight(SCREEN_BOUNDS.getHeight());
        holeImageView.setFitWidth(SCREEN_BOUNDS.getWidth());
        holeImageView.setPreserveRatio(true);
        double ScalexFactor = 1;
        double ScaleyFactor = 1;

        setHoleData(holenum - 1);
        mytext.setText(holeImageView.getBoundsInLocal().getWidth() + " / " + imagewidth);
        if (holeImageView.getBoundsInLocal().getWidth() != imagewidth) {
            ScalexFactor = holeImageView.getBoundsInLocal().getWidth() / imagewidth;
        }
        if (holeImageView.getBoundsInLocal().getHeight() != imageheight) {
            ScaleyFactor = holeImageView.getBoundsInLocal().getHeight() / imageheight;
        }

        System.out.println("image Scalex = " + ScalexFactor);
        System.out.println("image Scaley = " + ScaleyFactor);

        ScaleFactor = Math.min(ScalexFactor, ScaleyFactor);
        System.out.println("Scale Factor = " + ScaleFactor);
        mytext.setText(mytext.getText() + "\n" + ScaleFactor);

        // configure the startpad with the properties file data
        startpad.setWidth(startpadwidth * ScaleFactor);
        startpad.setHeight(startpadheight * ScaleFactor);
        startpad.setTranslateY(startpady * ScaleFactor);
        startpad.setTranslateX(startpadx * ScaleFactor);
       
        cup.setTranslateY(cupy * ScaleFactor);
        cup.setTranslateX(cupxstartrange * ScaleFactor + (Math.random() * ((cupxendrange * ScaleFactor - cupxstartrange * ScaleFactor) + 1)));
//        cup.setTranslateX(cupxstartrange * ScaleFactor);
//        cup2.setTranslateY(cupy * ScaleFactor);
//        cup2.setTranslateX(cupxendrange * ScaleFactor);

    }

    private void setHoleData(int num) {
        // get the hole from the Mini Golf Course
        MiniHole mh = minigolfcourse.getCourse().get(num);
        par = mh.getPar();
        imagewidth = mh.getImagewidth();
        imageheight = mh.getImageheight();
        startpadheight = mh.getStartpadheight();
        startpadwidth = mh.getStartpadwidth();
        startpadx = mh.getStartpadx();
        startpady = mh.getStartpady();
        cupy = mh.getCupy();
        cupxstartrange = mh.getCupxstartrange();
        cupxendrange = mh.getCupxendrange();
        powerMeterPosition = mh.getPowerMeterPosition();
    }

}
