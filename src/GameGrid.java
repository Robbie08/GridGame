import java.security.SecureRandom;
import java.util.Scanner;

public class GameGrid {

    // Declare and Initialize instance variables
    private static int[][] aiGrid = new int[10][10]; //instantiate and declare 2D Array
    private static int iUserRow = 0; // will keep the users position
    private static int iUserCol = 0; // will keep the users position
    private static int iWallChance; // determines the difficulty
    private static Scanner scan = new Scanner(System.in); // instance of the scanner class
    private static int iMovement = 0; // holds the users input on direction which will then be validated into a direction
    private static int iTotalMovements;

    void runTest(){
        boolean gameOn = true;
        promptDifficulty(); // will ask the user to enter their choice of difficulty
        createGrid(); // method will create our grid and populate it

        //runs the game loop
        while(gameOn){
            int iPosition = aiGrid[iUserCol][iUserRow];

            switch (iPosition){
                case 1:
                    //if the user ran into a wall
                    System.out.println("*****You ran into the wall: You Lose!******");
                    gameOn = false; // exit game loop
                    break;
                case 0:
                    // if the user did not run into the wall
                    promptUser(); //get user input to move
                    aiGrid[iUserCol][iUserRow] = 88; // sets an X at position the user steps on that doesn't exit the game
                    moveUser(); //move the user if the value is 0

                    if(iUserCol == 9){
                        // user won the game through a max col
                        System.out.println("*****You win!******");
                        iTotalMovements++;
                        aiGrid[iUserCol][iUserRow] = 88; // to print an x in the last position
                        gameOn = false; // will exit the game
                    }
                    else if(iUserRow == 9){
                        // user won the game through a max row
                        System.out.println("*****You win!******");
                        iTotalMovements++;
                        aiGrid[iUserCol][iUserRow] = 88; // to print x in the last position
                        gameOn = false; // will exit the game
                    }

                    iTotalMovements++; // count the number of steps the user took
                    break;
                default:

            }
        }

        printGrid(aiGrid); //print out the grid once the game loop is done
    }

    //Move user though the game board
    private static void moveUser(){
        //Move the user on the grid
        if(iMovement == 1) {
            //move the user down
            iUserCol += 1;
        }
        else if(iMovement == 2){
            //move the user to the right
            iUserRow += 1;
        }

    }

    //Will set the difficulty
    private static void validateDifficulty(int iDifficulty){
        if(iDifficulty == 1){
            iWallChance = 15; // sets the amount of walls to a 15/100 ratio
            System.out.println("Difficulty has been set to Easy");
        }
        else if(iDifficulty == 2){
            iWallChance = 25; // sets the amount of walls to a 25/100 ratio
            System.out.println("Difficulty has been set to Medium");
        }
        else {
            iWallChance = 35; // sets the amount of walls to a 35/100 ratio
            System.out.println("Difficulty has been set to Hard: You have no chance!");
        }
    }

    //will prompt the user for Easy, Medium, or Hard
    private static void promptDifficulty(){
        int iDifficulty;
        System.out.println("1 for Easy");
        System.out.println("2 for Medium");
        System.out.println("3 for Hard");
        System.out.println("Choose the difficulty: ");
        iDifficulty = scan.nextInt();

        if(iDifficulty < 1 || iDifficulty > 3){
            System.out.println("1 for Easy");
            System.out.println("2 for Medium");
            System.out.println("3 for Hard");
            System.out.println("Choose the difficulty: ");
            iDifficulty = scan.nextInt();
        }

        validateDifficulty(iDifficulty); //sets the difficulty level
    }

    //will prompt the user for a choice of action on game
    private static void promptUser(){

        System.out.println("-------------------------------");
        System.out.println("1 for down ");
        System.out.println("2 for right ");
        System.out.println("Where do you want to move?: ");
        iMovement = scan.nextInt();
        while(iMovement < 1 || iMovement > 2) {

            System.out.println("*******PLEASE TRY AGAIN*******");
            System.out.println("1 for down ");
            System.out.println("2 for right ");
            System.out.println("Where do you want to move?: ");

            iMovement = scan.nextInt();
        }
    }

    // Will create the game grid (2-D) array and populate
    // it with walls and open path for the player.
    private static void createGrid(){
        SecureRandom oRand = new SecureRandom();

        for(int x = 0; x < aiGrid.length; x++) {
            for(int y = 0; y < aiGrid[x].length; y++){
                //populate array
                int iRandomNum = oRand.nextInt(100); //bound will be from ( o -> n - 1): n being the passed value
                // percentage of the wall
                if(iRandomNum < iWallChance){
                    //Gives us a 15% chance that we will get a wall
                    aiGrid[x][y] = 1;
                }
                else {
                    //Gives us a 85% chance that we will get a path
                    aiGrid[x][y] = 0;
                }
            }
        }
        aiGrid[0][0] = 0; //sets our initial value to zero to avoid game end
    }

    // Will print the grid
    private static void printGrid(int[][] aiGrid){

        System.out.println("You took a total of: " +iTotalMovements);
        for (int[] anAiGrid : aiGrid) {
            for (int anAnAiGrid : anAiGrid) {

                if(anAnAiGrid == 88){
                    System.out.print(" " +(char) anAnAiGrid);
                }else
                System.out.print(" " + anAnAiGrid);
            }
            System.out.println(); //to make sure it will print out the grid and not just in a row
        }
    }
}
