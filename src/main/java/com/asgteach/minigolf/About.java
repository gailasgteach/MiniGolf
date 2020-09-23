/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import static com.asgteach.minigolf.Config.MG_VERSION;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author gail
 */
public class About extends StackPane {

    private final VBox vbox = new VBox();
    private final HBox hbox = new HBox();
    private final Button closeButton = new Button();
    private final ImageView imageView = new ImageView();
    private final Text titleText = new Text("MiniGolf-TeeTime");
    private final Text descriptionText = new Text();
    private final Text versionText = new Text(MG_VERSION);

    public About() {
        closeButton.setGraphic(new FontIcon());
        closeButton.setId("close-button");
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setImage(new Image(getClass().getResource("minigolf-icon.png").toExternalForm()));
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);
        
        descriptionText.setTextAlignment(TextAlignment.CENTER);
        descriptionText.setWrappingWidth(200);
        descriptionText.setLineSpacing(3);
        descriptionText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        descriptionText.setText("Original MiniGolf game designed and coded by Kellen Anderson " +
                "in JavaFX Script. " +
                "Ported to modern JavaFX by " +
                "Gail Anderson. " +
                "One code to run them all made possible by " +
                "Gluon Substrate, JavaFX, and GraalVM.");
        
        titleText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        versionText.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        Rectangle fill = new Rectangle(300, 450);
        fill.setFill(Color.web("#ffffff", .92));
        fill.setArcHeight(30);
        fill.setArcWidth(30);
        
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.getChildren().addAll(imageView, titleText, descriptionText, versionText);
        getChildren().addAll(fill, vbox, closeButton);
        closeButton.setTranslateX(120);
        closeButton.setTranslateY(-175);
    }

    public Button getCloseButton() {
        return closeButton;
    }
    
    

}
