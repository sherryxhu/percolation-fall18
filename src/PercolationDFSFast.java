public class PercolationDFSFast extends PercolationDFS {
	/**
	 * calls super to initialize the state in parent class
	 * @param n: int/size
	 */
	public PercolationDFSFast(int n){
		super(n);
	}
	
	/**
	 * checks to see if cell is in top row or if connected to a full cell
	 * if so, call dfs
	 * dfs marks cell as full
	 * @param row, col
	 */
	@Override 
	protected void updateOnOpen(int row, int col) {
	
		if(inBounds(row,col) && row==0) {
			dfs(row,col);
		}
		else if(inBounds(row,col-1)&&isFull(row,col-1)|| 
				inBounds(row, col+1)&&isFull(row, col+1) || 
				inBounds(row-1,col) && isFull(row-1,col) ||
				inBounds(row+1,col)&& isFull(row+1,col)) {
			dfs(row,col);
		}
	}
}

