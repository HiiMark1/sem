package ru.kpfu.itis.vagaviev.view;

import javafx.scene.Parent;
import ru.kpfu.itis.vagaviev.CheckersFxApp;

public abstract class BaseView {

      private static CheckersFxApp application;

      public abstract Parent getView();

      public static void setApplication(CheckersFxApp application) {
            BaseView.application = application;
      }

      public static CheckersFxApp getApplication() throws Exception {
            if (application!= null) {
                  return application;
            }
            throw new Exception("No Application in BaseView");
      }
}