package views;

import controllers.InputPaneTop;
import javafx.scene.layout.Pane;

public class SortingVisualizerView extends Pane {
    private InputPaneTop inputPaneTop;
    private AlgorithmPane algorithmPane;

    public SortingVisualizerView() {
        inputPaneTop = new InputPaneTop();
        inputPaneTop.relocate(109, 40);

        algorithmPane = new AlgorithmPane();
        algorithmPane.relocate(35, 120);
        getChildren().addAll(inputPaneTop, algorithmPane);
    }

    public AlgorithmPane getAlgorithmPane() {
        return algorithmPane;
    }

    public InputPaneTop getInputPane() {
        return inputPaneTop;
    }
}
