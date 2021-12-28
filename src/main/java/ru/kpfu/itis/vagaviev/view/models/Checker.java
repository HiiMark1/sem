package ru.kpfu.itis.vagaviev.view.models;

public class Checker extends Cell {
      boolean isWhite;
      boolean isQueen;

      public Checker(int x, int y, boolean isWhite, boolean isQueen) {
            setXY(x, y);
            this.isWhite = isWhite;
            this.isQueen = isQueen;
      }

      public boolean isWhite() {
            return isWhite;
      }

      public void setWhite(boolean white) {
            isWhite = white;
      }

      public boolean isQueen() {
            return isQueen;
      }

      public Checker() {
      }

      public void setQueen(boolean queen) {
            isQueen = queen;
      }

      public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
      }

      @Override
      public String toString() {
            return "Checker{" +
                    "x=" + x +
                    ", y=" + y +
                    ", isWhite=" + isWhite +
                    ", isQueen=" + isQueen +
                    '}';
      }
}