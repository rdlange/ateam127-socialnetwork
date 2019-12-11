/**
 * A social network of users from a file and constructs a Graph object according to the file. 
 * Keeps track of the user in the current ‘center’ of the graph in addition to friends to the 
 * central user.
 * 
 * Filename: SocialNetwork.java
 * Project: A-Team project (Social Network)
 * Authors: Robert Lange, Yu Long, Joe Hershey, Kevin Xiao, Lukas Her
 * Email: rdlange2@wisc.edu, long27@wisc.edu, joehershey@wisc.edu, klxiao@wisc.edu, lnher2@wisc.edu
 * Lecture: 001
 * Due: December 11th, 2019 (11:59pm) 
 */

package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* The SocialNetwork class stores the social network used by the NetworkGraph class. It makes use of
* a Graph object to store the current users and friendships in the social network and implements
* additional functionality (such as finding mutual friends between two users, describing the shortest
* path between two users, or counting the number of connected components in the graph).
*/
public class SocialNetwork {

	private Graph graph;// a graph used to keep track of the users
	private ArrayList<String> log;// an array list used to keep track of the user's inputs
	private String centralUser = "No data input yet";;

	/**
	 * Constructor for a SocialNetwork object.
	 */
	public SocialNetwork() {
		this.graph = new Graph();
		this.log = new ArrayList<String>();
	}

	/**
	 * Returns a list of friends of the given user.
	 *
	 * @param name the name of the person they want to get the friends of
	 * @return an array list of the names of the person's friends
	 */
	public List<String> getFriendsOf(String name) {
		return this.graph.getAdjacentVerticesOf(name);
	}

	/**
	 * Returns a list of all the users in this SocialNetwork.
	 *
	 * @return an array list of all users in the social network
	 */
	public List<String> getAllUsers() {
		return this.graph.getAllVertices();
	}
	
	/**
	 * Adds a user to the SocialNetwork with the name given by the String person.
	 *
	 * @param person - the name of the person to add to the social network
	 */
	public void addPerson(String person) {
		List<String> result = getAllUsers();
		if (result.size() == 0) {
			setCentral(person);
		}
		this.graph.addVertex(person);
		log.add("a " + person);
	}

	/**
	 * Creates a friendship between person1 and person2 in the SocialNetwork. If
	 * either person does not exist in the SocialNetwork, then the user(s) will be
	 * added to the network.
	 *
	 * @param person1 the new friend of person2
	 * @param person2 the new friend of person1
	 */
	public void addFriends(String person1, String person2) {
		List<String> result = getAllUsers();
		if (result.size() == 0) {
			setCentral(person1);
		}
		this.graph.addEdge(person1, person2);
		log.add("a " + person1 + " " + person2);
	}

	/**
	 * Removes the given person from the social network.
	 *
	 * @param person the person to remove
	 */
	public void removePerson(String person) {
		this.graph.removeVertex(person);
		log.add("r " + person);
	}

	/**
	 * Removes the friendship between person1 and person2.
	 *
	 * @param person1 the person who used to be friends with person2
	 * @param person2 the person who used to be friends with person1
	 */
	public void removeFriends(String person1, String person2) {
		this.graph.removeEdge(person1, person2);
		log.add("r " + person1 + " " + person2);
	}

	/**
	 * Removes all users from the social network.
	 */
	public void removeAll() {
		for (String user : graph.getAllVertices()) {
			graph.removeVertex(user);
			log.add("r " + user);
		}
		centralUser = "No data input yet";
	}

	/**
	 * Returns a list of all mutual (shared friends) between two friends in the
	 * graph.
	 *
	 * @param person1 the first person to find the friends of
	 * @param person2 the second person to find the friends of
	 * @return an array list of all of the friends person1 and person2 share
	 */
	public List<String> getMutualFriends(String person1, String person2) {
		List<String> user1Friends = graph.getAdjacentVerticesOf(person1);
		List<String> user2Friends = graph.getAdjacentVerticesOf(person2);
		List<String> mutualFriends = new ArrayList<String>();
		if (user1Friends != null && user2Friends !=null) {
			for (String friendOfUser1 : user1Friends) {
				for (String friendOfUser2 : user2Friends) {
					if (friendOfUser1.equals(friendOfUser2)) {
						mutualFriends.add(friendOfUser1);
					}
				}
			}
		}
		return mutualFriends;
	}

