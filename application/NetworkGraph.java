
package application;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class NetworkGraph {
	
	private SocialNetwork network; //SocialNetwork object used to access its users and methods
	private String centralUser;
	
	
	
	public NetworkGraph(String centralUser, SocialNetwork network) {
		this.network = network;
		this.centralUser = centralUser;
	}

	public String getCentralUser() {
		return centralUser;
	}

	public VBox visualizeGraph() {
		
		//ArrayList of all the Central User's friends
		ArrayList <String> friends = network.getFriendsOf(centralUser);
		
		
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
   		vbox.setSpacing(8);

		Text center = new Text(centralUser + " Friends:");
		center.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		vbox.getChildren().add(center);
		
		
		Button friend;

		for (int i=0; i< friends.size(); i++) {
			friend = new Button(friends.get(i));
        			vbox.setMargin(friend, new Insets(0, 0, 0, 8));
           			vbox.getChildren().add(friend);
    		}
		return vbox;
    	}

	public String mutualFriendsText (String user1, String user2) {
		String mutuals = “Here are the mutual friends of “ + user1 + “ and “ +        user2 + “: ”;
		ArrayList <String> mutualFriends = network.getMutualFriends(user1,user2);
		for (int i = 0; i < mutualFriends.size(); i++) {
			if ( i == mutualFriends.size() - 1) {
				return mutuals + mutualFriends.get(i) + “.”;
			}
			mutuals = mutuals + mutualFriends.get(i) + “, “;
	

	}

	
	public String shortestFriendPath(String user1, String user2) {
		ArrayList<String> path = network.getShortestPath(user1, user2);
		String message = “”;
		if (path.size() == 2) {
			return new String(“These users are friends, so the shortest path directly connects them!”);
		}		


		for (int i = 0; i < path.size(); i++) {
	
			if (i == 0) {
				message = user1 + “ <-> “;
			}
			else if(i == path.size() - 1) {
				message = message + user2;
			}
			else {
				message = message + path.get(i) + “ <-> “;
			}
		}
	}
     
}


	



