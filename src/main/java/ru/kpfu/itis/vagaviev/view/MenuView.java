package ru.kpfu.itis.vagaviev.view;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.vagaviev.CheckersFxApp;

import java.io.IOException;

public class MenuView extends BaseView {
      private AnchorPane pane = null;
      private Button singlePlayer;
      private Button multiPlayer;
      private VBox vBox;
      private final CheckersFxApp application = BaseView.getApplication();

      public MenuView() throws Exception {
      }


      @Override
      public Parent getView() {
            if (pane == null) {
                  this.createView();
            }

            return pane;
      }

      private void createView() {
            pane = new AnchorPane();

            vBox = new VBox(10);

            singlePlayer = new Button("SinglePlayer");
            singlePlayer.setOnAction(event -> {
                  application.getBoardView().isSinglePlayer=true;
                  application.setView(application.getBoardView());
            });

            multiPlayer = new Button("MultiPlayer");
            multiPlayer.setOnAction(event -> {
                  try {
                        application.startGameClient();
                  } catch (IOException e) {
                        e.printStackTrace();
                  }
                  application.setView(application.getBoardView());
            });

            vBox.getChildren().addAll(singlePlayer, multiPlayer);

            AnchorPane.setTopAnchor(vBox, 50.0);
            AnchorPane.setLeftAnchor(vBox, 100.0);
            AnchorPane.setRightAnchor(vBox, 100.0);

            pane.getChildren().add(vBox);
      }
}
