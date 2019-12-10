/**
 * This class creates a visual representation of a graph created by a SocialNetwork object. The
 * graph constructed by this class is then displayed using the Main method.
 * 
 * Filename: NetworkGraph.java
 * Project: A-Team project (Social Network)
 * Authors: Robert Lange, Yu Long, Joe Hershey, Kevin Xiao, Lukas Her
 * Email: rdlange2@wisc.edu, long27@wisc.edu, joehershey@wisc.edu, klxiao@wisc.edu, lnher2@wisc.edu
 * Lecture: 001
 * Due: December 11th, 2019 (11:59pm) 
 */

package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/*
* The NetworkGraph class displays a visual graph according to the data in its SocialNetwork object.
*/
public class NetworkGraph {

	private SocialNetwork network; // SocialNetwork object used to access its users and methods
	//private String centralUser; // the current central user in this graph

	/*
	 * Constructor used to create a NetworkGraph object with a given central user
	 * and SocialNetwork object.
	 *
	 * @param centralUser - the name of the central user in the SocialNetwork object
	 * 
	 * @param network - the SocialNetwork object used by this NetworkGraph
	 */
	public NetworkGraph(String centralUser, SocialNetwork network) {
		this.network = network;
		//this.centralUser = centralUser;
	}
	
	public NetworkGraph() {
		this.network = new SocialNetwork();
		//this.centralUser = "";
	}

	/*
	 * Returns the name of the central user in this graph visualization.
	 *
	 * @return the name of the central user
	 */
	public String getCentralUser() {
		System.out.println("central user: " + network.getCentralUser());
		return network.getCentralUser();
	}

	/*
	 * Returns the visual component to be displayed on the GUI defined in main.
	 *
	 * @return a vbox containing all the users to be displayed in the visual graph.
	 */
	public VBox visualizeGraph() {
		if (this.getCentralUser() != null) { 
		// ArrayList of all the Central User's friends
		List<String> friends = new ArrayList<>();
		friends = network.getFriendsOf(this.getCentralUser());
		
		System.out.println(friends.size());
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);

		Text center = new Text(this.getCentralUser() + " Friends:");
		center.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		vbox.getChildren().add(center);

		Button friend;

		for (int i = 0; i < friends.size(); i++) {
			friend = new Button(friends.get(i));
			vbox.setMargin(friend, new Insets(0, 0, 0, 8));
			vbox.getChildren().add(friend);
		}
		return vbox;
		}
		return new VBox();
	}

	public String mutualFriendsText(String user1, String user2) {
		String mutuals = "Here are the mutual friends of " + user1 + " and " + user2 + ": ";
		List<String> mutualFriends = network.getMutualFriends(user1, user2);
		for (int i = 0; i < mutualFriends.size(); i++) {
			if (i == mutualFriends.size() - 1) {
				return mutuals + mutualFriends.get(i) + ".";
			}
			mutuals = mutuals + mutualFriends.get(i) + ", ";
		}
		return user1 + " and " + user2 + " have no mutual friends.";
	}

	public String shortestFriendPath(String user1, String user2) {
		List<String> path = network.getShortestPath(user1, user2);
		String message = "";
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
			return message;
		}
	}
}
