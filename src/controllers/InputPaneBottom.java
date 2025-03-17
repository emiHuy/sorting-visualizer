package controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class InputPaneBottom extends Pane {
    Slider speedSlider;
    Button stopButton;
    Button pauseButton;
    Button playButton;

    public InputPaneBottom() {
        Label fastLabel = new Label("Fast");
        fastLabel.relocate(0,0);
        fastLabel.setPrefSize(35,28);
        fastLabel.setStyle("-fx-font: 15 arial");

        speedSlider = new Slider(0.01,0.1,0.01);
        speedSlider.relocate(40,0);
        speedSlider.setPrefSize(100,28);
        speedSlider.setValue(0.08);

        Label slowLabel = new Label("Slow");
        slowLabel.relocate(145,0);
        slowLabel.setPrefSize(35,28);
        slowLabel.setStyle("-fx-font: 15 arial");

        stopButton = new Button("Cancel");
        stopButton.relocate(730,0);
        stopButton.setPrefSize(70,0);
        stopButton.setStyle("-fx-font: 15 arial");

        pauseButton = new Button("Pause");
        pauseButton.relocate(807,0);
        pauseButton.setPrefSize(70,0);
        pauseButton.setStyle("-fx-font: 15 arial");

        playButton = new Button("Play");
        playButton.relocate(884,0);
        playButton.setPrefSize(70,0);
        playButton.setStyle("-fx-font: 15 arial");

        buttonVisibility(false);
        getChildren().addAll(fastLabel, slowLabel,speedSlider, stopButton, pauseButton, playButton);
    }

    public Slider getSpeedSlider() {
        return speedSlider;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public Button getPauseButton() {
        return pauseButton;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public void buttonVisibility(Boolean v) {
        playButton.setVisible(v);
        pauseButton.setVisible(v);
        stopButton.setVisible(v);
    }
}
