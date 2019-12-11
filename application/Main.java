/**
 * Main displayed the GUI used by the Social Network visualizer.
 * 
 * Filename: Main.java
 * Project: A-Team project (Social Network)
 * Authors: Robert Lange, Yu Long, Joe Hershey, Kevin Xiao, Lukas Her
 * Email: rdlange2@wisc.edu, long27@wisc.edu, joehershey@wisc.edu, klxiao@wisc.edu, lnher2@wisc.edu
 * Lecture: 001
 * Due: December 11th, 2019 (11:59pm) 
 */

package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * The Main class uses JavaFX to display the graphic user interface of this
 * social network visualizer. It displays the buttons the user can interact with
 * in addition to a visual graph representation of the current social network.
 */
public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;
	// Set the window height, width, and title.
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 640;
	private static final String APP_TITLE = "GetSocial - Social Network Visaulizer";
	private SocialNetwork socialNetwork = new SocialNetwork();
	BorderPane root = new BorderPane();

	/*
	 * Method used to create the GUI buttons and place them onto the stage. After
	 * constructing the stage, the method will display the GUI to the user.
	 *
	 * @param primaryStage - the stage used by this method
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// save any args passed to the program
		args = this.getParameters().getRaw();
		// place the logo in the top panel
		Image logo = new Image("logo.png");
		ImageView logoView = new ImageView(logo);
		VBox network = visualizeGraph();

		// create the 'Set Central User' button
		Button setCenterButton = new Button("Set Central User");
		setCenterButton.setMinSize(150, 50);
		setCenterButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setCenter();
				VBox leftBox = new VBox();
				leftBox.setSpacing(160.0);
				leftBox.getChildren().add(new Label(""));
				Button centralb = new Button(socialNetwork.getCentralUser());
				centralb.setMnemonicParsing(false);
				leftBox.getChildren().add(centralb);
				root.setLeft(leftBox);
				VBox center = new VBox();
				center.setSpacing(160.0);
				center.getChildren().add(new Label(""));
				center.getChildren().add(visualizeGraph());
				root.setCenter(center);
			}
		});

		// create the 'Add User' button
		Button addUserButton = new Button("Add User");
		addUserButton.setMinSize(150, 50);
		addUserButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addUser();
				VBox leftBox = new VBox();
				leftBox.setSpacing(160.0);
				leftBox.getChildren().add(new Label(""));
				Button centralb = new Button(socialNetwork.getCentralUser());
				centralb.setMnemonicParsing(false);
				leftBox.getChildren().add(centralb);
				root.setLeft(leftBox);
				VBox center = new VBox();
				center.setSpacing(160.0);
				center.getChildren().add(new Label(""));
				center.getChildren().add(new VBox(visualizeGraph()));
				root.setCenter(center);
			}
		});

		// create the 'Remove User' button
		Button removeUserButton = new Button("Remove User");
		removeUserButton.setMinSize(150, 50);
		removeUserButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				removeUser();
				VBox leftBox = new VBox();
				leftBox.setSpacing(160.0);
				leftBox.getChildren().add(new Label(""));
				Button centralb = new Button(socialNetwork.getCentralUser());
				centralb.setMnemonicParsing(false);
				leftBox.getChildren().add(centralb);
				root.setLeft(leftBox);
				VBox center = new VBox();
				center.setSpacing(160.0);
				center.getChildren().add(new Label(""));
				center.getChildren().add(new VBox(visualizeGraph()));
				root.setCenter(center);
			}
		});

		// create the 'Add Friendship' button
		Button addFriendshipButton = new Button("Add Friendship");
		addFriendshipButton.setMinSize(150, 50);
		addFriendshipButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addFriend();
				VBox leftBox = new VBox();
				leftBox.setSpacing(160.0);
				leftBox.getChildren().add(new Label(""));
				Button centralb = new Button(socialNetwork.getCentralUser());
				centralb.setMnemonicParsing(false);
				leftBox.getChildren().add(centralb);
				root.setLeft(leftBox);
				VBox center = new VBox();
				center.setSpacing(160.0);
				center.getChildren().add(new Label(""));
				center.getChildren().add(new VBox(visualizeGraph()));
				root.setCenter(center);
			}
		});

		// create the 'Remove Friendship' button
		Button removeFriendshipButton = new Button("Remove Friendship");
		removeFriendshipButton.setMinSize(150, 50);
		removeFriendshipButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				removeFriend();
				VBox leftBox = new VBox();
				leftBox.setSpacing(160.0);
				leftBox.getChildren().add(new Label(""));
				Button centralb = new Button(socialNetwork.getCentralUser());
				centralb.setMnemonicParsing(false);
				leftBox.getChildren().add(centralb);
				root.setLeft(leftBox);
				VBox center = new VBox();
				center.setSpacing(160.0);
				center.getChildren().add(new Label(""));
				center.getChildren().add(visualizeGraph());
				root.setCenter(center);
			}
		});

		// create the 'Import Network' button
		Button importNetworkButton = new Button("Import Network");
		importNetworkButton.setMinSize(150, 50);
		final FileChooser fileChooser = new FileChooser();
		importNetworkButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					try {
						socialNetwork.uploadNetwork(file);
						VBox leftBox = new VBox();
						leftBox.setSpacing(160.0);
						leftBox.getChildren().add(new Label(""));
						Button centralb = new Button(socialNetwork.getCentralUser());
						centralb.setMnemonicParsing(false);
						leftBox.getChildren().add(centralb);
						root.setLeft(leftBox);
						VBox center = new VBox();
						center.setSpacing(160.0);
						center.getChildren().add(new Label(""));
						center.getChildren().add(visualizeGraph());
						root.setCenter(center);
					} catch (IllegalArgumentException e1) {
						mistake();
					} catch (Exception e2) {
						failedEx();
					}
				}
			}
		});

		// create the 'Export Network' button
		Button exportNetworkButton = new Button("Export Network");
		exportNetworkButton.setMinSize(150, 50);
		exportNetworkButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					try {
						socialNetwork.saveNetwork(file);
					} catch (Exception e1) {
						failedEx();
					}
				}
			}
		});

		// create the 'Clear Network' button
		Button clearNetworkButton = new Button("Clear Network");
		clearNetworkButton.setMinSize(150, 50);
		clearNetworkButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				clearAll();
				VBox leftBox = new VBox();
				leftBox.setSpacing(160.0);
				leftBox.getChildren().add(new Label(""));
				Button centralb = new Button(socialNetwork.getCentralUser());
				centralb.setMnemonicParsing(false);
				leftBox.getChildren().add(centralb);
				root.setLeft(leftBox);
				VBox center = new VBox();
				center.setSpacing(160.0);
				center.getChildren().add(new Label(""));
				// TODO
				center.getChildren().add(visualizeGraph());
				root.setCenter(center);
			}
		});

		// create a vertical box containing the buttons for the user to click on
		VBox options = new VBox();
		options.setSpacing(12.0);

		// add the buttons to the vbox (which will be in the right panel)
		options.getChildren().add(setCenterButton);
		options.getChildren().add(addUserButton);
		options.getChildren().add(removeUserButton);
		options.getChildren().add(addFriendshipButton);
		options.getChildren().add(removeFriendshipButton);
		options.getChildren().add(importNetworkButton);
		options.getChildren().add(exportNetworkButton);
		options.getChildren().add(clearNetworkButton);

		// create the 'Find Mutual Friends' button
		Button mutualFriendsButton = new Button("Find Mutual Friends");
		mutualFriendsButton.setMinSize(140, 50);
		mutualFriendsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mutualFriend();
			}
		});

		// create the 'Find Shortest Path' button
		Button shortestPathButton = new Button("Find Shortest Path");
		shortestPathButton.setMinSize(120, 50);
		shortestPathButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				shortestPath();
			}
		});

		// create the 'Status Updates' button
		Button statusUpdatesButton = new Button("Status Updates");
		statusUpdatesButton.setMinSize(130, 50);
		statusUpdatesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				updates();
			}
		});

		// create the 'Connected Components' button
		Button connectedComponentButton = new Button("Statistics");
		connectedComponentButton.setMinSize(150, 50);
		connectedComponentButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				statistics();
			}
		});

		// create the 'Exit' button
		Button closeButton = new Button("Exit");
		closeButton.setMinSize(150, 50);
		closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				close();
			}
		});

		// create an hbox of buttons for the bottom panel
		HBox algorithmOptions = new HBox();
		algorithmOptions.setSpacing(22.0);

		// add each of the buttons to the hbox (which will be displayed in the bottom
		// panel)
		algorithmOptions.getChildren().add(mutualFriendsButton);
		algorithmOptions.getChildren().add(shortestPathButton);
		algorithmOptions.getChildren().add(statusUpdatesButton);
		algorithmOptions.getChildren().add(connectedComponentButton);
		algorithmOptions.getChildren().add(closeButton);

		// Main layout is Border Pane example (top,left,center,right,bottom)
		//BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		Button centralUserButton = new Button(socialNetwork.getCentralUser());
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
	
	/*
	 * Returns the visual component to be displayed on the GUI defined in main.
	 *
	 * @return a vbox containing all the users to be displayed in the visual socialNetwork.
	 */
	public VBox visualizeGraph() {
		if (socialNetwork.getCentralUser() != null) { 
		// ArrayList of all the Central User's friends
		List<String> friends = new ArrayList<>();
		friends = socialNetwork.getFriendsOf(socialNetwork.getCentralUser());
		
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);

		Text center = new Text(socialNetwork.getCentralUser() + " Friends:");
		center.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		vbox.getChildren().add(center);

		

		if (friends !=null) {
			for (int i = 0; i < friends.size(); i++) {
				final Button friend;
				friend = new Button(friends.get(i));
				friend.setMnemonicParsing(false);
				friend.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						clickSet(friend.getText());
					}
				});
				vbox.setMargin(friend, new Insets(0, 0, 0, 8));
				vbox.getChildren().add(friend);
			}
		}
		return vbox;
		}
		return new VBox();
	}

	private void clickSet(String name) {
		socialNetwork.setCentral(name);
		VBox leftBox = new VBox();
		leftBox.setSpacing(160.0);
		leftBox.getChildren().add(new Label(""));
		leftBox.getChildren().add(new Button(socialNetwork.getCentralUser()));
		root.setLeft(leftBox);
		VBox center = new VBox();
		center.setSpacing(160.0);
		center.getChildren().add(new Label(""));
		center.getChildren().add(visualizeGraph());
		root.setCenter(center);
	}
	
	/*
	 * Private helper method used to set a new central user in the socialNetwork.
	 */
	private void setCenter() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Set Center User");
		dialog.setHeaderText("Enter the username you want to set as center:");
		dialog.setContentText("Username:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && result.get().isEmpty()) {
			invalid();
		}
		if (result.isPresent() && !result.get().isEmpty()) {
			socialNetwork.setCentral(result.get());
		}
	}

	/*
	 * Private helper method used to add a user to the graph
	 */
	private void addUser() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Add User");
		dialog.setHeaderText("Enter your new user name:");
		dialog.setContentText("Name:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && result.get().isEmpty()) {
			invalid();
		}
		if (result.isPresent() && !result.get().isEmpty()) {
			socialNetwork.addPerson(result.get());
		}
	}

	/*
	 * Private helper method used to remove a user from the socialNetwork.
	 */
	private void removeUser() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Remove User");
		dialog.setHeaderText("Enter your username you want to remove:");
		dialog.setContentText("Name:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && result.get().isEmpty()) {
			invalid();
		}
		if (result.isPresent() && !result.get().isEmpty()) {
			if (result.get().equals(socialNetwork.getCentralUser())) {
				removeC();
			}
			else {
				socialNetwork.removePerson(result.get());
			}
		}
	}

	/*
	 * Private helper method used to add a friendship to the socialNetwork.
	 */
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

		// Convert the result to a username-password-pair when the login button is
		// clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ok) {
				return new Pair<>(from.getText(), to.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(pair -> {
			if (!pair.getKey().isEmpty() && !pair.getValue().isEmpty()) {
				socialNetwork.addFriends(pair.getKey(), pair.getValue());
			} else {
				invalid();
			}
		});
	}

	/*
	 * Private helper method used to remove a friendship from the socialNetwork.
	 */
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

		// Convert the result to a username-password-pair when the login button is
		// clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ok) {
				return new Pair<>(from.getText(), to.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(pair -> {
			if (!pair.getKey().isEmpty() && !pair.getValue().isEmpty()) {
				socialNetwork.removeFriends(pair.getKey(), pair.getValue());
			} else {
				invalid();
			}
		});
	}

	/*
	 * Private helper method used to display a warning when the user clicks the
	 * 'Clear All' button.
	 */
	private void clearAll() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Clear All");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to clear the network?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && !result.isEmpty()) {
			socialNetwork.removeAll();
		}
	}

	/*
	 * Private helper method used to display the updates (changes) to the socialNetwork.
	 */
	private void updates() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Status Updates");
		alert.setHeaderText("Here is a list of changes you have made:");
		ArrayList<String> result = socialNetwork.getLog();
		String s = "";
		for (int i = 0; i < result.size(); i++) {
			s += result.get(i) + "\n";
		}
		if (result.size() == 0) {
			s = "No command entered yet";
		}
		alert.setContentText(s);
		alert.showAndWait();
	}

	/*
	 * Private helper method used to display a warning when the user clicks the
	 * 'Exit' button.
	 */
	private void close() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to exit?");
		ButtonType saveButton = new ButtonType("Save");
		ButtonType exitButton = new ButtonType("Exit without saving");
		ButtonType cancelButton = new ButtonType("Cancel");
		alert.getButtonTypes().setAll(saveButton, exitButton, cancelButton);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == saveButton) {
			confirmSave();
		} else if (result.get() == exitButton) {
			confirmExit();
		}
	}

	/*
	 * Private helper method used to confirm exiting without saving.
	 */
	private void confirmExit() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit without saving");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to exit without saving?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			goodbye();
		} else {
			close();
		}
	}

	/*
	 * Private helper method used to confirm saving a file.
	 */
	private void confirmSave() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Save");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to save and exit?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			save();
		} else {
			close();
		}
	}

	/*
	 * Private helper method used to save the current graph to a text file.
	 */
	private void save() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Save");
		dialog.setHeaderText("Save");
		dialog.setContentText("Enter file name:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && result.get().isEmpty()) {
			invalid();
		}
		if (result.isPresent() && !result.get().isEmpty()) {
			String filename = result.get() + ".txt";
			File file = new File("./", filename);
			if (file.exists()) {
				try {
					socialNetwork.saveNetwork(file);
					saved();
				} catch (Exception e) {
					failed();
				}
			} else {
				try {
					if (file.createNewFile()) {
						socialNetwork.saveNetwork(file);
						saved();
					} else {
						failed();
					}
				} catch (Exception e) {
					failed();
				}
			}
		}
	}

	/*
	 * Private helper method used to display a warning when accessing the file
	 * fails.
	 */
	private void failedEx() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Failed");
		alert.setHeaderText(null);
		alert.setContentText("Failed during accessing file. Please try again");
		alert.showAndWait();
	}

	/*
	 * Private helper method used to display a warning when the loaded file has
	 * invalid input.
	 */
	private void mistake() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("Failed. Invalid file input.");
		alert.showAndWait();
	}

	/*
	 * Private helper method used to display a warning that the file couldn't be
	 * saved.
	 */
	private void failed() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Failed");
		alert.setHeaderText(null);
		alert.setContentText("Failed to create file. Please try again");
		alert.showAndWait();
		save();
	}

	/*
	 * Private helper method used to display a 'Goodbye' message to the user (after
	 * saving).
	 */
	private void saved() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Goodbye");
		alert.setHeaderText(null);
		alert.setContentText("Changes have been saved. Goodbye!");
		alert.showAndWait();
		Platform.exit();
	}

	/*
	 * Private helper method used to display a 'Goodbye' message to the user (no
	 * saving).
	 */
	private void goodbye() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Goodbye");
		alert.setHeaderText(null);
		alert.setContentText("Goodbye!");
		alert.showAndWait();
		Platform.exit();
	}

	/*
	 * Private helper method used to find the mutual friends between two users in
	 * the socialNetwork.
	 */
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

		// Convert the result to a username-password-pair when the login button is
		// clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ok) {
				return new Pair<>(from.getText(), to.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(pair -> {
			if (!pair.getKey().isEmpty() && !pair.getValue().isEmpty()) {
				mutualResult(pair.getKey(), pair.getValue());
			} else {
				invalid();
			}
		});
	}
	

	public String mutualFriendsText(String user1, String user2) {
		String mutuals = "Here are the mutual friends of " + user1 + " and " + user2 + ": ";
		List<String> mutualFriends = socialNetwork.getMutualFriends(user1, user2);
		if (mutualFriends != null) {
			for (int i = 0; i < mutualFriends.size(); i++) {
				if (i == mutualFriends.size() - 1) {
					return mutuals + mutualFriends.get(i) + ".";
				}
				mutuals = mutuals + mutualFriends.get(i) + ", ";
			}
		}
		return user1 + " and " + user2 + " have no mutual friends.";
	}

	public String shortestFriendPath(String user1, String user2) {
		List<String> path = new ArrayList<String>();
		path = socialNetwork.getShortestPath(user1, user2);
		String message = "";
		if (path != null) {
			if (path.size() == 2) {
				return new String("These users are friends, so the shortest path directly connects them!");
			} else {
				for (int i = 0; i < path.size(); i++) {
					if (i == 0) {
						message = user1 + " <-> ";
					} else if (i == path.size() - 1) {
						message = message + user2;
					} else {
						message = message + path.get(i) + " <-> ";
					}
				}
				
			}
		}
		return message;
	}

	/*
	 * Private helper method used to display the mutual friends between two users in
	 * the socialNetwork.
	 */
	private void mutualResult(String user1, String user2) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Mutual Friends");
		alert.setHeaderText("There is a list of mutual frinds between " + user1 + "and " + user2 + ":");
		String result = mutualFriendsText(user1, user2);
		alert.setContentText(result);
		alert.showAndWait();
	}

	/*
	 * Private helper method used to find the shortest path between two users in the
	 * socialNetwork.
	 */
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

		// Convert the result to a username-password-pair when the login button is
		// clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ok) {
				return new Pair<>(from.getText(), to.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(pair -> {
			if (!pair.getKey().isEmpty() && !pair.getValue().isEmpty()) {
				shortestResult(pair.getKey(), pair.getValue());
			} else {
				invalid();
			}
		});
	}

	/*
	 * Private helper method used to display the shortest path between two nodes in
	 * the socialNetwork.
	 */
	private void shortestResult(String user1, String user2) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Shortest Path");
		alert.setHeaderText("The shortest path between " + user1 + " and " + user2 + " is:");
		String result = shortestFriendPath(user1, user2);
		if (result == "") {
			result = user1 + " and " + user2 + " are not connected";
		}
		alert.setContentText(result);
		alert.showAndWait();
	}

	/*
	 * Private helper method used to display the statistics of the socialNetwork.
	 */
	private void statistics() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Social Network Statistics");
		alert.setHeaderText("Here are some statistics for you graph");
		int result = socialNetwork.connectedComponent();
		alert.setContentText(String.valueOf(result));
		alert.setContentText("The number of connected components is: " + String.valueOf(result) + "\n"
				+ "The number of users in this graph is: " + String.valueOf(socialNetwork.numOfVertices()) + "\n" + "The number of friendships in this graph is: " + String.valueOf(socialNetwork.numOfEdges()));
		alert.showAndWait();
	}

	/*
	 * Private helper method used to display a warning popup when the provided input
	 * is invalid.
	 */
	private void invalid() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid input");
		alert.setHeaderText(null);
		alert.setContentText("Invalid input. Please try again.");

		alert.showAndWait();
	}
	
	private void removeC() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid input");
		alert.setHeaderText(null);
		alert.setContentText("Cannot remove central user");

		alert.showAndWait();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
