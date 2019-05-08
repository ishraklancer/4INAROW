public class MinimaxTTTAgent extends Agent
{
	int maxDepth = 10;
	
	public MinimaxTTTAgent(String name) {
		super(name);
	}

	@Override
	public void makeMove(Game game) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		FourInARow tttGame = (FourInARow) game;
		CellValueTuple best = newMaxValue(tttGame, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		if(tttGame.isValidCell(best.row, best.col))
		{
			tttGame.board[best.row][best.col] = role;
			tttGame.setRow(best.col);
		}
		
	}
	
	public int evaluate(FourInARow game) {
		int ai = 7*4 + 4*7 + 4*4 * 4*4;
		int human = 7*4 + 4*7 + 4*4 * 4*4;
		//int human = 7*4 + 4*7 + 4*4 * 4*4;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (!game.isValidCell(i, j)) 
					continue;
				if (j == 3) {
					if (game.board[i][j] == role) {
						human -= (4+1+1+1);
					}
					else
						ai -= (4+1+1+1);
				}
				else {
					if (game.board[i][j] == role) {
						human -= 1+1+1+1;
					}
					else
						ai -= 1+1+1+1;
				}
			}
		}
//		System.out.println("debug//eval " + (ai-human));
		return ai - human;
		
	}

	
	private CellValueTuple newMaxValue(FourInARow game, int depth, int alpha, int beta)
	{	
		CellValueTuple maxCVT = new CellValueTuple();
		maxCVT.utility = -5000;
		
		int winner = game.checkForWin();
		
		//terminal check
		if(winner == role)
		{
			maxCVT.utility = 1; //this agent wins
			return maxCVT;
		}
		else if(winner!=-1) 
		{
			maxCVT.utility = -1; //opponent wins
			return maxCVT;  
		}
		else if (game.isBoardFull())
		{
			maxCVT.utility = 0; //draw
			return maxCVT;  
		}
		
		if (depth == maxDepth) {

//			System.out.println("debug//max maxDepth");
			maxCVT.utility = evaluate(game);
			return maxCVT;

		}
		
		for (int i = 0; i < 6; i++) 
		{
			for (int j = 0; j < 7;j++)
			{
				if(!game.isValidCell(i, j)) 
					continue;
				
				
				game.board[i][j] = role; //temporarily making a move
				int v = newMinValue(game, depth+1, alpha, beta).utility;
				if(v>maxCVT.utility)
				{
					maxCVT.utility=v;
					maxCVT.row = i;
					maxCVT.col = j;
				}
				alpha = Math.max(alpha, maxCVT.utility);
				if (beta <= alpha) {
					game.board[i][j] = -1; 
//					System.out.println("debug// break");
					break;
				}
				game.board[i][j] = -1; // reverting back to original state
				
			}
		}
		return maxCVT;
			
	}
	
	private CellValueTuple newMinValue(FourInARow game, int depth, int alpha, int beta)
	{	
		CellValueTuple minCVT = new CellValueTuple();
		minCVT.utility = 5000;
		
		int winner = game.checkForWin();
		
		//terminal check
		if(winner == role)
		{
			minCVT.utility = 1; //max wins
			return minCVT;
		}
		else if(winner!=-1) 
		{
			minCVT.utility = -1; //min wins
			return minCVT;  
		}
		else if (game.isBoardFull())
		{
			minCVT.utility = 0; //draw
			return minCVT;  
		}
		
		if (depth == maxDepth) {

//			System.out.println("debug//min maxDepth");
			minCVT.utility = evaluate(game);
			return minCVT;
		}
		
		for (int i = 0; i < 6; i++) 
		{
			for (int j = 0; j<7;j++)
			{
				if(!game.isValidCell(i, j)) 
					continue;
				
				game.board[i][j] = minRole();
				int v = newMaxValue(game, depth+1, alpha, beta).utility;
				if(v<minCVT.utility)
				{
					minCVT.utility=v;
					minCVT.row = i;
					minCVT.col = j;
				}
				beta = Math.min(beta, minCVT.utility);
				if (beta <= alpha) {
					game.board[i][j] = -1; 
					break;
				}
				game.board[i][j] = -1;
				
			}
		}
		return minCVT;
			
	}
	
	private int minRole()
	{
		if(role==0)return 1;
		else return 0;
	}

	class CellValueTuple
	{
		int row,col, utility;
		public CellValueTuple() {
			row =-1;
			col =-1;
		}
		
		

	}

}
