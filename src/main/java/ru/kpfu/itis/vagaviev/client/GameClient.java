package ru.kpfu.itis.vagaviev.client;

import ru.kpfu.itis.vagaviev.CheckersFxApp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class GameClient {
      private Socket socket;
      private GameClientThread gameClientThread;

      private final CheckersFxApp application;

      public CheckersFxApp getApplication() {
            return application;
      }

      public GameClient(CheckersFxApp application) {
            this.application = application;
      }

      public void sendMessage(String message) {
            try {
                  gameClientThread.getOutput().write(message);
                  gameClientThread.getOutput().flush();
            } catch (IOException e) {
                  e.printStackTrace();
            }
      }

      public void start() throws IOException {
            socket = new Socket("127.0.0.1", 5555);

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            gameClientThread = new GameClientThread(input, output, this);

            new Thread(gameClientThread).start();
      }
}
