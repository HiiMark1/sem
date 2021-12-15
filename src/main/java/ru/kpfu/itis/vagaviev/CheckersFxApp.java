package ru.kpfu.itis.vagaviev;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.vagaviev.client.GameClient;
import ru.kpfu.itis.vagaviev.view.BaseView;
import ru.kpfu.itis.vagaviev.view.BoardView;
import ru.kpfu.itis.vagaviev.view.MenuView;

import java.io.IOException;

public class CheckersFxApp extends Application {
      Stage primaryStage;
      private BorderPane rootLayout;

      private GameClient gameClient;
      private BoardView boardView;
      private MenuView menuView;

      public void startGameClient() throws IOException {
            getGameClient().start();
      }

      public void setView(BaseView view) {
            rootLayout.setCenter(view.getView());
      }

      @Override
      public void start(Stage primaryStage) throws Exception {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Italian Checkers Game");
            this.primaryStage.setOnCloseRequest(e-> System.exit(0));

            gameClient = new GameClient(this);

            BaseView.setApplication(this);
            menuView = new MenuView();
            boardView = new BoardView();

            this.initLayout();
      }

      private void initLayout(){
            rootLayout = new BorderPane();

            Scene scene = new Scene(rootLayout, 400, 300);
            primaryStage.setScene(scene);
            primaryStage.show();

            this.setView(getMenuView());
      }

      public static void main(String[] args) {
            launch(args);
      }

      public Stage getPrimaryStage() {
            return primaryStage;
      }

      public void setPrimaryStage(Stage primaryStage) {
            this.primaryStage = primaryStage;
      }

      public BorderPane getRootLayout() {
            return rootLayout;
      }

      public void setRootLayout(BorderPane rootLayout) {
            this.rootLayout = rootLayout;
      }

      public GameClient getGameClient() {
            return gameClient;
      }

      public void setGameClient(GameClient gameClient) {
            this.gameClient = gameClient;
      }

      public BoardView getBoardView() {
            return boardView;
      }

      public void setBoardView(BoardView boardView) {
            this.boardView = boardView;
      }

      public MenuView getMenuView() {
            return menuView;
      }

      public void setMenuView(MenuView menuView) {
            this.menuView = menuView;
      }

      public void sendMessageToGame(String message) {
            boardView.useMessageInGame(message);
      }
}
