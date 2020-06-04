package met.cs622.soccermodel;

/**
 * This class represent a player 
 * @author Max TEIGER--CHASSAGNE
 * Date: 05-25-2020
 * Course: CS-622
 */
public class Player extends TeamMember implements Comparable<Player> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6910565782377782752L;
	private int goals;
	private int price;

	public Player(String name, int age, int payroll, int goals, int price) throws TooYoungMemberException {
		super(name, age, payroll);
		this.goals = goals;
		this.price = price;
		
	}

	@Override
	public String getRole() {
		return "Player";
	}
	
	/**
	 * Return the number of goals for the player
	 * @return int
	 */
	public int getGoals() {
		return goals;
	}
	
	/**
	 * Set the number of goals for a player
	 * @param goals
	 */
	public void setGoals(int goals) {
		this.goals = goals;
	}
	
	/**
	 * Return the price of the player
	 * @return int
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Compare players according to their number of goals
	 */
	@Override
	public int compareTo(Player p) {
		Player compareToPlayer = (Player) p;
		
		if(this.goals < compareToPlayer.goals) {
			return -1;
		}else if(this.goals>compareToPlayer.goals) {
			return 1;
		}else{
			return 0;
		}
	}

}

