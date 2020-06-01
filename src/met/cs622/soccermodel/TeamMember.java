package met.cs622.soccermodel;

/**
 * This class represent the member of a team with
 * useful attributes for the team
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
public abstract class TeamMember {
	protected String name;
	protected int age;
	protected int payroll;

	/**
	 * TeamMember constructor
	 * @param name
	 * @param age
	 * @param payroll
	 * @throws TooYoungMemberException 
	 */
	public TeamMember(String name, int age, int payroll) throws TooYoungMemberException {
		this.name = name;
		this.payroll = payroll;
		
		if(age>=18) {
			this.age = age;
		}else {
			throw new TooYoungMemberException();
		}
	}

	/**
	 * Return a string containing the role of the team
	 * member.
	 * @return String
	 */
	public abstract String getRole();

	/**
	 * Return the name of the team member
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the TeamMember
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Return the payroll of the team member
	 * @return int
	 */
	public int getPayroll() {
		return payroll;
	}
	
	/**
	 * Set the payroll of the team member
	 * @param payroll
	 */
	public void setPayroll(int payroll) {
		this.payroll = payroll;
	}
	
	/**
	 * Return the age of the team member
	 * @return int 
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Set the variable age
	 * @param age
	 * @throws TooYoungMemberException 
	 */
	public void setAge(int age) throws TooYoungMemberException {
		
		if(age>=18) {
			this.age = age;
		}else {
			throw new TooYoungMemberException();
		}
	}

}
