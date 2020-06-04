package met.cs622.soccermodel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class implements the rankings methods for my application
 * @author Max TEIGER--CHASSAGNE
 *
 * @param <T> 
 */
public abstract class Ranking<T extends Comparable<? super T>> {

	/**
	 * This function rank (sort in descendong order) 
	 * comparable objects. In my application i use it to 
	 * ranks players and teams. 
	 * @param <T>
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void rank(ArrayList<T> arr) {
		Collections.sort(arr, Collections.reverseOrder());
	}// Method end
	
	
}
