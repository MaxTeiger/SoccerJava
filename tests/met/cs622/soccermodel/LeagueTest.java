package met.cs622.soccermodel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Class methods from league class
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
class LeagueTest {
	
	/**
	 * Test for the method to add a team 
	 * in the league
	 */
	@Test
	void testAddTeamWithName() {
		League l = new League();
		try {
			l.addTeamWithName("BOB");
		} catch (ExistingTeamException e) {
			fail("Adding BOB failed");
		}
		
		try {
			l.addTeamWithName("BOB");
			fail("Illegal assert");
		} catch (ExistingTeamException e) {
			
		}
	}
	
	/**
	 * Test for the method to add a player in a team
	 */
	@Test
	void testAddPlayerInTeam() {
		League l = new League();
		try {
			l.addTeamWithName("BOB");
		} catch (ExistingTeamException e) {
			e.printStackTrace();
		}
		
		Player new_p = null;
		try {
			new_p = new Player("Max T", 35, 35, 35, 35);
		} catch (TooYoungMemberException e) {
			fail("Cannot create player");
		}
		try {
			l.addPlayerInTeam("BOB", new_p);
		} catch (ExistingTeamException e) { // in case it fail
			fail("Cannot add player to team");
		}
	}
	
	/**
	 * test the function which returns the teams 
	 * in the league
	 */
	@Test
	void testGetTeams() {
		League l = new League();
		try {
			l.addTeamWithName("BOB");
			l.addTeamWithName("TES");
			l.addTeamWithName("MAX");
		} catch (ExistingTeamException e) {
			e.printStackTrace();
		}

		Team t1 = new Team("BOB");
		Team t2 = new Team("TES");
		Team t3 = new Team("MAX");
		ArrayList<Team> expected = new ArrayList<Team>();
		expected.add(t1);
		expected.add(t2);
		expected.add(t3);
		
		for(int teamIndex = 0; teamIndex<l.getTeams().size();teamIndex++) {
			assertEquals(l.getTeams().get(teamIndex).getTeamName(), expected.get(teamIndex).getTeamName());
		}
		
	}
	
	@Test
	void testGetAllPlayers() {
		League l = new League();
		Player p1 = null, p2 = null;
		try {
			p1 = new Player("Leo Messi", 32, 32, 32, 32);
			p2 = new Player("Max Messi", 32, 32, 32, 32);
			
		} catch (TooYoungMemberException e) {
			e.printStackTrace();
		}
		try {
			l.addPlayerInTeam("BOB", p1);
			l.addPlayerInTeam("BOB", p2);
		} catch (ExistingTeamException e) {
			e.printStackTrace();
		}
		
		ArrayList<Player> allPlayersTest = l.getAllPlayers();
		
		assert(allPlayersTest.get(0).getName().equals("Leo Messi"));
		assert(allPlayersTest.get(1).getName().equals("Max Messi"));
		
	}
	
	@Test 
	void testAddTeam() {
		League l = new League();
		
		assert(l.getTeams().size()==0);
		Team t1 = new Team("BOB");
		Team t2 = new Team("BOB");
		l.addTeam(t1);
		l.addTeam(t2);
		
		assert(l.getTeams().size()==1);
	}

}
