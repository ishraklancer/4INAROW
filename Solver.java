import java.util.Scanner;

public class Solver {	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Your Name: ");
		String name = sc.next();
		Agent human = new HumanTTTAgent(name);
		Agent machine = new MinimaxTTTAgent("MACHINE");
		Game game = new FourInARow(human,machine);
		game.play();
		
	}

}