	/**
	 * Returns an ordered list containing shortest path of users from person1 to
	 * person2 in the graph.
	 *
	 * @param person1 the starting person
	 * @param person2 the ending person
	 * @return an ordered list of how to get from person1 to person2
	 */
	public List<String> getShortestPath(String person1, String person2) {
		// TODO: IMPLEMENT ALGORITHM HERE (Dijikstra's?)
		return null;
	}

	/**
	 * Saves the log of this SocialNetwork to a text file.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void saveNetwork(File file) throws IOException {
		FileWriter toWrite = new FileWriter(file, true);
		try {
			for (int i = 0; i < log.size(); i++) {
				toWrite.write(log.get(i) + "\n");
			}
			toWrite.close();
		} catch (Exception e) {
			toWrite.close();
			throw new IOException();
		}
	}

	/**
	 * Loads the log file found on the given filePath and builds a SocialNetwork
	 * from this file.
	 *
	 * @param file a relative path to the file they want to read
	 * @throws IOException
	 * @throws IOException
	 */
	public void uploadNetwork(File file) throws IllegalArgumentException, IOException {
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String command = sc.nextLine();
			String[] part = command.split("\\s+");
			if (part[0].equals("s")) {
				setCentral(command.substring(2));
			} else if (part[0].equals("a")) {
				if (part.length == 2) {
					addPerson(part[1]);
				} else if (part.length == 3) {
					addFriends(part[1], part[2]);
				} else {
					throw new IllegalArgumentException();
				}
			} else if (part[0].equals("r")) {
				if (part.length == 2) {
					removePerson(part[1]);
				} else if (part.length == 3) {
					removeFriends(part[1], part[2]);
				} else {
					throw new IllegalArgumentException();
				}
			} else {
				throw new IllegalArgumentException();
			}
		}
	}

	public String getCentralUser() {
		return centralUser;
	}
	/**
	 * Sets a new central user in this social network.
	 *
	 * @param central user to set as central user
	 */
	public void setCentral(String central) {
		graph.addVertex(central);
		centralUser = central;
		log.add("s " + centralUser);
	}
	/**
	 * Returns the number of connected components in this social network.
	 *
	 * @return the number of connected components in this graph
	 */
	public int connectedComponent() {
		// store the number of components
		int numComponents = 0;
		// store a list of all the visited vertices in the graph
		ArrayList<String> visited = new ArrayList<String>();
		// if the graph contains at least one node, count the number of components.
		// otherwise, return 0.
		if (graph.order() > 0) {
			// for each user in the graph, increase the number of components if the user is
			// in a unique component.
			for (String user : graph.getAllVertices()) {
				// only count the component if it has not been counted yet
				if (!visited.contains(user)) {
					connectedComponentHelper(user, visited);
					numComponents++;
				}

			}
		}
		// return the total number of components in the social network.
		return numComponents;
	}

	/*
	 * Private helper method used to mark users in the current component as visited.
	 * Used to ensure that every user in a given component is counted.
	 * 
	 * @param currentUser - the current user to visit and check for friends
	 * @param visited - a list of all the users that have been visited
	 */
	private void connectedComponentHelper(String currentUser, ArrayList<String> visited) {
		// mark the current user as visited
		visited.add(currentUser);
		// for each of the user's friends, recursively call this method and mark the
		// friend as visited (if they have not been visited already)
		for (String user : graph.getAdjacentVerticesOf(currentUser)) {
			// only do a recursive call if the friend is not visited yet
			if (!visited.contains(user)) {
				this.connectedComponentHelper(user, visited);
			}
		}
	}

	/*
	 * Returns the number of vertices (users) in this social network
	 * 
	 * @return the number of vertices in the social network graph
	 */
	public int numOfVertices() {
		return graph.order();

	}

	/*
	 * Returns the number of friendships (edges) in this social network
	 * 
	 * @return the number of edges in the social network graph
	 */
	public int numOfEdges() {
		return graph.size();
	}

	/**
	 * Returns the current log of this SocialNetwork.
	 * 
	 * @return an array list of all the commands input
	 */
	public ArrayList<String> getLog() {
		return log;
	}
}
