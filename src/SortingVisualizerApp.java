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
        sort = new Sort(view.getAlgorithmPane(), view.getInputPane());
        elements = Element.generateNElements(50);
    }

    public void start(Stage primaryStage) {
        Pane mainPane = new Pane();
        mainPane.setStyle("-fx-background-color: rgb(240, 240, 240);");
        mainPane.getChildren().add(view);
        view.getAlgorithmPane().display(elements);

        view.getInputPane().getGenerateListButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                elements = Element.generateNElements(Integer.parseInt(""+view.getInputPane().getSizeChoiceBox().getValue()));
                view.getAlgorithmPane().display(elements);
            }
        });

        view.getInputPane().getSortButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String sortType = ""+view.getInputPane().getSortChoiceBox().getValue();
                Element[] elementsCopy = Arrays.copyOf(elements, elements.length);

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

        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainPane, 1024, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
