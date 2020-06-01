package met.cs622.soccermodel;

/**
 * Exception returned when a team already exists in
 * a league
 * @author Max TEIGER--CHASSAGNE 
 * Date: 05-25-2020
 * Course: CS-622
 */
public class ExistingTeamException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor 
	 */
	public ExistingTeamException() {
		System.out.println("Trying to add a team with existing name");
	}

}
