package application;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class NetworkGraph {
	
	//private SocialNetwork network;
	private String centralUser;
	
	//network = new SocialNetwork(); 
	
	/*public NetworkGraph(String centralUser, SocialNetwork network) {
		this.network = network;
		this.centralUser = centralUser;
	}*/

	public NetworkGraph() {
		//this.network = null;
		this.centralUser = "user1";
	}

	public String getCentralUser() {
		return centralUser;
	}

	public VBox visualizeGraph() {
		
		//commented out as the SocialNetwork class isn¡¯t established yet
		//ArrayList <String> friends = network.getFriendsOf(centralUser);
		
		//hardcoded so friends can be present without the network
		ArrayList <String> friends = new ArrayList <String>();
		friends.add("friend1");
		friends.add("friend2");
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
   		vbox.setSpacing(8);

		Text center = new Text(centralUser);
		center.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		vbox.getChildren().add(center);
		
		
		Button friend;

		for (int i=0; i< friends.size(); i++) {
			friend = new Button(friends.get(i));
        			vbox.setMargin(friend, new Insets(0, 0, 0, 8));
           			vbox.getChildren().add(friend);
    		}
		return vbox;
    	}
      }


