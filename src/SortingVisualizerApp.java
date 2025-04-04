import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.ElementArray;
import views.SortingVisualizerView;

public class SortingVisualizerApp extends Application {
    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 700;
    SortingVisualizerView view;
    ElementArray model;

    public SortingVisualizerApp() {
        model = new ElementArray();
        view = new SortingVisualizerView(model);
        setupSortSignalListeners();
    }

    public void start(Stage primaryStage) {
        // Set up the main pane
        Pane mainPane = new Pane();
        mainPane.setStyle("-fx-background-color: rgb(240, 240, 240);");
        mainPane.getChildren().add(view);

        // Display elements onto view
        view.displayUnsortedElements();

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
        model.getSorter().isTimelineCompleteProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) resetViewAfterSort();
        });
        model.getSorter().isNewKeyFrameCreatedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) updateViewWithKeyFrame();
        });
    }

    /**
     * Resets the view after sorting.
     */
    private void resetViewAfterSort() {
        view.disableInputComponents(false);
        model.getSorter().setTimelineComplete(false);
    }

    /**
     * Updates the view to show a new key frame.
     */
    private void updateViewWithKeyFrame() {
        view.displayCurrentElementOrder();
        model.getSorter().setNewKeyFrameCreated(false);
    }

    /**
     * Handle the generation of new array of elements. Call methods to display the elements on the view.
     * @param actionEvent event triggered by selecting a new list size or by clicking the generate new list button.
     */
    private void handleGenerateOrUpdate(ActionEvent actionEvent) {
        model.generateNElements(view.getInputPaneTop().getSizeChoiceBox().getValue());
        view.displayUnsortedElements();
    }

    /**
     * Handle the sort button and call methods to begin sorting.
     * @param actionEvent event triggered by clicking the sort button.
     */
    private void handleSortButton(ActionEvent actionEvent) {
        view.disableInputComponents(true);
        model.sort(view.getSortType(), view.getSortSpeed());
    }

    /**
     * Resets to the unsorted array of the current array of elements when a new sort algorithm is selected.
     * @param event event triggered by selecting a sorting algorithm in the choice box.
     */
    private void handleSortChoiceBox(Event event) {
        view.displayUnsortedElements();
    }

    /**
     * Handles the pause button by calling methods to pause the timeline.
     * @param actionEvent event triggered by clicking the pause button.
     */
    private void handlePauseButton(ActionEvent actionEvent) {
        model.getSorter().pause();
        view.sortIsPaused(true);
    }

    /**
     * Handles the play button by calling methods to resume the timeline.
     * @param actionEvent event triggered by clicking the play button.
     */
    private void handlePlayButton(ActionEvent actionEvent) {
        model.getSorter().play();
        view.sortIsPaused(false);
    }

    /**
     * Handles the stop button by calling methods to cancel the timeline and to reset the sorting process.
     * @param actionEvent event triggered by clicking the stop button.
     */
    private void handleStopButton(ActionEvent actionEvent) {
        model.getSorter().cancel();
        view.displayUnsortedElements();
        view.sortIsPaused(false);
    }

    // Main entry point
    public static void main(String[] args) {
        launch(args);
    }
}