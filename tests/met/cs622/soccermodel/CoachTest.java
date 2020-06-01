package met.cs622.soccermodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test usefull methods from class Coach
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
class CoachTest {

	@Test
	void testGetRole() {
		Coach c=null;
		try {
			c = new Coach("Max", 22, 15000, 8);
		} catch (TooYoungMemberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Coach", c.getRole());
	}

}
