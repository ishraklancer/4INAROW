public class FourInARow extends Game 
{

	/**
	 * The actual game board
	 * -1 empty, 0 -> O, 1 -> X
	 */
	public int[] rowStat;
	public int[][] board;
	
	/**
	 * First agent starts with O
	 * @param a
	 * @param b
	 */
	public FourInARow(Agent a, Agent b) {
		super(a, b);
		
		a.setRole(0); // The first argument/agent is always assigned O (0)
		b.setRole(1); // The second argument/agent is always assigned X (1)			  
		name = "4 IN A ROW";
		board = new int[6][7];
		rowStat = new int[7];
		for (int i = 0; i < 7; i++) rowStat[i] = 5;
		
	}
	
	public int getRow(int column) {
		return rowStat[column];
	}
	public void setRow(int column) {
		rowStat[column]--;
	}

	@Override
	boolean isFinished() {
		if(checkForWin() != -1)
			return true;
		else if(isBoardFull())
			return true;
		else return false;
	}

	@Override
	void initialize(boolean fromFile) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = -1;
			}
		}
	}

	@Override
	void showGameState() {
		for (int i = 1; i <= 7; i++){
        	System.out.print("  " + i + " ");
        }
        System.out.println("|");
        System.out.println("----------------------------");
        
        for (int i = 0; i < 6; i++) 
        {
            System.out.print("| ");
            for (int j = 0; j < 7; j++) 
            {
            	if(board[i][j]==-1)
            		System.out.print(" " + " | ");
            	else if	(board[i][j]==0)
            		System.out.print( "O | ");
            	else
            		System.out.print( "X | ");
            }
            System.out.println();
            System.out.println("----------------------------");
        }
    }

    public boolean isBoardFull() {

		
        for (int i = 0; i < 6; i++) 
        {
            for (int j = 0; j < 7; j++) 
            {
                if (board[i][j] == -1) 
                {
                   return false;
                }
            }
        }
		
        return true;
    }
	

    public int checkForWin() 
    {
    	winner = null;
    	int winRole=-1;
    	
        for (int j = 0; j < 7; j++) 
        {
        	for (int i = 0; i < 3; i++)
	            if (checkRowCol(board[i][j], board[i+1][j], board[i+2][j], board[i+3][j]) == true) 
	            {
	            	winRole = board[i][j];
	            }
        }
        
        
        for (int i = 0; i < 6; i++)
        {
        	for (int j = 0; j < 4; j++)
	            if (checkRowCol(board[i][j], board[i][j+1], board[i][j+2], board[i][j+3]) == true) 
	            {
	            	winRole = board[i][j];
	            }
        }
        
        //diagonal
        for (int i = 0; i < 3; i++)
	    	if(checkRowCol(board[i][i], board[i+1][i+1], board[i+2][i+2], board[i+3][i+3]))
	    		winRole = board[i][i];
        
        for (int i = 0; i < 3; i++) {
        	if (checkRowCol(board[5-i][i], board[4-i][i+1], board[3-i][i+2], board[2-i][i+3]))
        		winRole = board[5-i][i];
        }
    	


    	if(winRole!=-1)
    	{
    		winner = agent[winRole];
    	}
		return winRole;
    }
	
    // Check to see if all three values are the same (and not empty) indicating a win.
    private boolean checkRowCol(int c1, int c2, int c3, int c4) 
    {
        if (c1 == -1 || c2 == -1 || c3 == -1 || c4 == -1)
        	return false;
        if (c1 == c2 && c1 == c3 && c1 == c4)
        	return true;
        return false;
    }
	
	public boolean isValidCell(int row, int col)
	{
		if(row<0 || row>5 ||col<0 ||col>6) return false;
		if(board[row][col]!=-1) return false;
		if (row != 5)
			if(board[row+1][col] == -1) return false;	
		
		return true;
	}
	
}
