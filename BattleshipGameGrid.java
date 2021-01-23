/*
 Assignment 4
 BattleshipGameGrid.java
 Written by: Christian Jerjian ID: 40031909
 For COMP 248 Section EC – Fall 2020 

This class is where the 2-D array is created. It includes a default constructor and a copy constructor.
This class includes methods for important game mechanics such as hit ship and hit grenades. 
Methods which generate a random number between 0-7 in order for the computer to call upon them and create
it's ship and grenades.  
 
 
 */



public class BattleshipGameGrid {


	private int human; 
	private int computer; 
	private int row; // row
	private int col; // column
	private String [][] grid  = new String [8][8];


	// Default Constructor, gets called when create object and set the whole grid to "_"
	public BattleshipGameGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = "_";
			}
		}
	}



	// This is the constructor which will copy the calling object
	public BattleshipGameGrid (BattleshipGameGrid copyGrid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				this.grid[i][j] = copyGrid.grid[i][j];
			}
		}

	}

	// will launch a rocket at the location and designate wheter it hit nothing, a ship, or a grenade
	// this method is also the winning condition which closes the program as a counter up to 6 ship
	// is being tracked. 
	public void userRocket (BattleshipGameGrid assignedGrid) {
		if (assignedGrid.grid[col][row] == "_") {
			System.out.println("nothing.");
			grid[col][row] = "*";
			printOut();

		}else if (assignedGrid.grid[col][row] == "s") {
			System.out.println("ship hit. ");
			
			//tells the new grid that a ship has been hit in order to print it out.
			grid[col][row] = assignedGrid.grid[col][row];
			
			computer++; // keep track of ships down, if it reaches 6, then human loses
			printOut();
			if(computer == 6) {
				System.out.print("You lose!");
				printOut(assignedGrid);
				System.exit(0);

			}

			// same explanation as above, but with Computer's ship.
		}else if (assignedGrid.grid[col][row] == "S") {
			System.out.println("ship hit. ");
			grid[col][row] = assignedGrid.grid[col][row];
			human ++;
			printOut();
			// if computer has no more ships, then human wins
			if (human == 6) {
				System.out.println("You win!");
				printOut(assignedGrid);
				System.exit(0);
			}

		} 	
		
		//hit a grenade
		if (assignedGrid.grid[col][row] == "G" || assignedGrid.grid[col][row] == "g") {
			System.out.println("boom! grenade.");
			//assigns to new grid in order to print out
			grid[col][row] = assignedGrid.grid[col][row];
			printOut();			
		}

	}
	
	// method to generate a random rocket used by computer
	public void randomRocket(BattleshipGameGrid gridCopy) {
		//generate a random col and row 
		assignRandomCol();
		assignRandomRow();
		if (isCalled()) {
			//if it has been called before, then retry another rocket
			randomRocket(gridCopy);
			return;
		}
		
		// print row
		switch (row) {
		case 0: {
			System.out.print("position of my rocket: A");
			break;

		}
		case 1: {
			System.out.print("position of my rocket: B");
			break;

		}
		case 2: {
			System.out.print("position of my rocket: C");
			break;

		}
		case 3: {
			System.out.print("position of my rocket: D");
			break;

		}
		case 4: {
			System.out.print("position of my rocket: E");
			break;

		}
		case 5: {
			System.out.print("position of my rocket: F");
			break;
		}
		case 6: {
			System.out.print("position of my rocket: G");
			break;

		}
		case 7: {
			System.out.print("position of my rocket: H");
			break;

		}
		default:
			break;
		}
		
		
		
		//Now print the column next to the row
		switch (col) {
		case 0: {
			System.out.println("1"); 
			break;

		}
		case 1: {
			System.out.println("2");
			break;

		}
		case 2: {
			System.out.println("3");
			break;

		}
		case 3: {
			System.out.println("4");
			break;

		}
		case 4: {
			System.out.println("5");
			break;

		}
		case 5: {
			System.out.println("6");
			break;
		}
		case 6: {
			System.out.println("7");
			break;

		}
		case 7: {
			System.out.println("8");
			break;

		}
		default:
			break;
		}
		


	}


	public boolean userGrenadeHit (BattleshipGameGrid gridCopy) {
		return (gridCopy.grid[col][row] == "g");
	}
	public boolean compGrenadeHit (BattleshipGameGrid gridCopy) {
		return (gridCopy.grid[col][row] == "G");
	}


	//method that assigns input by human to correct row
	public int assignRow(String coordinate) {
		switch (coordinate.charAt(0)) {
		case 'a':
			row = 0;
			break;
		case 'b':
			row = 1;
			break;
		case 'c':
			row = 2;
			break;
		case 'd':
			row = 3;
			break;
		case 'e':
			row = 4;
			break;
		case 'f':
			row = 5;
			break;
		case 'g':
			row = 6;
			break;
		case 'h':
			row = 7;
			break;

		}

		return (row);
	}

	//method that assigns input by human to correct col
	public int assignCol(String coordinate) {
		switch (coordinate.charAt(1)) {
		case '1':
			col = 0;
			break;
		case '2':
			col = 1;
			break;
		case '3':
			col = 2;
			break;
		case '4':
			col = 3;
			break;
		case '5':
			col = 4;
			break;
		case '6':
			col = 5;
			break;
		case '7':
			col = 6;
			break;
		case '8':
			col = 7;
			break;

		}
		return (col);
	}

	// making sure that coordinate has not been used 
	public boolean gridCheck() {
		if (grid[col][row] == "g" || grid[col][row] == "G" || 
				grid[col][row] == "s" || grid[col][row] == "S") {
			return false;
		} else {
			return true;
		}
	}
	
	// making sure the coordinate has not been called previously. in order to send another rocket
	public boolean isCalled() {
		if (grid[col][row] == "g" || grid[col][row] == "G" || 
				grid[col][row] == "s" || grid[col][row] == "S" || grid[col][row] == "*") {
			return true;
		} else {
			return false;
		}

	}

	
	public void userShip() {
		grid[col][row] = "s";
	}

	public void compShip() {
		grid[col][row] = "S";
	}

	public void userGrenade() {
		grid[col][row] = "g";
	}

	public void compGrenade() {
		grid[col][row] = "G";
	}


	// assigns a random number between 0-7 (will be used for array purposes)
	public int assignRandomRow() {
		row = (int) (Math.random()*(7-0+1)+0);
		return row;


	}
	// assigns a random number between 0-7 (will be used for array purposes)
	public int assignRandomCol() {
		col = (int) (Math.random()*(7-0+1)+0);
		return col;


	}



	// Prints the board
	public void printOut() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Prints the Master board (the one which has not been reset)
	public void printOut(BattleshipGameGrid assignedGrid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	// resetting the board and making the grid back to "_" everywhere
	public void gridReset() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = "_";
			}
		}

	}

}








