package models;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;

public class Sort {
    private final BooleanProperty isTimelineComplete = new SimpleBooleanProperty(false);
    private final BooleanProperty newKeyFrameCreated = new SimpleBooleanProperty(false);
    private Timeline timeline;
    private double delay;
    private Element[] keyFrameArr;

    public Sort(){
        timeline = new Timeline();
        delay = 0.08;
    }

    public BooleanProperty isTimelineCompleteProperty() {
        return isTimelineComplete;
    }

    public BooleanProperty isNewKeyFrameCreatedProperty() {
        return newKeyFrameCreated;
    }

    public Element[] getKeyFrameArr() {
        return keyFrameArr;
    }

    public void setTimelineComplete(boolean value) {
        isTimelineComplete.set(value);
    }

    public void setNewKeyFrameCreated(boolean value) {
        newKeyFrameCreated.set(value);
    }

    public void setDelay(double newDelay) {
        delay = newDelay;
    }

    /**
     * Sorts the given array using selection sort.
     * @param arr array to be sorted.
     */
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
            temp = arr[i];
            arr[i] = arr[nextSmallestIndex];
            arr[nextSmallestIndex] = temp;
            makeKeyFrame(arr.clone(), i*delay);
        }
        timeline.setCycleCount(1);
        executeAfterTimeline(arr);
        timeline.play();
    }

    /**
     * Sorts the given array using bubble sort.
     * @param arr array to be sorted.
     */
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

    /**
     * Sorts the given array using insertion sort.
     * @param arr array to be sorted.
     */
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

    /**
     * Calls the correct sort method based on the given sort type.
     * @param sortType the type of sorting algorithm that will be used.
     * @param elements the array of elements to be sorted.
     */
    public void startSort(String sortType, Element[] elements){
        switch (sortType) {
            case "Selection Sort":
                selectionSort(elements);
                break;
            case "Bubble Sort":
                bubbleSort(elements);
                break;
            case "Insertion Sort":
                insertionSort(elements);
        }
    }

    /**
     * Handles post-sorting procedures and resets values.
     * @param arr the sorted array.
     */
    private void executeAfterTimeline(Element[] arr) {
        timeline.setOnFinished(event -> {
            isTimelineComplete.set(true);
            timeline = new Timeline();
        });
    }

    /**
     * Makes new key frame in timeline that shows a specific step of the sorting process.
     * @param arr the array in its current state during the sorting process.
     * @param delayTime double that controls the timing of key frame in the timeline.
     */
    private void makeKeyFrame(Element[] arr, double delayTime){
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(delayTime), event -> {
            keyFrameArr = arr;
            setNewKeyFrameCreated(true);
        }));
    }

    /**
     * Cancels the timeline.
     */
    public void cancel() {
        timeline.stop();
        timeline = new Timeline();
        isTimelineComplete.set(true);
    }

    /**
     * Pauses the timeline.
     */
    public void pause(){
        timeline.pause();
    }

    /**
     * Resumes the timeline.
     */
    public void play(){
        timeline.play();
    }
}
