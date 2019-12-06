/*
 * ATEAM GUI
 */
package application;
import java.io.File;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
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
  private static final String APP_TITLE = "GetSocial - Social Network Visaulizer";
  @Override
  public void start(Stage primaryStage) throws Exception {
	// save any args passed to the program
	args = this.getParameters().getRaw();
	// place the logo in the top panel
	Image logo = new Image("logo.png");
	ImageView logoView = new ImageView(logo);
	
	// create a picutre for the center panel
	//Image portrait = new Image("portrait.png");
	//ImageView portraitView = new ImageView(portrait);
	NetworkGraph graph = new NetworkGraph();
	VBox network = graph.visualizeGraph();
	
	// Create a vertical box containing a label and check boxes for the right panel
	VBox options = new VBox();
	options.setSpacing(12.0);
	// add buttons in a vbox for the options
	
	Button setCenterButton = new Button("Set Central User");
	setCenterButton.setMinSize(150, 50);
	setCenterButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			setCenter();
         }
     });
	
	
	Button addUserButton = new Button("Add User");
	addUserButton.setMinSize(150, 50);
	addUserButton.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
         public void handle(ActionEvent event) {
			 addUser();
           }
       });
	
	Button removeUserButton = new Button("Remove User");
	removeUserButton.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
         public void handle(ActionEvent event) {
			 removeUser();
           }
       });
	removeUserButton.setMinSize(150, 50);
	
	Button addFriendshipButton = new Button("Add Friendship");
	addFriendshipButton.setMinSize(150, 50);
	addFriendshipButton.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
        public void handle(ActionEvent event) {
			 addFriend();
          }
      });
	
	Button removeFriendshipButton = new Button("Remove Friendship");
	removeFriendshipButton.setMinSize(150, 50);
	removeFriendshipButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			removeFriend();
         }
     });
	
	Button importNetworkButton = new Button("Import Network");
	importNetworkButton.setMinSize(150, 50);
	final FileChooser fileChooser = new FileChooser();
	importNetworkButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(final ActionEvent e) {
			File file = fileChooser.showOpenDialog(primaryStage);
	                   
	                }
	            });
	
	Button exportNetworkButton = new Button("Export Network");
	exportNetworkButton.setMinSize(150, 50);
	exportNetworkButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(final ActionEvent e) {
			File file = fileChooser.showOpenDialog(primaryStage);
	                   
	                }
	            });
	
	Button clearNetworkButton = new Button("Clear Network");
	clearNetworkButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			clearAll();
         }
     });
	clearNetworkButton.setMinSize(150, 50);
	
	// add the buttons on the right panel
	options.getChildren().add(setCenterButton);
	options.getChildren().add(addUserButton);
	options.getChildren().add(removeUserButton);
	options.getChildren().add(addFriendshipButton);
	options.getChildren().add(removeFriendshipButton);
	options.getChildren().add(importNetworkButton);
	options.getChildren().add(exportNetworkButton);
	options.getChildren().add(clearNetworkButton);
	
	
	// create an hbox of buttons for the bottom panel
	HBox algorithmOptions = new HBox();
	algorithmOptions.setSpacing(22.0);
	Button mutualFriendsButton = new Button("Find Mutual Friends");
	mutualFriendsButton.setMinSize(140, 50);
	mutualFriendsButton.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
       public void handle(ActionEvent event) {
			 mutualFriend();
         }
     });
	
	Button shortestPathButton = new Button("Find Shortest Path");
	shortestPathButton.setMinSize(120, 50);
	shortestPathButton.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
       public void handle(ActionEvent event) {
			 shortestPath();
         }
     });
	
	Button statusUpdatesButton = new Button("Status Updates");
	statusUpdatesButton.setMinSize(130, 50);
	statusUpdatesButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			updates();
         }
     });
	
	Button connectedComponentButton = new Button("Connected Components");
	connectedComponentButton.setMinSize(150, 50);
	connectedComponentButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			connected();
         }
     });
	
	Button closeButton = new Button("Exit");
	closeButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			close();
         }
     });
	closeButton.setMinSize(150, 50);
	
	        
	algorithmOptions.getChildren().add(mutualFriendsButton);
	algorithmOptions.getChildren().add(shortestPathButton);
	algorithmOptions.getChildren().add(statusUpdatesButton);
	algorithmOptions.getChildren().add(connectedComponentButton);
	algorithmOptions.getChildren().add(closeButton);
	// Main layout is Border Pane example (top,left,center,right,bottom)
	BorderPane root = new BorderPane();
	root.setPadding(new Insets(10));
	Button centralUserButton = new Button(graph.getCentralUser());
	centralUserButton.setMinHeight(100);
	// Use VBoxes for aesthetic spacing purposes on left panel
	VBox leftBox = new VBox();
	leftBox.setSpacing(160.0);
	leftBox.getChildren().add(new Label(""));
	leftBox.getChildren().add(centralUserButton);
	// Use VBoxes for aesthetic spacing purposes on center panel
	VBox center = new VBox();
	center.setSpacing(160.0);
	center.getChildren().add(new Label(""));
	center.getChildren().add(network);
	// Set the elements to their respective panels
	root.setTop(logoView);
	root.setLeft(leftBox);
	root.setCenter(center);
	root.setRight(options);
	root.setBottom(algorithmOptions);
	Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	// Set the primary stage
	primaryStage.setTitle(APP_TITLE);
	primaryStage.setScene(mainScene);
	primaryStage.show();
  }
  
  private void setCenter() { 
      TextInputDialog dialog = new TextInputDialog("a-z,0-9,underscore and apostrophe only");
      dialog.setTitle("Set Center User");
      dialog.setHeaderText("Enter center username:");
      dialog.setContentText("Name:");
      Optional<String> result = dialog.showAndWait();
  }
  
  
  private void addUser() { 
      TextInputDialog dialog = new TextInputDialog("a-z,0-9,underscore and apostrophe only");
      dialog.setTitle("Add User");
      dialog.setHeaderText("Enter your new user name:");
      dialog.setContentText("Name:");
      Optional<String> result = dialog.showAndWait();
  }
  
  private void removeUser() { 
      TextInputDialog dialog = new TextInputDialog("a-z,0-9,underscore and apostrophe only");
      dialog.setTitle("Remove User");
      dialog.setHeaderText("Enter your username you want to remove:");
      dialog.setContentText("Name:");
      Optional<String> result = dialog.showAndWait();
  }
  

	private void addFriend() {
		// Create the custom dialog.
	    Dialog<Pair<String, String>> dialog = new Dialog<>();
	    dialog.setTitle("Add Friendship");
	    dialog.setHeaderText("Enter the usernames to create a friendship between:");

	    // Set the button types.
	    ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
	    dialog.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);

	    GridPane gridPane = new GridPane();
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);
	    gridPane.setPadding(new Insets(20, 150, 10, 10));

	    TextField from = new TextField();
	    from.setPromptText("User 1");
	    TextField to = new TextField();
	    to.setPromptText("User 2");

	    gridPane.add(from, 0, 0);
	    gridPane.add(new Label("And"), 1, 0);
	    gridPane.add(to, 2, 0);

	    dialog.getDialogPane().setContent(gridPane);

	    // Request focus on the username field by default.
	    Platform.runLater(() -> from.requestFocus());

	    // Convert the result to a username-password-pair when the login button is clicked.
	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == ok) {
	            return new Pair<>(from.getText(), to.getText());
	        }
	        return null;
	    });

	    Optional<Pair<String, String>> result = dialog.showAndWait();

	    result.ifPresent(pair -> {
	        System.out.println("From=" + pair.getKey() + ", To=" + pair.getValue());
	    });
	}
	

	private void removeFriend() {
		// Create the custom dialog.
	    Dialog<Pair<String, String>> dialog = new Dialog<>();
	    dialog.setTitle("Remove Friendship");
	    dialog.setHeaderText("Enter the usernames to remove the friendship between:");

	    // Set the button types.
	    ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
	    dialog.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);

	    GridPane gridPane = new GridPane();
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);
	    gridPane.setPadding(new Insets(20, 150, 10, 10));

	    TextField from = new TextField();
	    from.setPromptText("User 1");
	    TextField to = new TextField();
	    to.setPromptText("User 2");

	    gridPane.add(from, 0, 0);
	    gridPane.add(new Label("And"), 1, 0);
	    gridPane.add(to, 2, 0);

	    dialog.getDialogPane().setContent(gridPane);

	    // Request focus on the username field by default.
	    Platform.runLater(() -> from.requestFocus());

	    // Convert the result to a username-password-pair when the login button is clicked.
	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == ok) {
	            return new Pair<>(from.getText(), to.getText());
	        }
	        return null;
	    });

	    Optional<Pair<String, String>> result = dialog.showAndWait();

	    result.ifPresent(pair -> {
	    });
	}
	
	private void clearAll() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Clear All");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to clear the network?");

		Optional<ButtonType> result = alert.showAndWait();
	}
	
	private void importNetwork() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
	//	fileChooser.showOpenDialog(stage);
	}
	
	private void exportNetwork() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
	//	fileChooser.showOpenDialog(stage);
	}
	
	private void updates() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Status Updates");
		alert.setHeaderText("Here is a list of changes you have made");
		alert.setContentText("a deb\r\n" + 
				"r brian\r\n" + 
				"a sapan\r\n" + 
				"r deb\r\n" + 
				"a brian\r\n" + 
				"s brian\r\n" + 
				"");

		alert.showAndWait();
	}
	
	private void close() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to exit?");
		ButtonType saveButton = new ButtonType("Save");
		ButtonType exitButton = new ButtonType("Exit without save");
		ButtonType cancelButton = new ButtonType("Cancel");
		alert.getButtonTypes().setAll(saveButton,exitButton,cancelButton);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == saveButton){
		    confirmSave();
		} else if (result.get() == exitButton) {
			confirmExit();
		}
	}
	
	private void confirmExit() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit without save");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to exit without save?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    goodbye();
		} 
	}
	
	private void confirmSave() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Save");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to save and exit?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    save();
		} 
	}
	
	private void save() {
		TextInputDialog dialog = new TextInputDialog("File Name");
		dialog.setTitle("Save");
		dialog.setHeaderText("Save");
		dialog.setContentText("Enter file name:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    saved();
		}
	}
	
	private void saved() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Goodbye");
		alert.setHeaderText(null);
		alert.setContentText("Changes have been saved. Goodbye!");
		alert.showAndWait();
		Platform.exit();
	}
	
	private void goodbye() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Goodbye");
		alert.setHeaderText(null);
		alert.setContentText("Goodbye!");
		alert.showAndWait();
		Platform.exit();
	}
	
	private void mutualFriend() {
		// Create the custom dialog.
	    Dialog<Pair<String, String>> dialog = new Dialog<>();
	    dialog.setTitle("Mutual Friends");
	    dialog.setHeaderText("Enter the usernames to look for mutual friends:");

	    // Set the button types.
	    ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
	    dialog.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);

	    GridPane gridPane = new GridPane();
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);
	    gridPane.setPadding(new Insets(20, 150, 10, 10));

	    TextField from = new TextField();
	    from.setPromptText("User 1");
	    TextField to = new TextField();
	    to.setPromptText("User 2");

	    gridPane.add(from, 0, 0);
	    gridPane.add(new Label("And"), 1, 0);
	    gridPane.add(to, 2, 0);

	    dialog.getDialogPane().setContent(gridPane);

	    // Request focus on the username field by default.
	    Platform.runLater(() -> from.requestFocus());

	    // Convert the result to a username-password-pair when the login button is clicked.
	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == ok) {
	            return new Pair<>(from.getText(), to.getText());
	        }
	        return null;
	    });

	    Optional<Pair<String, String>> result = dialog.showAndWait();

	    result.ifPresent(pair -> {
	    	if (!pair.getKey().isEmpty() && !pair.getValue().isEmpty()) {
	    		mutualResult(pair.getKey(),pair.getValue());
	    	}
	    });
	}
	
	private void mutualResult(String user1, String user2) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Mutual Friends");
		alert.setHeaderText("There is a list of mutual frinds between " + user1 + "and " + user2 + ":");
		alert.setContentText("Joe, Robert, Yu");

		alert.showAndWait();
	}
	
	private void shortestPath() {
		// Create the custom dialog.
	    Dialog<Pair<String, String>> dialog = new Dialog<>();
	    dialog.setTitle("ShortestPath");
	    dialog.setHeaderText("Enter the usernames to find shortest path:");

	    // Set the button types.
	    ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
	    dialog.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);

	    GridPane gridPane = new GridPane();
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);
	    gridPane.setPadding(new Insets(20, 150, 10, 10));

	    TextField from = new TextField();
	    from.setPromptText("User 1");
	    TextField to = new TextField();
	    to.setPromptText("User 2");

	    gridPane.add(from, 0, 0);
	    gridPane.add(new Label("And"), 1, 0);
	    gridPane.add(to, 2, 0);

	    dialog.getDialogPane().setContent(gridPane);

	    // Request focus on the username field by default.
	    Platform.runLater(() -> from.requestFocus());

	    // Convert the result to a username-password-pair when the login button is clicked.
	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == ok) {
	            return new Pair<>(from.getText(), to.getText());
	        }
	        return null;
	    });

	    Optional<Pair<String, String>> result = dialog.showAndWait();

	    result.ifPresent(pair -> {
	    	if (!pair.getKey().isEmpty() && !pair.getValue().isEmpty()) {
	    		mutualResult(pair.getKey(),pair.getValue());
	    	}
	    });
	}
	
	private void shortestResult(String user1, String user2) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Shortest Path");
		alert.setHeaderText("The shortest path between " + user1 + "and " + user2 + "is:");
		alert.setContentText("Joe, Robert, Yu");
		alert.showAndWait();
	}
	
	private void connected() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Connected Components");
		alert.setHeaderText("The number of connected components is:");
		alert.setContentText("2");
		alert.showAndWait();
	}
	
  /**
   * @param args
   */
  public static void main(String[] args) {
	launch(args);
  }
}
