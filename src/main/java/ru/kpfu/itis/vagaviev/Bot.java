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
      ArrayList<Pair<Checker, Checker>> canMove;
      ArrayList<Pair<Checker, Checker>> canAtack;
      Random random = new Random();

      public Bot(boolean isWhite) {
            this.isWhite = isWhite;
      }

      public Pair<Integer, Pair<Checker, Checker>> move(Checker[][] checkers) {
            canMove = new ArrayList<>();
            canAtack = new ArrayList<>();
            this.checkers = checkers;
            Pair<Checker, Checker> pairAtack;
            Pair<Checker, Checker> pairMove;
            for (int row = 0; row < 8; row++) {
                  for (int col = 0; col < 8; col++) {
                        if (checkers[row][col] != null && checkers[row][col].isWhite() == this.isWhite) {
                              if (isCanMove(checkers[row][col])) {
                                    pairMove = new Pair<>(checkers[row][col], moveTo);
                                    canMove.add(pairMove);
                              }
                              if (isCanCapture(checkers[row][col])) {
                                    System.out.println(22);
                                    pairAtack = new Pair<>(checkers[row][col], atackTo);
                                    canAtack.add(pairAtack);
                              }
                        }
                  }
            }
            if(canAtack.size()!=0){
                  System.out.println(13);
                  return new Pair<>(2, canAtack.get(random.nextInt(canAtack.size())));
            } else {
                  if (canMove.size() != 0) {
                        System.out.println(14);
                        return new Pair<>(1, canMove.get(random.nextInt(canMove.size())));
                  } else {
                        return null;
                  }
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
                        if (y + 2 < 8) {
                              if (x + 2 < 8) {
                                    System.out.println(66);
                                    if (checkers[x + 2][y + 2] == null && checkers[x + 1][y + 1] != null && checkers[x + 1][y + 1].isWhite() != checker.isWhite()) {
                                          atackTo = new Checker(x + 2, y + 2, checker.isWhite(), checker.isQueen());
                                          System.out.println(666);
                                          return true;
                                    }
                              }
                              if (x - 2 > -1) {
                                    System.out.println(x + "   ,,   " + y);
                                    System.out.println(88);
                                    System.out.println(checkers[x - 2][y + 2] == null);
                                    if(checkers[x - 1][y + 1] != null){
                                          System.out.println(checkers[x - 1][y + 1].isWhite() != checker.isWhite());
                                    }
                                    if (checkers[x - 2][y + 2] == null && checkers[x - 1][y + 1] != null && checkers[x - 1][y + 1].isWhite() != checker.isWhite()) {
                                          atackTo = new Checker(x - 2, y + 2, checker.isWhite(), checker.isQueen());
                                          System.out.println(888);
                                          return true;
                                    }
                              }
                        }
                  }
            }
            return false;
      }
}