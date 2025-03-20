package components;

import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InputPaneTop extends Pane {
    private final Button generateListButton;
    private final ChoiceBox<Integer> sizeChoiceBox;
    private final ChoiceBox<String> sortChoiceBox;
    private final Button sortButton;

    public InputPaneTop(){
        // Create the generateListButton that will generate a new collection to sort
        generateListButton = new Button("Generate new list");
        generateListButton.relocate(0, 0);
        generateListButton.setPrefSize(160, 35);
        generateListButton.setStyle("-fx-font: 17 arial");

        // Create the "Size of list" label for the sizeChoiceBox
        Label sizeLabel = new Label("Size of list: ");
        sizeLabel.relocate(180, 0);
        sizeLabel.setPrefSize(100, 35);
        sizeLabel.setStyle("-fx-font: 17 arial");

        Integer[] sizeOptions = {50, 100, 150, 200};
        sizeChoiceBox = new ChoiceBox<Integer>(FXCollections.observableArrayList(sizeOptions));
        sizeChoiceBox.relocate(270, 0);
        sizeChoiceBox.setPrefSize(70, 35);
        sizeChoiceBox.setStyle("-fx-font: 17 arial");
        sizeChoiceBox.setValue(50);

        // Create the "Sorting Algorithm" label for sortChoiceBox
        Label sortLabel = new Label("Sorting Algorithm: ");
        sortLabel.relocate(410, 0);
        sortLabel.setPrefSize(140, 35);
        sortLabel.setStyle("-fx-font: 17 arial");

        // Create the sortChoiceBox that will select the sorting algorithm
        String[] sortOptions = {"Selection Sort", "Bubble Sort", "Insertion Sort"};
        sortChoiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(sortOptions));
        sortChoiceBox.relocate(555,0);
        sortChoiceBox.setPrefSize(170,35);
        sortChoiceBox.setStyle("-fx-font: 17 arial");
        sortChoiceBox.setValue("Selection Sort");

        // Create the sort button that will begin sorting
        sortButton = new Button("Sort");
        sortButton.relocate(736, 0);
        sortButton.setPrefSize(60, 35);
        sortButton.setStyle("-fx-font: 17 arial");

        getChildren().addAll(generateListButton, sizeLabel, sizeChoiceBox, sortLabel, sortChoiceBox, sortButton);
    }

    public ChoiceBox<String> getSortChoiceBox() {
        return sortChoiceBox;
    }

    public Button getSortButton() {
        return sortButton;
    }

    public Button getGenerateListButton() {
        return generateListButton;
    }

    public ChoiceBox<Integer> getSizeChoiceBox() {
        return sizeChoiceBox;
    }

    /**
     * Sets the components' disabled value.
     * If disable is true, then the components will be disabled. Otherwaise, the components will be enabled.
     * @param disable Boolean value used to set the components' disable properties.
     */
    public void setComponentsDisabled(boolean disable) {
        sortChoiceBox.setDisable(disable);
        sizeChoiceBox.setDisable(disable);
        sortButton.setDisable(disable);
        sizeChoiceBox.setDisable(disable);
        generateListButton.setDisable(disable);
    }
}
