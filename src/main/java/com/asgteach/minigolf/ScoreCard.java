/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author gail
 */
public class ScoreCard extends GridPane {

    private final Text holeText = new Text("HOLE");
    private final Text parText = new Text("PAR");
    private final Text shotsText = new Text("SHOTS");
    private final Text scoreText = new Text("TOTAL");
    private final Text overUnderText = new Text("O/U PAR");

    private final Text holeValue = new Text();
    private final Text parValue = new Text();
    private final Text shotsValue = new Text();
    private final Text scoreValue = new Text();
    private final Text overUnderValue = new Text();

    private final IntegerProperty holeNumber = new SimpleIntegerProperty(0);
    private final IntegerProperty parNumber = new SimpleIntegerProperty(0);
    private final IntegerProperty shotsNumber = new SimpleIntegerProperty(0);
    private final IntegerProperty scoreNumber = new SimpleIntegerProperty(0);
    private final IntegerProperty overUnderNumber = new SimpleIntegerProperty(0);
    private final IntegerProperty runningParNumber = new SimpleIntegerProperty(0);
    
    private int currentPar = 0;

    public ScoreCard() {
        getStyleClass().add("score-card");

        holeValue.textProperty().bind(holeNumber.asString());
        parValue.textProperty().bind(parNumber.asString());
        shotsValue.textProperty().bind(shotsNumber.asString());
        scoreValue.textProperty().bind(scoreNumber.asString());
        overUnderValue.textProperty().bind(overUnderNumber.asString()); 
        overUnderNumber.bind(Bindings.subtract(scoreNumber, runningParNumber));
        overUnderValue.fillProperty().bind(new When(overUnderNumber.greaterThan(0)).then(Color.RED).otherwise(Color.WHITESMOKE));
        overUnderValue.strokeProperty().bind(overUnderValue.fillProperty());

        add(holeText, 0, 0); // column=0 row=0
        add(parText, 0, 1);
        add(shotsText, 0, 2);
        add(scoreText, 0, 3);
        add(overUnderText, 0, 4);

        add(holeValue, 1, 0); // column=1 row=0
        add(parValue, 1, 1);
        add(shotsValue, 1, 2);
        add(scoreValue, 1, 3);
        add(overUnderValue, 1, 4);
        getChildren().forEach((n) -> {
            n.getStyleClass().add("score-cardtext");
        });

        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column1 = new ColumnConstraints();
        column2.setHalignment(HPos.RIGHT);
        setHgap(20);
        column1.setHalignment(HPos.LEFT);
        //column2.setPercentWidth(50);
        getColumnConstraints().addAll(column1, column2);
        this.setMaxWidth(100);

    }

    public IntegerProperty holeNumberProperty() {
        return holeNumber;
    }

    public IntegerProperty parNumberProperty() {
        return parNumber;
    }

    public IntegerProperty shotsNumberProperty() {
        return shotsNumber;
    }
    
    public IntegerProperty scoreNumberProperty() {
        return scoreNumber;
    }
    
    public IntegerProperty overUnderNumberProperty() {
        return overUnderNumber;
    }
    
    public int getCurrentPar() {
        return currentPar;
    }
    
    public void setCurrentPar(int num) {
        currentPar = num;
        runningParNumber.set(runningParNumber.get() + currentPar);
    }
    
    public IntegerProperty runningParNumberProperty() {
        return runningParNumber;
    }

}
