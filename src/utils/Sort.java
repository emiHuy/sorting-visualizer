package utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import models.Element;
import views.AlgorithmPane;

public class Sort {
    AlgorithmPane algorithmPane;
    static double delay = 0.1;

    public Sort(AlgorithmPane algorithmPane){
        this.algorithmPane = algorithmPane;
    }

    public void selectionSort(Element[] arr) {
        int nextSmallestIndex;
        Element temp;
        Timeline timeline = new Timeline(); // Create a timeline to manage animations
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

            // Add a KeyFrame to display the current state of the array
            Element[] currentState = arr.clone(); // Clone the array to capture its current state
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(i * delay), event -> {
                algorithmPane.display(currentState); // Display the array at this state
            }));
        }

        // Play the timeline
        timeline.setCycleCount(1);
        timeline.play();
    }

    public void bubbleSort(Element[] arr) {
        int n = arr.length;
        boolean swapped;
        double delayTime = 0;
        Element temp;
        Timeline timeline = new Timeline();
        for (int i = 0; i < n; i++) {
            swapped = false;
            for (int j = 0; j < n-i-1; j++) {
                delayTime += delay/2;
                if (arr[j].compareTo(arr[j+1]) > 0) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    // Add a KeyFrame to display the current state of the array
                    Element[] currentState = arr.clone(); // Clone the array to capture its current state
                    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(delayTime), event -> {
                        algorithmPane.display(currentState); // Display the array at this state
                    }));
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        timeline.setCycleCount(1);
        timeline.play();
    }

    public void insertionSort(Element[] arr) {
        Timeline timeline = new Timeline();
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
                Element[] currentState = arr.clone(); // Clone the array to capture its current state
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(delayTime), event -> {
                    algorithmPane.display(currentState); // Display the array at this state
                }));
            }
            arr[j + 1] = key;
        }
        timeline.setCycleCount(1);
        timeline.play();
    }
}
