package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alertbox extends Application{

    public static void display(String title, String message){
        Stage window =new Stage();// blank window;

        window.initModality(Modality.APPLICATION_MODAL);// this is done to block the user to use other windows untill this done completely or closed !!
        window.setTitle(title);
        window.setMinWidth(500);
        Label label =new Label(message);
        Button closeButton=new Button("close the window");
        closeButton.getStyleClass().add("button-red");
        closeButton.setOnAction(e->window.close());

        VBox layout =new VBox(10);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

        Scene scene =new Scene(layout);
        scene.getStylesheets().add("alertboxstyle.css");
        window.setScene(scene);
        window.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage arg0) throws Exception {
        display("This is the title", "here goes the message you want to display");
    }
}
