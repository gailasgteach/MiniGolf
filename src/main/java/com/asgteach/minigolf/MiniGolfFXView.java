/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.minigolf;

import static com.asgteach.minigolf.Config.IS_TOUCH;
import static com.asgteach.minigolf.Config.LINE_DISTANCE;
import static com.asgteach.minigolf.Config.MAX_DISTANCE;
import static com.asgteach.minigolf.Config.MAX_HOLE_NUM;
import static com.asgteach.minigolf.Config.MAX_POWER;
import static com.asgteach.minigolf.Config.SCREEN_BOUNDS;
import static com.asgteach.minigolf.Config.ScaleFactor;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author gail
 */
public class MiniGolfFXView extends StackPane {

    private final PowerMeter powerMeter = new PowerMeter();
    private final Flag flag = new Flag();
    private final ScoreCard scoreCard = new ScoreCard();
    private final VBox vboxPowerMeter = new VBox();
    private final VBox vboxRight = new VBox();
    private final Club club = new Club();

    private final GolfBall golfball = new GolfBall();
    private final Rectangle startpad = new Rectangle();
    private final GenericHole generichole = new GenericHole();
    private final About aboutScreen = new About();
    private final Button aboutButton = new Button();

    private final List<Rectangle> holeBarriers = new ArrayList<>();
    private GameButton startScreen;
    private GameButton infoScreen;
    private static final int FADETIME = 1000;
    private final Rectangle wall = new Rectangle(10, 10);
    private final Rectangle frame = new Rectangle(10, 10);
    private final Rectangle clipLine = new Rectangle(10, 10);
    private Text debugtext = new Text();

    private IntegerProperty holecount = new SimpleIntegerProperty(0);
    private DoubleProperty gamex = new SimpleDoubleProperty(0.0);
    private DoubleProperty gamey = new SimpleDoubleProperty(0.0);
    private final Line line1 = new Line();
    private final Line line2 = new Line();
    private final DoubleProperty xLineStart = new SimpleDoubleProperty(0.0);
    private final DoubleProperty yLineStart = new SimpleDoubleProperty(0.0);
    private final DoubleProperty xLineFinish = new SimpleDoubleProperty(0.0);
    private final DoubleProperty yLineFinish = new SimpleDoubleProperty(0.0);
    private double ratex = 0.0;
    private double ratey = 0.0;

    private final int INTERVALS = 800;
    private double distance = 1.0;
    private double speed;

