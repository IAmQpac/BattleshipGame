/*
 Assignment 4
 BattleshipDriver.java
 Written by: Christian Jerjian ID: 40031909
 For COMP 248 Section EC – Fall 2020 



Algorithm
1) print welcome message
2) create and initialize variables and constants to keep track of ships and grenades
3) create a new class (BattleshipGameGrid) where the 2-d array is created and methods can be written.
	Set the variables to keep track of score (human and computer), row, col, 
4) create and initialize new object in driver class in order to use the methods established in BattleshipGameGrid
5) Prompt user to enter 6 ships into the grid (while statement). The ships are within the grid's border
6) Prompt user to enter 4 grenades into the grid (while statement). The grenades have to be within the grid's border
7) Create randomRow and randomCol methods in BattleshipGameGrid which assigns a random number between 0-7
8) Assign 6 ships and 4 grenades of computer to the grid using the randomRow and randomCol methods previously established
9)Signal the start of the game
10) Create a new object (copyGrid) which creates a temporary grid in order to save the assignment of ships and grenades
11) reset original grid (create a new method)
12) Prompt user to launch a new rocket, ensure it is within the grid requirements. If rocket hits ANY grenade, give a free round to other user
13) Ensure method (userRocket) ends with a definitive result and system.exit(0); the program when someone has sunk 6 ships



 This class is where the main method is present. This class creates objects and calls methods from the BattleshipGameGrid
 class in order to perform repeated actions. 
 */




import java.util.Scanner;


public class BattleshipDriver {

