package ru.kpfu.itis.vagaviev.view.models;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class CheckersBoard extends GridPane {
      private Checker[] fields = new Checker[64];
      private int currentTurn = 1;
      private int widthScreen;
      private int heightScreen;
      private Scene scene;
      public GridPane map;
      public GridPane enemyMap;
}