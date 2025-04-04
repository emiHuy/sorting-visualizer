package components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class InputPaneBottom extends Pane {
    private final Slider speedSlider;
    private final Button cancelButton;
    private final Button pauseButton;
    private final Button playButton;

    public InputPaneBottom() {
        // Create the "Fast" label for the speed slider
        Label fastLabel = new Label("Fast");
        fastLabel.relocate(0,0);
        fastLabel.setPrefSize(35,28);
        fastLabel.setStyle("-fx-font: 15 arial");

        // Create the speed slider to control the visualizer's speed/delay (lower = faster)
        speedSlider = new Slider(0.001,0.3,0.01);
        speedSlider.relocate(40,0);
        speedSlider.setPrefSize(150,28);
        speedSlider.setValue(0.08);

        // Create the "Slow" label for the speed slider
        Label slowLabel = new Label("Slow");
        slowLabel.relocate(195,0);
        slowLabel.setPrefSize(35,28);
        slowLabel.setStyle("-fx-font: 15 arial");

        // Create the cancel button that will stop the visualizer
        cancelButton = new Button("Cancel");
        cancelButton.relocate(730,0);
        cancelButton.setPrefSize(70,0);
        cancelButton.setStyle("-fx-font: 15 arial");

        // Create the pause button that will pause the visualizer
        pauseButton = new Button("Pause");
        pauseButton.relocate(807,0);
        pauseButton.setPrefSize(70,0);
        pauseButton.setStyle("-fx-font: 15 arial");

        // Create the play button that will resume the visualizer
        playButton = new Button("Play");
        playButton.relocate(884,0);
        playButton.setPrefSize(70,0);
        playButton.setStyle("-fx-font: 15 arial");

        // Initially hide all buttons
        buttonVisibility(false);

        // Add components to pane
        getChildren().addAll(fastLabel,speedSlider, slowLabel, cancelButton, pauseButton, playButton);
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

    /**
     * Updates button visibility based on the Boolean value given.
     * If value is true, all buttons will be visible. If false, all buttons will be hidden.
     * @param value Boolean that controls button visibility.
     */
    public void buttonVisibility(Boolean value) {
        playButton.setVisible(value);
        pauseButton.setVisible(value);
        cancelButton.setVisible(value);
    }
}
