package telran.util;

import java.util.Comparator;

public class ProximityNumberComparator implements Comparator<Integer> {
int number;

	public ProximityNumberComparator(int number) {
	this.number = number;
}

	

	@Override
	public int compare(Integer o1, Integer o2) {
		
		return Math.abs(number - o1) - Math.abs(number - o2);
	}

}
