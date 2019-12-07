  
/**
 * a social network of users from a file and constructs a Graph object according to the file. 
 * Keeps track of the user in the current ‘center’ of the graph in addition to friends to the 
 * central user.
 * 
 * @authors Robert Lange, Lukas Her, Kevin Xiao, Yu Long, Joe Hershey
 *
 */
package application;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SocialNetwork { 
    
    private Graph graph;//a graph used to keep track of the users
    private ArrayList<String> log;//an array list used to keep track of the user's inputs
    
    /**
     * Constructor for SocialNetwork class
     */
    public SocialNetwork() {
        graph = new Graph();
        log = new ArrayList<String>();
    }
    
    /**
     * Returns a list of friends of the given user
     * @param name the name of the person they want to get the friends of
     * @return an array list of the names of the person's friends
     */
    public List<String> getFriendsOf(String name) {
        return this.graph.getAdjacentVerticesOf(name);
    }
    
    /**
     * Returns a list of all the users in this SocialNetwork
     * @return an array list of all users in the social network
     */
    public List<String> getAllUsers() {
        return this.graph.getAllVertices();
    }
    
    /**
     * Adds a user to the SocialNetwork with the name given by person.
     * @param person the person to add to the social network
     */
    public void addPerson(String person) {
       this.graph.addVertex(person);
       log.add("a " + person);
    }
    
    /**
     * Creates a friendship between person1 and person2 in the SocialNetwork. If either person does
     * not exist in the SocialNetwork, then those users will be added to the network.
     * @param person1 the new friend of person2
     * @param person2 the new friend of person1
     */
    public void addFriends(String person1, String person2) {
        this.graph.addEdge(person1, person2);
        log.add("a " + person1 + " " + person2);
    }
    
    /**
     * Removes the given person from the social network.
     * @param person the person to remove
     */
    public void removePerson(String person) {
        this.graph.removeVertex(person);
        log.add("r " + person);
    }
    
    /**
     * Removes the friendship between person1 and person2.
     * @param person1 the person who used to be friends with person2
     * @param person2 the person who used to be friends with person1
     */
    public void removeFriends(String person1, String person2) {
        this.graph.removeEdge(person1, person2);
        log.add("r " + person1 + " " + person2);
    }
    
    /**
     * Removes all people from the social network.
     */
    public void removeAll() {
        for (String user : graph.getAllVertices()) {
            graph.removeVertex(user);
            log.add("r " + user);
        }
    }
    
    /**
     * Returns a list of all mutual (shared friends) between two friends in the graph.
     * @param person1 the first person to find the friends of
     * @param person2 the second person to find the friends of
     * @return an array list of all of the friends person1 and person2 share
     */
    public List<String> getMutualFriends(String person1, String person2) {
        List<String> user1Friends = graph.getAdjacentVerticesOf(person1);
        List<String> user2Friends = graph.getAdjacentVerticesOf(person2);
        List<String> mutualFriends = new ArrayList<String>();
        for (String friendOfUser1 : user1Friends) {
            for (String friendOfUser2 : user2Friends) {
                if (friendOfUser1.equals(friendOfUser2)) {
                    mutualFriends.add(friendOfUser1);
                }
            }
        }
        return mutualFriends;
    }
    
    /**
     * Returns an ordered list containing shortest path of users from person1 to person2 in the 
     * graph.
     * @param person1 the starting person
     * @param person2 the ending person
     * @return an ordered list of how to get from person1 to person2
     */
    public List<String> getShortestPath(String person1, String person2) {
        
        return null;
    }
    
    /**
     * Saves the log of this SocialNetwork to a text file.
     */
    public void saveNetwork() {
        try {
            FileWriter writer = new FileWriter("network.txt");
            for (String str: log) {
                writer.write(str + "\n");
            }
                writer.close();
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Loads the log file found on the given filePath and builds a SocialNetwork from this file.
     * @param filePath a relative path to the file they want to read
     */
    public void uploadNetwork(String filePath) {
        
    }
    
    /**
     * 
     * @param central user to set as central user
     */
    public void setCentral(String central){
        
    }
    
    /**
     * 
     * @return
     */
    public int connectedComponent(){
        return 0;
    }
    
    /**
     * Returns the current log of this SocialNetwork.
     * @return an array list of all the commands input
     */
    public ArrayList<String> getLog() {
        return log;
    }
}
