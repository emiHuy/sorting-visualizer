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
    private static final int DEFAULT_ELEMENT_LIST_SIZE = 50;
    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 700;
    SortingVisualizerView view;
    Element[] elements;
    Sort sort;

    public SortingVisualizerApp() {
        view = new SortingVisualizerView();
        sort = new Sort();
        elements = Element.generateNElements(DEFAULT_ELEMENT_LIST_SIZE);
        setupSortSignalListeners();
    }

    public void start(Stage primaryStage) {
        // Set up the main pane
        Pane mainPane = new Pane();
        mainPane.setStyle("-fx-background-color: rgb(240, 240, 240);");
        mainPane.getChildren().add(view);

        // Display elements onto view
        view.displayElements(elements);

        // Add event handling to top input pane components
        view.getInputPaneTop().getGenerateListButton().setOnAction(this::handleGenerateOrUpdate);
        view.getInputPaneTop().getSizeChoiceBox().setOnAction(this::handleGenerateOrUpdate);
        view.getInputPaneTop().getSortChoiceBox().setOnAction(this::handleSortChoiceBox);
        view.getInputPaneTop().getSortButton().setOnAction(this::handleSortButton);
        // Add event handling to bottom input pane components
        view.getInputPaneBottom().getCancelButton().setOnAction(this::handleStopButton);
        view.getInputPaneBottom().getPauseButton().setOnAction(this::handlePauseButton);
        view.getInputPaneBottom().getPlayButton().setOnAction(this::handlePlayButton);

        // Set up the window/stage
        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();
    }

    /**
     * Sets up listeners that take signals from the Sort class whenever the declared properties are updated
     */
    private void setupSortSignalListeners() {
        sort.isTimelineCompleteProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) resetViewAfterSort();
        });
        sort.isNewKeyFrameCreatedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) updateViewWithKeyFrame();
        });
    }

    /**
     * Resets the view after sorting.
     */
    private void resetViewAfterSort() {
        view.reset();
        sort.setTimelineComplete(false);
    }

    /**
     * Updates the view to show a new key frame.
     */
    private void updateViewWithKeyFrame() {
        view.displayElements(sort.getKeyFrameArr());
        sort.setNewKeyFrameCreated(false);
    }

    /**
     * Handle the generation of new array of elements. Call methods to display the elements on the view.
     * @param actionEvent event triggered by selecting a new list size or by clicking the generate new list button.
     */
    private void handleGenerateOrUpdate(ActionEvent actionEvent) {
        elements = Element.generateNElements(view.getInputPaneTop().getSizeChoiceBox().getValue());
        view.displayElements(elements);
    }

    /**
     * Handle the sort button and call methods to begin sorting.
     * @param actionEvent event triggered by clicking the sort button.
     */
    private void handleSortButton(ActionEvent actionEvent) {
        view.updateInputComponentsForSort();
        sort.setDelay(view.getSortSpeed());
        sort.startSort(view.getSortType(), Arrays.copyOf(elements, elements.length));
    }

    /**
     * Resets to the unsorted array of the current array of elements when a new sort algorithm is selected.
     * @param event event triggered by selecting a sorting algorithm in the choice box.
     */
    private void handleSortChoiceBox(Event event) {
        view.displayElements(elements);
    }

    /**
     * Handles the pause button by calling methods to pause the timeline.
     * @param actionEvent event triggered by clicking the pause button.
     */
    private void handlePauseButton(ActionEvent actionEvent) {
        sort.pause();
        view.getInputPaneBottom().getPlayButton().setDisable(false);
        view.getInputPaneBottom().getPauseButton().setDisable(true);
    }

    /**
     * Handles the play button by calling methods to resume the timeline.
     * @param actionEvent event triggered by clicking the play button.
     */
    private void handlePlayButton(ActionEvent actionEvent) {
        sort.play();
        view.getInputPaneBottom().getPlayButton().setDisable(true);
        view.getInputPaneBottom().getPauseButton().setDisable(false);
    }

    /**
     * Handles the stop button by calling methods to cancel the timeline and to reset the sorting process.
     * @param actionEvent event triggered by clicking the stop button.
     */
    private void handleStopButton(ActionEvent actionEvent) {
        sort.cancel();
        view.displayElements(elements);
    }

    // Main entry point
    public static void main(String[] args) {
        launch(args);
    }
}