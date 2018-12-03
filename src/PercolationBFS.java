import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{
	public PercolationBFS(int n) {
		super(n);
	}
	
	/**
	 * add comments
	 */
	@Override
	protected void dfs(int row, int col) {
		Queue<Integer> q = new LinkedList<>();
		myGrid[row][col] = FULL;
		q.add(row*myGrid.length+col);
		while(!q.isEmpty()) {
			int value = q.poll();
			int r = value/myGrid.length;
			int c = value%myGrid.length;
			int[] rows = {r-1,r+1,r,r};
			int[] cols = {c,c,c-1,c+1};
			for(int i = 0; i<4; i++) {
				if(inBounds(rows[i],cols[i])&&isOpen(rows[i],cols[i])&&(!isFull(rows[i],cols[i]))) {
					myGrid[rows[i]][cols[i]] = FULL;
					q.add(rows[i]*myGrid.length+cols[i]);
				}
			}
		}
	}

}
