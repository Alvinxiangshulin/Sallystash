
import java.util.ArrayList;
import java.util.Scanner;
//class of the game
public class SallysStash {
	private Player A;
	private Player B;
	//private Scanner scan;
	
	//constructor, "H" is huamn, "C" is computer 
	public SallysStash(String p1, String p2,String name1, String name2) {
		
		if(p1.equalsIgnoreCase("H") && p2.equalsIgnoreCase("H")){
			Human player_1 = new Human(name1,name2,"H");
			Human player_2 = new Human(name2,name1, "H");
			Board b1 = new Board(10,20);
			Board b2 = new Board(10,20);
			player_1.setMe(b1);
			player_1.setOp(b2);
			player_2.setMe(b2);
			player_2.setOp(b1);
			A = player_1;
			B = player_2; 
			//this.scan = new Scanner(System.in);
		}
		else if(p1.equalsIgnoreCase("C") && p2.equalsIgnoreCase("C")){
			Computer player_1 = new Computer(name1,name2, "C");
			Computer player_2 = new Computer(name2,name1,"C");
			Board b1 = new Board(10,20);
			Board b2 = new Board(10,20);
			player_1.setMe(b1);
			player_1.setOp(b2);
			player_2.setMe(b2);
			player_2.setOp(b1);
			A = player_1;
			B = player_2;
		}
		else if(p1.equalsIgnoreCase("H") &&p2.equalsIgnoreCase("C")){
			Human player_1 = new Human(name1,name2, "H");
			Computer player_2 = new Computer(name2,name1, "C");
			Board b1 = new Board(10,20);
			Board b2 = new Board(10,20);
			player_1.setMe(b1);
			player_1.setOp(b2);
			player_2.setMe(b2);
			player_2.setOp(b1);
			A = player_1;
			B = player_2;
		}
		else {
			Computer player_1 = new Computer(name1,name2, "C");
			Human player_2 = new Human(name2,name1, "H");
			Board b1 = new Board(10,20);
			Board b2 = new Board(10,20);
			player_1.setMe(b1);
			player_1.setOp(b2);
			player_2.setMe(b2);
			player_2.setOp(b1);
			A = player_1;
			B = player_2;
		}
	}
	
	
	//Run game, only display when the player is a human
	public void run() {
		ArrayList<ArrayList<Stack>> A_stacks = new ArrayList<ArrayList<Stack>>();
		for(int i = 0; i < A.getStacks().size(); i++) {
			ArrayList<Stack> stack = new ArrayList<Stack>();
			for(int j = 0; j <A.getStacks().get(i).size();j++) {
				stack.add(A.getStacks().get(i).get(j));
			}
			A_stacks.add(stack);
		}
		ArrayList<ArrayList<Stack>> B_stacks = new ArrayList<ArrayList<Stack>>();
		for(int i = 0; i < B.getStacks().size(); i++) {
			ArrayList<Stack> stack = new ArrayList<Stack>();
			for(int j = 0; j <B.getStacks().get(i).size();j++) {
				stack.add(B.getStacks().get(i).get(j));
			}
			B_stacks.add(stack);
		}
		
		Display display = new Display();
		
		if(A.getRole().equalsIgnoreCase("H")) {
			System.out.println(display.title(A));
			System.out.println(display.putstack_display(A));
			System.out.println("hrere");
		}
		
		while(!A_stacks.isEmpty()) {
			ArrayList<Stack> stack = A_stacks.get(0);
			if(A.getRole().equalsIgnoreCase("H")) {
				if(stack.get(0).getStackColor().equals("G")) {
					System.out.println("Player A, where do you want to place the Green stack?\n");
				}
				else if(stack.get(0).getStackColor().equals("R")){
					System.out.println("Player A, where do you want to place the Red stack?\n");
				}
				else if(stack.get(0).getStackColor().equals("P")){
					System.out.println("Player A, where do you want to place the Purple stack?\n");
				}
				else if(stack.get(0).getStackColor().equals("B")){
					System.out.println("Player A, where do you want to place the Blue stack?\n");
				}
				else {
					System.out.println("Wrong stack color.\n");
				}
			}
			
			//check if the putstack is valid 
			if(A.userPutStack(stack)) {
				if(A.getRole().equalsIgnoreCase("H")) {
					System.out.println(display.putstack_display(A));
				}
				
				A_stacks.remove(0);
			}
			else {
				System.out.println("error in put stack, please put the stack again!\n");
				System.out.println(display.putstack_display(A));
			}
		}
		
		
		//player B
		if(B.getRole().equalsIgnoreCase("H")) {
			System.out.println(display.title(B));
			System.out.println(display.putstack_display(B));
			System.out.println("hrere");
		}
		
		while(!B_stacks.isEmpty()) {
			ArrayList<Stack> stack = B_stacks.get(0);
			if(B.getRole().equalsIgnoreCase("H")) {
				if(stack.get(0).getStackColor().equals("G")) {
					System.out.println("Player B, where do you want to place the Green stack?\n");
				}
				else if(stack.get(0).getStackColor().equals("R")){
					System.out.println("Player B, where do you want to place the Red stack?\n");
				}
				else if(stack.get(0).getStackColor().equals("P")){
					System.out.println("Player B, where do you want to place the Purple stack?\n");
				}
				else if(stack.get(0).getStackColor().equals("B")){
					System.out.println("Player B, where do you want to place the Blue stack?\n");
				}
				else {
					System.out.println("Wrong stack color.\n");
				}
			}
			
			if(B.userPutStack(stack)) {
				if(B.getRole().equalsIgnoreCase("H")) {
					System.out.println(display.putstack_display(B));
				}
				
				B_stacks.remove(0);
			}
			else {
				System.out.println("error in put stack, please put the stack again!\n");
				System.out.println(display.putstack_display(B));
			}
			
		}
		
		//play game 
		while(true) {
			if(A.getRole().equalsIgnoreCase("H")) {
				System.out.println(display.play_display(A));
				if(!A.play()) {
					System.out.println("invalid input\n");
					System.out.println(display.play_display(A));
				}
				else {
					System.out.println(display.play_display(A));
				} 
			}
			else {
				A.play();
			}
			
			if(A.Win()) {
				System.out.println(A.getName()+ " win! Game over!\n");
				break;
			}
			if(B.getRole().equalsIgnoreCase("H")) {
				System.out.println(display.play_display(B));
				if(!B.play()) {
					System.out.println("invalid input\n");
					System.out.println(display.play_display(B));
				}
				else {
					System.out.println(display.play_display(B));
				}
			}
			else {
				B.play();
			}
			
			if(B.Win()) {
				System.out.println(B.getName() + " win! Game over!\n");
				break;
			}
			
		}

	}
	
	public static void main(String[] args) {
		System.out.println("Please give name to first player");
		Scanner scan = new Scanner(System.in);
		String n1 = scan.nextLine();	
		
		System.out.println("Please select the first player to be Computer(C) or Human(H)");
		String p1 = scan.nextLine();
		
		System.out.println("Please give name to second player");
		String n2 = scan.nextLine();
		
		System.out.println("Please select the second player to be Computer(C) or Human(H)");
		String p2 = scan.nextLine();
		
		SallysStash game = new SallysStash(p1,p2,n1,n2);
		
		
//		scan.close();
		game.run();
	}
}
