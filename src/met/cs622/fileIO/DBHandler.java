package met.cs622.fileIO;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import met.cs622.soccermodel.League;
import met.cs622.soccermodel.Player;
import met.cs622.soccermodel.Team;

public class DBHandler {

	private Connection conn = null;
	/**
	 * Create 
	 * @param dbfilename
	 * @throws SQLException
	 */
	public DBHandler(String dbfilename) throws SQLException {
		// TODO Auto-generated constructor stub
		if(this.conn == null) {
				connect(dbfilename);
		}else {
			System.out.println("Connection already opened");
		}

	}

	/**
	 * Create the connection in order to use the
	 * database
	 * @param dbfilename
	 * @throws SQLException
	 */
	private void connect(String dbfilename) throws SQLException {
		File dbfile=new File(".");
		String url = "jdbc:sqlite:"
				+dbfile.getAbsolutePath()
				+File.separator
				+"files"
				+File.separator
				+dbfilename;

		this.conn = DriverManager.getConnection(url);
		System.out.println("Connection to db successfull");
	}

	/**
	 * Add a player to the database
	 * @param p
	 * @param teamId
	 * @throws SQLException
	 */
	public void addPlayerToDB(Player p, int teamId) throws SQLException {
		String query = "INSERT INTO "
				+ "players(name, goals, payroll, price, team_id)"
				+ " values(? , ? , ? , ?, ?)";
		PreparedStatement prepared = this.conn.prepareStatement(query);

		prepared.setString(1, p.getName());
		prepared.setInt(2, p.getGoals());
		prepared.setInt(3, p.getPayroll());
		prepared.setInt(4, p.getPrice());
		prepared.setInt(5, teamId);
		prepared.execute();
	}

	/**
	 * Add a team to the Database
	 * @param t
	 * @throws SQLException
	 */
	public void addTeamToDB(Team t) throws SQLException {
		String query = "INSERT INTO teams(name, points) "
				+ "VALUES(?, ?)";
		PreparedStatement prepared = this.conn.prepareStatement(query);

		prepared.setString(1, t.getTeamName());
		prepared.setInt(2, t.getPoints());

		prepared.execute();
	}

	/**
	 * Update information on players and teams in the database 
	 * if the team to update doesn't exist, we call the method
	 * addTeamToDB to add the specific team in the DB
	 * Same for players, where we call the method 
	 * addPlayerToDB
	 * @param l
	 * @throws SQLException
	 */
	public void updateLeagueDB(League l) throws SQLException{
		String updateTeamQuery = "UPDATE teams SET name = ? , points = ? "
				+ "WHERE name = ?";
		String updatePlayerQuery = "UPDATE players "
				+ "SET name = ?, goals = ?, payroll = ?, price = ?, team_id = ? "
				+ "WHERE name = ?";
		String queryTeamId = "SELECT id "
				+ "FROM teams "
				+ "WHERE name = ? LIMIT 1";

		PreparedStatement updateTeamStatement = 
				this.conn.prepareStatement(updateTeamQuery);
		PreparedStatement updatePlayerStatement = 
				this.conn.prepareStatement(updatePlayerQuery);
		PreparedStatement retrieveTeamIdStatement = 
				this.conn.prepareStatement(queryTeamId);

		int teamId, updatedRows;

		for(Team t : l.getTeams()) { // for each team 

			try {
				updateTeamStatement.setString(1, t.getTeamName());
				updateTeamStatement.setInt(2, t.getPoints());
				updateTeamStatement.setString(3, t.getTeamName());
				updatedRows = updateTeamStatement.executeUpdate();

				if(updatedRows==0) {
					this.addTeamToDB(t);
				}

				retrieveTeamIdStatement.setString(1, t.getTeamName());
				teamId = (int) retrieveTeamIdStatement.executeQuery().getObject(1);

				for(Player p : t.getPlayerList()) { // For each player in the team
					try {

						updatePlayerStatement.setString(1, p.getName());
						updatePlayerStatement.setInt(2, p.getGoals());
						updatePlayerStatement.setInt(3, p.getPayroll());
						updatePlayerStatement.setInt(4, p.getPrice());
						updatePlayerStatement.setInt(5, teamId);
						updatePlayerStatement.setString(6, p.getName());

						updatedRows = updatePlayerStatement.executeUpdate();
						if(updatedRows == 0) {
							this.addPlayerToDB(p, teamId);
						}
					} catch (SQLException e) {
					}
				}
			} catch (SQLException e) {
			}
		}

		updateTeamStatement.close();
	}// Method end

	/**
	 * Display information about ranking and goals for each 
	 * teams but using the DataBase
	 * @throws SQLException
	 */
	public void getTeamsInformationFromDB() throws SQLException {
		String query = "SELECT TEAMS.name, TEAMS.points, "
				+ "sum(PLAYERS.goals) as 'Goals' "
				+ "FROM PLAYERS JOIN TEAMS "
				+ "ON PLAYERS.team_id = TEAMS.id "
				+ "GROUP BY TEAMS.id "
				+ "ORDER BY points DESC";

		PreparedStatement prepared = this.conn.prepareStatement(query);
		ResultSet rs = prepared.executeQuery();
		ResultSetMetaData resultMeta = rs.getMetaData();

		System.out.println("\n**************************"
				+ "************************");

		//Display columns
		System.out.print("*");
		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
			System.out.print("\t"
					+ resultMeta.getColumnName(i).toUpperCase() 
					+ "\t *");

		System.out.println("\n**************************"
				+ "************************");

		while(rs.next()) {
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t"
						+ rs.getObject(i).toString() 
						+ "\t |");

			System.out.println("\n----------------------"
					+ "----------------------------");
		}// while end
	}// method end

	/**
	 * Display an ordered list of player either on their name, 
	 * or on their number of goals
	 * @param orderInput (either "name" or "goals")
	 * @throws SQLException
	 */
	public void displayPlayersOrdered(String orderInput) throws SQLException {

		String query = "SELECT name, goals "
				+ "FROM players ORDER BY " +orderInput;
		if (orderInput == "goals")
			query+= " DESC";

		PreparedStatement prepared = this.conn.prepareStatement(query);

		ResultSet rs = prepared.executeQuery();
		ResultSetMetaData resultMeta = rs.getMetaData();

		System.out.println("\n**********************************");
		//Display columns
		System.out.print("*");
		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
			System.out.print("\t"
					+ resultMeta.getColumnName(i).toUpperCase() 
					+ "\t *");

		System.out.println("\n**********************************");

		while(rs.next()) {
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t"
						+ rs.getObject(i).toString() 
						+ "\t |");

			System.out.println("\n---------------------------------");
		}

		rs.close();
		prepared.close();

	}
	
	/**
	 * Only used for test purposes
	 * @throws SQLException
	 */
	public void deleteAll() throws SQLException {
		String deleteFromTeams = "DELETE FROM TEAMS";
		String deleteFromPlayers = "DELETE FROM PLAYERS";
		PreparedStatement prepared1 = this.conn.prepareStatement(deleteFromTeams);
		PreparedStatement prepared2 = this.conn.prepareStatement(deleteFromPlayers);
		
		prepared1.execute();
		prepared2.execute();
	}

	/**
	 * Close the connection to avoid any 
	 * problems (database lock)
	 */
	public void close() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}// Class End
