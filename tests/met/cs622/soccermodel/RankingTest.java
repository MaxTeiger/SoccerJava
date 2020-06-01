package met.cs622.soccermodel;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class RankingTest {

	@Test
	void testRank() {
		ArrayList<Team> teams = new ArrayList<Team>();
		
		teams.add(new Team("TE1",1));
		teams.add(new Team("TE2",2));
		teams.add(new Team("TE3",3));
		
		Ranking.rank(teams);
		assert(teams.get(0).getTeamName().equals("TE3"));
		assert(teams.get(1).getTeamName().equals("TE2"));
		assert(teams.get(2).getTeamName().equals("TE1"));
		
		ArrayList<Player> players = new ArrayList<Player>();
		try {
			players.add(new Player("Player 1", 32, 32, 0, 32));
			players.add(new Player("Player 2", 32, 32, 1, 32));
		} catch (TooYoungMemberException e) {
			e.printStackTrace();
		}
		
		Ranking.rank(players);
		
		assert(players.get(0).getName().equals("Player 2"));
		assert(players.get(1).getName().equals("Player 1"));
		
	}

}
