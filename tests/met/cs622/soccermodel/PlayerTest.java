package met.cs622.soccermodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test methods from class Player
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
class PlayerTest {

	@Test
	void testGetRole() {
		Player c=null;
		try {
			c = new Player("Max", 22, 15000, 8, 0);
		} catch (TooYoungMemberException e) {
			e.printStackTrace();
		}
		assertEquals("Player", c.getRole());
	}
	
	@Test
	void testGetSetGoals() {
		Player c=null;
		try {
			c = new Player("Max", 22, 15000, 8, 0);
		} catch (TooYoungMemberException e) {
			e.printStackTrace();
		}
		
		assertEquals(8, c.getGoals());
		c.setGoals(12);
		assertEquals(12, c.getGoals());
	}
	
	@Test
	void testTooYoungException() { // Test if the class throws the exception when we want
		Player c=null;
		try {
			c = new Player("Max", 5, 15000, 8, 0);
			fail("Instanciate a too young player");
		} catch (TooYoungMemberException e) {
		}
		
		try {
			c = new Player("Max", 20, 15000, 8, 0);
		} catch (TooYoungMemberException e) {
			fail("Player constructor failed");
			e.printStackTrace();
		}
		
		try {
			c.setAge(10);
			fail("Cannot set player's age under 18.");
		} catch (TooYoungMemberException e) {
		}
	}
	
	@Test
	void testCompareTo() {
		Player p1 = null, p2 = null;
		try {
			p1 = new Player("Player Test1", 32,1,1,1);
			p2 = new Player("Player Test2", 32,1,2,1);
		} catch (TooYoungMemberException e) {
			fail("Cannot create player");
		}
		
		assert(p1.compareTo(p2)==-1);
		assert(p1.compareTo(p1)==0);
		assert(p2.compareTo(p1)==1);
	}
	
	@Test
	void testSettersTeamMember() {
		TeamMember p = null;
		try {
			p = new Player("Player Test", 22, 15000, 0, 150000);
		} catch (TooYoungMemberException e) {
			e.printStackTrace();
		}
		

		try {
			p.setAge(22);
		} catch (TooYoungMemberException e) {
			fail("Cannot set TeamMember Age");
		}
		
		try {
			p.setAge(15);
			fail("Not supposed to work");
		} catch (TooYoungMemberException e) {
		}
		
		p.setName("Test Test");
		
		assert(p.getName().equals("Test Test"));
		
		p.setPayroll(15);
		
		assert(p.getPayroll()==15);
		
	}// method end
	

}// Class end
