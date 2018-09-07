import java.security.SecureRandom;

public class GameGrid {


    private static int[][] aiGrid = new int[10][10]; //instantiate and declare 2D Array

    void runTest(){

        createGrid();

        printGrid(aiGrid);
    }

    public static void createGrid(){
        SecureRandom oRand = new SecureRandom();
        int iUserRow = 0; // will keep the users position
        int iUserCol = 0; // will keep the users position

        for(int x = 0; x < aiGrid.length; x++) {
            for(int y = 0; y < aiGrid[x].length; y++){
                //populate array
                int iRandomNum = oRand.nextInt(100); //bound will be from ( o -> n - 1): n being the passed value
                // percentage of the wall
                int iWallChance = 15;
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
    public static void printGrid(int [][] aiGrid){
        for (int[] anAiGrid : aiGrid) {
            for (int anAnAiGrid : anAiGrid) {
                System.out.print(" " + anAnAiGrid);
            }
            System.out.println(); //to make sure it will print out the grid and not just in a row
        }
    }
}
