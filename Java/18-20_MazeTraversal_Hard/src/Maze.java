import java.util.ArrayList;
/**
 * This class creates and traverses the maze
 * @author rbanda
 *
 */
public class Maze {
	/**
	 * This variable holds the number of moves
	 */
	private int move = 0;
	/**
	 * This variable holds the maze
	 *
	 */
	private char mazeArray[][] = { { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
			{ '#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#' },
			{ '.', '.', '#', '.', '#', '.', '#', '#', '#', '#', '.', '#' },
			{ '#', '#', '#', '.', '#', '.', '.', '.', '.', '#', '.', '#' },
			{ '#', '.', '.', '.', '.', '#', '#', '#', '.', '#', '.', '.' },
			{ '#', '#', '#', '#', '.', '#', '.', '#', '.', '#', '.', '#' },
			{ '#', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#' },
			{ '#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#' },
			{ '#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '#' },
			{ '#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '.', '#' },
			{ '#', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#' },
			{ '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' } };
	/**
	 * This method calls the traverse maze function
	 * if maze cannot be traversable it prints out no solution
	 * @param xPos
	 * 			Takes in the xpos
	 * @param yPos
	 * 			Takes in the ypos
	 *
	 */
	public Maze(int xPos, int yPos) {
		boolean result = traverse(mazeArray, 2, 0);
		if(!result){
			System.out.println("No solution");
		}

	}
	/**
	 * This method tries to traverse the maze by recursively calling itself
	 * if maze cannot be traversable it returns false
	 * 
	 * @param array[][]
	 * 			Takes in the maze
	 * @param xPos
	 * 			Takes in the xpos
	 * @param yPos
	 * 			Takes in the ypos
	 * 
	 * @return boolean
	 * 			returns false if maze is not traversable
	 * 			returns true if mase is traversable
	 *
	 */
	public boolean traverse(char array[][],int xPos,int yPos){

		mazeArray[xPos][yPos]= 'X';
		
		move++;
		mazePrint();

		if(exitMaze(xPos,yPos) && move>1){
			System.out.println("Maze is succesfully solved");
			for(int i = 0; i < mazeArray.length; i++)
			{
				for(int j = 0; j < mazeArray[i].length; j++)
				{
					if(mazeArray[i][j] == '0')
					{
						mazeArray[i][j] = '.';
					}
				}
			}
			mazePrint();
			return true;
		}else{
			int runCount= 0;
			while(runCount < 4){
				if(runCount == 0)
				{
					if(validMove(xPos+1,yPos)){
						if(traverse(array,xPos+1,yPos)){
							return true;
						}
					}
				}
				else if(runCount == 1)
				{
					if(validMove(xPos-1,yPos)){
						if(traverse(array,xPos-1,yPos)){
							return true;
						}
					}
				}
				else if(runCount == 2)
				{
					if(validMove(xPos,yPos+1)){
						if(traverse(array,xPos,yPos+1)){
							return true;
						}
					}
				}
				else if(runCount == 3)
				{
					if(validMove(xPos,yPos-1)){
						if(traverse(array,xPos,yPos-1)){
							return true;
						}
					}
				}
				runCount++;
			}		
			mazeArray[xPos][yPos] = '0';
			return false;
		}
		
	}
	/**
	 * This method sees if the move is valid
	 * if move is not valid it returns false
	 * 
	 * @param xPos
	 * 			Takes in the xpos
	 * @param yPos
	 * 			Takes in the ypos
	 * 
	 * @return boolean
	 * 			returns true or false if the move is valid or not
	 *
	 */
	public boolean validMove(int xPos, int yPos){

		return ((xPos>= 0) && (xPos <=11) && (yPos >=0) && (yPos<=11)
				&& (mazeArray[xPos][yPos]=='.'));
	}
	/**
	 * This method sees if it is at the exit or not
	 * 
	 * @param xPos
	 * 			Takes in the xpos
	 * @param yPos
	 * 			Takes in the ypos
	 * 
	 * @return boolean
	 * 			returns true or false if at the exit
	 *
	 */
	public boolean exitMaze(int xPos, int yPos){
		return ((xPos==0) || (xPos==11) || (yPos ==0) || (yPos ==11));	
	}
	/**
	 * This method reprints the maze to the console
	 */
	private void mazePrint() {
		System.out.println("\n\n");
		for (int i = 0; i < mazeArray.length; i++) {
			for (int j = 0; j < mazeArray[i].length; j++) {
				System.out.printf("%c ", mazeArray[i][j]);
			}
			System.out.printf("\n");
		}
	}

}
