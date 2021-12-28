package ru.kpfu.itis.vagaviev.view.models;

public class Cell {
      int x;
      int y;

      public void Cell(int x, int y){
            this.x = x;
            this.y = y;
      }

      public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
      }

      public int getX() {
            return x;
      }

      public void setX(int x) {
            this.x = x;
      }

      public int getY() {
            return y;
      }

      public void setY(int y) {
            this.y = y;
      }
}
