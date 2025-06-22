package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Converterinterface extends Application{

    Stage mainWindow;
    Scene mainscene,scene1;
    String qString="",aString="";
    TextField field_output;
    TextField field_input;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        mainWindow=arg0;
        mainWindow.setTitle("POSTFIXTOPREFIXANDVICEVERSA");

        //main window
        Label Question=new Label("Choose any option:");
        ToggleGroup selection=new ToggleGroup();
        RadioButton intopre=new RadioButton("Infix to prefix conversion");
        RadioButton intopost=new RadioButton("Infix to postfix conversion");
        RadioButton pretopost=new RadioButton("prefix to postfix  conversion");
        RadioButton pretoin=new RadioButton("Prefix to Infix  conversion");
        RadioButton posttopre=new RadioButton("Postfix to prefix conversion");
        RadioButton posttoin=new RadioButton("Postfix to Infix conversion");
        Button ok=new Button("Ok");
        Button exit=new Button("Exit");
        ok.setOnAction(e->{
            gotonxtscene();
        });
        exit.setOnAction(e->{
            exit();
        });
        exit.getStyleClass().add("button-red");

        intopre.setToggleGroup(selection);
        intopost.setToggleGroup(selection);
        posttopre.setToggleGroup(selection);
        pretopost.setToggleGroup(selection);
        posttoin.setToggleGroup(selection);
        pretoin.setToggleGroup(selection);

        GridPane main_gp=new GridPane();
        main_gp.setVgap(10);
        main_gp.setHgap(10);

        GridPane.setConstraints(Question,0,0);
        GridPane.setConstraints(intopre,0,1);
        GridPane.setConstraints(intopost,0,2);
        GridPane.setConstraints(pretoin,0,3);
        GridPane.setConstraints(pretopost,0,4);
        GridPane.setConstraints(posttoin,0,5);
        GridPane.setConstraints(posttopre,0,6);
        GridPane.setConstraints(ok,0,7);
        GridPane.setConstraints(exit,0,9);
        main_gp.setAlignment(Pos.CENTER);

        selection.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue ==intopre) {
                qString="INFIX";
                aString="PREFIX";
            } else if (newValue == intopost) {
                qString="INFIX";
                aString="POSTFIX";
            } else if (newValue == pretoin) {
                qString="PREFIX";
                aString="INFIX";
            } else if (newValue == pretopost) {
                qString="PREFIX";
                aString="POSTFIX";
            } else if (newValue == posttoin) {
                qString="POSTFIX";
                aString="INFIX";
            } else if (newValue == posttopre) {
                qString="POSTFIX";
                aString="PREFIX";
            }
        });

        main_gp.getChildren().addAll(Question,intopre,intopost,pretoin,pretopost,posttoin,posttopre,ok,exit);
        mainscene=new Scene(main_gp,350,350);
        mainscene.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
        mainWindow.setScene(mainscene);
        mainWindow.show();

    }

    void gotonxtscene(){

        if((qString=="") & (aString=="")){
            Alertbox.display("Error","Please select one option!");
            mainWindow.setScene(mainscene);
        }else{

            //scene-1
            Label label_input=new Label("Enter "+qString+" expression: ");
            Label label_output=new Label("Converted "+aString+" expresssion: ");
            field_input=new TextField();
            field_input.setText("");
            field_output=new TextField("");
            field_output.setEditable(false);
            field_input.setPromptText("input");
            Button back=new Button("Back");
            Button calculate=new Button("Calculate");
            Button clearall=new Button("ClearAll");
            clearall.setOnAction(e->clearall());
            calculate.getStyleClass().add("button-green");
            calculate.setOnAction(e->result());
            back.setOnAction(e->back());
            GridPane gp=new GridPane();
            gp.setVgap(10);
            gp.setHgap(10);
            GridPane.setConstraints(label_input,0,0);
            GridPane.setConstraints(label_output,0,1);
            GridPane.setConstraints(field_input,1,0);
            GridPane.setConstraints(field_output,1,1);
            GridPane.setConstraints(back,0,4);
            GridPane.setConstraints(calculate,0,2);
            GridPane.setConstraints(clearall,1,2);
            gp.setAlignment(Pos.CENTER);

            gp.getChildren().addAll(label_input,label_output,field_input,field_output,back,calculate,clearall);
            scene1=new Scene(gp,400,400);
            scene1.getStylesheets().add(getClass().getResource("Converterinterfacestyle.css").toExternalForm());
            mainWindow.setScene(scene1);
        }
        
    }
        
    void back(){
        mainWindow.setScene(mainscene);
    }

     void exit(){
        mainWindow.close();
    }  
    void clearall(){
        field_input.setText("");
        field_output.setText("");
    }

    void result(){
        if(field_input.getText().equals("")){
            Alertbox.display("Suggestion","Input is empty please enter expression");
            mainWindow.setScene(scene1);
        }
        else{
            String value="";
            field_output.setText("");
            Outputproviders O=new Outputproviders();
            if(qString=="INFIX" & aString=="PREFIX"){
                String alone=field_input.getText();
                alone=alone.replace(" ","");
                value=O.infixToPrefix(alone);
            }
            else if(qString=="INFIX" & aString=="POSTFIX"){
                String alone=field_input.getText();
                alone=alone.replace(" ","");
                value=O.infixToPostfix(alone);
            }
            else if(qString=="PREFIX" & aString=="POSTFIX"){
                String alone=field_input.getText();
                alone=alone.replace(" ","");
                value=O.prefixToPostfix(alone);
            }
            else if(qString=="PREFIX" & aString=="INFIX"){
                String alone=field_input.getText();
                alone=alone.replace(" ","");
                value=O.prefixToInfix(alone);
            }
            else if(qString=="POSTFIX" & aString=="PREFIX"){
                String alone=field_input.getText();
                alone=alone.replace(" ","");
                value=O.postfixToPrefix(alone);
            }else if(qString=="POSTFIX" & aString=="INFIX"){
                String alone=field_input.getText();
                alone=alone.replace(" ","");
                value=O.postfixToInfix(alone);
            }
            field_output.setText(value);
        }
    }
}
