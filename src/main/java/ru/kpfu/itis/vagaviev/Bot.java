package ru.kpfu.itis.vagaviev;

import javafx.scene.shape.Rectangle;
import ru.kpfu.itis.vagaviev.view.models.Checker;

public class Bot {
      boolean isWhite;
      Checker[][] checkers;

      public Bot(boolean isWhite) {
            this.isWhite = isWhite;
      }

      public void move(){
            for (int row = 0; row<8; row++){
                  for (int col = 0; col < 8; col++) {
                        if(checkers[row][col]!=null && checkers[row][col].isWhite()==this.isWhite){
                              if(row-2>-1){

                              }
                              if(row+2<8){

                              }
                              if(col-2>-1){

                              }
                              if(col+2<8){

                              }
                        }
                  }
            }
      }

      public void update(Checker[][] checkers){
            this.checkers = checkers;
      }
}