    public MiniGolfFXView() {

        line1.startXProperty().bind(xLineStart);
        line1.startYProperty().bind(yLineStart);
        line1.endXProperty().bind(golfball.translateXProperty());
        line1.endYProperty().bind(golfball.translateYProperty());
        line1.setVisible(false);
        line1.getStrokeDashArray().addAll(6.0, 6.0);
        line2.startXProperty().bind(golfball.translateXProperty());
        line2.startYProperty().bind(golfball.translateYProperty());
        line2.endXProperty().bind(xLineFinish);
        line2.endYProperty().bind(yLineFinish);
        line2.setVisible(false);
        line2.getStrokeDashArray().addAll(6.0, 6.0);
        club.translateXProperty().bind(xLineStart);
        club.translateYProperty().bind(yLineStart);
        club.setVisible(false);
        debugtext.setTranslateY(-200);

        startScreen = new GameButton(SCREEN_BOUNDS.getWidth(), 200,
                "Start Game", "Rules",
                "There are 9 Holes.\n"
                + "Drag finger over pad to place\nthe golfball and release.\n"
                + "Then, use touch to aim the shot lines.\n"
                + "When ready to shoot, release.\n"
                + "The Power Meter determines\nthe power of your shot.\n"
                + "Hint: careful with too much power!\nGet the lowest score possible!",
                e -> {
                    // fade the start screen
                    startScreen.getGameButton().setDisable(true);
                    FadeTransition ft = new FadeTransition(Duration.millis(FADETIME),
                            startScreen);
                    ft.setToValue(0);
                    ft.setOnFinished(f -> doStartGame());
                    ft.playFromStart();
                });

        infoScreen = new GameButton(200, 200,
                " Next Tee ", "Congratulations, Shot Made!",
                "",
                e -> {
                    // fade the start screen
                    infoScreen.getGameButton().setDisable(true);
                    FadeTransition ft = new FadeTransition(Duration.millis(FADETIME),
                            infoScreen);
                    ft.setToValue(0);
                    ft.setOnFinished(f -> {
                        getChildren().remove(infoScreen);
                        infoScreen.getGameButton().setDisable(false);
                    });
                    ft.playFromStart();
                    configureHole();
                });

        vboxRight.getChildren().add(scoreCard);
        powerMeter.setScaleX(.75);
        powerMeter.setScaleY(.75);
        vboxPowerMeter.getChildren().add(powerMeter);

        //startpad.setStroke(Color.YELLOW);
        startpad.setFill(Color.TRANSPARENT);
        startpad.setVisible(false);
        vboxRight.setVisible(false);
        vboxRight.setAlignment(Pos.TOP_RIGHT);
        vboxPowerMeter.setAlignment(Pos.BOTTOM_RIGHT);
        vboxPowerMeter.setVisible(false);
        debugtext.setFill(Color.WHITE);
        aboutScreen.setVisible(false);
        aboutButton.setGraphic(new FontIcon());
        aboutButton.setId("about-button");

        aboutButton.setOnAction(e -> aboutScreen.setVisible(true));
        aboutScreen.getCloseButton().setOnAction(
                e -> aboutScreen.setVisible(false));
        aboutButton.setTranslateX(-SCREEN_BOUNDS.getWidth() / 2 + 30);
        aboutButton.setTranslateY(-SCREEN_BOUNDS.getHeight() / 2 + 20);

        getChildren().addAll(generichole, wall, frame, vboxPowerMeter,
                startScreen, vboxRight, flag, club, line1, line2, golfball, aboutButton, aboutScreen);

        //getChildren().addAll(hole4);
        flag.setOpacity(0.0);
        line1.setManaged(false);
        line2.setManaged(false);
        wall.setManaged(false);
        startScreen.setTranslateY(100);
        club.setManaged(false);
        golfball.setManaged(false);
        golfball.setVisible(false);
        wall.setVisible(false);
        frame.visibleProperty().bind(wall.visibleProperty());
        golfball.translateXProperty().bind(gamex);
        golfball.translateYProperty().bind(gamey);
        //setAlignment(Pos.CENTER);
        scoreCard.holeNumberProperty().bind(holecount);
        System.out.println(SCREEN_BOUNDS.getWidth() + " x " + SCREEN_BOUNDS.getHeight());

        if (IS_TOUCH) {
            startpad.setOnTouchMoved(evt -> {
                debugtext.setText("Touch Moved: " + evt.getEventType().getName());
                // place golfball for first shot
                golfball.setVisible(true);
                // touch events let you go outside the node with the
                // touch event handler, so make sure the gamex, gamey placement is
                // inside the startpad bounds
                gamex.set(Math.max(evt.getTouchPoint().getSceneX(), startpad.getTranslateX() + (getLayoutBounds().getCenterX() - startpad.getWidth() / 2)));
                gamex.set(Math.min(gamex.get(), startpad.getTranslateX() + (getLayoutBounds().getCenterX() + startpad.getWidth() / 2)));
                gamey.set(Math.max(evt.getTouchPoint().getSceneY(), startpad.getTranslateY() + (getLayoutBounds().getCenterY() - startpad.getHeight() / 2)));
                gamey.set(Math.min(gamey.get(), startpad.getTranslateY() + (getLayoutBounds().getCenterY() + startpad.getHeight() / 2)));
            });
            startpad.setOnTouchReleased(evt -> {
                beginHole();
            });
            frame.setOnTouchMoved(evt -> {
                debugtext.setText("Touch Moved: " + evt.getEventType().getName());
                // aim the shotline
                aimShotLine(evt.getTouchPoint().getSceneX(),
                        evt.getTouchPoint().getSceneY());
            });
            frame.setOnTouchPressed(evt -> {
                debugtext.setText("Touch Pressed: " + evt.getEventType().getName());
                // initiate power meter animation from point
                beginPowerMeter(evt.getTouchPoint().getSceneX(),
                        evt.getTouchPoint().getSceneY());

            });
            frame.setOnTouchReleased(evt -> {
                // set power meter and shoot
                debugtext.setText("Touch release: " + evt.getEventType().getName());
                engageShot();
            });
        } else {
            startpad.setOnMouseMoved(evt -> {
                // place golfball for first shot
                debugtext.setText("Mouse Moved: " + evt.getEventType().getName());
                golfball.setVisible(true);
                gamex.set(evt.getSceneX());
                gamey.set(evt.getSceneY());
            });
            startpad.setOnMouseReleased(evt -> {
                // set the golfball for first shot
                System.out.println("Mouse Released " + holecount.get());
                beginHole();
            });
            frame.setOnMouseMoved(evt -> {
                // aim the shotline
                debugtext.setText("Mouse Moved: " + evt.getEventType().getName());
                aimShotLine(evt.getSceneX(),
                        evt.getSceneY());
            });
            frame.setOnMousePressed(evt -> {
                // initiate power meter animation from point
                debugtext.setText("Mouse Pressed: " + evt.getEventType().getName());
                beginPowerMeter(evt.getSceneX(),
                        evt.getSceneY());

            });
            frame.setOnMouseReleased(evt -> {
                // set power meter and shoot
                debugtext.setText("Mouse Released: " + evt.getEventType().getName());
                engageShot();
            });
        }
    }

