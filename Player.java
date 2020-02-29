import java.util.*;

//Class of the Player
public class Player {
	private String role;
	private String name;
	private String op_name;
	private Board me;
	private Board opponent;
	private ArrayList<ArrayList<Stack>> stacks; 
	private Map<String,Integer> moves;
	
	//constructor
	public Player(String me, String op, String role) {
	
		moves = new HashMap<String, Integer>();
		moves.put("M", 3);
		moves.put("S", 3);
		this.name  = me;
		this.op_name = op;
		this.role = role;
		this.stacks = new ArrayList<ArrayList<Stack>>();
		//two Green normal 
		for(int i = 0; i <1; i++) {
			Stack stack = new Stack("G",2, "normal");
			ArrayList<Stack> one_stack = new ArrayList<Stack>();
			one_stack.add(stack);
			this.stacks.add(one_stack);
		}
		//Three purple normal
		for(int i = 0; i <1; i++) {
			Stack stack = new Stack("P",3, "normal");
			ArrayList<Stack> one_stack = new ArrayList<Stack>();
			one_stack.add(stack);
			this.stacks.add(one_stack);
		}
		//three superstacks
		for(int i = 0; i <1; i++) {
			Stack first_stack = new Stack("R",3,"Superstacks");
			//first_stack.setOwner(this);
			ArrayList<Stack> two_stack = new ArrayList<Stack>();
			two_stack.add(first_stack);
			Stack second_stack = new Stack("R", 1,"Superstacks");
			two_stack.add(second_stack);
			this.stacks.add(two_stack);
		}
		//three crazystacks
		for(int i = 0; i <1; i++) {
			Stack first_stack = new Stack("B",3,"Crazystacks");
			//first_stack.setOwner(this);
			ArrayList<Stack> two_stack = new ArrayList<Stack>();
			two_stack.add(first_stack);
			Stack second_stack = new Stack( "B", 3,"Crazystacks");
			two_stack.add(second_stack);
			this.stacks.add(two_stack);
		}
	}
	
	//if the player is human or computer
	public String getRole() {
		return this.role;
	}
	
	
	
	//return the number of the move
	public Integer getMovenum(String move){
		return this.moves.get(move);
	}
	
	//set my board
	public void setMe(Board me) {
		this.me = me;
	}
	
	//get my board
	public Board getMe() {
		return this.me;
	}
	
	//set opponent board
	public void setOp(Board op) {
		this.opponent = op;
	}
	
	//get the opponent board 
	public Board getOp() {
		return this.opponent;
	}
	
	//get the name of player
	public String getName() {
		return this.name;
	}
	
	//get the opponent name
	public String getOpName() {
		return this.op_name;
	}
	
	//get the stacks of the player
	public ArrayList<ArrayList<Stack>> getStacks(){
		return this.stacks;
	}
	
