package MonitorService;

import java.util.Comparator;


public class MessageComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		if (o1<5)
			return 1;
		else if (o1> 15)
			return 2;
		else 
			return 3;
	}
}