/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

/**
 *
 * @author gail
 */
public class MiniGolfFX extends Application {

    private MiniGolfFXView gameView;

    @Override
    public void start(Stage stage) {
        
        gameView = new MiniGolfFXView();
        var scene = new Scene(gameView, 
                Config.SCREEN_BOUNDS.getWidth(), 
                Config.SCREEN_BOUNDS.getHeight());
        Stop[] stops = new Stop[]{new Stop(0, Color.LIGHTBLUE), new Stop(1, Color.BLUE)};
        scene.setFill(
                new LinearGradient(0.0, 0.0, 1.0, 1.0, true, CycleMethod.NO_CYCLE, stops));
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Mini Golf");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
