package views;

import components.AlgorithmPane;
import components.InputPaneBottom;
import components.InputPaneTop;
import javafx.scene.layout.Pane;
import models.Element;

public class SortingVisualizerView extends Pane {
    private final InputPaneTop inputPaneTop;
    private final AlgorithmPane algorithmPane;
    private final InputPaneBottom inputPaneBottom;

    public SortingVisualizerView() {
        // Create the top input pane
        inputPaneTop = new InputPaneTop();
        inputPaneTop.relocate(109, 40);

        // Create the algorithm pane - this pane visually demonstrates the sorting processes of different sorting algorithms
        algorithmPane = new AlgorithmPane();
        algorithmPane.relocate(35, 100);

        // Create the bottom input pane
        inputPaneBottom = new InputPaneBottom();
        inputPaneBottom.relocate(35, 650);
        getChildren().addAll(inputPaneTop, algorithmPane, inputPaneBottom);
    }

    public InputPaneTop getInputPaneTop() {
        return inputPaneTop;
    }

    public InputPaneBottom getInputPaneBottom() {
        return inputPaneBottom;
    }

    public String getSortType() {
        return inputPaneTop.getSortChoiceBox().getValue();
    }

    public double getSortSpeed() {
        return inputPaneBottom.getSpeedSlider().getValue();
    }

    /**
     * Resets state of input components after sorting.
     */
    public void reset() {
        inputPaneTop.setComponentsDisabled(false);
        inputPaneBottom.getSpeedSlider().setDisable(false);
        inputPaneBottom.buttonVisibility(false);
    }

    /**
     * Displays the given array of elements; updates the view.
     * @param elements array of elements to be displayed.
     */
    public void displayElements(Element[] elements) {
        algorithmPane.update(elements);
    }

    /**
     * Update component states for sorting.
     */
    public void updateInputComponentsForSort() {
        inputPaneTop.setComponentsDisabled(true);
        inputPaneBottom.buttonVisibility(true);
        inputPaneBottom.getPlayButton().setDisable(true);
        inputPaneBottom.getSpeedSlider().setDisable(true);
    }
}
