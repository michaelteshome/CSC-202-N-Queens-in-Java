import java.util.*;
public class Quee {

	static Scanner kybd = new Scanner(System.in);
	int stack, queue;
	private int currentRows, currentColumn, origCapRow, origCapColumn;
	int columnRear, rowRear, frontRow, frontColumn, maze, mazeColumn;
	
	ArrayList<Integer>listH = new ArrayList<Integer>();
	ArrayList<Integer>listW = new ArrayList<Integer>();
 	boolean full, visit;
	boolean empty, unvisited;
	String mazeCreation[][];
	boolean visited[][] = new boolean[queue][stack];
	
	int top = -1;
	String[][] stackQueue;
	
	int width, height;
	
	public Quee() {
		stackQueue = new String[queue][stack];
		setRowRear(queue - 1);
		setColumnRear(stack - 1);
		origCapRow = queue;
		origCapColumn = stack;
	}
	
	
	
	public void read() {
		System.out.println("Enter a number for your max width");
		int width = kybd.nextInt();
		setQueue(width);
		
		System.out.println("Enter a number for your max height");
		int height = kybd.nextInt();
		setStack(height);
		
		setRowRear(queue - 1);
		setColumnRear(stack - 1);
		frontRow = 0;
		frontColumn = 0;
		
		stackQueue = new String[queue][stack];
		
		
	}
	
	
	
	public void printMaze() {
	
		for(int rows = 0; rows < stackQueue.length; rows ++) {
			
			for(int columns = 0; columns < stackQueue[rows].length; columns ++) {
				stackQueue[rows][columns] = "0";
			}
		}
		
		for(int rows = 0; rows < stackQueue.length; rows ++){
			
			for(int column = 0; column < stackQueue[rows].length; column ++) {
				System.out.print(stackQueue[rows][column] + " ");
				
			}
			
			System.out.println();
			
		}
		
		System.out.println("Column Rear: " + columnRear);
		System.out.println("Row Rear: " + rowRear);
		
	}
	
	public void createMaze() {
		int dq = this.queue - 1;
		int dc = this.stack - 1;
		mazeCreation = new String[queue][stack];
		
		Random rnd1 = new Random();
		
		for(int x = 0; x < 20; x++) {
			maze = rnd1.nextInt(dq);
			mazeColumn = rnd1.nextInt(dc);
			listH.add(maze);
			listW.add(mazeColumn);
			
			
			
			System.out.println("Random Row Positions " + maze + " | " + "Random Column Positions " + mazeColumn);
			stackQueue[maze][mazeColumn] = "1";
			
				
		}
		
		//Loop for target point
		
		int target;		
		for(int j = 0; j < 1; j++) {
			target = rnd1.nextInt(dq) + 1;
			stackQueue[target][target] = "T";			
		}
		
		
		
		
		int j = 0; // Loop going down, if stackQueue = 1
		if(stackQueue[0 + 1][0] != "1" && j < queue && stackQueue[0 + 1][0] != "T") {
			
			for(j = 0; j < queue; j++) {
				stackQueue[j][0] = "*";
				
				if(stackQueue[j][j] == "T") {
					System.out.println("Congrats");
					break;
				}
				
				
			}
		}
		
		
		// Loop going right, if stackQueue = 0 
		if(stackQueue[0][0+1] == "0" && stackQueue[0][0 + 1] != "1" && stackQueue[0][0 + 1] != "T") {
			
			for(j = 0; j < stack; j ++) {
				stackQueue[0][j] = "*";
			}
		}
		// Loop to check if going down equals one but starting from indice 0 and going right = 0
		if(stackQueue[0 + 1][0] == "1" && stackQueue[0][0 + 1] == "0" && stackQueue[0][0 + 1] != "T") {
			
			for(j = 0; j < stack; j++) {
				stackQueue[0][0 + j] = "*"; 
				
			}
		}
		
		
		
		
		
		for(int rows = 0; rows < stackQueue.length; rows ++) {
			
			for(int column = 0; column < stackQueue[rows].length; column ++) {
				System.out.print(stackQueue[rows][column] + "  ");
			}
			System.out.println();
		}
		
		System.out.println("Current Row: " + dq);
		System.out.println("Current Column: " + dc);
		
		
	}
	
	public void dequeue() {
		int deqRow = getQueue() - 1;
		setQueue(deqRow);
		int deqColumn = getStack() - 1;
		setStack(deqColumn);
		
		setRowRear(deqRow - 1);
		setColumnRear(deqColumn - 1);
		while(!isEmpty()) {
		String[][] decreaseSize = new String[deqRow][deqColumn];
		for(int rows = 0; rows < decreaseSize.length; rows ++) {
			
			for(int column = 0; column < decreaseSize[rows].length; column ++) {
				decreaseSize[rows][column] = "0";
				System.out.print(decreaseSize[rows][column] + "  ");
			}
			System.out.println();
		}
		}
		System.out.println("Row Rear: " + getRowRear());
		System.out.println("Column Rear: " + getColumnRear());
		
		
	}
	
	public void enqueue() {
		int newColumn = getStack() + 1;
		setStack(newColumn);
		int newRow = getQueue() + 1;
		setQueue(newRow);
		
		String[][] enlarge = new String[queue][stack];
		setRowRear(newRow - 1);
		setColumnRear(newColumn - 1);
		
		for(int rows = 0; rows < enlarge.length; rows ++) {
			
			for(int column = 0; column < enlarge[rows].length; column ++) {
				
				enlarge[rows][column] = "0";
				System.out.print(enlarge[rows][column] + "  ");
			}
			
			System.out.println();
		}
		
		System.out.println("New Enlarged Dimension Row Rear: " + getRowRear());
		System.out.println("New Enlarged Dimension Column Rear: " + getColumnRear());
		
	}
	
	public boolean visited() {
		if(stackQueue[queue][stack] == "*") {
			visit = true;
			visited[queue][stack] = visit; 
		}
		return true;
	}
	
	public boolean unvisited() {
		if(stackQueue[queue][stack] == "0") {
			unvisited = true;
			visit = false;
			visited[queue][stack] = unvisited;
		}
		
		return unvisited;
	}
	
	
	public boolean isEmpty() {
		if(queue <= 0 && stack <= 0) {
			System.out.println("Your Maze is empty: ");
			return true;
		}
		else 
			return false;
	}
	
	public void setStack(int length) {
		this.stack = length;
	}
	
	public int getStack() {
		return stack;
	}
	
	public void setQueue(int wide) {
		this.queue = wide;
	}
	
	public int getQueue() {
		return queue;
	}
	
	public void setRowRear(int r) {
		this.rowRear = r;
	}
	
	public int getRowRear() {
		return rowRear;
	}
	
	public void setColumnRear(int c) {
		this.columnRear = c;
	}
	
	public int getColumnRear() {
		return columnRear;
	}
	
	public int getMaze() {
		return maze;
	}
	
	
	
}
