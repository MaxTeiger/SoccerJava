package met.cs622.fileIO;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import met.cs622.soccermodel.ExistingTeamException;
import met.cs622.soccermodel.League;
import met.cs622.soccermodel.Player;
import met.cs622.soccermodel.Team;
import met.cs622.soccermodel.TooYoungMemberException;

class DBHandlerTest {

	static Connection conn = null;

	@BeforeAll
	static void setUpBeforeClass() {
		try {
			DBHandler db = new DBHandler("metcs622_soccerjava_test.db");
			db.deleteAll();
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Cannot connect to DB");
		}
	}


	@Test
	void testDisplayPlayersOrdered() {
		try {
			DBHandler db = new DBHandler("metcs622_soccerjava_test.db");
			db.displayPlayersOrdered("name");
			db.displayPlayersOrdered("goals");

			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Cannot connect to DB");
		}
	}

	@Test
	void testDBHandler() {
		try {
			DBHandler db = new DBHandler("metcs622_soccerjava_test.db");
			db.deleteAll();
			db.close();
			assert(true);
		} catch (SQLException e) {
			fail("Cannot connect to DB");
		}
	}

	@Test
	void testAddPlayerToDB() {
		Player t=null;
		try {
			t = new Player("TES", 30, 10, 10, 10);
		} catch (TooYoungMemberException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DBHandler db=null;
		try {
			db = new DBHandler("metcs622_soccerjava_test.db");
			assert(true);
		} catch (SQLException e) {
			fail("Cannot connect to DB");
		}

		try {
			db.addPlayerToDB(t, 1);
			db.close();
		} catch (SQLException e) {
			fail("cannot add team to db");
			e.printStackTrace();
		}
	}

	@Test
	void testAddTeamToDB() {
		Team t = new Team("TES");
		DBHandler db=null;
		try {
			db = new DBHandler("metcs622_soccerjava_test.db");
			assert(true);
		} catch (SQLException e) {
			fail("Cannot connect to DB");
		}

		try {
			db.addTeamToDB(t);
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();

			fail("cannot add team to db");
		}

	}

	@Test
	void testUpdateLeagueDB() {
		League l = new League();
		Team t = new Team("TES");
		Player p = null;

		try {
			p = new Player("Roberto", 20, 20, 20, 20);
		} catch (TooYoungMemberException e1) {
			e1.printStackTrace();
		}

		l.addTeam(t);
		try {
			l.addPlayerInTeam("TES", p);
		} catch (ExistingTeamException e1) {
			e1.printStackTrace();
			fail("cannot add player in team");
		}

		try {
			DBHandler db = new DBHandler("metcs622_soccerjava_test.db");
			db.updateLeagueDB(l);
			db.close();
			assert(true);
		} catch (SQLException e) {
			fail("Cannot connect to DB");
		}
	}

	@Test
	void testgetTeamsInformationFromDB() {
		try {
			DBHandler db = new DBHandler("metcs622_soccerjava_test.db");
			db.getTeamsInformationFromDB();
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Cannot connect to DB");
		}
	}

}
