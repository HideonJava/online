package com.java.online.fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FXController implements Initializable{

    private double x = 0;
    private double y = 0;

    @FXML
    private TextArea log;
    @FXML
    private BorderPane main;

    @FXML
    public void onMouseDragged(MouseEvent event){
        Launch.stage.setX(event.getScreenX() - x);
        Launch.stage.setY(event.getScreenY() - y);
    }

    @FXML
    public void onMousePressed(MouseEvent event){
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    public void onCloseClick(MouseEvent event) {
        Launch.stage.close();
    }

    @FXML
    public void onMinusClick(MouseEvent event) {
        Launch.stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

}
