/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author gail
 */
public class GameButton extends VBox {

    private final Text displayTextTop = new Text();
    private final Text displayTextBottom = new Text();
    private final Button gameButton;
            

    public GameButton(double width, double height, String buttonText, 
            String topText, String bottomText, EventHandler<ActionEvent> handler) {
        setWidth(width-20);
        setHeight(height-50);
        getStyleClass().add("game");
        displayTextTop.setText(topText);
        displayTextBottom.setText(bottomText);
        
        this.gameButton = new Button(buttonText);
        this.gameButton.setOnAction(handler);
        this.gameButton.getStyleClass().add("game-button");
        init();
    }
    
    private void init() {   
        displayTextTop.setFont(Font.font(20));
        displayTextTop.setStroke(Color.WHITE);
        displayTextTop.setFill(Color.WHITE);
        displayTextBottom.setFont(Font.font(17));
        displayTextBottom.setStroke(Color.WHITE);
        displayTextBottom.setFill(Color.WHITE);
        displayTextBottom.setTextAlignment(TextAlignment.CENTER);
        displayTextBottom.setWrappingWidth(getWidth() - 30);        
        gameButton.setCursor(Cursor.HAND);
        
        getChildren().addAll(displayTextTop, displayTextBottom, gameButton);
        setAlignment(Pos.CENTER);
        setSpacing(15);
        
    }
    
    public Text getDisplayTextTop() {
        return displayTextTop;
    }

    public Text getDisplayTextBottom() {
        return displayTextBottom;
    }
    
    public Button getGameButton() {
        return gameButton;
    }

}
