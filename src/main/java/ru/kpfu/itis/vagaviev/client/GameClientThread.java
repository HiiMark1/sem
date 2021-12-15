package ru.kpfu.itis.vagaviev.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class GameClientThread extends Thread {
      private final BufferedReader input;

      private final BufferedWriter output;

      private final GameClient gameClient;

      public GameClientThread(BufferedReader input, BufferedWriter output, GameClient gameClient) {
            this.input = input;
            this.output = output;
            this.gameClient = gameClient;
      }

      public BufferedReader getInput() {
            return input;
      }

      public BufferedWriter getOutput() {
            return output;
      }

      @Override
      public void run() {
            try {
                  while (true) {
                        String message = input.readLine();
                        gameClient.getApplication().sendMessageToGame(message);
                  }
            } catch (IOException e) {
                  e.printStackTrace();
            }
      }
}
