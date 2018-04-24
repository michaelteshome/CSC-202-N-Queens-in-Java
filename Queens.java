import java.util.*;
public class Queens {

	static Scanner kybd = new Scanner(System.in);
	int length;
	String[][] queens;
	boolean[][] board;
	static int search;
	static int reset;
	public Queens() {
		
	}
	
	public void read() {
		System.out.println("Enter how many rows and columns you want:");
		length = kybd.nextInt();
		setLength(length);
		queens = new String[length][length];
		board = new boolean[length][length];
		
		
	}
	
	public void print() {
		
		for(int rows = 0; rows < queens.length; rows++ ) {
			
			for(int column = 0; column < queens[rows].length; column ++) {
				if(queens[rows][column] != "Q") {
					queens[rows][column] = "X";
				}
				System.out.print(queens[rows][column] + " ");
			}
			System.out.println();
		}
		
	}
	
	public boolean safechecker(int row, int column) {
		
		//loop for going down or column
		int x, j;
		
		for(x = row; x < length; x++) {
			if(queens[x][column] == "Q") {
				return false;
			}
			
		}
		
		
		//loop for going up
		for(x = row; x > 0; x--) {
			if(queens[x][column] == "Q") {
				return false;
			}
			
		}
		
		//loop for diagonally up and left
		for(x = row, j = column; x >= 0 && j >= 0; x--, j--) {
			if(queens[x][j] =="Q") {
				return false;
			}
		}
		
		//loop for going diagonally up and right
		for(x = row, j = column; x >= 0 && j < length; x --, j++) {
			if(queens[x][j] == "Q") {
				return false;
			}
		}
		
		//loop for going left
		for(x = column; x >= 0; x --) {
			if(queens[row][x] == "Q") {
				return false;
			}
			
		}
		//loop for going right
		for(x = column; x < length; x ++) {
			
			if(queens[row][x] == "Q") {
				return false;
			}
			
		}
		
		// loop for going diagonally down and left
		/*for(x = row, j = column; x < length && j >= 0; x ++, j--) {
			if(queens[x][j] == "Q") {
				return false;
			}
		}*/
		
		// loop for going diagonally down and right
		/*for(x = row, j = column; x < length && j < length; x ++, j++) {
			if(queens[x][j] == "Q") {
				return false;
			}
		}*/
		
		return true;
	}
	
	public boolean solve(int col) {
		if(col == length) {
			//print();
			return true;
		}
		
		for(int i = 0; i < length; i++) {
			
			if(safechecker(i, col) == true) {
				queens[i][col] = "Q";
				
				if(col < length) {
					if(solve(col + 1) == true) {
						return true;
					}
				}
				queens[i][col] = "X";
				
			}
			
			
			
		}
		
		//queens[length][length] = "0";
		
		
		return false;
	}
	
	public boolean checkSolve() {
		
		if(solve(0) == false) {
			return false;
		}
		print();
		return true;
	}
	
	public void solver(int row) {
		int count = 0;
		if(row == length) {
			print();
		}
		
		for(int i = 0; i < length; i ++) {
			
			if(safechecker(row, i) == true) {
				queens[row][i] = "Q";
			}
			
			if(row < length) {
				solver(row + 1);
				System.out.println("Number of Solution: " + count);
			}
			
		}
		
		
	}
	
	
	public int getLength() {
		return this.length;
	}
	
	public void setLength(int len) {
		this.length = len;
	}
	
	
	
		
		
		
		
		
		
	
}
