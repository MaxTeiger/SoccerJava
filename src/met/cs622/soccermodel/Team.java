package met.cs622.soccermodel;

import java.util.ArrayList;

/**
 * The class describing a Team, meaning all the members
 * and their current points in the championship.
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
public class Team implements Comparable<Team>{
	private String teamName;
	private ArrayList<TeamMember> teamMembers;
	private int points;

	/**
	 * Constructor Team
	 * @param teamName
	 * @param teamMembers
	 * @param points
	 */
	public Team(String teamName, ArrayList<TeamMember> teamMembers, int points) {
		this.teamName = teamName;
		this.teamMembers = teamMembers;
		this.points = points;
	}

	/**
	 * Constructor Team
	 * @param teamName
	 */
	public Team(String teamName) {
		this.teamName = teamName;
		this.teamMembers = new ArrayList<TeamMember>();
	}
	
	/**
	 * Constructor Team
	 * @param teamName
	 */
	public Team(String teamName, int points) {
		this.teamName = teamName;
		this.teamMembers = new ArrayList<TeamMember>();
		this.points = points;
	}

	/**
	 * return a string with a nice presentation of the team
	 * @return String
	 */
	public String prettyPrintTeam() {

		String playerNames = "", coachName = "";

		for(TeamMember member : this.teamMembers) {
			if(member.getRole() == "Player")
				playerNames += "\t" + member.name + "\n";
			else if (member.getRole() == "Coach")
				coachName = member.name;
		}

		return this.teamName + "\n" 
		+ "====\n"
		+ "Coach: " + coachName
		+ "\nPlayers:\n" 
		+ playerNames 
		+ "Goals: " + this.getTotalGoals()
		+ "\nPoints: " + this.points;
	}

	/**
	 * Return the total number of goals for the team
	 * @return int
	 */
	public int getTotalGoals() {
		int totalGoals = 0;
		for(TeamMember tm : this.teamMembers) {
			if(tm instanceof Player) {
				totalGoals += ((Player) tm).getGoals();
			}
		}

		return totalGoals;
	}

	/**
	 * Return the teamName
	 * @return teamName
	 */
	public String getTeamName() {
		return this.teamName;
	}

	/**
	 * Add a player to the team
	 * @param p
	 */
	public void addPlayer(Player p) {
		this.teamMembers.add(p);
	}
	
	/**
	 * Return the list of the players in the team in an 
	 * ArrayList
	 * @return ArrayList<Player>
	 */
	public ArrayList<Player> getPlayerList(){
		ArrayList<Player> players = new ArrayList<Player>();
		for(TeamMember member : this.teamMembers) {
			if(member.getRole().equalsIgnoreCase("Player")) {
				players.add((Player) member);				
			}		
		}
		return players;
	}
	
	/**
	 * Compare Teams according to their points
	 */
	@Override
	public int compareTo(Team t) {
		if(this.points==t.points) {
			if(this.getTotalGoals()==t.getTotalGoals()) return 0;
			if(this.getTotalGoals()>t.getTotalGoals()) return 1;
			else return -1;
		}
		if(this.points>t.points) return 1;
		
		return -1;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * Delete a player from the index parameter
	 * @param index
	 */
	public void removePlayer(Player toDelete) {
		this.teamMembers.remove(toDelete);
	}
	

}// end class