	public static void main(String[] args) {

		Scanner myKeyboard = new Scanner(System.in);


		BattleshipGameGrid grid = new BattleshipGameGrid(); // create object of name grid

		System.out.println ("Hi, let's play Battleship! \n");


		int shipEntered = 0;
		int grenadeEntered = 0; 
		final int ShipTotal = 6;
		final int grenadeTotal = 4;


		// user enters the 6 ships they have in grid.
		while (shipEntered < ShipTotal) { 
			System.out.print("Enter the coordinates of your ship #" + (shipEntered+1) + ": ");

			String coordinate = myKeyboard.next();
			coordinate = coordinate.toLowerCase(); // turning everything to lower case


			//ensuring the coordinates are part of the grid
			if ((coordinate.charAt(0) == 'a' || coordinate.charAt(0) == 'b' ||
					coordinate.charAt(0) == 'c' || coordinate.charAt(0) == 'd' ||
					coordinate.charAt(0) == 'e' || coordinate.charAt(0) == 'f' ||
					coordinate.charAt(0) == 'g' || coordinate.charAt(0) == 'h')
					&&  
					(coordinate.charAt(1) == '1'|| coordinate.charAt(1) == '2' ||
					coordinate.charAt(1) == '3'|| coordinate.charAt(1) == '4' ||
					coordinate.charAt(1) == '5'|| coordinate.charAt(1) == '6' ||
					coordinate.charAt(1) == '7'|| coordinate.charAt(1) == '8' ))
			{

				//calling assignment methods to assign row and col
				grid.assignRow(coordinate);
				grid.assignCol(coordinate);

				//making sure the coordinate haven't been used before
				if (!grid.gridCheck()) {
					System.out.println("sorry, coordinates already used. try again.");
					continue;		
				}
				//setting the ship 
				grid.userShip();


			} else {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			}

			// next ship until we reach 6 ships
			shipEntered++;	
		}

		System.out.println();


		//Same thing as with ships above but with grenades
		while(grenadeEntered < grenadeTotal) {
			System.out.print("Enter the coordinates of your grenade #" + (grenadeEntered+1) + ": ");
			String coordinate2 = myKeyboard.next();
			coordinate2 = coordinate2.toLowerCase(); // turning everything to lower case

			//ensuring the coordinates are part of the grid
			if ((coordinate2.charAt(0) == 'a' || coordinate2.charAt(0) == 'b' ||
					coordinate2.charAt(0) == 'c' || coordinate2.charAt(0) == 'd' ||
					coordinate2.charAt(0) == 'e' || coordinate2.charAt(0) == 'f' ||
					coordinate2.charAt(0) == 'g' || coordinate2.charAt(0) == 'h')
					&&
					(coordinate2.charAt(1) == '1'|| coordinate2.charAt(1) == '2' ||
					coordinate2.charAt(1) == '3'|| coordinate2.charAt(1) == '4' ||
					coordinate2.charAt(1) == '5'|| coordinate2.charAt(1) == '6' ||
					coordinate2.charAt(1) == '7'|| coordinate2.charAt(1) == '8' ))
			{
				//calling assignment methods to assign row and col
				grid.assignRow(coordinate2);
				grid.assignCol(coordinate2);

				//making sure the coordinate haven't been used before
				if (!grid.gridCheck()) {
					System.out.println("sorry, coordinates already used. try again.");
					continue;		
				}
				//setting up grenade
				grid.userGrenade();

			}
			else {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			}

			//next grenade until we reach 4 grenades.
			grenadeEntered++;

		}

		System.out.println();


		// computer placing its ships
		shipEntered = 0;
		while (shipEntered<ShipTotal) {
			grid.assignRandomCol();
			grid.assignRandomRow();
			if (!grid.gridCheck()) {
				continue;		
			}
			grid.compShip();
			shipEntered++;

		}

		//Computer placing its grenades
		grenadeEntered = 0;
		while (grenadeEntered < grenadeTotal) {
			grid.assignRandomCol();
			grid.assignRandomRow();
			if (!grid.gridCheck()) {
				continue;		
			}
			grid.compGrenade();
			grenadeEntered++;

		}

		BattleshipGameGrid gridCopy = new BattleshipGameGrid(grid);  // will call the constructor method, we will reset the grid 
		//so is a copy of original grid.

		System.out.println();
		System.out.println("OK, the computer placed its ships and grenades at random. Let's play. \n");
		System.out.println();

		// Need a clean board in order to show hit ship/misses/hit grenade
		grid.gridReset();


		//HUMAN'S TURN TO LAUNCH ROCKET
		while (true) {
			System.out.print("position of your rocket: ");
			String rocketCoordinate = myKeyboard.next();
			//ensuring the coordinate is within the grid
			if ((rocketCoordinate.charAt(0) == 'a' || rocketCoordinate.charAt(0) == 'b' ||
					rocketCoordinate.charAt(0) == 'c' || rocketCoordinate.charAt(0) == 'd' ||
					rocketCoordinate.charAt(0) == 'e' || rocketCoordinate.charAt(0) == 'f' ||
					rocketCoordinate.charAt(0) == 'g' || rocketCoordinate.charAt(0) == 'h')
					&&
					(rocketCoordinate.charAt(1) == '1'|| rocketCoordinate.charAt(1) == '2' ||
					rocketCoordinate.charAt(1) == '3'|| rocketCoordinate.charAt(1) == '4' ||
					rocketCoordinate.charAt(1) == '5'|| rocketCoordinate.charAt(1) == '6' ||
					rocketCoordinate.charAt(1) == '7'|| rocketCoordinate.charAt(1) == '8' )) 
			{ 	
				//assign coordinates to respective row and col
				grid.assignRow(rocketCoordinate);
				grid.assignCol(rocketCoordinate);


				// if position has already been called previously, retry to send rocket
				if (grid.isCalled() == true) {
					System.out.println ("position already called.");
					grid.printOut();
					continue;


				}else {
					grid.userRocket(gridCopy);
					if (grid.compGrenadeHit(gridCopy)){
						grid.randomRocket(gridCopy);	// computer gets a 2nd turn
					} if (grid.userGrenadeHit(gridCopy)) {
						grid.randomRocket(gridCopy);	// computer gets a 2nd turn	
					}

				}


				//COMPUTER'S TURN TO LAUNCH ROCKET

				grid.randomRocket(gridCopy); // assign random row and col
				grid.userRocket(gridCopy); //launch rocket

				// if it's hit by a grenade, give human free turn
				if(grid.userGrenadeHit(gridCopy) || grid.compGrenadeHit(gridCopy)) {
					while(true) {
						System.out.print("position of your rocket: ");
						String rocketCoordinate2 = myKeyboard.next();
						if((rocketCoordinate2.charAt(0) == 'a' || rocketCoordinate2.charAt(0) == 'b' ||
								rocketCoordinate2.charAt(0) == 'c' || rocketCoordinate2.charAt(0) == 'd' ||
								rocketCoordinate2.charAt(0) == 'e' || rocketCoordinate2.charAt(0) == 'f' ||
								rocketCoordinate2.charAt(0) == 'g' || rocketCoordinate2.charAt(0) == 'h')
								&&
								(rocketCoordinate2.charAt(1) == '1'|| rocketCoordinate2.charAt(1) == '2' ||
								rocketCoordinate2.charAt(1) == '3'|| rocketCoordinate2.charAt(1) == '4' ||
								rocketCoordinate2.charAt(1) == '5'|| rocketCoordinate2.charAt(1) == '6' ||
								rocketCoordinate2.charAt(1) == '7'|| rocketCoordinate2.charAt(1) == '8' )) {
							//coordinate within bound, now assign row and col
							grid.assignRow(rocketCoordinate2);
							grid.assignCol(rocketCoordinate2);

							// ensure new coordinate not already called
							if (grid.isCalled() == true) {
								System.out.println ("position already called.");
								grid.printOut();
								continue;
							}else {
								grid.userRocket(gridCopy);
								//hit a computer grenade
								if (grid.compGrenadeHit(gridCopy)){
									grid.printOut();
									grid.userRocket(gridCopy);	// human gets a 2nd turn
									continue;
									//hit a user grenade
								}if (grid.userGrenadeHit(gridCopy)){
									grid.printOut();
									grid.userRocket(gridCopy);	// human gets a 2nd turn
									continue;
								}

							}

						}

					}

				}


			}
			else {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;			
			}

			continue;
			// can't close scanner because of continue; statement
		}

	}
}

