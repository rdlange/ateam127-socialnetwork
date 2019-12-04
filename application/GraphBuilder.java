package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GraphBuilder {
	private Graph graphToVisualize;
	private String centerUser;
	private VBox friendsList;
	
	// constructor
	public GraphBuilder (Graph graph, String center) {
		this.graphToVisualize = graph;
		this.centerUser = center;
	} 
	
	public void setCenterButton(String newCenter) {
		this.centerUser = newCenter;
	}
	
	// return a button of the center user
	public Button getCenterUser() {
		Button centerButton = new Button(centerUser);
		centerButton.setMinHeight(100);
		return centerButton;
	}
	
	class friendHandler implements EventHandler<ActionEvent> {
		private Button button;
		private GraphBuilder graphBuilderUsed;
		public friendHandler(Button friendButton, GraphBuilder builder) {
			this.button = friendButton;
			this.graphBuilderUsed = builder;
		}
		@Override
		public void handle(ActionEvent e) {
			graphBuilderUsed.setCenterButton(button.getText());
		}
	}
	
	// return a vbox containing the center user's friends as buttons
	public VBox getFriendsOfCenterUser() {
		this.friendsList = new VBox();
		friendsList.getChildren().add(new Label ("Friends of " + centerUser));
		for (String friend : graphToVisualize.getAdjacentVerticesOf(centerUser)) {
			Button friendButton = new Button (friend);
			friendHandler handler = new friendHandler(friendButton, this);
			friendButton.setOnAction(handler);
			friendsList.getChildren().add(friendButton);
		}
		return friendsList;
	}
	
}
