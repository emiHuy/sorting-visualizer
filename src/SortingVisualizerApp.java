import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Element;
import models.Sort;
import views.SortingVisualizerView;

import java.util.Arrays;

public class SortingVisualizerApp extends Application {
    SortingVisualizerView view;
    Element[] elements;
    Sort sort;

    public SortingVisualizerApp() {
        view = new SortingVisualizerView();
        sort = new Sort();
        elements = Element.generateNElements(50);
        initializeSignals();
    }

    public void start(Stage primaryStage) {
        Pane mainPane = new Pane();
        mainPane.setStyle("-fx-background-color: rgb(240, 240, 240);");
        mainPane.getChildren().add(view);
        view.updateVisualizer(elements);

        view.getInputPaneTop().getGenerateListButton().setOnAction(this::handleGenerateListButton);
        view.getInputPaneTop().getSizeChoiceBox().setOnAction(this::handleSizeChoiceBox);
        view.getInputPaneTop().getSortChoiceBox().setOnAction(this::handleSortChoiceBox);
        view.getInputPaneTop().getSortButton().setOnAction(this::handleSortButton);

        view.getInputPaneBottom().getCancelButton().setOnAction(this::handleStopButton);
        view.getInputPaneBottom().getPauseButton().setOnAction(this::handlePauseButton);
        view.getInputPaneBottom().getPlayButton().setOnAction(this::handlePlayButton);

        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainPane, 1024, 700));
        primaryStage.show();
    }

    public void handleGenerateListButton(ActionEvent actionEvent) {
        elements = Element.generateNElements(Integer.parseInt(String.valueOf(view.getInputPaneTop().getSizeChoiceBox().getValue())));
        view.updateVisualizer(elements);
    }

    public void handleSizeChoiceBox(Event actionEvent) {
        elements = Element.generateNElements(Integer.parseInt(String.valueOf(view.getInputPaneTop().getSizeChoiceBox().getValue())));
        view.updateVisualizer(elements);
    }

    public void handleSortButton(ActionEvent actionEvent) {
        String sortType = ""+view.getInputPaneTop().getSortChoiceBox().getValue();
        view.updateInputComponentsForSort();
        sort.setDelay(view.getInputPaneBottom().getSpeedSlider().getValue());

        switch (sortType) {
            case "Selection Sort":
                sort.selectionSort(Arrays.copyOf(elements, elements.length));
                break;
            case "Bubble Sort":
                sort.bubbleSort(Arrays.copyOf(elements, elements.length));
                break;
            case "Insertion Sort":
                sort.insertionSort(Arrays.copyOf(elements, elements.length));
        }
    }

    public void handleSortChoiceBox(Event event) {
        view.updateVisualizer(elements);
    }

    public void handlePauseButton(ActionEvent actionEvent) {
        sort.pause();
        view.getInputPaneBottom().getPlayButton().setDisable(false);
        view.getInputPaneBottom().getPauseButton().setDisable(true);
    }

    public void handlePlayButton(ActionEvent actionEvent) {
        sort.play();
        view.getInputPaneBottom().getPlayButton().setDisable(true);
        view.getInputPaneBottom().getPauseButton().setDisable(false);
    }

    public void handleStopButton(ActionEvent actionEvent) {
        sort.cancel();
        view.updateVisualizer(elements);
    }

    public void initializeSignals() {
        sort.isTimelineCompleteProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                view.reset();
                sort.setTimelineComplete(false);
            }
        });

        sort.isNewKeyFrameCreatedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                view.updateVisualizer(sort.getKeyFrameArr());
                sort.setNewKeyFrameCreated(false);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}