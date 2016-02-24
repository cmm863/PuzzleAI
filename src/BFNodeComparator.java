import java.util.Comparator;

public class BFNodeComparator implements Comparator<BFNode> {
	@Override
	public int compare(BFNode x, BFNode y) {
		if(x.getF() < y.getF()) {
			return -1;
		}
		if(x.getF() > y.getF()) {
			return 1;
		}
		return 0;
	}
}