    private void doStartGame() {
        startScreen.setVisible(false);
        startScreen.getGameButton().setDisable(false);
        configureHole();
    }

    private void configureHole() {
        aboutButton.setVisible(false);
        getChildren().remove(aboutButton);
        holeBarriers.forEach((rec) -> {
            getChildren().remove(rec);
        });

        holeBarriers.clear();
//        temp for testing individual holes
//        holecount.set(4);
        holecount.set(holecount.get() + 1);
        if (holecount.get() > MAX_HOLE_NUM) {
            scoreCard.scoreNumberProperty().set(0);
            scoreCard.runningParNumberProperty().set(0);
            holecount.set(1);
        }

        // configure the image, set the scaling factor,
        // and configure the startpad size and position
        generichole.configureImage("Hole" + holecount.get() + ".png");
        flag.setHoleNumber(holecount.get());
        scoreCard.parNumberProperty().set(generichole.getPar());
        scoreCard.shotsNumberProperty().set(0);

        for (Rectangle rec : generichole.getBarriers()) {
            Rectangle locrec = new Rectangle(rec.getWidth() * ScaleFactor, rec.getHeight() * ScaleFactor);
            locrec.setTranslateX(rec.getTranslateX() * ScaleFactor
                    + (getLayoutBounds().getCenterX() - locrec.getWidth() / 2));
            locrec.setTranslateY(rec.getTranslateY() * ScaleFactor
                    + (getLayoutBounds().getCenterY() - locrec.getHeight() / 2));
            locrec.setFill(Color.TRANSPARENT);
            //locrec.setStroke(Color.WHITE);
            //locrec.setStrokeWidth(2);
            locrec.setArcHeight(rec.getArcHeight());
            locrec.setArcWidth(rec.getArcWidth());
            getChildren().add(locrec);
            locrec.setManaged(false);
            holeBarriers.add(locrec);
        }

        startpad.setWidth(generichole.getStartpad().getWidth());
        startpad.setHeight(generichole.getStartpad().getHeight());
        startpad.setTranslateX(generichole.getStartpad().getTranslateX());
        startpad.setTranslateY(generichole.getStartpad().getTranslateY());
        startpad.setVisible(true);
        wall.setWidth(holeBarriers.get(0).getWidth());
        wall.setHeight(holeBarriers.get(0).getHeight());
        wall.setTranslateX(getLayoutBounds().getCenterX() - wall.getWidth() / 2);
        wall.setTranslateY(getLayoutBounds().getCenterY() - wall.getHeight() / 2);
        wall.setFill(Color.TRANSPARENT);
//        wall.setStroke(Color.WHITE);
//        wall.setStrokeWidth(2);
        wall.setArcHeight(holeBarriers.get(0).getArcHeight());
        wall.setArcWidth(holeBarriers.get(0).getArcWidth());
        wall.setVisible(false);
        frame.setWidth(generichole.getImageWidth() + 35);
        frame.setHeight(generichole.getImageHeight() + 35);
        frame.setFill(Color.TRANSPARENT);

        holeBarriers.get(0).setVisible(false);

        golfball.setVisible(false);
        golfball.setOpacity(1);
        vboxPowerMeter.setAlignment(generichole.getPowerMeterPosition());
        vboxPowerMeter.setVisible(false);
        vboxRight.setVisible(true);

        getChildren().remove(wall);
        getChildren().add(wall);

        getChildren().remove(frame);
        getChildren().add(frame);

        getChildren().remove(startpad);
        getChildren().add(startpad);

        gamex.set(
                generichole.getStartpad().getTranslateX()
                + getLayoutBounds().getCenterX());
        gamey.set(
                generichole.getStartpad().getTranslateY()
                + getLayoutBounds().getCenterY());
        flag.setTranslateX(generichole.getCup().getTranslateX());
        flag.setTranslateY(generichole.getCup().getTranslateY());
        flag.setOpacity(1.0);
    }

