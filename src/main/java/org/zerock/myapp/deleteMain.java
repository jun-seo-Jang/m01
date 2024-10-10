package org.zerock.myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


//@Log4j2

public class deleteMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("delete.fxml"));
        primaryStage.setTitle("삭제/수정");
        primaryStage.setScene(new Scene(root, 375, 466));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }// main
}//end class
