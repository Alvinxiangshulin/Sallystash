import java.util.ArrayList;
import java.util.Scanner;

//class of Human 
public class Human extends Player{
	//constructor
	public Human(String me, String op, String role) {
		super(me, op, role);
	}
	@Override
	//put stack function
	public boolean userPutStack(ArrayList<Stack> stack) {
		Scanner Scan = new Scanner(System.in);
		String select = Scan.nextLine();
		if(select.length() != 3) { 
			System.out.println("invalid select input");
		}
		int y = (int)(Character.toUpperCase(select.charAt(0)) - 'A');
		int x = (int)(select.charAt(1) - '0');
		String dir = select.substring(2);
		return(putStackonMe(stack, x, y, dir));
	} 
	@Override
	//play function
	public boolean play() {
		StringBuilder sb = new StringBuilder();
		sb.append("-------------------------------------------------------------------------------- \n"+
				"Possible actions for Player ").append(this.getName()).append(":\n" + 
						"D Dig in a square\n" + 
						"M Move a stack to another square ").append("( ").append(getMovenum("M")).append(" remaining) S Sonar scan ").append("( ").append(getMovenum("S")).append(" remaining)\n" + 
						"Player ").append(this.getName()).append(", what would you like to do? \n" + 
								"--------------------------------------------------------------------------------");
		System.out.println(sb.toString());
		Scanner Scan = new Scanner(System.in);
		String select = Scan.nextLine();
		if(select.equalsIgnoreCase("M")) {
			System.out.println("Start coordinate:");
			String start_pos = Scan.nextLine();
			if(start_pos.length() != 2) { 
				System.out.println("invalid start coordinate\n");
				return false;
			}
			System.out.println("End coordinate and direction:");
			String end_pos = Scan.nextLine();
			if(end_pos.length() != 3) { 
				System.out.println("invalid end input\n");
				return false;
			}
			int start_y = (int)(Character.toUpperCase(start_pos.charAt(0)) - 'A');
			int start_x = (int)(start_pos.charAt(1) - '0'); 
			int end_y = (int)(Character.toUpperCase(end_pos.charAt(0)) - 'A');
			int end_x = (int)(end_pos.charAt(1) - '0'); 
			String dir = end_pos.substring(2);
			return this.move(start_x, start_y,  end_x,  end_y, dir);
			
		}
		else if(select.equalsIgnoreCase("S")) {
			this.decrease_move("S");
			System.out.println("Enter coordinate:");
			String pos = Scan.nextLine();
			if(pos.length() != 2) {
				System.out.println("Invalid coordinate.");
				return false;
			}
			int y = (int)(Character.toUpperCase(pos.charAt(0)) - 'A');
			int x = (int)(pos.charAt(1) - '0');
			if( y < 0 || y> 19 || x < 0|| x>9) {
				System.out.println("Invalid X or Y.");
				return false;
			}
			this.scan_move(x, y);
			return true;
		}
		else if(select.equalsIgnoreCase("D")){
			System.out.println("Enter coordinate:");
			String pos = Scan.nextLine();
			if(pos.length() != 2) {
				System.out.println("Invalid coordinate.");
				return false;
			}
			int y = (int)(Character.toUpperCase(pos.charAt(0)) - 'A');
			int x = (int)(pos.charAt(1) - '0');
			return this.guess(x, y);
		}
		else {
			System.out.println("Invalid input\n");
			return false;
		}
		
	}
	

}
