import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Element;
import utils.Sort;
import views.SortingVisualizerView;

import java.util.Arrays;

public class SortingVisualizerApp extends Application {
    SortingVisualizerView view;
    Element[] elements;
    Sort sort;

    public SortingVisualizerApp() {
        view = new SortingVisualizerView();
        sort = new Sort(view);
        elements = Element.generateNElements(50);
    }

    public void start(Stage primaryStage) {
        Pane mainPane = new Pane();
        mainPane.setStyle("-fx-background-color: rgb(240, 240, 240);");
        mainPane.getChildren().add(view);
        view.getAlgorithmPane().display(elements);

        view.getInputPaneTop().getGenerateListButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                elements = Element.generateNElements(Integer.parseInt(""+view.getInputPaneTop().getSizeChoiceBox().getValue()));
                view.getAlgorithmPane().display(elements);
            }
        });

        view.getInputPaneTop().getSortButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String sortType = ""+view.getInputPaneTop().getSortChoiceBox().getValue();
                Element[] elementsCopy = Arrays.copyOf(elements, elements.length);
                view.getInputPaneTop().disable();
                view.getInputPaneBottom().buttonVisibility(true);
                view.getInputPaneBottom().getPlayButton().setDisable(true);
                sort.setDelay(view.getInputPaneBottom().getSpeedSlider().getValue());
                view.getInputPaneBottom().getSpeedSlider().setDisable(true);

                switch (sortType) {
                    case "Selection Sort":
                         sort.selectionSort(elementsCopy);
                        break;
                    case "Bubble Sort":
                         sort.bubbleSort(elementsCopy);
                        break;
                    case "Insertion Sort":
                        sort.insertionSort(elementsCopy);
                }
            }
        });

        view.getInputPaneTop().getSortChoiceBox().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getAlgorithmPane().display(elements);
            }
        });

        view.getInputPaneBottom().getPauseButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sort.pause();
                view.getInputPaneBottom().getPlayButton().setDisable(false);
            }
        });

        view.getInputPaneBottom().getStopButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sort.stop();
                view.getAlgorithmPane().display(elements);
            }
        });

        view.getInputPaneBottom().getPlayButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sort.play();
            }
        });

        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainPane, 1024, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
