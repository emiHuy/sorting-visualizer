package utils;

import controllers.InputPaneBottom;
import controllers.InputPaneTop;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import models.Element;
import views.AlgorithmPane;
import views.SortingVisualizerView;

import java.util.TreeMap;

public class Sort {
    SortingVisualizerView view;
    private Timeline timeline;
    private double delay = 0.08;

    public Sort(SortingVisualizerView view){
        this.view = view;
        timeline = new Timeline();
    }

    public void selectionSort(Element[] arr) {
        int nextSmallestIndex;
        Element temp;
        for (int i = 0; i < arr.length - 1; i++) {
            nextSmallestIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[nextSmallestIndex]) < 0) {
                    nextSmallestIndex = j;
                }
            }
            // Swap the elements
            temp = arr[i];
            arr[i] = arr[nextSmallestIndex];
            arr[nextSmallestIndex] = temp;

            makeKeyFrame(arr.clone(), i*delay);
        }

        // Play the timeline
        timeline.setCycleCount(1);
        executeAfterTimeline(arr);
        timeline.play();
    }

    public void bubbleSort(Element[] arr) {
        int n = arr.length;
        boolean swapped;
        double delayTime = 0;
        Element temp;
        for (int i = 0; i < n; i++) {
            swapped = false;
            for (int j = 0; j < n-i-1; j++) {
                delayTime += delay/2;
                if (arr[j].compareTo(arr[j+1]) > 0) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    // Add a KeyFrame to display the current state of the array
                    makeKeyFrame(arr.clone(), delayTime);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        timeline.setCycleCount(1);
        executeAfterTimeline(arr);
        timeline.play();
    }

    public void insertionSort(Element[] arr) {
        int n = arr.length;
        Element key;
        Element temp;
        double delayTime = 0;

        for (int i = 1; i < n; i++) {
            key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                delayTime += delay/2;
                temp = arr[j+1];
                arr[j + 1] = arr[j];
                arr[j] = temp;
                j--;
                makeKeyFrame(arr.clone(), delayTime);
            }
            arr[j + 1] = key;
        }
        timeline.setCycleCount(1);
        executeAfterTimeline(arr);
        timeline.play();
    }

    private void executeAfterTimeline(Element[] arr) {
        timeline.setOnFinished(event -> {
            view.resetAfterExecution();
            for (Element e: arr) {
                System.out.println(e.getValue());
            }
            timeline = new Timeline();
            System.out.println();
        });
    }

    private void makeKeyFrame(Element[] arr, double delayTime){
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(delayTime), event -> {
            view.update(arr);
        }));
    }

    public void pause(){
        timeline.pause();
    }

    public void play(){
        timeline.play();
    }

    public void stop() {
        timeline.stop();
        timeline = new Timeline();
        view.resetAfterExecution();
    }

    public void setDelay(double newDelay) {
        delay = newDelay;
    }
}
