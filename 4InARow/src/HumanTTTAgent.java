import java.util.Scanner;
public class HumanTTTAgent extends Agent
{

	static Scanner in = new Scanner(System.in);
	public HumanTTTAgent(String name) {
		super(name);
	}

	@Override
	public void makeMove(Game game) {
		int row,col;
		FourInARow tttGame = (FourInARow) game;
		
		boolean first = true;
		do
		{
			if(first) 	System.out.println("Insert Column number (1-7)");
			else System.out.println("Invalid input! Insert Column number (1-7)");
//			row = in.nextInt();
			col = in.nextInt();
			col -=1;
			first=false;
		}while(!tttGame.isValidCell(tttGame.getRow(col), col));
		
		tttGame.board[tttGame.getRow(col)][col] = role;
		tttGame.setRow(col);
	}


	

}
