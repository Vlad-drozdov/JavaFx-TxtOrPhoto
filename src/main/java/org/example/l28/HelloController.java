package org.example.l28;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class HelloController implements IController {
    @FXML
    private Label ModelData;

    @FXML
    private Pane main;

    IModel model;

    @FXML
    public void initialize() {
        model = Init.init();
        model.setiController(this);
    }



    @FXML
    protected void onHelloButtonClick() {
        model.doIt();
    }

    @Override
    public void setText(String text) {
        ModelData.setText(text);
    }

    @Override
    public void setImage(Image image) {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        main.setBackground(new Background(bgImage));
    }

    @Override
    public void removeAll() {
        main.setBackground(null);
        ModelData.setText("");
    }
}