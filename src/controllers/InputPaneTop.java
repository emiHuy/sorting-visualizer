package controllers;

import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InputPaneTop extends Pane {
    ChoiceBox sortChoiceBox;
    Button sortButton;
    Button generateListButton;
    ChoiceBox sizeChoiceBox;

    public InputPaneTop(){
        Label sortLabel = new Label("Sorting Algorithm: ");
        sortLabel.relocate(410, 0);
        sortLabel.setPrefSize(140, 35);
        sortLabel.setStyle("-fx-font: 17 arial");

        String[] sortOptions = {"Selection Sort", "Bubble Sort", "Insertion Sort"};
        sortChoiceBox = new ChoiceBox(FXCollections.observableArrayList(sortOptions));
        sortChoiceBox.relocate(555,0);
        sortChoiceBox.setPrefSize(170,35);
        sortChoiceBox.setStyle("-fx-font: 17 arial");
        sortChoiceBox.setValue("Selection Sort");

        sortButton = new Button("Sort");
        sortButton.relocate(736, 0);
        sortButton.setPrefSize(60, 35);
        sortButton.setStyle("-fx-font: 17 arial");

        generateListButton = new Button("Generate new list");
        generateListButton.relocate(0, 0);
        generateListButton.setPrefSize(160, 35);
        generateListButton.setStyle("-fx-font: 17 arial");

        Label sizeLabel = new Label("Size of list: ");
        sizeLabel.relocate(180, 0);
        sizeLabel.setPrefSize(100, 35);
        sizeLabel.setStyle("-fx-font: 17 arial");

        String[] sizeOptions = {"50", "100", "150", "200"};
        sizeChoiceBox = new ChoiceBox(FXCollections.observableArrayList(sizeOptions));
        sizeChoiceBox.relocate(270, 0);
        sizeChoiceBox.setPrefSize(70, 35);
        sizeChoiceBox.setStyle("-fx-font: 17 arial");
        sizeChoiceBox.setValue("50");

        getChildren().addAll(sortChoiceBox, sortButton, generateListButton, sizeChoiceBox, sizeLabel, sortLabel);
    }

    public ChoiceBox getSortChoiceBox() {
        return sortChoiceBox;
    }

    public Button getSortButton() {
        return sortButton;
    }

    public Button getGenerateListButton() {
        return generateListButton;
    }

    public ChoiceBox getSizeChoiceBox() {
        return sizeChoiceBox;
    }

    public void disable() {
        sortChoiceBox.setDisable(true);
        sizeChoiceBox.setDisable(true);
        sortButton.setDisable(true);
        sizeChoiceBox.setDisable(true);
        generateListButton.setDisable(true);
    }

    public void enable(){
        sortChoiceBox.setDisable(false);
        sizeChoiceBox.setDisable(false);
        sortButton.setDisable(false);
        sizeChoiceBox.setDisable(false);
        generateListButton.setDisable(false);
    }

}
