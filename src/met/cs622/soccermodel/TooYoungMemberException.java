package met.cs622.soccermodel;

/**
 * Exception when a TeamMember is too young (<18)
 * @author TEIGER--CHASSAGNE Max
 * Date: 05-25-2020
 * Course: CS-622
 */
public class TooYoungMemberException extends Exception {

	private static final long serialVersionUID = -3216682355210921879L;
	
	/**
	 * Default constructor 
	 */
	public TooYoungMemberException() {
		System.out.println("You're trying to set a Team Member age under 18!");
	}
}
