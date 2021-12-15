package ru.kpfu.itis.vagaviev.server;

import ru.kpfu.itis.vagaviev.client.GameClientThread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.SocketException;

public class GameServerThread extends Thread {
      private final BufferedReader input;

      private final BufferedWriter output;

      private final GameServer gameServer;

      public GameServerThread(BufferedReader input, BufferedWriter output, GameServer gameServer) {
            this.input = input;
            this.output = output;
            this.gameServer = gameServer;
      }

      public BufferedReader getInput() {
            return input;
      }

      public BufferedWriter getOutput() {
            return output;
      }

      public GameServer getServer() {
            return gameServer;
      }


      @Override
      public void run() {
            try {
                  while (true) {
                        String message = input.readLine();
                        gameServer.sendMessage(message, this);
                  }
            } catch (SocketException socketException) {
                  gameServer.removeClient(this);
            } catch (IOException e) {
                  e.printStackTrace();
            }
      }
}
