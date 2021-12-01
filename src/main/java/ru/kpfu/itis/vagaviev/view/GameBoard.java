package ru.kpfu.itis.vagaviev.view;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameBoard{
            GridPane gridPane = new GridPane();

            public void createBoard(int size){
                  for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                              Rectangle rectangle = new Rectangle();
                              rectangle.setWidth(size);
                              rectangle.setHeight(size);
                              if((i+j) % 2 == 0){
                                    rectangle.setFill(Color.WHITE);
                              } else {
                                    rectangle.setFill(Color.BLACK);
                              }
                              rectangle.setStroke(Color.BLACK);
                              GridPane.setConstraints(rectangle, i, j);
                              gridPane.getChildren().add(rectangle);
                        }
                  }
            }
}
