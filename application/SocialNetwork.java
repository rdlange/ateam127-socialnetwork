/**
 * a social network of users from a file and constructs a Graph object according to the file. 
 * Keeps track of the user in the current ‘center’ of the graph in addition to friends to the 
 * central user.
 * 
 * @authors Robert Lange, Lukas Her, Kevin Xiao, Yu Long, Joe Hershey
 *
 */
package application;

import java.util.ArrayList;

public class SocialNetwork { 
    
    private Graph graph;//a graph used to keep track of the users
    private ArrayList<String> log;//an array list used to keep track of the user's inputs
    
    /**
     * Returns a list of friends of the given user
     * @param name the name of the person they want to get the friends of
     * @return an array list of the names of the person's friends
     */
    public ArrayList<String> getFriendsOf(String name) {
        return null;
    }
    
    /**
     * Returns a list of all the users in this SocialNetwork
     * @return an array list of all users in the social network
     */
    public ArrayList<String> getAllUsers() {
        return null;
    }
    
    /**
     * Adds a user to the SocialNetwork with the name given by person.
     * @param person the person to add to the social network
     */
    public void addPerson(String person) {
        
    }
    
    /**
     * Creates a friendship between person1 and person2 in the SocialNetwork. If either person does
     * not exist in the SocialNetwork, then those users will be added to the network.
     * @param person1 the new friend of person2
     * @param person2 the new friend of person1
     */
    public void addFriends(String person1, String person2) {
        
    }
    
    /**
     * Removes the given person from the social network.
     * @param person the person to remove
     */
    public void removePerson(String person) {
        
    }
    
    /**
     * Removes the friendship between person1 and person2.
     * @param person1 the person who used to be friends with person2
     * @param person2 the person who used to be friends with person1
     */
    public void removeFriend(String person1, String person2) {
        
    }
    
    /**
     * Removes all people from the social network.
     */
    public void removeAll() {
    
    }
    
    /**
     * Returns a list of all mutual (shared friends) between two friends in the graph.
     * @param person1 the first person to find the friends of
     * @param person2 the second person to find the friends of
     * @return an array list of all of the friends person1 and person2 share
     */
    public ArrayList<String> getMutualFriends(String person1, String person2) {
        return null;
    }
    
    /**
     * Returns an ordered list containing shortest path of users from person1 to person2 in the 
     * graph.
     * @param person1 the starting person
     * @param person2 the ending person
     * @return an ordered list of how to get from person1 to person2
     */
    public ArrayList<String> getShortestPath(String person1, String person2) {
        return null;
    }
    
    /**
     * Saves the log of this SocialNetwork to a text file.
     */
    public void saveNetwork() {
        
    }
    
    /**
     * Loads the log file found on the given filePath and builds a SocialNetwork from this file.
     * @param filePath a relative path to the file they want to read
     */
    public void uploadNetwork(String filePath) {
        
    }
    
    public void setCentral(String central){
        
    }
    
    public int connectedComponent(){
        return 0;
    }
    
    /**
     * Returns the current log of this SocialNetwork.
     * @return an array list of all the commands input
     */
    public ArrayList<String> getLog() {
        return null;
    }
}
