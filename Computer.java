
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random; 

//class of computer
public class Computer extends Player{
	//constructor
	public Computer(String me, String op, String role) {
		super(me, op, role);
	}
	@Override
	//computer put stack on board
	public boolean userPutStack(ArrayList<Stack> stack) {
		Random rand = new Random(); 
		int y = rand.nextInt(20);
		int x = rand.nextInt(10);
		int dir_int = rand.nextInt(4);
		if(stack.size() == 1) {
			if(dir_int == 1 || dir_int == 3) {
				while(true) {
					if(putStackonMe(stack, x, y, "H")) {
						return true;
					}
					else {
						y = rand.nextInt(20);
						x = rand.nextInt(10);
					}
				}
			}
			else {
				while(true) {
					if(putStackonMe(stack, x, y, "V")) {
						return true;
					}
					else {
						y = rand.nextInt(20);
						x = rand.nextInt(10);
					}
				}
			} 
		}
		else {
			if(dir_int == 0) {
				while(true) {
					if(putStackonMe(stack, x, y, "U")) {
						return true;
					}
					else {
						y = rand.nextInt(20);
						x = rand.nextInt(10);
					}		
				}
			}
			else if(dir_int == 1){
				while(true) {
					if(putStackonMe(stack, x, y, "D")) {
						return true;
					}
					else {
						y = rand.nextInt(20);
						x = rand.nextInt(10);
					}
				}
			}
			else if(dir_int == 2){
				while(true) {
					if(putStackonMe(stack, x, y, "R")) {
						return true;
					}
					else {
						y = rand.nextInt(20);
						x = rand.nextInt(10);
					}
				}
			}
			else {
				while(true) {
					if(putStackonMe(stack, x, y, "L")) {
						return true;
					}
					else {
						y = rand.nextInt(20);
						x = rand.nextInt(10);
					}
				}
			}
		}	
	}
	
	@Override
	//computer play the game
	public boolean play() {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		int select_int = rand.nextInt(1000);
		String select;
		if(select_int < 100) {
			if(this.getMovenum("M") ==0) {
				select =  "D";
			}
			else {
				select = "M";
			}			
		}
		else if(select_int >900){
			if(this.getMovenum("S") ==0) {
				select =  "D";
			}
			else {
				select = "S";
				this.decrease_move("S");
			}			 
		}
		else {
			select =  "D";
		}	
		if(select.equalsIgnoreCase("M")) {
			int start_y = rand.nextInt(20);
			int start_x = rand.nextInt(10);
			int end_y = rand.nextInt(20);
			int end_x = rand.nextInt(10); 
			int dir_int = rand.nextInt(4);
			String dir;
			while(true) {
				if(this.getMe().getSquare(start_x, start_y).isMine()){
					break;
				}
				else {				
					start_y = rand.nextInt(20);
					start_x = rand.nextInt(10);
				}
				
			}
			String shape = this.retrieve_shape(start_x, start_y);
			if(shape.equals("normal")) {
				if(dir_int == 0 || dir_int == 2) {
					dir = "V";
				}
				else {
					dir = "H";
				}
			}
			else {
				if(dir_int == 0) {
					dir = "D";
				}
				else if(dir_int == 1){
					dir = "U";
				}
				else if(dir_int == 2){
					dir = "R";
				}
				else {
					dir = "L";
				} 
			}
			
			
			while(true) {
				if(this.move(start_x, start_y, end_x, end_y, dir)) {
					StringBuilder m = new StringBuilder();
					m.append("Player ").append(this.getName()).append(" used a special action\n");
					System.out.println(m.toString());
					return true;
				}
				end_y = rand.nextInt(20);
				end_x = rand.nextInt(10);
			}
			
		}
		else if(select.equalsIgnoreCase("S")) {
			StringBuilder s = new StringBuilder();
			s.append("Player ").append(this.getName()).append(" used a special action\n");
			System.out.println(s.toString());
			return true;
		}
		else{
			int x  =rand.nextInt(10);
			int y = rand.nextInt(20);
			return this.guess(x, y);
			
		}
		
		
	}
}
