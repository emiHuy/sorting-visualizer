# Sorting Visualizer

## Description
This is an application that visually displays how different sorting algorithms work.

## Project Metadata
**Author:** Emily <br>
**Date Created:** March 16, 2025 <br>
**Last Updated:** March 18, 2025 <br>

## Requirements
* Java 23
* JavaFX 21
* Recommended IDE: IntelliJ IDEA

## File and Folder Information
### Main Entry Point
- SortingVisualizerApp → entry point and main controller

### Folders
#### components/
- InputPaneTop.java → GUI used for input and user interactions (on top)
- InputPaneBottom.java → GUI used for input and user interactions (on bottom)
- AlgorithmPane.java → pane used to visually demonstrate sorting algorithm

#### models/
- Element.java → object to be sorted 
- Sort.java → contains sorting algorithms

#### views/
- SortingVisualizerView.java → arranges different parts of UI

## Features
### Sorting Algorithms Implemented
1. **Selection Sort**
2. **Bubble Sort**
3. **Insertion Sort**

### Interactive Features
1. **Generate new list button**: Generates new collection of elements to sort.
2. **List size choice box**: Selects the size of the collection to sort.
3. **Sort type choice box**: Selects the sorting algorithm.
4. **Sort button**: Initiates sorting process.
5. **Speed slider**: Adjusts speed of the visualizer.
6. **Cancel button**: Cancels and resets the sorting process.
7. **Pause button**: Pauses the sorting process.
8. **Play button**: Continues the sorting process after it is paused. <br>

Note that some interactive features will be disabled/not visible during some parts of the application (ie. during sorting, when not sorting, and more).

### Instructions
1. Select the size of the collection to be sorted by using the **list size choice box**.
2. Select the sorting algorithm by using the **sort type choice box**.
3. Set the speed by using the **speed slider**.
4. Click the **sort button** to start sorting.
5. During sorting: <br>
<indent> - Click the **pause button** to pause sorting.<br>
<indent> - Click the **play button** to continue sorting. <br>
<indent> - Click the **cancel button** to cancel sorting.

## Future Enhancements
- Add more sorting algorithms
- Add colour to GUI
