package components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class InputPaneBottom extends Pane {
    private Slider speedSlider;
    private Button cancelButton;
    private Button pauseButton;
    private Button playButton;

    public InputPaneBottom() {
        Label fastLabel = new Label("Fast");
        fastLabel.relocate(0,0);
        fastLabel.setPrefSize(35,28);
        fastLabel.setStyle("-fx-font: 15 arial");

        speedSlider = new Slider(0.001,0.3,0.01);
        speedSlider.relocate(40,0);
        speedSlider.setPrefSize(150,28);
        speedSlider.setValue(0.08);

        Label slowLabel = new Label("Slow");
        slowLabel.relocate(195,0);
        slowLabel.setPrefSize(35,28);
        slowLabel.setStyle("-fx-font: 15 arial");

        cancelButton = new Button("Cancel");
        cancelButton.relocate(730,0);
        cancelButton.setPrefSize(70,0);
        cancelButton.setStyle("-fx-font: 15 arial");

        pauseButton = new Button("Pause");
        pauseButton.relocate(807,0);
        pauseButton.setPrefSize(70,0);
        pauseButton.setStyle("-fx-font: 15 arial");

        playButton = new Button("Play");
        playButton.relocate(884,0);
        playButton.setPrefSize(70,0);
        playButton.setStyle("-fx-font: 15 arial");

        buttonVisibility(false);
        getChildren().addAll(fastLabel, slowLabel,speedSlider, cancelButton, pauseButton, playButton);
    }

    public Slider getSpeedSlider() {
        return speedSlider;
    }

    public Button getCancelButton() {
        return cancelButton;
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
        cancelButton.setVisible(v);
    }
}
