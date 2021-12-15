package ru.kpfu.itis.vagaviev;

import javafx.util.Pair;
import ru.kpfu.itis.vagaviev.view.models.Checker;

import java.util.ArrayList;
import java.util.Random;

public class Bot {
      boolean isWhite;
      Checker[][] checkers;
      Checker moveTo = new Checker();
      Checker atackTo = new Checker();
      ArrayList<Pair<Checker, Checker>> canMove = new ArrayList<>();
      ArrayList<Pair<Checker, Checker>> canAtack = new ArrayList<>();
      Random random = new Random();

      public Bot(boolean isWhite) {
            this.isWhite = isWhite;
      }

      public Pair<Integer, Pair<Checker, Checker>> move(Checker[][] checkers) {
            this.checkers = checkers;
            Pair<Checker, Checker> pairAtack;
            Pair<Checker, Checker> pairMove;
            for (int row = 0; row < 8; row++) {
                  for (int col = 0; col < 8; col++) {
                        if (checkers[row][col] != null && checkers[row][col].isWhite() == this.isWhite) {
                              System.out.println(checkers[row][col]);
                              if (isCanMove(checkers[row][col])) {
                                    pairMove = new Pair<>(checkers[row][col], moveTo);
                                    System.out.println(checkers[row][col]);
                                    System.out.println(moveTo);
                                    canMove.add(pairMove);
                              }
                              if (isCanCapture(checkers[row][col])) {
                                    pairAtack = new Pair<>(checkers[row][col], atackTo);
                                    canAtack.add(pairAtack);
                              }
                        }
                  }
            }
            if (canMove.size() != 0) {
                  System.out.println(canMove.get(random.nextInt(canMove.size())));
                  return new Pair<>(1, canMove.get(random.nextInt(canMove.size())));
            } else {
                  return null;
            }
      }

      public boolean isCanMove(Checker checker) {
            int x = checker.getX();
            int y = checker.getY();
            if (checker.isQueen()) {
                  if (x + 1 < 8) {
                        if (y + 1 < 8) {
                              if (checkers[x + 1][y + 1] == null) {
                                    moveTo = new Checker(x + 1, y + 1, checker.isWhite(), checker.isQueen());
                                    return true;
                              }
                        }
                        if (y - 1 > -1) {
                              if (checkers[x + 1][y - 1] == null) {
                                    moveTo = new Checker(x + 1, y - 1, checker.isWhite(), checker.isQueen());
                                    return true;
                              }
                        }
                  }
                  if (x - 1 > -1) {
                        if (y + 1 < 8) {
                              if (checkers[x - 1][y + 1] == null) {
                                    moveTo = new Checker(x - 1, y + 1, checker.isWhite(), checker.isQueen());
                                    return true;
                              }
                        }
                        if (y - 1 > -1) {
                              if (checkers[x - 1][y - 1] == null) {
                                    moveTo = new Checker(x - 1, y - 1, checker.isWhite(), checker.isQueen());
                                    return true;
                              }
                        }
                  }
            } else {
                  if (!checker.isWhite()) {
                        if (y + 1 < 8) {
                              if (x + 1 < 8) {
                                    if (checkers[x + 1][y + 1] == null) {
                                          moveTo = new Checker(x + 1, y + 1, checker.isWhite(), checker.isQueen());
                                          return true;
                                    }
                              }
                              if (x - 1 > -1) {
                                    if (checkers[x - 1][y + 1] == null) {
                                          moveTo = new Checker(x - 1, y + 1, checker.isWhite(), checker.isQueen());
                                          return true;
                                    }
                              }
                        }
                  } else {
                        if (y - 1 > -1) {
                              if (x + 1 < 8) {
                                    if (checkers[x + 1][y - 1] == null) {
                                          moveTo = new Checker(x + 1, y - 1, checker.isWhite(), checker.isQueen());
                                          return true;
                                    }
                              }
                              if (x - 1 > -1) {
                                    if (checkers[x - 1][y - 1] == null) {
                                          moveTo = new Checker(x - 1, y - 1, checker.isWhite(), checker.isQueen());
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
                                    atackTo = new Checker(x + 2, y + 2, checker.isWhite(), checker.isQueen());
                                    return true;
                              }
                        }
                        if (y - 2 > -1) {
                              if (checkers[x + 2][y - 2] == null && checkers[x + 1][y - 1] != null && checkers[x + 1][y - 1].isWhite() != checker.isWhite()) {
                                    atackTo = new Checker(x + 2, y - 2, checker.isWhite(), checker.isQueen());
                                    return true;
                              }
                        }
                  }
                  if (x - 2 > -1) {
                        if (y + 2 < 8) {
                              if (checkers[x - 2][y + 2] == null && checkers[x - 1][y + 1] != null && checkers[x - 1][y + 1].isWhite() != checker.isWhite()) {
                                    atackTo = new Checker(x - 2, y + 2, checker.isWhite(), checker.isQueen());
                                    return true;
                              }
                        }
                        if (y - 2 > -1) {
                              if (checkers[x - 2][y - 2] == null && checkers[x - 1][y - 1] != null && checkers[x - 1][y - 1].isWhite() != checker.isWhite()) {
                                    atackTo = new Checker(x - 2, y - 2, checker.isWhite(), checker.isQueen());
                                    return true;
                              }
                        }
                  }
            } else {
                  if (checker.isWhite()) {
                        if (y - 2 > -1) {
                              if (x + 2 < 8) {
                                    if (checkers[x + 2][y - 2] == null && checkers[x + 1][y - 1] != null && checkers[x + 1][y - 1].isWhite() != checker.isWhite()) {
                                          atackTo = new Checker(x + 2, y - 2, checker.isWhite(), checker.isQueen());
                                          return true;
                                    }
                              }
                              if (x - 2 > -1) {
                                    if (checkers[x - 2][y - 2] == null && checkers[x - 1][y - 1] != null && checkers[x - 1][y - 1].isWhite() != checker.isWhite()) {
                                          atackTo = new Checker(x - 2, y - 2, checker.isWhite(), checker.isQueen());
                                          return true;
                                    }
                              }
                        }
                  } else {
                        if (y + 2 > 8) {
                              if (x + 2 < 8) {
                                    if (checkers[x + 2][y + 2] == null && checkers[x + 1][y + 1] != null && checkers[x + 1][y + 1].isWhite() != checker.isWhite()) {
                                          atackTo = new Checker(x + 2, y + 2, checker.isWhite(), checker.isQueen());
                                          return true;
                                    }
                              }
                              if (x - 2 > -1) {
                                    if (checkers[x - 2][y + 2] == null && checkers[x - 1][y + 1] != null && checkers[x - 1][y + 1].isWhite() != checker.isWhite()) {
                                          atackTo = new Checker(x - 2, y + 2, checker.isWhite(), checker.isQueen());
                                          return true;
                                    }
                              }
                        }
                  }
            }
            return false;
      }
}