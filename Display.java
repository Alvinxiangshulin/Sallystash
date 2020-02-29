import java.util.ArrayList;
import java.util.Scanner;

//class of display
public class Display {
	//begin 
	public String title(Player p) {
		StringBuilder sb = new StringBuilder();
		sb.append("Player ").append(p.getName()).append(", you are going place Sally’s stash on the board. Make sure Player B isn’t looking! For each stack, type the coordinate of the upper left side of the stash, followed by either H (for horizontal) or V (for vertical). For example, M4H would place a stack horizontally starting at M4 and going to the right. You have\n" + 
				"2 Green stacks that are 1x2\n" + 
				"3 Purple stacks that are 1x3\n" + 
				"3 Red stacks that are 1x4 2 Blue stacks that are 1x6");
		return sb.toString();
	}
	//display the board when one player put his stack
	public String putstack_display(Player p) {
		StringBuilder sb = new StringBuilder();
		sb.append("--------------------------\n");
		sb.append("  0|1|2|3|4|5|6|7|8|9  \n"); 
		for(int i =0; i < 20; i++) {
			sb.append((char)(i+65)).append(" ");
			for(int j = 0; j<10; j++) {
				//string of self
				Square cell = p.getMe().getSquare(j, i);
				if(cell.isMine()) {
					if(j < 9) {
						sb.append(cell.getColor()).append("|");
					}
					else {
						sb.append(cell.getColor());
					}
				}
				else {
					if(j< 9) {
						sb.append(" |");
					}
					else {
						sb.append(" ");
					}
				}
			}
			sb.append(" ").append((char)(i+65)).append("\n");
		}
		sb.append("  0|1|2|3|4|5|6|7|8|9  \n");
		sb.append("------------------------------\n");
		return sb.toString();
	}
	
	//display when one player is play the game
	public String play_display(Player p) {
		StringBuilder sb = new StringBuilder();
		sb.append("----------------------------------------------------\n");
		sb.append("Player ").append(p.getName()).append("'s turn:\n");
		sb.append("     Your tree                   Player ").append(p.getOpName()).append("'s tree\n");
		sb.append("  0|1|2|3|4|5|6|7|8|9          0|1|2|3|4|5|6|7|8|9\n");
		for(int i =0; i < 20; i++) {
			sb.append((char)(i+65)).append(" ");
			for(int j = 0; j<10; j++) {
				
				//string of self
				Square cell = p.getMe().getSquare(j, i);
				if(cell.isShown()) {
					if(cell.isMine()) {	
						if(j < 9) {
							sb.append("*|");
						}
						else {
							sb.append("*");
						}
					}
					else {
						if(j < 9) {
							sb.append(cell.getColor()).append("|");
						}
						else {
							sb.append(cell.getColor());
						}
					}
				}
				else {
					if(cell.isMine()) {
						
						if(j < 9) {
							sb.append(cell.getColor()).append("|");
						}
						else {
							sb.append(cell.getColor());
						}
					}
					else {
						
						if(j< 9) {
							sb.append(" |");
						}
						else {
							sb.append(" ");
						}
					}
				}
			}
			sb.append(" ").append((char)(i+65));
			sb.append("      ");
			
			//string of opponent
			sb.append((char)(i+65)).append(" ");
			for(int k = 0; k<10; k++) {
				Square op_cell = p.getOp().getSquare(k, i);
				if(op_cell.isShown() ) {
					if(op_cell.isKeep()) {
						if(k< 9) {
							sb.append(op_cell.getState()+"|");
						}
						else {
							sb.append(op_cell.getState());
						}
					}
					else {
						if(op_cell.isMine()) {
							if(k < 9) {
								sb.append(op_cell.getColor()).append("|");
							}
							else {
								sb.append(op_cell.getColor());
							}
							op_cell.setState(op_cell.getColor());
						}
						
						else { 
							if(k < 9) {
								sb.append("X|");
							}
							else {
								sb.append("X");
							}
							op_cell.setState("X");
						}
					}
				}
				else { 
					if(op_cell.isKeep()) {
						if(k< 9) {
							sb.append(op_cell.getState()+"|");
						}
						else {
							sb.append(op_cell.getState());
						}
					}
					else {
						if(k < 9) {
							sb.append(" |");
						}
						
						else {
							sb.append(" ");
						}
						op_cell.setState(" ");
					}
					
				}
			}
			
			sb.append(" ").append((char)(i+65)).append("\n");
		}
		
		sb.append("  0|1|2|3|4|5|6|7|8|9          0|1|2|3|4|5|6|7|8|9\n");
		sb.append("----------------------------------------------------\n");
		return sb.toString();
	}

}	
