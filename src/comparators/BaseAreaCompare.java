package comparators;
import java.util.Comparator;

import shapes.*;

public class BaseAreaCompare implements Comparator<Shape>{

	@Override
	public int compare(Shape arg0, Shape arg1) {
		return Double.compare(arg0.calcBaseArea(), arg1.calcBaseArea());
	}

}
