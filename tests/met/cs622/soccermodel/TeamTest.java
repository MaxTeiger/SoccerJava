package met.cs622.soccermodel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Test methods from class Team
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
class TeamTest {

	@Test
	void testTeam() {
		Team tm=null;
		try {
			tm = teamCreation();
		} catch (TooYoungMemberException e) {
			e.printStackTrace();
		}

		assert (tm) != null;
	}

	@Test
	void testGetTotalGoals() {
		Team tm=null;
		try {
			tm = teamCreation();
		} catch (TooYoungMemberException e) {
			e.printStackTrace();
		}
		assertEquals(360, tm.getTotalGoals());
	}

	@Test
	void testAddGetPlayer() {
		// test functions getPlayerList and addPlayer
		Team t = null;
		try {
			t = teamCreation();
		} catch (TooYoungMemberException e) {
			e.printStackTrace();
			fail("Cannot create team");
		}

		ArrayList<Player> expected = t.getPlayerList();

		for(int playerIndex = 0; playerIndex<t.getPlayerList().size();playerIndex++) {
			assertEquals(t.getPlayerList().get(playerIndex), expected.get(playerIndex));
		}

		Player p=null;
		try {
			p = new Player("John Test", 20, 15,15, 15 );
		} catch (TooYoungMemberException e) {
			e.printStackTrace();
			fail("Cannot create player");
		}

		t.addPlayer(p);
		expected.add(p);

		for(int playerIndex = 0; playerIndex<t.getPlayerList().size();playerIndex++) {
			assertEquals(t.getPlayerList().get(playerIndex), expected.get(playerIndex));
		}

	}

	@Test
	void testPrettyPrintTeam() {
		Team t = null;

		try {
			t = teamCreation();
		} catch (TooYoungMemberException e) {
			e.printStackTrace();
		}

		assert t.prettyPrintTeam().matches("(.|\n)*(Points: 54)$");
	}
	/**
	 * Methods which create an return the team used in 
	 * test methods.
	 * @return Team 
	 * @throws TooYoungMemberException
	 */
	public Team teamCreation() throws TooYoungMemberException {
		TeamMember p1 = new Player("Cristiano Ronaldo", 32, 980000, 180, 100000000);
		TeamMember p2 = new Player("Leo Messi", 31, 880000, 180, 100000000);

		TeamMember coach = new Coach("Jurgen Klopp", 50, 200000, 5);

		ArrayList<TeamMember> teamMembers = new ArrayList<TeamMember>(Arrays.asList(p1,p2,coach));

		Team tm = new Team("Example Team", teamMembers, 54);
		return tm;
	}

	@Test
	void testCompareTo() {
		Team t1 = new Team("TE1", 1);
		Team t2 = new Team("TE2", 2);

		assert(t2.compareTo(t1)==1);
		assert(t2.compareTo(t2)==0);
		assert(t1.compareTo(t2)==-1);

		Team t3 = new Team("TE3", 1);

		Player p1=null, p2=null;
		try {
			p1 = new Player("Player Test", 32, 12, 2, 12);
			p2 = new Player("Player Test2", 32, 12, 1, 12);
		} catch (TooYoungMemberException e) {
			fail("cannot create player");
		}
		
		t1.addPlayer(p1);
		t2.addPlayer(p2);
		
		assert(t1.compareTo(t3)==1);
		assert(t3.compareTo(t1)==-1);
		


	}

	@Test
	void testRemovePlayer() {
		Team t = new Team("BOB");
		Player p=null;
		try {
			p = new Player("Test test", 32, 32 ,32, 32);
		} catch (TooYoungMemberException e) {
			fail("Cannot create players");
		}
		t.addPlayer(p);

		assert(t.getPlayerList().size()==1);
		t.removePlayer(p);
		assert(t.getPlayerList().size()==0);
	}
}
