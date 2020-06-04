package met.cs622.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import met.cs622.fileIO.*;
import met.cs622.soccermodel.*;

/**
 * The main class to display information to the user.
 * Basically the interface between the user and the application.
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
public class MainMenu {

	private static Scanner sc = new Scanner(System.in);
	private static League league = new League();

	/**
	 * Application's main function
	 * @param args
	 */	
	public static void main(String[] args) {


		league = new League();
		String serializedPath = "files" +File.separator +"serializedLeague.dat";
		try {
			// Initialisation using serialization
			league = FileHandler.deserializeLeague(serializedPath);
		} catch (ClassNotFoundException | IOException e1) {
			System.out.println("Cannot load using serialization, trying with regular file I/O...");
			try {
				// Initialisation of the league using the file : files/players.txt
				FileHandler.createTeams(league);
				FileHandler.teamPoints(league);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}finally {
			mainMenu(); // Launch the main Menu once the initialization is complete	

			try { // save information on players 
				FileHandler.saveCurrentPlayerInfo(league);
				FileHandler.saveCurrentTeamsInfo(league);
				
				// Serialize the league
				FileHandler.serializeLeague(serializedPath, league);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Display the main menu 
	 * @param None
	 */
	public static void mainMenu() {

		boolean out = false;
		char userInput;

		while(!out) { // Menu loop 
			System.out.println("Main Menu\n"
					+ "=========\n"
					+ "What do you want to do?\n"
					+ "1. Manage Teams\n"
					+ "2. Display Ranking\n"
					+ "3. Quit\n");

			userInput = sc.nextLine().charAt(0);

			switch(userInput) {
			case '1':
				teamsMenu();
				break;
			case '2':
				rankingMenu();
				break;
			case '3':
				System.out.println("Bye!");
				out = true;
				break;
			default:
				System.out.println("Wrong input");
			}

		}// Loop end

	}// method end

	/**
	 * Display the menu about managing teams
	 * (let the user choose which team he wants to manage)
	 */
	public static void teamsMenu() {

		boolean out = false;
		int userInput;
		int teamIndex;
		String teamList, nTeamName;

		while(!out) { // Menu loop 
			// generate String to  
			teamIndex=0;
			teamList="";
			for(Team t : league.getTeams()) {
				teamList += (teamIndex+1) +". " +t.getTeamName() +"\n";
				teamIndex++;
			}
			System.out.println("Manage Teams\n"
					+ "============\n"
					+ "Which team do you want to manage?\n"
					+ teamList
					+ (teamIndex+1) +". Add Team\n"
					+ (teamIndex+2) +". Back\n");

			userInput = sc.nextInt();
			sc.nextLine();

			if(userInput == teamIndex+2) { // If the user choose back
				out=true;
				System.out.println("Back");
			}else if(userInput == teamIndex+1) { // If the user chose to add a Team
				System.out.println("What is the name of the team you want to add?");
				nTeamName = sc.next();
				try {
					league.addTeamWithName(nTeamName);
				} catch (ExistingTeamException e) {
					e.printStackTrace();
				}
				sc.nextLine();
			}else if(userInput <= teamIndex){
				Team selectedTeam = league.getTeams().get(userInput-1);
				System.out.println("Selected team: " +selectedTeam.getTeamName());				
				specificTeamMenu(selectedTeam);
			}
		}// Loop end

	}// method end

	/**
	 * Option to modify specific team
	 * @param selectedTeam
	 */
	public static void specificTeamMenu(Team selectedTeam) {
		boolean out=false;
		char userInput;

		while(!out) {
			System.out.println("Team Options\n"
					+ "===========\n"
					+ "1. Add Player\n"
					+ "2. Remove Player\n"
					+ "3. Change coach\n"
					+ "4. Display information\n"
					+ "5. Back\n");

			userInput = sc.nextLine().charAt(0);

			switch(userInput) {
			case '1':
				createAndAddSpecificPlayer(selectedTeam);
				break;
			case '2':
				removePlayerFromTeam(selectedTeam);
				break;
			case '3':
				break;
			case '4':
				System.out.println(selectedTeam.prettyPrintTeam());
				break;
			case '5':
				out=true;
				break;
			default:
				System.out.println("Wrong input...\n\n");

			}
		}
	}

	/**
	 * Menu to remove a player from a selected team
	 * @param selectedTeam
	 */
	private static void removePlayerFromTeam(Team selectedTeam) {

		boolean out = false;
		int userInput;
		int playerIndex;
		String playerList;

		while(!out) { // Menu loop 
			// generate String to display player list 
			playerIndex=0;
			playerList="";
			for(Player p : selectedTeam.getPlayerList()) {
				playerList += (playerIndex+1) +". " +p.getName() +"\n";
				playerIndex++;
			}
			System.out.println("Manage Teams\n"
					+ "============\n"
					+ "Which player do you want to remove?\n"
					+ playerList
					+ (playerIndex+1) +". Back\n");

			userInput = sc.nextInt();
			sc.nextLine();

			if(userInput == playerIndex+1) { // If the user choose back
				out=true;
				System.out.println("Back");	
			}else if(userInput <= playerIndex){ // If the user selects a player, we delete it from the team 
				System.out.println("Selected player: " +selectedTeam.getPlayerList().get(userInput-1).getName());
				Player toDelete = selectedTeam.getPlayerList().get(userInput-1);
				selectedTeam.removePlayer(toDelete);
				out = true;
			}
		}// Loop end
	}// Method end

	/**
	 * This methods asks the user some information on a player 
	 * create and add the player in the specified team given as 
	 * a parameter.
	 * @param t
	 */
	public static void createAndAddSpecificPlayer(Team t) {
		String firstName, lastName;
		int age, payroll, goals, price;
		System.out.println("What is the player first name?");
		firstName=sc.next();
		System.out.println("What is the player last name?");
		lastName=sc.next();
		System.out.println("What is the player age?");
		age=sc.nextInt();
		System.out.println("What is his payroll?");
		payroll=sc.nextInt();
		System.out.println("How many goals did he score?");
		goals=sc.nextInt();
		System.out.println("What is his price?");
		price=sc.nextInt();

		sc.nextLine();
		TeamMember p=null;
		try {
			p = new Player(firstName+" " +lastName, age, payroll, goals, price);
		} catch (TooYoungMemberException e) {
			e.printStackTrace();
		}

		t.addPlayer((Player) p);

		System.out.println("Player " +p.getName() +" now plays for team " +t.getTeamName());
	}

	/**
	 * Display the ranking menu 
	 * @param None
	 */
	public static void rankingMenu() {

		boolean out = false;
		char userInput;
		int rank;
		ArrayList<Player> allPlayers;

		while(!out) { // Menu loop 
			System.out.println("Ranking Menu\n"
					+ "=========\n"
					+ "1. Teams ranking\n"
					+ "2. Players ranking\n"
					+ "3. Custom filters on players\n"
					+ "4. Back\n");

			userInput = sc.nextLine().charAt(0);

			switch(userInput) {
			case '1':
				rank = 1;
				ArrayList<Team> allTeams = league.getTeams();
				Ranking.rank(allTeams);
				for(Team t:allTeams) {
					System.out.println(rank + ":\t" +t.getTeamName() + "\tGoals: " +t.getTotalGoals() +"\tPoints: "
							+ t.getPoints());
					rank++;
				}
				break;
			case '2':
				rank = 1;
				allPlayers = league.getAllPlayers();
				Ranking.rank(allPlayers);

				for(Player p: allPlayers) {
					System.out.println(rank+ ":\t" +p.getName() + "\t Goals: " +p.getGoals());
					rank++;
				}
				break;
			case '3':
				// 0: age 1: payroll 2: goals 3: price
				int[] filters = setUserFilters();
				allPlayers = league.getAllPlayers();
				
				// Stream creation
				Stream<Player> playersStream = allPlayers.stream();
				
				// We apply the filters to the stream 
				playersStream.filter(x -> x.getAge() > filters[0])
				.filter(x -> x.getPayroll() > filters[1])
				.filter(x -> x.getGoals() > filters[2])
				.filter(x -> x.getPrice() > filters[3])
				.forEach(x -> {
					System.out.println(
						x.getName() + "\tGoals: "
						+ x.getGoals() +"\tPayroll: "
						+ x.getPayroll() +"\tPrice: "
						+ x.getPrice());});
				
				playersStream.close();
				break; 
			case '4':
				out = true;
				break;
			default:
				System.out.println("Wrong input");
			}

		}// Loop end

	}// method end
	
	/**
	 * This method is used in the ranking menu to retrieve 
	 * what filter the user wants to use 
	 * index 0: age 1: payroll 2: goals 3: price
	 * @return int[] 
	 */
	public static int[] setUserFilters() {
		
		// asks the user what filters he wants to apply
		int age, payroll, goals, price;
		System.out.println("What minimum age do you want to display?");
		age=sc.nextInt();
		System.out.println("What minimum payroll do you want to display?");
		payroll=sc.nextInt();
		System.out.println("What minimum number of goals you want to display?");
		goals=sc.nextInt();
		System.out.println("What minimum price do you want to display?");
		price=sc.nextInt();
		
		sc.nextLine(); // empty buffer
		int[] filters = {age, payroll, goals, price};
		return filters;
		
	}
}
