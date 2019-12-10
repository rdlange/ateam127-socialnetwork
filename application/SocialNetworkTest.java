package application;

/* Class used to test the SocialNetwork class
 * 
 */
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class that tests my SocialNetwork.
 */
public class SocialNetworkTest {

	// SocialNetwork object used for the tests
	SocialNetwork socialNetwork;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.socialNetwork = new SocialNetwork();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		socialNetwork = null;
	}

	/*
	 * Verify that a newly constructed social network is empty, has no users,
	 * and the activity log is empty/
	 */
	@Test
	public void test00_constructSocialNetwork() {
		assertEquals(this.socialNetwork.getLog().size(), 0);
	}

	/*
	 * Verify that the log shows when users and friendships are added.
	 */
	@Test
	public void test01_log_shows_add_remove_person_add_remove_friends() {
		socialNetwork.addPerson("person1");
		socialNetwork.addFriends("person1","person2");
		assertEquals(socialNetwork.getLog().get(0),"a person1");
		assertEquals(socialNetwork.getLog().get(1),"a person1 person2");
		
		socialNetwork.removeFriends("person1","person2");
		socialNetwork.removePerson("person1");
		assertEquals(socialNetwork.getLog().get(2),"r person1 person2");
		assertEquals(socialNetwork.getLog().get(3),"r person1");
	}
}
