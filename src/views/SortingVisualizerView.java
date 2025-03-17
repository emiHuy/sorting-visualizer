package views;

import controllers.InputPane;
import javafx.scene.layout.Pane;

public class SortingVisualizerView extends Pane {
    private InputPane inputPane;
    private AlgorithmPane algorithmPane;

    public SortingVisualizerView() {
        inputPane = new InputPane();
        inputPane.relocate(109, 40);

        algorithmPane = new AlgorithmPane();
        algorithmPane.relocate(35, 120);
        getChildren().addAll(inputPane, algorithmPane);
    }

    public AlgorithmPane getAlgorithmPane() {
        return algorithmPane;
    }

    public InputPane getInputPane() {
        return inputPane;
    }
}
