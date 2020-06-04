package met.cs622.fileIO;

//Scanner: read from a textfile
import java.util.Scanner;

import met.cs622.soccermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
/**
 * Class used to read and write the information in the
 * files 
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
public class FileHandler
{

	private static String filePath = "files"+File.separator+"players.txt";
	private static String teamsFilePath = "files"+File.separator+"teams.txt";

	/**
	 * Load the informations about the league from a text file
	 * @param l
	 * @throws IOException
	 */
	public static void createTeams(League l) throws IOException
	{
		Scanner infile = new Scanner(new File(filePath)); 

		String name, team;
		int age, payroll, goals, price;

		while (infile.hasNext())
		{         
			team = infile.next();
			name = infile.next() +" " +infile.next();
			age = infile.nextInt();
			payroll = infile.nextInt();
			goals = infile.nextInt();
			price = infile.nextInt();

			Player player=null;
			try {
				player = new Player(name, age, payroll, goals, price);
			} catch (TooYoungMemberException e) {
				e.printStackTrace();
			} 

			try {
				l.addPlayerInTeam(team, player);
			} catch (ExistingTeamException e) {
				e.printStackTrace();
			}


		}

		infile.close();
		System.out.println("Data loaded successfully!");
	}

	/**
	 * Save information about the players in a file
	 * @param l
	 * @throws FileNotFoundException
	 */
	public static void saveCurrentPlayerInfo(League l) throws FileNotFoundException {
		PrintWriter outfilepw = new PrintWriter(filePath); 

		for(Team t : l.getTeams()) {
			for(Player p : t.getPlayerList()) {
				outfilepw.format("%3s "
						+ "%20s "
						+ "%3d "
						+ "%8d "
						+ "%4d "
						+ "%10d%n", t.getTeamName(), p.getName(), p.getAge(), p.getPayroll(), p.getGoals(), p.getPrice());
			}
		}


		outfilepw.close();
		System.out.println("Data saved successfully in " +filePath);
	}

	/**
	 * it is used to set the filePath 
	 * (used by the test's methods)
	 * @param fPath
	 */
	public static void setFilePathForTest(String fPath) {
		FileHandler.filePath = fPath;
	}

	public static void setTeamsFilePathForTest(String fPath) {
		FileHandler.teamsFilePath = fPath;
	}

	/**
	 * Load the informations about the league from a text file
	 * @param l
	 * @throws IOException
	 */
	public static void teamPoints(League l) throws IOException
	{
		Scanner infile = new Scanner(new File(teamsFilePath)); 

		String name;
		int points;
		Team teamToAdd;

		while (infile.hasNext())
		{         
			name = infile.next();
			points = infile.nextInt();

			teamToAdd = new Team(name, points);

			l.addTeam(teamToAdd);


		}// loop end

		infile.close();
		System.out.println("Teams data loaded successfully!");
	}// method end

	/**
	 * Save information about the teams in a file
	 * @param l
	 * @throws FileNotFoundException
	 */
	public static void saveCurrentTeamsInfo(League l) throws FileNotFoundException {
		PrintWriter outfilepw = new PrintWriter(teamsFilePath); 

		for(Team t : l.getTeams()) {
			outfilepw.format("%3s "
					+ "%3d\n", t.getTeamName(), t.getPoints());
		}// loop end

		outfilepw.close();
		System.out.println("Teams data saved successfully in " +teamsFilePath);
	}
	
	/**
	 * This method saves the current object League in a file
	 * using serialization.
	 * @param filePath
	 * @param league
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void serializeLeague(String filePath, League league) throws FileNotFoundException, IOException {
		File serializationFic = new File(filePath);
		// Stream opening on the file
		ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(serializationFic));
		oos.writeObject(league);
		oos.close();
		System.out.println("Succesfully saved using Serialization FH");
	}
	
	/**
	 * This method retrieve the object league from the file 
	 * specified using deserialization
	 * @param filePath
	 * @return League
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static League deserializeLeague(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
		File serializationFic = new File(filePath);
		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(serializationFic)) ;
		
		// Read Object from the file 
		League l = (League)ois.readObject();
		// Close the stream
		ois.close();
		return l; 
	}

}// class end