	//put the stacks on me 
	public boolean putStackonMe(ArrayList<Stack> stacks, int x, int y, String dir) {
		if(this.me.validputStack(stacks, x, y, dir)) {
			this.me.putStack(stacks, x, y, dir);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	//guess a point
	public boolean guess(int x, int y) {
		if(x<10 && x>= 0 && y <20 && y>=0) {
			 this.opponent.check(x, y);
			 return true;
		}
		else {
			System.out.println("invalid x or y!\n");
			return false;
		}
	}
	
	//get the shape of the stack
	public String retrieve_shape(int x, int y){
		return this.me.getSquare(x, y).getOwner().getShape();
	}
	
	//check if the move is a valid 
	public boolean move(int start_x, int start_y, int end_x, int end_y,String dir){
		if(this.moves.get("M") <= 0) {
			System.out.println("No more move.");
			return true;
		}
		if(start_x<0||start_x>9||start_y<0||start_y>19 ||end_x<0||end_x>9||end_y<0||end_y>19) {
			System.out.println("Out boundary. Please move again!");
			return false;
			
		}

		if(this.me.getSquare(start_x, start_y).isMine()) {		
			Stack square_owner = this.me.getSquare(start_x, start_y).getOwner();
			ArrayList<Stack> stacks_ = new ArrayList<Stack>();			

			for(int i = 0; i< this.stacks.size();i++) {
				for(Stack s: this.stacks.get(i)) {
					if(s.equals(square_owner)) {
						stacks_ = this.stacks.get(i);
						break;
					}	
				}
			}
		
			ArrayList<Square> squares = this.me.copy_stack_from_board(stacks_);
			this.me.remove_test(stacks_);
			if(this.me.validputStack(stacks_, end_x, end_y, dir)){
				this.me.remove_stack(stacks_);
				this.me.putStack(stacks_, end_x, end_y, dir);
				this.moves.replace("M", this.moves.get("M")-1);
				return true;
			}
			else {
				this.me.restore_stack(squares);
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	//scan a point 
	public void scan_move(int x, int y) {
		if(this.moves.get("S") <= 0) {
			System.out.println("No more scan.");
			return;
		}
		if(x <0 || x>9 || y <0 || y > 19) {
			System.out.println("Invalid scan coordinate, please scan again");
			 
		}
		else {
			
			System.out.println(scan(x,y));
		}
	}
	
	//print of the scan 
	public String scan(int x,int y) {
		Map<String, Integer> res = new HashMap<String, Integer>();
		if(this.opponent.getSquare(x, y).isMine() && !this.opponent.getSquare(x, y).isShown()) {
			String color = this.opponent.getSquare(x, y).getColor();
			res.put(color, 1);
		}
		for(int i = 0; i < 4; i++) {
			for(int j = 3-i; j >=0; j--) {
				if(x+i> 9 || y+j >19 || (i ==0 && j==0)) {
					continue;
				}
				if(this.opponent.getSquare(x+i, y+j).isMine() && !this.opponent.getSquare(x+i, y+j).isShown()) {
					String color = this.opponent.getSquare(x+i, y+j).getColor();
					if(res.containsKey(color)) {
						res.replace(color, res.get(color) +1); 
					}
					else {
						res.put(color, 1);
					}
				}
			}
		}
		for(int i = 0; i < 4; i++) {
			for(int j = 3-i; j >=0; j--) {
				if(x-i<0 || y+j >19|| i ==0 ) {
					continue;
				}
				if(this.opponent.getSquare(x-i, y+j).isMine() && !this.opponent.getSquare(x-i, y+j).isShown()) {
					String color = this.opponent.getSquare(x-i, y+j).getColor();
					if(res.containsKey(color)) {
						res.replace(color, res.get(color) +1); 
					}
					else {
						res.put(color, 1);
					}
				}
			}
		}
		for(int i = 0; i < 4; i++) {
			for(int j = 3-i; j >=0; j--) {
				if(x+i> 9 || y-j <0||  j==0) {
					continue;
				}
				if(this.opponent.getSquare(x+i, y-j).isMine() && !this.opponent.getSquare(x+i, y-j).isShown()) {
					String color = this.opponent.getSquare(x+i, y-j).getColor();
					if(res.containsKey(color)) {
						res.replace(color, res.get(color) +1); 
					}
					else {
						res.put(color, 1);
					}
				}
			}
		} 
		for(int i = 0; i < 4; i++) {
			for(int j = 3-i; j >=0; j--) {
				if(x-i<0 || y-j <0|| i ==0 || j==0) {
					continue;
				}
				if(this.opponent.getSquare(x-i, y-j).isMine() && !this.opponent.getSquare(x-i, y-j).isShown()) {
					String color = this.opponent.getSquare(x-i, y-j).getColor();
					if(res.containsKey(color)) {
						res.replace(color, res.get(color) +1); 
					}
					else {
						res.put(color, 1);
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(scan_helper(res,"G"));
		sb.append(scan_helper(res,"P"));
		sb.append(scan_helper(res,"R"));
		sb.append(scan_helper(res,"B"));
		return sb.toString();
	}
	
	//return the result of the scan
	public String scan_helper(Map<String,Integer>map, String color) {
		String real_color;
		if(color.equals("G")) {
			real_color = "Green";
		}
		else if(color.equals("R")) {
			real_color = "Red"; 
		}
		else if(color.equals("P")) {
			real_color = "Purple";
		}
		else {
			real_color = "Blue";
		}
		StringBuilder sb = new StringBuilder();
		if(map.containsKey(color)) {
			sb.append(real_color).append(" stacks occupy ").append(Integer.toString(map.get(color))).append(" squares\n");
		}
		else {
			sb.append(real_color).append(" stacks occupy 0 squares\n");
		}
		return sb.toString();
	}
	
	//check if this player win 
	public boolean Win() {
		return this.opponent.isSolved();
	}
	
	//parent play() which will be override
	public boolean play() {
		return true;
	}
	
	//parent userPutStack which will be override
	public boolean userPutStack(ArrayList<Stack> stack) {
		return true;
	}
	
	//decrease the move time
	public void decrease_move(String move) {
		this.moves.replace(move, this.moves.get(move)-1);
		
	}
}
