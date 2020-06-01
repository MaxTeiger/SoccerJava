package met.cs622.fileIO;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import met.cs622.soccermodel.ExistingTeamException;
import met.cs622.soccermodel.League;
import met.cs622.soccermodel.Player;
import met.cs622.soccermodel.Team;
import met.cs622.soccermodel.TooYoungMemberException;

/**
 * Class used to test methods of file handler 
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
class FileHandlerTest {

	@Test
	void testCreateTeams() {
		FileHandler.setFilePathForTest("files"+File.separator+"tests"+File.separator+"playersReadTest.txt");
		League l = new League();

		try {
			FileHandler.createTeams(l);
		} catch (IOException e) {
			fail("Problem reading the file.");
		}

		assertEquals(l.getTeams().get(0).getTeamName(), "ARS");

		assertEquals(l.getTeams().get(0).getPlayerList().get(0).getName(), "Cristiano Ronaldo");

	}

	@Test
	void testSaveCurrentPlayerInfo() {
		League l = new League();

		try { // adding a team in the league 
			l.addTeamWithName("BOB");
		} catch (ExistingTeamException e) {
			e.printStackTrace();
		}

		Player p=null;
		try {
			p = new Player("Max Teiger", 30, 30, 30, 30);
		} catch (TooYoungMemberException e1) {
			e1.printStackTrace();
			fail("fail to create a player");
		}
		try {
			l.addPlayerInTeam("BOB", p);
		} catch (ExistingTeamException e) {
			e.printStackTrace();
			fail("fail to add a player in a team");
		}

		try {
			FileHandler.setFilePathForTest("files"+File.separator+"tests"+File.separator+"playersWriteTest.txt");
			FileHandler.saveCurrentPlayerInfo(l);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("File not found");
		}

	}// Method end
	
	@Test
	void testTeamPoints() {
		FileHandler.setTeamsFilePathForTest("files"+File.separator+"tests"+File.separator+"teamsTest.txt");
		League l = new League();

		try {
			FileHandler.teamPoints(l);
		} catch (IOException e) {
			fail("Problem reading the file.");
		}
		assertEquals(l.getTeams().get(0).getPoints(), 86);
	}// method end
	
	@Test
	void testSaveCurrentTeamsInfo() {
		League l = new League();
		l.addTeam(new Team("LIV", 12));
		l.addTeam(new Team("NOT", 8));
		
		FileHandler.setTeamsFilePathForTest("files"+File.separator+"tests"+File.separator+"teamsWriteTest.txt");
		try {
			FileHandler.saveCurrentTeamsInfo(l);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}// method end

}// Class End
