package ru.kpfu.itis.vagaviev.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import ru.kpfu.itis.vagaviev.Bot;
import ru.kpfu.itis.vagaviev.view.models.Cell;
import ru.kpfu.itis.vagaviev.view.models.Checker;

import java.util.Objects;

public class GameBoard extends Application {
      boolean isSinglePlayer = true;
      GridPane gridPane = new GridPane();
      int size = 50;
      Checker[][] checkers = new Checker[8][8];
      Rectangle[][] rectangles = new Rectangle[8][8];
      boolean isHaveChosenChecker = false;
      Rectangle chosenRect = new Rectangle();
      Checker chosenChecker = new Checker(0, 0, false, false);
      boolean isLongAtack = false;
      Cell longAtackFrom = new Cell();
      boolean is1PlayerWhite = true;
      boolean is2PlayerWhite = false;
      Bot bot = null;
      Label label;
      int color = 0;
      int numOfTurn = 0;

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
                              System.out.println(rectangle.getFill());
                        } else {
                              rectangle.setFill(Color.BLACK);
                              System.out.println(rectangle.getFill());
                        }
                        rectangle.setStroke(Color.BLACK);
                        rectangle.setOnMouseClicked(event -> {
                              if (isHaveChosenChecker && (checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite() == is1PlayerWhite)) {
                                    if (!checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isQueen()) {
                                          if ((chosenRect.getY() + 1 == rectangle.getY() && chosenRect.getX() + 1 == rectangle.getX()) ||
                                                  (chosenRect.getY() + 1 == rectangle.getY() && chosenRect.getX() - 1 == rectangle.getX()) ||
                                                  (chosenRect.getY() - 1 == rectangle.getY() && chosenRect.getX() + 1 == rectangle.getX()) ||
                                                  (chosenRect.getY() - 1 == rectangle.getY() && chosenRect.getX() - 1 == rectangle.getX())) {
                                                if (checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite()) {
                                                      if ((chosenRect.getY() - 1 == rectangle.getY() && chosenRect.getX() + 1 == rectangle.getX()) ||
                                                              (chosenRect.getY() - 1 == rectangle.getY() && chosenRect.getX() - 1 == rectangle.getX())) {
                                                            if (!isLongAtack) {
                                                                  move(rectangle, null);
                                                            }
                                                      }
                                                } else {
                                                      if ((chosenRect.getY() + 1 == rectangle.getY() && chosenRect.getX() + 1 == rectangle.getX()) ||
                                                              (chosenRect.getY() + 1 == rectangle.getY() && chosenRect.getX() - 1 == rectangle.getX())) {
                                                            if (!isLongAtack) {
                                                                  move(rectangle, null);
                                                            }
                                                      }
                                                }
                                          } else {
                                                if (checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite()) {
                                                      if ((chosenRect.getY() - 2 == rectangle.getY() && chosenRect.getX() + 2 == rectangle.getX()) &&
                                                              checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1] != null &&
                                                              !checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1].isWhite()) {
                                                            if (isLongAtack) {
                                                                  if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                          (int) chosenRect.getX() == longAtackFrom.getX()) {
                                                                        capture(rectangle, 1, -1);
                                                                  }
                                                            } else {
                                                                  capture(rectangle, 1, -1);
                                                            }
                                                      }
                                                      if ((chosenRect.getY() - 2 == rectangle.getY() && chosenRect.getX() - 2 == rectangle.getX()) &&
                                                              checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() - 1] != null &&
                                                              !checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() - 1].isWhite()) {
                                                            if (isLongAtack) {
                                                                  if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                          (int) chosenRect.getX() == longAtackFrom.getX()) {
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
                                                            if (isLongAtack) {
                                                                  if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                          (int) chosenRect.getX() == longAtackFrom.getX()) {
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
                                                                    (int) chosenRect.getX() == longAtackFrom.getX()) {
                                                                  if (chosenRect.equals(longAtackFrom)) {
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
                                                if (!isLongAtack) {
                                                      move(rectangle, null);
                                                }
                                          } else {
                                                if ((chosenRect.getY() - 2 == rectangle.getY() && chosenRect.getX() + 2 == rectangle.getX()) &&
                                                        checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1] != null &&
                                                        (checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1].isWhite() !=
                                                                checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite())) {
                                                      if (isLongAtack) {
                                                            if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAtackFrom.getX()) {
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
                                                      if (isLongAtack) {
                                                            if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAtackFrom.getX()) {
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
                                                      if (isLongAtack) {
                                                            if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAtackFrom.getX()) {
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
                                                      if (isLongAtack) {
                                                            if ((int) chosenRect.getY() == longAtackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAtackFrom.getX()) {
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
            label = new Label("Ход белых");
            gridPane.add(label, 8, 8);
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
                          if (numOfTurn % 2 == color) {
                                label.setText("Ход белых");
                                rect.setStroke(Color.GOLD);
                                chosenRect = rect;
                                chosenRect.setStroke(Color.BLACK);
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
            if (isSinglePlayer) {
                  bot = new Bot(is2PlayerWhite);
            }
            createBoard();
            addCheckers();
            Scene scene = new Scene(gridPane, 600, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Italian Checkers Game");
            primaryStage.show();
      }

      public void move(Rectangle rectangle, Checker checker1) {
            if (!isLongAtack) {
                  if (rectangle == null && checker1 != null) {
                        rectangle = new Rectangle();
                        rectangle.setX(checker1.getX());
                        rectangle.setY(checker1.getY());
                  }
                  if (rectangle != null && checkers[(int) rectangle.getX()][(int) rectangle.getY()] == null) {
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
                                            if (numOfTurn % 2 == color) {
                                                  label.setText("Ход белых");
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
                              gridPane.add(chosenRect, (int) rectangle.getX(), (int) rectangle.getY());
                              chosenRect.setX(rectangle.getX());
                              chosenRect.setY(rectangle.getY());
                              chosenRect.setStroke(Color.BLACK);
                              isHaveChosenChecker = false;
                        }
                        int a = checkWinner();
                        switch (a) {
                              case (1):
                                    label.setText("White Winners");
                                    break;
                              case (2):
                                    label.setText("Black Winners");
                                    break;
                              case (3):
                                    label.setText("Draw");
                                    break;
                        }
                  }
                  numOfTurn++;
                  if (numOfTurn % 2 == 1) {
                        label.setText("Ход черных");
                  } else {
                        label.setText("Ход белых");
                  }
                  if (isSinglePlayer && numOfTurn % 2 != color) {
                        while (numOfTurn % 2 != color) {
                              Pair<Integer, Pair<Checker, Checker>> pair = bot.move(checkers);
                              isHaveChosenChecker = true;
                              System.out.println(pair);
                              chosenRect = rectangles[pair.getValue().getKey().getX()][pair.getValue().getKey().getY()];
                              System.out.println(chosenRect);
                              if(pair.getKey()==1){
                                    move(null, pair.getValue().getValue());
                              } else {
                                    Rectangle rectangle1 = new Rectangle();
                                    rectangle1.setX(pair.getValue().getValue().getX());
                                    rectangle1.setY(pair.getValue().getValue().getY());
                                    capture(rectangle1, (pair.getValue().getValue().getX()-pair.getValue().getKey().getX())/2,
                                            (pair.getValue().getValue().getY()-pair.getValue().getKey().getY())/2);
                              }
                        }
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
                                      if (numOfTurn % 2 == color) {
                                            label.setText("Ход белых");
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
                        chosenRect.setStroke(Color.BLACK);
                        isHaveChosenChecker = false;
                  } else {
                        System.out.println(11222222);
                        System.out.println(chosenRect.getX() + changeX);
                        System.out.println(chosenRect.getY() + changeY);
                        System.out.println(rectangles[(int) chosenRect.getX() + changeX][(int) chosenRect.getY() + changeY]);
                        gridPane.getChildren().remove(rectangles[(int) chosenRect.getX() + changeX][(int) chosenRect.getY() + changeY]);
                        gridPane.getChildren().remove(chosenRect);
                        checkers[(int) rectangle.getX()][(int) rectangle.getY()] = checkers[(int) chosenRect.getX()][(int) chosenRect.getY()];
                        checkers[(int) chosenRect.getX()][(int) chosenRect.getY()] = null;
                        rectangles[(int) rectangle.getX()][(int) rectangle.getY()] = rectangles[(int) chosenRect.getX()][(int) chosenRect.getY()];
                        rectangles[(int) chosenRect.getX()][(int) chosenRect.getY()] = null;
                        checkers[(int) chosenRect.getX() + changeX][(int) chosenRect.getY() + changeY] = null;
                        chosenRect.setX(rectangle.getX());
                        chosenRect.setY(rectangle.getY());
                        chosenRect.setStroke(Color.BLACK);
                        gridPane.add(chosenRect, (int) rectangle.getX(), (int) rectangle.getY());
                        isHaveChosenChecker = false;
                  }
                  boolean flag1 = false;
                  boolean flag2 = false;
                  boolean flag3 = false;
                  boolean flag4 = false;
                  if (checkers[(int) rectangle.getX()][(int) rectangle.getY()].isQueen()) {
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
                  } else {
                        if (!checkers[(int) rectangle.getX()][(int) rectangle.getY()].isWhite() && rectangle.getY() + 2 < 8) {
                              if (rectangle.getX() + 2 < 8) {
                                    flag1 = longAtack(rectangle, 1, 1);
                              }
                              if (rectangle.getX() - 2 > -1) {
                                    flag2 = longAtack(rectangle, -1, 1);
                              }
                        }
                        if (checkers[(int) rectangle.getX()][(int) rectangle.getY()].isWhite() && rectangle.getY() - 2 > -1) {
                              if (rectangle.getX() + 2 < 8) {
                                    flag3 = longAtack(rectangle, 1, -1);
                              }
                              if (rectangle.getX() - 2 > -1) {
                                    flag4 = longAtack(rectangle, -1, -1);
                              }
                        }
                  }
                  if (!flag1 && !flag2 && !flag3 && !flag4) {
                        isLongAtack = false;
                        numOfTurn++;
                        if (isSinglePlayer && numOfTurn % 2 != color) {
                              while (numOfTurn % 2 != color) {
                                    Pair<Integer, Pair<Checker, Checker>> pair = bot.move(checkers);
                                    isHaveChosenChecker = true;
                                    chosenRect = rectangles[pair.getValue().getKey().getX()][pair.getValue().getKey().getY()];
                                    System.out.println(chosenRect);
                                    if(pair.getKey()==1){
                                          move(null, pair.getValue().getValue());
                                    } else {
                                          Rectangle rectangle1 = new Rectangle();
                                          rectangle1.setX(pair.getValue().getValue().getX());
                                          rectangle1.setY(pair.getValue().getValue().getY());
                                          capture(rectangle1, (pair.getValue().getKey().getX()-pair.getValue().getValue().getX())/2,
                                                  (pair.getValue().getKey().getY()-pair.getValue().getValue().getY())/2);
                                    }
                              }
                        }
                  }
                  int a = checkWinner();
                  switch (a) {
                        case (1):
                              label.setText("White Winners");
                              break;
                        case (2):
                              label.setText("Black Winners");
                              break;
                        case (3):
                              label.setText("Draw");
                              break;
                  }
            }
      }

      public boolean longAtack(Rectangle rectangle, int changeX, int changeY) {
            if (checkers[(int) rectangle.getX() + (changeX * 2)][(int) rectangle.getY() + (changeY * 2)] == null &&
                    checkers[(int) rectangle.getX() + changeX][(int) rectangle.getY() + changeY] != null &&
                    (checkers[(int) rectangle.getX() + changeX][(int) rectangle.getY() + changeY].isWhite() !=
                            checkers[(int) rectangle.getX()][(int) rectangle.getY()].isWhite())) {
                  isLongAtack = true;
                  longAtackFrom.setXY((int) rectangle.getX(), (int) rectangle.getY());
                  return true;
            } else {
                  return false;
            }
      }

      public int checkWinner() {
            int white = 0;
            int black = 0;
            for (int row = 0; row < 8; row++) {
                  for (int col = 0; col < 8; col++) {
                        if (checkers[row][col] != null) {
                              if (checkers[row][col].isWhite() && white == 0) {
                                    if (isCanMove(checkers[row][col])) {
                                          white++;
                                    }
                                    if (isCanCapture(checkers[row][col])) {
                                          white++;
                                    }
                              }
                              if (!checkers[row][col].isWhite() && black == 0) {
                                    if (isCanMove(checkers[row][col])) {
                                          black++;
                                    }
                                    if (isCanCapture(checkers[row][col])) {
                                          black++;
                                    }
                              }
                        }
                  }
            }
            if (white != 0 && black == 0) {
                  return 1;
            }
            if (white == 0 && black != 0) {
                  return 2;
            }
            if (white == 0 && black == 0) {
                  return 3;
            }
            if (black != 0 && white != 0) {
                  return 0;
            }
            return 0;
      }

      public boolean isCanMove(Checker checker) {
            int x = checker.getX();
            int y = checker.getY();
            if (checker.isQueen()) {
                  if (x + 1 < 8) {
                        if (y + 1 < 8) {
                              if (checkers[x + 1][y + 1] == null) {
                                    return true;
                              }
                        }
                        if (y - 1 > -1) {
                              if (checkers[x + 1][y - 1] == null) {
                                    return true;
                              }
                        }
                  }
                  if (x - 1 > -1) {
                        if (y + 1 < 8) {
                              if (checkers[x - 1][y + 1] == null) {
                                    return true;
                              }
                        }
                        if (y - 1 > -1) {
                              if (checkers[x - 1][y - 1] == null) {
                                    return true;
                              }
                        }
                  }
            } else {
                  if (checker.isWhite()) {
                        if (y - 1 > -1) {
                              if (x + 1 < 8) {
                                    if (checkers[x + 1][y - 1] == null) {
                                          return true;
                                    }
                              }
                              if (x - 1 > -1) {
                                    if (checkers[x - 1][y - 1] == null) {
                                          return true;
                                    }
                              }
                        }
                  } else {
                        if (y + 1 < 8) {
                              if (x + 1 < 8) {
                                    if (checkers[x + 1][y + 1] == null) {
                                          return true;
                                    }
                              }
                              if (x - 1 > -1) {
                                    if (checkers[x - 1][y + 1] == null) {
                                          return true;
                                    }
                              }
                        }
                  }
            }
            return false;
      }

      public boolean isCanCapture(Checker checker) {
            int x = checker.getX();
            int y = checker.getY();
            if (checker.isQueen()) {
                  if (x + 2 < 8) {
                        if (y + 2 < 8) {
                              if (checkers[x + 2][y + 2] == null && checkers[x + 1][y + 1] != null && checkers[x + 1][y + 1].isWhite() != checker.isWhite()) {
                                    return true;
                              }
                        }
                        if (y - 2 > -1) {
                              if (checkers[x + 2][y - 2] == null && checkers[x + 1][y - 1] != null && checkers[x + 1][y - 1].isWhite() != checker.isWhite()) {
                                    return true;
                              }
                        }
                  }
                  if (x - 2 > -1) {
                        if (y + 2 < 8) {
                              if (checkers[x - 2][y + 2] == null && checkers[x - 1][y + 1] != null && checkers[x - 1][y + 1].isWhite() != checker.isWhite()) {
                                    return true;
                              }
                        }
                        if (y - 2 > -1) {
                              if (checkers[x - 2][y - 2] == null && checkers[x - 1][y - 1] != null && checkers[x - 1][y - 1].isWhite() != checker.isWhite()) {
                                    return true;
                              }
                        }
                  }
            } else {
                  if (checker.isWhite()) {
                        if (y - 2 > -1) {
                              if (x + 2 < 8) {
                                    if (checkers[x + 2][y - 2] == null && checkers[x + 1][y - 1] != null && checkers[x + 1][y - 1].isWhite() != checker.isWhite()) {
                                          return true;
                                    }
                              }
                              if (x - 2 > -1) {
                                    if (checkers[x - 2][y - 2] == null && checkers[x - 1][y - 1] != null && checkers[x - 1][y - 1].isWhite() != checker.isWhite()) {
                                          return true;
                                    }
                              }
                        }
                  } else {
                        if (y + 2 < 8) {
                              if (x + 2 < 8) {
                                    if (checkers[x + 2][y + 2] == null && checkers[x + 1][y + 1] != null && checkers[x + 1][y + 1].isWhite() != checker.isWhite()) {
                                          return true;
                                    }
                              }
                              if (x - 2 > -1) {
                                    if (checkers[x - 2][y + 2] == null && checkers[x - 1][y + 1] != null && checkers[x - 1][y + 1].isWhite() != checker.isWhite()) {
                                          return true;
                                    }
                              }
                        }
                  }
            }
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
