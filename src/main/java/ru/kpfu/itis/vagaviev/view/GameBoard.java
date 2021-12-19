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
import ru.kpfu.itis.vagaviev.view.models.Cell;
import ru.kpfu.itis.vagaviev.view.models.Checker;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameBoard extends Application {
      GridPane gridPane = new GridPane();
      int size = 50;
      Checker[][] checkers = new Checker[8][8];
      Rectangle[][] rectangles = new Rectangle[8][8];
      boolean isUrTurn = true;
      boolean isHaveChosenChecker = false;
      Rectangle chosenRect = new Rectangle();
      Checker chosenChecker = new Checker(0, 0, false, false);
      boolean isLongAttack = false;
      boolean isWhiteTurn = true;
      Cell longAttackFrom = new Cell();
      Label label = new Label("Ход белых");
      Random random = new Random();

      private void createBoard() {
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
                              System.out.println(isLongAttack);
                              if (isHaveChosenChecker && (checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite())) {
                                    if (!checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isQueen()) {
                                          if (checkCanMove(rectangle, 1, 1) || checkCanMove(rectangle, -1, 1) ||
                                                  checkCanMove(rectangle, 1, -1) || checkCanMove(rectangle, -1, -1)) {
                                                if (checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite()) {
                                                      if (checkCanMove(rectangle, 1, -1) || checkCanMove(rectangle, -1, -1)) {
                                                            if (!isLongAttack) {
                                                                  move(rectangle);
                                                            }
                                                      }
                                                } else {
                                                      if (checkCanMove(rectangle, 1, 1) || checkCanMove(rectangle, -1, 1)) {
                                                            if (!isLongAttack) {
                                                                  move(rectangle);
                                                            }
                                                      }
                                                }
                                          } else {
                                                if (checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite()) {
                                                      if (checkCanMove(rectangle, 2, -2) &&
                                                              checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1] != null &&
                                                              !checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1].isWhite()) {
                                                            if (isLongAttack) {
                                                                  if ((int) chosenRect.getY() == longAttackFrom.getY() &&
                                                                          (int) chosenRect.getX() == longAttackFrom.getX()) {
                                                                        capture(rectangle, 1, -1);
                                                                  }
                                                            } else {
                                                                  capture(rectangle, 1, -1);
                                                            }
                                                      }
                                                      if (checkCanMove(rectangle, -2, -2) &&
                                                              checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() - 1] != null &&
                                                              !checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() - 1].isWhite()) {
                                                            if (isLongAttack) {
                                                                  if ((int) chosenRect.getY() == longAttackFrom.getY() &&
                                                                          (int) chosenRect.getX() == longAttackFrom.getX()) {
                                                                        capture(rectangle, -1, -1);
                                                                  }
                                                            } else {
                                                                  capture(rectangle, -1, -1);
                                                            }
                                                      }
                                                } else {
                                                      if (checkCanMove(rectangle, 2, 2) &&
                                                              checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() + 1] != null &&
                                                              checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() + 1].isWhite()) {
                                                            if ((int) chosenRect.getY() == longAttackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAttackFrom.getX()) {
                                                                  if (isLongAttack) {
                                                                        capture(rectangle, 1, 1);
                                                                  }
                                                            } else {
                                                                  capture(rectangle, 1, 1);
                                                            }
                                                      }
                                                      if (checkCanMove(rectangle, -2, 2) &&
                                                              checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() + 1] != null &&
                                                              checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() + 1].isWhite()) {
                                                            if ((int) chosenRect.getY() == longAttackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAttackFrom.getX()) {
                                                                  if (isLongAttack) {
                                                                        capture(rectangle, -1, 1);
                                                                  }
                                                            } else {
                                                                  capture(rectangle, -1, 1);
                                                            }
                                                      }
                                                }
                                          }
                                    } else {
                                          if (checkCanMove(rectangle, 1, 1) || checkCanMove(rectangle, -1, 1) ||
                                                  checkCanMove(rectangle, 1, -1) || checkCanMove(rectangle, -1, -1)) {
                                                if (!isLongAttack) {
                                                      move(rectangle);
                                                }
                                          } else {
                                                if (checkCanMove(rectangle, 2, -2) &&
                                                        checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1] != null &&
                                                        (checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() - 1].isWhite() !=
                                                                checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite())) {
                                                      if (isLongAttack) {
                                                            if ((int) chosenRect.getY() == longAttackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAttackFrom.getX()) {
                                                                  capture(rectangle, 1, -1);
                                                            }
                                                      } else {
                                                            capture(rectangle, 1, -1);
                                                      }
                                                }
                                                if (checkCanMove(rectangle, -2, -2) &&
                                                        checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() - 1] != null &&
                                                        (checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() - 1].isWhite() !=
                                                                checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite())) {
                                                      if (isLongAttack) {
                                                            if ((int) chosenRect.getY() == longAttackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAttackFrom.getX()) {
                                                                  capture(rectangle, -1, -1);
                                                            }
                                                      } else {
                                                            capture(rectangle, -1, -1);
                                                      }
                                                }
                                                if (checkCanMove(rectangle, 2, 2) &&
                                                        checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() + 1] != null &&
                                                        (checkers[(int) chosenRect.getX() + 1][(int) chosenRect.getY() + 1].isWhite() !=
                                                                checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite())) {
                                                      if (isLongAttack) {
                                                            if ((int) chosenRect.getY() == longAttackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAttackFrom.getX()) {
                                                                  capture(rectangle, 1, 1);
                                                            }
                                                      } else {
                                                            capture(rectangle, 1, 1);
                                                      }
                                                }
                                                if (checkCanMove(rectangle, -2, 2) &&
                                                        checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() + 1] != null &&
                                                        (checkers[(int) chosenRect.getX() - 1][(int) chosenRect.getY() + 1].isWhite() !=
                                                                checkers[(int) chosenRect.getX()][(int) chosenRect.getY()].isWhite())) {
                                                      if (isLongAttack) {
                                                            if ((int) chosenRect.getY() == longAttackFrom.getY() &&
                                                                    (int) chosenRect.getX() == longAttackFrom.getX()) {
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
            gridPane.add(label, 8, 8);
      }

      private void addChecker(int x, int y) {
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

      @Override
      public void start(Stage primaryStage) {
            createBoard();
            addCheckers();
            Scene scene = new Scene(gridPane, 600, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Italian Checkers Game");
            primaryStage.show();
      }

      private void move(Rectangle rectangle) {
            if (!isLongAttack) {
                  System.out.println("Move " + checkers[(int) rectangle.getX()][(int) rectangle.getY()]);
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
                        //isWhiteTurn = !isWhiteTurn;
                        if (isWhiteTurn) {
                              label.setText("Ход белых");
                        } else {
                              label.setText("Ход черных");
                        }
                  }
            }
            int i = checkWinner();
            switch (i) {
                  case (1):
                        label.setText("Белые победили");
                        break;
                  case (2):
                        label.setText("Черные победили");
                        break;
                  case (3):
                        label.setText("Ничья");
                        break;
            }
            if (i == 0) {
                  botMove();
            }
      }

      private void capture(Rectangle rectangle, int changeX, int changeY) {
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

                  if (isLongAttackAfterCapture(rectangle)) {
                        isLongAttack = false;
                        //isWhiteTurn = !isWhiteTurn;
                        if (isWhiteTurn) {
                              label.setText("Ход белых");
                        } else {
                              label.setText("Ход черных");
                        }
                  }
            }
            int i = checkWinner();
            switch (i) {
                  case (1):
                        label.setText("Белые победили");
                        break;
                  case (2):
                        label.setText("Черные победили");
                        break;
                  case (3):
                        label.setText("Ничья");
                        break;
            }
            if (i == 0 && !isLongAttack) {
                  botMove();
            }
      }

      private boolean isLongAttackAfterCapture(Rectangle rectangle) {
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag4 = false;
            Checker checker = checkers[(int) rectangle.getX()][(int) rectangle.getY()];
            if (checker.isQueen()) {
                  if (rectangle.getY() + 2 < 8) {
                        if (rectangle.getX() + 2 < 8) {
                              flag1 = longAttack(rectangle, 1, 1);
                        }
                        if (rectangle.getX() - 2 > -1) {
                              flag2 = longAttack(rectangle, -1, 1);
                        }
                  }
                  if (rectangle.getY() - 2 > -1) {
                        if (rectangle.getX() + 2 < 8) {
                              flag3 = longAttack(rectangle, 1, -1);
                        }
                        if (rectangle.getX() - 2 > -1) {
                              flag4 = longAttack(rectangle, -1, -1);
                        }
                  }
            } else {
                  if (checker.isWhite()) {
                        if (rectangle.getY() - 2 > -1) {
                              if (rectangle.getX() + 2 < 8) {
                                    flag3 = longAttack(rectangle, 1, -1);
                              }
                              if (rectangle.getX() - 2 > -1) {
                                    flag4 = longAttack(rectangle, -1, -1);
                              }
                        }
                  } else {
                        if (rectangle.getY() + 2 < 8) {
                              if (rectangle.getX() + 2 < 8) {
                                    flag1 = longAttack(rectangle, 1, 1);
                              }
                              if (rectangle.getX() - 2 > -1) {
                                    flag2 = longAttack(rectangle, -1, 1);
                              }
                        }
                  }
            }
            return !flag1 && !flag2 && !flag3 && !flag4;
      }

      private boolean checkCanMove(Rectangle rectangle, int changeX, int changeY) {
            return chosenRect.getY() + changeY == rectangle.getY() && chosenRect.getX() + changeX == rectangle.getX();
      }

      private boolean longAttack(Rectangle rectangle, int changeX, int changeY) {
            if (checkers[(int) rectangle.getX() + (changeX * 2)][(int) rectangle.getY() + (changeY * 2)] == null &&
                    checkers[(int) rectangle.getX() + changeX][(int) rectangle.getY() + changeY] != null &&
                    (checkers[(int) rectangle.getX() + changeX][(int) rectangle.getY() + changeY].isWhite() !=
                            checkers[(int) rectangle.getX()][(int) rectangle.getY()].isWhite())) {
                  isLongAttack = true;
                  longAttackFrom.setXY((int) rectangle.getX(), (int) rectangle.getY());
                  return true;
            } else {
                  return false;
            }
      }

      private int checkWinner() {
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
            if (white == 0) {
                  return 3;
            }
            return 0;
      }

      private boolean isCanCapture(Checker checker) {
            if (checker.isQueen()) {
                  if (checker.getY() + 2 < 8) {
                        if (checker.getX() + 2 < 8) {
                              return checkers[checker.getX() + 1][checker.getY() + 1] != null &&
                                      checkers[checker.getX() + 1][checker.getY() + 1].isWhite() != checker.isWhite() &&
                                      checkers[checker.getX() + 2][checker.getY() + 2] == null;

                        }
                        if (checker.getX() - 2 > -1) {
                              return checkers[checker.getX() - 1][checker.getY() + 1] != null &&
                                      checkers[checker.getX() - 1][checker.getY() + 1].isWhite() != checker.isWhite() &&
                                      checkers[checker.getX() - 2][checker.getY() + 2] == null;
                        }
                  }
                  if (checker.getY() - 2 > -1) {
                        if (checker.getX() + 2 < 8) {
                              return checkers[checker.getX() + 1][checker.getY() - 1] != null &&
                                      checkers[checker.getX() + 1][checker.getY() - 1].isWhite() != checker.isWhite() &&
                                      checkers[checker.getX() + 2][checker.getY() - 2] == null;
                        }
                        if (checker.getX() - 2 > -1) {
                              return checkers[checker.getX() - 1][checker.getY() - 1] != null &&
                                      checkers[checker.getX() - 1][checker.getY() - 1].isWhite() != checker.isWhite() &&
                                      checkers[checker.getX() - 2][checker.getY() - 2] == null;
                        }
                  }
            } else {
                  if (checker.isWhite()) {
                        if (checker.getY() - 2 > -1) {
                              if (checker.getX() + 2 < 8) {
                                    return checkers[checker.getX() + 1][checker.getY() - 1] != null &&
                                            checkers[checker.getX() + 1][checker.getY() - 1].isWhite() != checker.isWhite() &&
                                            checkers[checker.getX() + 2][checker.getY() - 2] == null;
                              }
                              if (checker.getX() - 2 > -1) {
                                    return checkers[checker.getX() - 1][checker.getY() - 1] != null &&
                                            checkers[checker.getX() - 1][checker.getY() - 1].isWhite() != checker.isWhite() &&
                                            checkers[checker.getX() - 2][checker.getY() - 2] == null;
                              }
                        }
                  } else {
                        if (checker.getY() + 2 < 8) {
                              if (checker.getX() + 2 < 8) {
                                    return checkers[checker.getX() + 1][checker.getY() + 1] != null &&
                                            checkers[checker.getX() + 1][checker.getY() + 1].isWhite() != checker.isWhite() &&
                                            checkers[checker.getX() + 2][checker.getY() + 2] == null;
                              }
                              if (checker.getX() - 2 > -1) {
                                    return checkers[checker.getX() - 1][checker.getY() + 1] != null &&
                                            checkers[checker.getX() - 1][checker.getY() + 1].isWhite() != checker.isWhite() &&
                                            checkers[checker.getX() - 2][checker.getY() + 2] == null;
                              }
                        }
                  }
            }
            return false;
      }

      private boolean isCanMove(Checker checker) {
            if (checker.isQueen()) {
                  if (checker.getY() + 1 < 8) {
                        if (checker.getX() + 1 < 8) {
                              return checkers[checker.getX() + 1][checker.getY() + 1] == null;
                        }
                        if (checker.getX() - 1 > -1) {
                              return checkers[checker.getX() - 1][checker.getY() + 1] == null;
                        }
                  }
                  if (checker.getY() - 1 > -1) {
                        if (checker.getX() + 1 < 8) {
                              return checkers[checker.getX() + 1][checker.getY() - 1] == null;
                        }
                        if (checker.getX() - 1 > -1) {
                              return checkers[checker.getX() - 1][checker.getY() - 1] == null;
                        }
                  }
            } else {
                  if (checker.isWhite()) {
                        if (checker.getY() - 1 > -1) {
                              if (checker.getX() + 1 < 8) {
                                    return checkers[checker.getX() + 1][checker.getY() - 1] == null;
                              }
                              if (checker.getX() - 1 > -1) {
                                    return checkers[checker.getX() - 1][checker.getY() - 1] == null;
                              }
                        }
                  } else {
                        if (checker.getY() + 1 < 8) {
                              if (checker.getX() + 1 < 8) {
                                    return checkers[checker.getX() + 1][checker.getY() + 1] == null;
                              }
                              if (checker.getX() - 1 > -1) {
                                    return checkers[checker.getX() - 1][checker.getY() + 1] == null;
                              }
                        }
                  }
            }
            return false;
      }

      private void addCheckers() {
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

      private void botMove() {
            ArrayList<Pair<Checker, Checker>> moveList = new ArrayList<>();
            ArrayList<Pair<Checker, Checker>> attackList = new ArrayList<>();
            for (int row = 0; row < 8; row++) {
                  for (int col = 0; col < 8; col++) {
                        Checker checker = checkers[row][col];
                        if (checker != null && !checker.isWhite()) {
                              if (checker.isQueen()) {
                                    if (checker.getY() + 1 < 8) {
                                          if (checker.getX() + 1 < 8 && checkers[checker.getX() + 1][checker.getY() + 1] == null) {
                                                moveList.add(new Pair<>(checker, new Checker(checker.getX() + 1, checker.getY() + 1, checker.isWhite(), checker.isQueen())));
                                          }
                                          if (checker.getX() - 1 > -1 && checkers[checker.getX() - 1][checker.getY() + 1] == null) {
                                                moveList.add(new Pair<>(checker, new Checker(checker.getX() - 1, checker.getY() + 1, checker.isWhite(), checker.isQueen())));
                                          }
                                    }
                                    if (checker.getY() - 1 > -1) {
                                          if (checker.getX() + 1 < 8 && checkers[checker.getX() + 1][checker.getY() - 1] == null) {
                                                moveList.add(new Pair<>(checker, new Checker(checker.getX() + 1, checker.getY() - 1, checker.isWhite(), checker.isQueen())));
                                          }
                                          if (checker.getX() - 1 > -1 && checkers[checker.getX() - 1][checker.getY() - 1] == null) {
                                                moveList.add(new Pair<>(checker, new Checker(checker.getX() - 1, checker.getY() - 1, checker.isWhite(), checker.isQueen())));
                                          }
                                    }
                              } else {
                                    if (checker.isWhite()) {
                                          if (checker.getY() - 1 > -1) {
                                                if (checker.getX() + 1 < 8 && checkers[checker.getX() + 1][checker.getY() - 1] == null) {
                                                      moveList.add(new Pair<>(checker, new Checker(checker.getX() + 1, checker.getY() - 1, checker.isWhite(), checker.isQueen())));
                                                }
                                                if (checker.getX() - 1 > -1 && checkers[checker.getX() - 1][checker.getY() - 1] == null) {
                                                      moveList.add(new Pair<>(checker, new Checker(checker.getX() - 1, checker.getY() - 1, checker.isWhite(), checker.isQueen())));
                                                }
                                          }
                                    } else {
                                          if (checker.getY() + 1 < 8) {
                                                if (checker.getX() + 1 < 8 && checkers[checker.getX() + 1][checker.getY() + 1] == null) {
                                                      moveList.add(new Pair<>(checker, new Checker(checker.getX() + 1, checker.getY() + 1, checker.isWhite(), checker.isQueen())));
                                                }
                                                if (checker.getX() - 1 > -1 && checkers[checker.getX() - 1][checker.getY() + 1] == null) {
                                                      moveList.add(new Pair<>(checker, new Checker(checker.getX() - 1, checker.getY() + 1, checker.isWhite(), checker.isQueen())));
                                                }
                                          }
                                    }
                              }
                              if (checker.isQueen()) {
                                    if (checker.getY() + 2 < 8) {
                                          if (checker.getX() + 2 < 8 && checkers[checker.getX() + 2][checker.getY() + 2] == null &&
                                                  checkers[checker.getX() + 1][checker.getY() + 1] != null && checkers[checker.getX() + 1][checker.getY() + 1].isWhite() != checker.isWhite()) {
                                                attackList.add(new Pair<>(checker, new Checker(checker.getX() + 2, checker.getY() + 2, checker.isWhite(), checker.isQueen())));
                                          }
                                          if (checker.getX() - 2 > -1 && checkers[checker.getX() - 2][checker.getY() + 2] == null &&
                                                  checkers[checker.getX() - 1][checker.getY() + 1] != null && checkers[checker.getX() - 1][checker.getY() + 1].isWhite() != checker.isWhite()) {
                                                attackList.add(new Pair<>(checker, new Checker(checker.getX() - 2, checker.getY() + 2, checker.isWhite(), checker.isQueen())));
                                          }
                                    }
                                    if (checker.getY() - 2 > -1) {
                                          if (checker.getX() + 2 < 8 && checkers[checker.getX() + 2][checker.getY() - 2] == null &&
                                                  checkers[checker.getX() + 1][checker.getY() - 1] != null && checkers[checker.getX() + 1][checker.getY() - 1].isWhite() != checker.isWhite()) {
                                                attackList.add(new Pair<>(checker, new Checker(checker.getX() + 2, checker.getY() - 2, checker.isWhite(), checker.isQueen())));
                                          }
                                          if (checker.getX() - 2 > -1 && checkers[checker.getX() - 2][checker.getY() - 2] == null &&
                                                  checkers[checker.getX() - 1][checker.getY() - 1] != null && checkers[checker.getX() - 1][checker.getY() - 1].isWhite() != checker.isWhite()) {
                                                attackList.add(new Pair<>(checker, new Checker(checker.getX() - 2, checker.getY() - 2, checker.isWhite(), checker.isQueen())));
                                          }
                                    }
                              } else {
                                    if (checker.isWhite()) {
                                          if (checker.getY() - 2 > -1) {
                                                if (checker.getX() + 2 < 8 && checkers[checker.getX() + 2][checker.getY() - 2] == null &&
                                                        checkers[checker.getX() + 1][checker.getY() - 1] != null && checkers[checker.getX() + 1][checker.getY() - 1].isWhite() != checker.isWhite()) {
                                                      attackList.add(new Pair<>(checker, new Checker(checker.getX() + 2, checker.getY() - 2, checker.isWhite(), checker.isQueen())));
                                                }
                                                if (checker.getX() - 2 > -1 && checkers[checker.getX() - 2][checker.getY() - 2] == null &&
                                                        checkers[checker.getX() - 1][checker.getY() - 1] != null && checkers[checker.getX() - 1][checker.getY() - 1].isWhite() != checker.isWhite()) {
                                                      attackList.add(new Pair<>(checker, new Checker(checker.getX() - 2, checker.getY() - 2, checker.isWhite(), checker.isQueen())));
                                                }
                                          }
                                    } else {
                                          if (checker.getY() + 2 < 8) {
                                                if (checker.getX() + 2 < 8 && checkers[checker.getX() + 2][checker.getY() + 2] == null &&
                                                        checkers[checker.getX() + 1][checker.getY() + 1] != null && checkers[checker.getX() + 1][checker.getY() + 1].isWhite() != checker.isWhite()) {
                                                      attackList.add(new Pair<>(checker, new Checker(checker.getX() + 2, checker.getY() + 2, checker.isWhite(), checker.isQueen())));
                                                }
                                                if (checker.getX() - 2 > -1 && checkers[checker.getX() - 2][checker.getY() + 2] == null &&
                                                        checkers[checker.getX() - 1][checker.getY() + 1] != null && checkers[checker.getX() - 1][checker.getY() + 1].isWhite() != checker.isWhite()) {
                                                      attackList.add(new Pair<>(checker, new Checker(checker.getX() - 2, checker.getY() + 2, checker.isWhite(), checker.isQueen())));
                                                }
                                          }
                                    }
                              }
                        }
                  }
            }
            if (attackList.size() != 0) {
                  int i = random.nextInt(attackList.size());
                  Pair<Checker, Checker> pair = attackList.get(i);
                  int changeX = (pair.getValue().getX() - pair.getKey().getX()) / 2;
                  int changeY = (pair.getValue().getY() - pair.getKey().getY()) / 2;
                  gridPane.getChildren().remove(rectangles[pair.getKey().getX()][pair.getKey().getY()]);
                  gridPane.getChildren().remove(rectangles[pair.getKey().getX() + changeX][pair.getKey().getY() + changeY]);
                  rectangles[pair.getValue().getX()][pair.getValue().getY()] = rectangles[pair.getKey().getX()][pair.getKey().getY()];
                  rectangles[pair.getKey().getX()][pair.getKey().getY()] = null;
                  rectangles[pair.getKey().getX() + changeX][pair.getKey().getY() + changeY] = null;
                  System.out.println("Bot " + rectangles[pair.getValue().getX()][pair.getValue().getY()]);
                  rectangles[pair.getValue().getX()][pair.getValue().getY()].setX(rectangles[pair.getValue().getX()][pair.getValue().getY()].getX() + changeX);
                  rectangles[pair.getValue().getX()][pair.getValue().getY()].setY(rectangles[pair.getValue().getX()][pair.getValue().getY()].getY() + changeY);
                  gridPane.add(rectangles[pair.getValue().getX()][pair.getValue().getY()], pair.getValue().getX(), pair.getValue().getY());
                  checkers[pair.getValue().getX()][pair.getValue().getY()] = new Checker(pair.getValue().getX(), pair.getValue().getY(),
                          checkers[pair.getKey().getX()][pair.getKey().getY()].isWhite(), checkers[pair.getKey().getX()][pair.getKey().getY()].isQueen());
                  checkers[pair.getKey().getX()][pair.getKey().getY()] = null;
                  checkers[pair.getValue().getX()][pair.getValue().getY()].setXY(checkers[pair.getValue().getX()][pair.getValue().getY()].getX() + changeX,
                          checkers[pair.getValue().getX()][pair.getValue().getY()].getY() + changeY);
                  checkers[pair.getKey().getX() + changeX][pair.getKey().getY() + changeY] = null;
                  if (rectangles[pair.getValue().getX()][pair.getValue().getY()].getY() == 7) {
                        gridPane.getChildren().remove(rectangles[pair.getValue().getX()][pair.getValue().getY()]);
                        rectangles[pair.getValue().getX()][pair.getValue().getY()] = null;
                        checkers[pair.getValue().getX()][pair.getValue().getY()] = null;
                        Image img = new Image(Objects.requireNonNull(this.getClass().getResource("/qBlack.png")).toExternalForm());
                        int x = pair.getValue().getX();
                        int y = pair.getValue().getY();
                        Rectangle rect = new Rectangle();
                        rect.setX(x);
                        rect.setY(y);
                        rect.setWidth(size);
                        rect.setHeight(size);
                        rect.setFill(new ImagePattern(img));
                        rectangles[x][y] = rect;
                        gridPane.add(rectangles[x][y], x, y);
                  }
            } else {
                  int i = random.nextInt(moveList.size());
                  Pair<Checker, Checker> pair = moveList.get(i);
                  rectangles[pair.getValue().getX()][pair.getValue().getY()] = rectangles[pair.getKey().getX()][pair.getKey().getY()];
                  //TODO fix nullPointer
                  //TODO choose black or white
                  System.out.println("Botmove " + rectangles[pair.getValue().getX()][pair.getValue().getY()]);
                  rectangles[pair.getValue().getX()][pair.getValue().getY()].setX(pair.getValue().getX());
                  rectangles[pair.getValue().getX()][pair.getValue().getY()].setY(pair.getValue().getY());
                  gridPane.getChildren().remove(rectangles[pair.getKey().getX()][pair.getKey().getY()]);
                  rectangles[pair.getKey().getX()][pair.getKey().getY()] = null;
                  gridPane.add(rectangles[pair.getValue().getX()][pair.getValue().getY()], pair.getValue().getX(), pair.getValue().getY());
                  checkers[pair.getValue().getX()][pair.getValue().getY()] = new Checker(pair.getValue().getX(), pair.getValue().getY(),
                          checkers[pair.getKey().getX()][pair.getKey().getY()].isWhite(), checkers[pair.getKey().getX()][pair.getKey().getY()].isQueen());
                  checkers[pair.getKey().getX()][pair.getKey().getY()] = null;
                  if (rectangles[pair.getValue().getX()][pair.getValue().getY()].getY() == 7) {
                        gridPane.getChildren().remove(rectangles[pair.getValue().getX()][pair.getValue().getY()]);
                        rectangles[pair.getValue().getX()][pair.getValue().getY()] = null;
                        checkers[pair.getValue().getX()][pair.getValue().getY()] = null;
                        Image img = new Image(Objects.requireNonNull(this.getClass().getResource("/qBlack.png")).toExternalForm());
                        int x = pair.getValue().getX();
                        int y = pair.getValue().getY();
                        Rectangle rect = new Rectangle();
                        rect.setX(x);
                        rect.setY(y);
                        rect.setWidth(size);
                        rect.setHeight(size);
                        rect.setFill(new ImagePattern(img));
                        rectangles[x][y] = rect;
                        gridPane.add(rectangles[x][y], x, y);
                  }
            }
      }
}