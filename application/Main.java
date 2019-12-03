/*
 * ATEAM PROJECT
 */
package application;

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * ATEAM PROJECT
 *
 */
public class Main extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also
  private List<String> args;

  private static final int WINDOW_WIDTH = 800;
  private static final int WINDOW_HEIGHT = 640;
  private static final String APP_TITLE = "GetSocial";

  @Override
  public void start(Stage primaryStage) throws Exception {
    // save any args passed to the program
    args = this.getParameters().getRaw();

    // place the logo in the top panel
    Image logo = new Image("logo.png");
    ImageView logoView = new ImageView(logo);
    
    // create a picutre for the center panel
    Image portrait = new Image("portrait.png");
    ImageView portraitView = new ImageView(portrait);

    // Create a vertical box containing a label and check boxes for the right panel
    VBox options = new VBox();
    options.setSpacing(16.0);
    // add buttons in a vbox for the options
    Button setCenterButton = new Button("Set Central User");
    setCenterButton.setMinSize(150, 60);
    Button addUserButton = new Button("Add User");
    addUserButton.setMinSize(150, 60);
    Button removeUserButton = new Button("Remove User");
    removeUserButton.setMinSize(150, 60);
    Button addFriendshipButton = new Button("Add Friendship");
    addFriendshipButton.setMinSize(150, 60);
    Button removeFriendshipButton = new Button("Remove Friendship");
    removeFriendshipButton.setMinSize(150, 60);
    Button importNetworkButton = new Button("Import Network");
    importNetworkButton.setMinSize(150, 60);
    Button exportNetworkButton = new Button("Export Network");
    exportNetworkButton.setMinSize(150, 60);
    Button clearNetworkButton = new Button("Clear Network");
    clearNetworkButton.setMinSize(150, 60);
    options.getChildren().add(setCenterButton);
    options.getChildren().add(addUserButton);
    options.getChildren().add(removeUserButton);
    options.getChildren().add(addFriendshipButton);
    options.getChildren().add(removeFriendshipButton);
    options.getChildren().add(addFriendshipButton);
    options.getChildren().add(removeFriendshipButton);
    options.getChildren().add(importNetworkButton);
    options.getChildren().add(exportNetworkButton);
    options.getChildren().add(clearNetworkButton);

    // create an hbox of buttons for the bottom panel
    HBox algorithmOptions = new HBox();
    algorithmOptions.setSpacing(32.0);
    Button mutualFriendsButton = new Button("Mutual Friends...");
    mutualFriendsButton.setMinSize(150, 60);
    Button shortestPathButton = new Button("Shortest Path...");
    shortestPathButton.setMinSize(150, 60);
    Button statusUpdatesButton = new Button("Status Updates");
    statusUpdatesButton.setMinSize(150, 60);
    algorithmOptions.getChildren().add(mutualFriendsButton);
    algorithmOptions.getChildren().add(shortestPathButton);
    algorithmOptions.getChildren().add(statusUpdatesButton);

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Set the elements to their respective panels
    root.setTop(logoView);
    root.setLeft(portraitView);
    root.setCenter(options);
    root.setBottom(algorithmOptions);
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
