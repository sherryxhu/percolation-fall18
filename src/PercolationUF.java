public class PercolationUF implements IPercolate{
	/**
	 * instance variables
	 */
	private boolean[][] myGrid; 
	int myOpenCount;
	IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	/**
	 * calls initialize to initialize myFinder
	 * VTOP and VBOTTOM set into int values
	 * @param size
	 * @param finder
	 */
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		finder.initialize(size*size+2);
		myFinder = finder;
		VTOP = size*size;
		VBOTTOM = size*size+1;
	}
	
	/**
	 * throws exception when needed
	 * checks to see if cell is already open
	 * if not open, marks cell as open and connects with open neighbors
	 */
	@Override
	public void open(int row, int col) {
		if (row < 0 || row >= myGrid.length) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (col < 0 || col >= myGrid[0].length) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if(myGrid[row][col]!=true) {
			myGrid[row][col]=true;
			myOpenCount++;
			if(row==0) myFinder.union(row*myGrid.length+col, VTOP);
			if(row==myGrid.length-1) myFinder.union(row*myGrid.length+col, VBOTTOM);
			
			int[] rows = {row-1,row+1,row,row};
			int[] cols = {col,col,col-1,col+1};
			for(int i = 0; i<rows.length;i++) {
				if(rows[i]>=0 && rows[i]< myGrid.length && cols[i]>=0 && cols[i] < myGrid.length && isOpen(rows[i],cols[i])){
					myFinder.union(row*myGrid.length+col, rows[i]*myGrid.length+cols[i]);
				}
			}
		}
	}
	
	/**
	 * returns true when the cell is open
	 * returns false when cell is not open
	 * throws exception when needed
	 */
	@Override
	public boolean isOpen(int row, int col) {
		if (row < 0 || row >= myGrid.length) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (col < 0 || col >= myGrid[0].length) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}
	
	/**
	 * returns true when cell is full
	 * returns false when cell is not full
	 * throws exception when needed
	 */
	@Override
	public boolean isFull(int row, int col) {
		if (row < 0 || row >= myGrid.length) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (col < 0 || col >= myGrid[0].length) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myFinder.connected(VTOP, row*myGrid.length + col);
	}
	
	/**
	 * returns true if system percolates
	 * returns false if system does not percolate
	 * does this by calling connected with parameters VTOP and VBOTTOM
	 */
	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
	
	/**
	 * returns number of open sites
	 */
	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
}
