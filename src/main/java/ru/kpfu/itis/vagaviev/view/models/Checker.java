package ru.kpfu.itis.vagaviev.view.models;

import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Checker extends Rectangle implements Serializable {
      public int x;
      public int y;
      boolean isWhite;
      boolean isDead;
      boolean isQueen;

      public Checker(int x, int y, boolean isWhite, boolean isDead, boolean isQueen) {
            setXY(x, y);
            this.isWhite = isWhite;
            this.isDead = isDead;
            this.isQueen = isQueen;
      }

      public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
      }
}