package components;

import javafx.scene.layout.Pane;
import models.Element;

public class AlgorithmPane extends Pane {
    public AlgorithmPane() {
        setStyle("-fx-background-color: black");
        setPrefSize(954, 540);
    }

    public void update(Element[] elements) {
        getChildren().clear();
        int width = 950/elements.length;
        int x = 2 + (475-width*elements.length/2);
        for (Element e: elements) {
            e.positionRect(x, 538-e.getValue());
            getChildren().add(e.getRect());
            x+=width;
        }
    }
}
