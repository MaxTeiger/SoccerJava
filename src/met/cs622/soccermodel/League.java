package met.cs622.soccermodel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains all the informations about a league.
 * (teams, rankings, points)
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
public class League implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4241626652013472548L;
	private ArrayList<Team> teams;

	public League() 
	// Constructor Method
	{
		this.teams = new ArrayList<Team>();
	}

	/**
	 * Method to add a team in the league based on the name of
	 * the team. (3 letters code)
	 * @param tm
	 * @throws ExistingTeamException 
	 */
	public void addTeamWithName(String tm_name) throws ExistingTeamException {
		boolean existing=false;
		for(Team t : this.teams) {
			if(t.getTeamName().equalsIgnoreCase(tm_name)) {
				existing = true;
				throw new ExistingTeamException();
			}
		}

		if(!existing && tm_name.length()==3) {
			this.teams.add(new Team(tm_name));
		}
	}

	/**
	 * Add a team in the league
	 * @param teamName
	 * @param points
	 * @throws ExistingTeamException 
	 */
	public void addTeam(Team toAdd){
		boolean existing=false;
		for(Team t : this.teams) {
			if(t.getTeamName().equalsIgnoreCase(toAdd.getTeamName())) {
				existing = true;
				t.setPoints(toAdd.getPoints()); // If the team is already in the league, set points
			}
		}

		if(!existing && toAdd.getTeamName().length()==3) {
			this.teams.add(toAdd);
		}
	}

	/**
	 * Add a new player in the specified team
	 * @param teamName
	 * @param new_p
	 * @throws ExistingTeamException 
	 */
	public void addPlayerInTeam(String teamName, Player new_p) throws ExistingTeamException {
		boolean added=false;
		for(Team t:this.teams) {
			if(t.getTeamName().equalsIgnoreCase(teamName)) {
				t.addPlayer(new_p);
				added=true;
			}
		}

		// if the team doesn't exist
		if(!added) {
			this.addTeamWithName(teamName);
			for(Team t:this.teams) {
				if(t.getTeamName().equalsIgnoreCase(teamName)) {
					t.addPlayer(new_p);
					added=true;
				}
			}
		}
	}

	/**
	 * Return the list of the teams in the 
	 * league
	 * @return ArrayList<Team> 
	 */
	public ArrayList<Team> getTeams(){
		return this.teams;
	}

	public ArrayList<Player> getAllPlayers(){
		ArrayList<Player> allPlayers= new ArrayList<Player>();
		for(Team t:this.teams) {
			for(Player p : t.getPlayerList()) {
				allPlayers.add(p);
			}
		}// for loop end

		return allPlayers;

	}// method end

}// Class end
