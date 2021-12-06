package ru.kpfu.itis.vagaviev.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.kpfu.itis.vagaviev.view.models.Cell;
import ru.kpfu.itis.vagaviev.view.models.Checker;

import java.nio.file.Paths;
import java.util.Objects;

public class GameBoard extends Application {
      boolean isSinglePlayer = false;
      GridPane gridPane = new GridPane();
      int size = 50;
      Checker[][] checkers = new Checker[8][8];
      Rectangle[][] rectangles = new Rectangle[8][8];
      boolean isUrTurn = true;
      boolean isHaveChosenChecker = false;
      Rectangle chosenRect = new Rectangle();
      Checker chosenChecker = new Checker(0, 0, false, false);
      boolean isLongAtack = false;
      boolean isWhiteTurn = true;
      Cell longAtackFrom = new Cell();

      public void createBoard() {
            for (int row = 0; row < 8; row++) {
                  for (int col = 0; col < 8; col++) {
                        Rectangle rectangle = new Rectangle();
                        rectangle.setX(row);
                        rectangle.setY(col);
                        rectangle.setWidth(size);
                        rectangle.setHeight(size);
                        if ((row + col) % 2 == 0) {
                              rectangle.setFill(Color.WHITE);
                        } else {
                              rectangle.setFill(Color.BLACK);
                        }
                        rectangle.setStroke(Color.BLACK);
                        rectangle.setOnMouseClicked(event -> {
                              System.out.println(isLongAtack);
                              if (isHaveChosenChecker && (checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite() == isWhiteTurn)) {
                                    if (!checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isQueen()) {
                                          if ((chosenRect.getY() + 1 == rectangle.getY() && chosenRect.getX() + 1 == rectangle.getX()) ||
                                                  (chosenRect.getY() + 1 == rectangle.getY() && chosenRect.getX() - 1 == rectangle.getX()) ||
                                                  (chosenRect.getY() - 1 == rectangle.getY() && chosenRect.getX() + 1 == rectangle.getX()) ||
                                                  (chosenRect.getY() - 1 == rectangle.getY() && chosenRect.getX() - 1 == rectangle.getX())) {
                                                if (checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite()) {
                                                      if ((chosenRect.getY() - 1 == rectangle.getY() && chosenRect.getX() + 1 == rectangle.getX()) ||
                                                              (chosenRect.getY() - 1 == rectangle.getY() && chosenRect.getX() - 1 == rectangle.getX())) {
                                                            if (!isLongAtack){
                                                                  move(rectangle);
                                                            }
                                                      }
                                                } else {
                                                      if ((chosenRect.getY() + 1 == rectangle.getY() && chosenRect.getX() + 1 == rectangle.getX()) ||
                                                              (chosenRect.getY() + 1 == rectangle.getY() && chosenRect.getX() - 1 == rectangle.getX())) {
                                                            if(!isLongAtack){
                                                                  move(rectangle);
                                                            }
                                                      }
                                                }
                                          } else {
                                                if (checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite()) {
                                                      if ((chosenRect.getY() - 2 == rectangle.getY() && chosenRect.getX() + 2 == rectangle.getX()) &&
                                                              checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1] != null &&
                                                              !checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1].isWhite()) {
                                                            if (isLongAtack){
                                                                  if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                          (int) chosenRect.getX() == longAtackFrom.getX()){
                                                                        capture(rectangle, 1, -1);
                                                                  }
                                                            } else {
                                                                  capture(rectangle, 1, -1);
                                                            }
                                                      }
                                                      if ((chosenRect.getY() - 2 == rectangle.getY() && chosenRect.getX() - 2 == rectangle.getX()) &&
                                                              checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() - 1] != null &&
                                                              !checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() - 1].isWhite()) {
                                                            if (chosenRect.equals(longAtackFrom)){
                                                                  if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                          (int) chosenRect.getX() == longAtackFrom.getX()){
                                                                        capture(rectangle, -1, -1);
                                                                  }
                                                            } else {
                                                                  capture(rectangle, -1, -1);
                                                            }
                                                      }
                                                } else {
                                                      if ((chosenRect.getY() + 2 == rectangle.getY() && chosenRect.getX() + 2 == rectangle.getX()) &&
                                                              checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() + 1] != null &&
                                                              checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() + 1].isWhite()) {
                                                            if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAtackFrom.getX()){
                                                                  if (chosenRect.equals(longAtackFrom)){
                                                                        capture(rectangle, 1, 1);
                                                                  }
                                                            } else {
                                                                  capture(rectangle, 1, 1);
                                                            }
                                                      }
                                                      if ((chosenRect.getY() + 2 == rectangle.getY() && chosenRect.getX() - 2 == rectangle.getX()) &&
                                                              checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() + 1] != null &&
                                                              checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() + 1].isWhite()) {
                                                            if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAtackFrom.getX()){
                                                                  if (chosenRect.equals(longAtackFrom)){
                                                                        capture(rectangle, -1, 1);
                                                                  }
                                                            } else {
                                                                  capture(rectangle, -1, 1);
                                                            }
                                                      }
                                                }
                                          }
                                    } else {
                                          if ((chosenRect.getY() + 1 == rectangle.getY() && chosenRect.getX() + 1 == rectangle.getX()) ||
                                                  (chosenRect.getY() + 1 == rectangle.getY() && chosenRect.getX() - 1 == rectangle.getX()) ||
                                                  (chosenRect.getY() - 1 == rectangle.getY() && chosenRect.getX() + 1 == rectangle.getX()) ||
                                                  (chosenRect.getY() - 1 == rectangle.getY() && chosenRect.getX() - 1 == rectangle.getX())) {
                                                if(!isLongAtack){
                                                      move(rectangle);
                                                }
                                          } else {
                                                if ((chosenRect.getY() - 2 == rectangle.getY() && chosenRect.getX() + 2 == rectangle.getX()) &&
                                                        checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1] != null &&
                                                        (checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1].isWhite() !=
                                                                checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite())) {
                                                      if (isLongAtack){
                                                            if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAtackFrom.getX()){
                                                                  capture(rectangle, 1, -1);
                                                            }
                                                      } else {
                                                            capture(rectangle, 1, -1);
                                                      }
                                                }
                                                if ((chosenRect.getY() - 2 == rectangle.getY() && chosenRect.getX() - 2 == rectangle.getX()) &&
                                                        checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() - 1] != null &&
                                                        (checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() - 1].isWhite() !=
                                                                checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite())) {
                                                      if (isLongAtack){
                                                            if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAtackFrom.getX()){
                                                                  capture(rectangle, -1, -1);
                                                            }
                                                      } else {
                                                            capture(rectangle, -1, -1);
                                                      }
                                                }
                                                if ((chosenRect.getY() + 2 == rectangle.getY() && chosenRect.getX() + 2 == rectangle.getX()) &&
                                                        checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() + 1] != null &&
                                                        (checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() + 1].isWhite() !=
                                                                checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite())) {
                                                      if (isLongAtack){
                                                            if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAtackFrom.getX()){
                                                                  capture(rectangle, 1, 1);
                                                            }
                                                      } else {
                                                            capture(rectangle, 1, 1);
                                                      }
                                                }
                                                if ((chosenRect.getY() + 2 == rectangle.getY() && chosenRect.getX() - 2 == rectangle.getX()) &&
                                                        checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() + 1] != null &&
                                                        (checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() + 1].isWhite() !=
                                                                checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite())) {
                                                      if (isLongAtack){
                                                            if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAtackFrom.getX()){
                                                                  capture(rectangle, -1, 1);
                                                            }
                                                      } else {
                                                            capture(rectangle, -1, 1);
                                                      }
                                                }
                                          }
                                    }
                              }
                        });
                        gridPane.add(rectangle, row, col);
                  }
            }
      }

      public void addChecker(int x, int y) {
            Image img;
            if (y > 4) {
                  img = new Image(Objects.requireNonNull(this.getClass().getResource("/white.png"))
                          .toExternalForm());
            } else {
                  img = new Image(Objects.requireNonNull(this.getClass().getResource("/black.png"))
                          .toExternalForm());
            }
            Rectangle rect = new Rectangle();
            rect.setX(x);
            rect.setY(y);
            rect.setWidth(size);
            rect.setHeight(size);
            rect.setFill(new ImagePattern(img));
            rect.setOnMouseClicked(
                    event -> {
                          if (isUrTurn) {
                                chosenRect.setStroke(Color.BLACK);
                                rect.setStroke(Color.GOLD);
                                chosenRect = rect;
                                chosenChecker = checkers[(int) rect.getX()][(int) rect.getY()];
                                isHaveChosenChecker = true;
                          }
                    }
            );
            rectangles[x][y] = rect;
            gridPane.add(rectangles[x][y], x, y);
            Checker checker = new Checker(x, y, y > 4, false);
            checkers[x][y] = checker;
      }

      public GridPane getGameBoard() {
            return gridPane;
      }

      @Override
      public void start(Stage primaryStage) throws Exception {
            music();
            createBoard();
            addCheckers();
            Scene scene = new Scene(gridPane, 600, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Italian Checkers Game");
            primaryStage.show();
      }

      public void move(Rectangle rectangle) {
            if (!isLongAtack) {
                  if (checkers[(int) rectangle.getX()][(int) rectangle.getY()] == null) {
                        if ((rectangle.getY() == 7 && !chosenChecker.isWhite()) || (rectangle.getY() == 0) && chosenChecker.isWhite()) {
                              gridPane.getChildren().remove(chosenRect);
                              checkers[(int) chosenRect.getX()][(int) chosenRect.getY()] = null;
                              rectangles[(int) chosenRect.getX()][(int) chosenRect.getY()] = null;
                              Image img;
                              if (chosenChecker.isWhite()) {
                                    img = new Image(Objects.requireNonNull(this.getClass().getResource("/qWhite.png")).toExternalForm());
                              } else {
                                    img = new Image(Objects.requireNonNull(this.getClass().getResource("/qBlack.png")).toExternalForm());
                              }
                              int x = (int) rectangle.getX();
                              int y = (int) rectangle.getY();
                              Rectangle rect = new Rectangle();
                              rect.setX(x);
                              rect.setY(y);
                              rect.setWidth(size);
                              rect.setHeight(size);
                              rect.setFill(new ImagePattern(img));
                              rect.setOnMouseClicked(
                                      event -> {
                                            if (isUrTurn) {
                                                  chosenRect.setStroke(Color.BLACK);
                                                  rect.setStroke(Color.GOLD);
                                                  chosenRect = rect;
                                                  chosenChecker = checkers[(int) rect.getX()][(int) rect.getY()];
                                                  isHaveChosenChecker = true;
                                            }
                                      }
                              );
                              rectangles[x][y] = rect;
                              gridPane.add(rectangles[x][y], x, y);
                              Checker checker = new Checker(x, y, chosenChecker.isWhite(), true);
                              checkers[x][y] = checker;
                              chosenRect.setX(rectangle.getX());
                              chosenRect.setY(rectangle.getY());
                              chosenRect.setStroke(Color.BLACK);
                              isHaveChosenChecker = false;
                        } else {
                              gridPane.getChildren().remove(chosenRect);
                              checkers[(int) rectangle.getX()][(int) rectangle.getY()] =
                                      checkers[(int) chosenRect.getX()][(int) chosenRect.getY()];
                              checkers[(int) chosenRect.getX()][(int) chosenRect.getY()] = null;
                              rectangles[(int) rectangle.getX()][(int) rectangle.getY()] =
                                      rectangles[(int) chosenRect.getX()][(int) chosenRect.getY()];
                              rectangles[(int) chosenRect.getX()][(int) chosenRect.getY()] = null;
                              chosenRect.setX(rectangle.getX());
                              chosenRect.setY(rectangle.getY());
                              gridPane.add(chosenRect, (int) rectangle.getX(), (int) rectangle.getY());
                              chosenRect.setStroke(Color.BLACK);
                              isHaveChosenChecker = false;
                        }
                        isWhiteTurn = !isWhiteTurn;
                  }
            }
      }

      public void capture(Rectangle rectangle, int changeX, int changeY) {
            if (checkers[(int) rectangle.getX()][(int) rectangle.getY()] == null) {
                  if ((rectangle.getY() == 7 && !chosenChecker.isWhite()) || (rectangle.getY() == 0) && chosenChecker.isWhite()) {
                        gridPane.getChildren().remove(rectangles[(int) chosenRect.getX() + changeX][(int) chosenRect.getY() + changeY]);
                        gridPane.getChildren().remove(chosenRect);
                        checkers[(int) chosenRect.getX()][(int) chosenRect.getY()] = null;
                        rectangles[(int) chosenRect.getX()][(int) chosenRect.getY()] = null;
                        checkers[(int) chosenRect.getX() + changeX][(int) chosenRect.getY() + changeY] = null;
                        Image img;
                        if (chosenChecker.isWhite()) {
                              img = new Image(Objects.requireNonNull(this.getClass().getResource("/qWhite.png")).toExternalForm());
                        } else {
                              img = new Image(Objects.requireNonNull(this.getClass().getResource("/qBlack.png")).toExternalForm());
                        }
                        int x = (int) rectangle.getX();
                        int y = (int) rectangle.getY();
                        Rectangle rect = new Rectangle();
                        rect.setX(x);
                        rect.setY(y);
                        rect.setWidth(size);
                        rect.setHeight(size);
                        rect.setFill(new ImagePattern(img));
                        rect.setOnMouseClicked(
                                event -> {
                                      if (isUrTurn) {
                                            chosenRect.setStroke(Color.BLACK);
                                            rect.setStroke(Color.GOLD);
                                            chosenRect = rect;
                                            chosenChecker = checkers[(int) rect.getX()][(int) rect.getY()];
                                            isHaveChosenChecker = true;
                                      }
                                }
                        );
                        rectangles[x][y] = rect;
                        gridPane.add(rectangles[x][y], x, y);
                        Checker checker = new Checker(x, y, chosenChecker.isWhite(), true);
                        checkers[x][y] = checker;
                        chosenRect.setX(rectangle.getX());
                        chosenRect.setY(rectangle.getY());
                        chosenRect.setStroke(Color.BLACK);
                        isHaveChosenChecker = false;
                  } else {
                        gridPane.getChildren().remove(rectangles[(int) chosenRect.getX() + changeX][(int) chosenRect.getY() + changeY]);
                        gridPane.getChildren().remove(chosenRect);
                        checkers[(int) rectangle.getX()][(int) rectangle.getY()] = checkers[(int) chosenRect.getX()][(int) chosenRect.getY()];
                        checkers[(int) chosenRect.getX()][(int) chosenRect.getY()] = null;
                        rectangles[(int) rectangle.getX()][(int) rectangle.getY()] = rectangles[(int) chosenRect.getX()][(int) chosenRect.getY()];
                        rectangles[(int) chosenRect.getX()][(int) chosenRect.getY()] = null;
                        checkers[(int) chosenRect.getX() + changeX][(int) chosenRect.getY() + changeY] = null;
                        chosenRect.setStroke(Color.BLACK);
                        chosenRect.setX(rectangle.getX());
                        chosenRect.setY(rectangle.getY());
                        gridPane.add(chosenRect, (int) rectangle.getX(), (int) rectangle.getY());
                        isHaveChosenChecker = false;
                  }
                  boolean flag1 = false;
                  boolean flag2 = false;
                  boolean flag3 = false;
                  boolean flag4 = false;
                  if (rectangle.getY() + 2 < 8) {
                        if (rectangle.getX() + 2 < 8) {
                              flag1 = longAtack(rectangle, 1, 1);
                        }
                        if (rectangle.getX() - 2 > -1) {
                              flag2 = longAtack(rectangle, -1, 1);
                        }
                  }
                  if (rectangle.getY() - 2 > -1) {
                        if (rectangle.getX() + 2 < 8) {
                              flag3 = longAtack(rectangle, 1, -1);
                        }
                        if (rectangle.getX() - 2 > -1) {
                              flag4 = longAtack(rectangle, -1, -1);
                        }
                  }
                  if(!flag1 && !flag2 && !flag3 && !flag4){
                        isLongAtack=false;
                        isWhiteTurn = !isWhiteTurn;
                  }
            }
      }

      public boolean longAtack(Rectangle rectangle, int changeX, int changeY) {
            if (checkers[(int) rectangle.getX() + (changeX * 2)][(int) rectangle.getY() + (changeY * 2)] == null &&
                    checkers[(int) rectangle.getX() + changeX][(int) rectangle.getY() + changeY] != null &&
                    (checkers[(int) rectangle.getX() + changeX][(int) rectangle.getY() + changeY].isWhite() !=
                            checkers[(int) rectangle.getX()][(int) rectangle.getY()].isWhite())) {
                  isLongAtack = true;
                  longAtackFrom.setXY((int) rectangle.getX(),(int) rectangle.getY());
                  return true;
            } else {
                  return false;
            }
      }

      public boolean isWin(){

            return false;
      }

      public void addCheckers() {
            addChecker(1, 0);
            addChecker(3, 0);
            addChecker(5, 0);
            addChecker(7, 0);
            addChecker(0, 1);
            addChecker(2, 1);
            addChecker(4, 1);
            addChecker(6, 1);
            addChecker(1, 2);
            addChecker(3, 2);
            addChecker(5, 2);
            addChecker(7, 2);
            addChecker(0, 7);
            addChecker(2, 7);
            addChecker(4, 7);
            addChecker(6, 7);
            addChecker(1, 6);
            addChecker(3, 6);
            addChecker(5, 6);
            addChecker(7, 6);
            addChecker(0, 5);
            addChecker(2, 5);
            addChecker(4, 5);
            addChecker(6, 5);
      }
}
