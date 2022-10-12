/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author Stegger
 */
public class TicTacToe extends Application
{
    
    @Override
    public void start(Stage stage) throws Exception
    {
        /*Parent root = FXMLLoader.load(getClass().getResource("views/MenuScreen.fxml"));
        stage.setTitle("Tic Tac Toe");
        stage.getIcons().add(new Image(getClass().getResource("images/X.png").toExternalForm()));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);*/
        generateBoard(10, stage);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    public void generateBoard(int number, Stage _stage)
    {
        Scene scene = new Scene(new Group());
        GridPane grid = new GridPane();
        for (int i = 0; i < number; i++) {
            ColumnConstraints colC = new ColumnConstraints();
                colC.setMinWidth(30);
                colC.setMaxWidth(100);
                colC.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(colC);
            RowConstraints rowC = new RowConstraints();
                rowC.setPrefHeight(100);
                rowC.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rowC);
        }
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                var btn = new Button("");
                btn.maxWidth(Control.USE_PREF_SIZE);
                btn.maxHeight(Integer.MAX_VALUE);
                grid.add(btn, i, j);
            }
        }
        scene.setRoot(grid);
        _stage.setScene(scene);
    }
    
}
