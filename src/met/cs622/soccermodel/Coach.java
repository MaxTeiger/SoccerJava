package met.cs622.soccermodel;

/**
 * Class representing a coach 
 * inherit from TeamMember
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
public class Coach extends TeamMember {

	@SuppressWarnings("unused")
	private int trophiesWon;
	
	/**
	 * Object constructor
	 * @param name
	 * @param age
	 * @param payroll
	 * @param trophiesWon
	 * @throws TooYoungMemberException
	 */
	public Coach(String name, int age, int payroll, int trophiesWon) throws TooYoungMemberException {
		super(name, age, payroll);
		this.trophiesWon = trophiesWon;
	}

	@Override
	public String getRole() {
		return "Coach";
	}
}