    private double fratey(double x, double y, double xc, double yc) {
        var temp = Math.sin(Math.atan(Math.abs(yc - y) / Math.abs(xc - x)));
        if ((yc - y) < 0) {
            temp = temp * (- 1);
        }
        return temp;
    }

    private double fratex(double x, double y, double xc, double yc) {
        var temp = Math.cos(Math.atan(Math.abs(yc - y) / Math.abs(xc - x)));
        if ((xc - x) < 0) {
            temp = temp * (- 1);
        }
        return temp;
    }

    private double fdistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    double xVector;
    double yVector;
    private int intervalCount = 0;
    private double ballRollRate = 1.0;
    // main timeline
    private final Timeline timeline = new Timeline();
    private final int FRAMES = 70;

    private void ballRoll() {
        System.out.println("-----");
        vboxRight.setVisible(false);
        intervalCount = 0;
        ballRollRate = 1.0;
        // ratex and ratey depend on the mouse click position
        xVector = (distance * ratex) / INTERVALS;
        yVector = (distance * ratey) / INTERVALS;
        timeline.stop();
        timeline.getKeyFrames().clear();
        KeyFrame keyframe = new KeyFrame(Duration.millis(700 / FRAMES),
                e -> update());
        timeline.getKeyFrames().add(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void update() {
        intervalCount++;
        if (shotMade()) {
            timeline.stop();
            Circle circle = generichole.getCup();
            doFinish(circle.getTranslateX()
                    + getLayoutBounds().getCenterX(),
                    circle.getTranslateY()
                    + getLayoutBounds().getCenterY());
        } else {
            // check for collisions
            barrierDetection();
            if (intervalCount >= (INTERVALS - 600)) {
                // slow down, you move too fast
                ballRollRate -= 0.002;
                if (ballRollRate < 0.0) {
                    ballRollRate = 0.0;
                }
            }
            speed = (Math.sqrt(yVector * yVector * ballRollRate
                    + xVector * xVector * ballRollRate));
            gamex.set(gamex.get() + xVector * ballRollRate);
            gamey.set(gamey.get() + yVector * ballRollRate);

            if (intervalCount >= INTERVALS || ballRollRate <= 0.0) {
                timeline.stop();
                wall.setVisible(true);
                vboxRight.setVisible(true);
            }
        }
    }

    private boolean shotMade() {
        // if ball is going too fast, it won't go in the hole!
        Circle circle = generichole.getCup();
        var c = fdistance(golfball.getTranslateX(),
                golfball.getTranslateY(),
                circle.getTranslateX() + getLayoutBounds().getCenterX(),
                circle.getTranslateY() + getLayoutBounds().getCenterY());
        return c <= circle.getRadius() && (speed <= (0.5 * MAX_DISTANCE / INTERVALS) + .35);
    }

    private boolean collisionX() {
        return golfball.getTranslateX() + golfball.getRadius() + xVector > (wall.getWidth() + wall.getTranslateX())
                || golfball.getTranslateX() - golfball.getRadius() + xVector < wall.getTranslateX();
    }

    private boolean collisionY() {
        return golfball.getTranslateY() - golfball.getRadius() + yVector < wall.getTranslateY()
                || golfball.getTranslateY() + golfball.getRadius() + yVector > (wall.getHeight() + wall.getTranslateY());
    }

    private void barrierDetection() {
        for (int i = 1; i < holeBarriers.size(); i++) {
            Rectangle rec = holeBarriers.get(i);
            // Note: all the barriers are disconnected from each other;
            // therefore, if collision with one barrier occurs, stop checking
            // the other barriers.
            boolean collisionFound = false;
            //If I keep moving in my current X direction, will I collide with the barrier?
            if (golfball.getTranslateX() + golfball.getRadius() + xVector > rec.getTranslateX()
                    && golfball.getTranslateX() + xVector - golfball.getRadius() < rec.getTranslateX() + rec.getWidth()
                    && golfball.getTranslateY() + golfball.getRadius() > rec.getTranslateY()
                    && golfball.getTranslateY() - golfball.getRadius() < rec.getTranslateY() + rec.getHeight()) {
                xVector *= -1;
                collisionFound = true;
                System.out.println("X change: golfball = (" + golfball.getTranslateX() + ","
                        + golfball.getTranslateY() + "), new xVector= " + xVector);
            }
            //If I keep moving in my current Y direction, will I collide with the barrier?
            if (golfball.getTranslateX() + golfball.getRadius() > rec.getTranslateX()
                    && golfball.getTranslateX() - golfball.getRadius() < rec.getTranslateX() + rec.getWidth()
                    && golfball.getTranslateY() + golfball.getRadius() + yVector > rec.getTranslateY()
                    && golfball.getTranslateY() - golfball.getRadius() + yVector < rec.getTranslateY() + rec.getHeight()) {
                yVector *= -1;
                collisionFound = true;
                System.out.println("Y change: golfball = (" + golfball.getTranslateX() + ","
                        + golfball.getTranslateY() + "), new yVector= " + yVector);
            }
            if (collisionFound) {
                return;
            }
        }
        // check outer walls
        if (collisionX()) {
            xVector *= -1;
        }
        if (collisionY()) {
            yVector *= -1;
        }
    }

    private void doFinish(double xEnd, double yEnd) {
        Timeline finishTimeLine = new Timeline();
        finishTimeLine.getKeyFrames().clear();

        finishTimeLine.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(gamex, xEnd),
                        new KeyValue(gamey, yEnd)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(golfball.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.7),
                        new KeyValue(golfball.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(0.0),
                        new KeyValue(flag.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(flag.opacityProperty(), 0.0))
        );

        finishTimeLine.setOnFinished(e -> {
            wall.setVisible(false);
            scoreCard.setCurrentPar(generichole.getPar());
            scoreCard.scoreNumberProperty().set(
                    scoreCard.scoreNumberProperty().get()
                    + scoreCard.shotsNumberProperty().get());
            vboxRight.setVisible(true);
            getChildren().remove(infoScreen);
            getChildren().add(infoScreen);
            if (holecount.get() >= MAX_HOLE_NUM) {
                infoScreen.getGameButton().setText("Play Another Round");
            } else {
                infoScreen.getGameButton().setText(" Next Tee ");
            }
            infoScreen.setVisible(true);
            infoScreen.setOpacity(1.0);
            getChildren().remove(aboutButton);
            getChildren().add(aboutButton);
            getChildren().remove(aboutScreen);
            getChildren().add(aboutScreen);
            aboutButton.setVisible(true);
        });
        finishTimeLine.play();
    }

    private void beginHole() {
        golfball.setVisible(true);
        startpad.setVisible(false);
        wall.setVisible(true);
    }

    private void engageShot() {
        // Make it so
        powerMeter.getPowerTimeline().pause();
        System.out.println("Power Height = "
                + (MAX_POWER - powerMeter.getPowerHeightProperty().get()));
        distance = (1 - (powerMeter.getPowerHeightProperty().get() / MAX_POWER)) * MAX_DISTANCE;
        vboxPowerMeter.setVisible(false);
        vboxRight.setVisible(true);
        line1.setVisible(false);
        line2.setVisible(false);
        scoreCard.shotsNumberProperty().set(
                scoreCard.shotsNumberProperty().get() + 1);
        wall.setVisible(false);
        club.setVisible(false);
        ballRoll();
    }

    void beginPowerMeter(double x, double y) {
        ratex = fratex(x, y,
                golfball.getTranslateX(), golfball.getTranslateY());
        ratey = fratey(x, y,
                golfball.getTranslateX(), golfball.getTranslateY());
        vboxPowerMeter.setVisible(true);
        powerMeter.getPowerTimeline().play();
    }

    void aimShotLine(double x, double y) {
        club.setVisible(true);
        clipLine.setTranslateX(wall.getTranslateX());
        clipLine.setTranslateY(wall.getTranslateY());
        clipLine.setWidth(wall.getWidth());
        clipLine.setHeight(wall.getHeight());
        line2.setClip(clipLine);
        line1.setVisible(true);
        line2.setVisible(true);
        ratex = fratex(x, y,
                golfball.getTranslateX(), golfball.getTranslateY());
        ratey = fratey(x, y,
                golfball.getTranslateX(), golfball.getTranslateY());
        xLineStart.set(x);
        yLineStart.set(y);
        xLineFinish.set(LINE_DISTANCE * ratex + golfball.getTranslateX());
        yLineFinish.set(LINE_DISTANCE * ratey + golfball.getTranslateY());
    }

}
