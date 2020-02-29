import java.util.ArrayList;

//Class of Board
public class Board {
	private ArrayList<ArrayList<Square>> Squares;
	private int x_len;
	private int y_len;
	private boolean solved;
	
	//Constructor
	public Board(int x, int y) {
		this.x_len = x;
		this.y_len = y;
		this.solved = false;
		this.Squares = new ArrayList<ArrayList<Square>>();
		for(int i =0; i<y; i++) {
			ArrayList<Square> row = new ArrayList<Square>();
			for(int j = 0; j<x; j++) {
				Square cell = new Square(j,i," ");
				row.add(cell);
			}
			this.Squares.add(row);
		}
	}
	
	//check if it is valid to put a simple stack(no shape)
	//V is vertical up->down
	//A is vertical down->up
	//H is horizontal left->right
	//O is horizontal right->left
	public boolean valid_put_simple(Stack stack,int x,int y, String dir) {
		if(x>9 || y >19 || x<0 || y <0) {
			return false;
		}
		int x_dir = x;
		int y_dir = y;
		if(dir.equalsIgnoreCase("V")) {
			for(int i = 0; i<stack.getSize(); i++) {
				if(y_dir>19) {
					return false;
				}
				if(this.Squares.get(y_dir).get(x_dir).isMine()) {
					return false;
				}				
				y_dir++;
			}
			return true;
		}
		else if(dir.equalsIgnoreCase("H")){
			x_dir = x;
			y_dir = y;
			for(int i = 0; i < stack.getSize();i++) {
				if(x_dir>9) {
					return false;
				}
				if(this.Squares.get(y_dir).get(x_dir).isMine()) {
					return false;
				}				
				x_dir++;
			}
			return true;
		}	
		else if(dir.equalsIgnoreCase("A")) {
			x_dir = x;
			y_dir = y;
			for(int i = 0; i < stack.getSize();i++) {
				if(x_dir<0) {
					return false;
				}
				if(this.Squares.get(y_dir).get(x_dir).isMine()) {
					return false;
				}
				
				y_dir--;
			}
			return true;
		}
		else {
			x_dir = x;
			y_dir = y;
			for(int i = 0; i < stack.getSize();i++) {
				if(x_dir<0) {
					return false;
				}
				if(this.Squares.get(y_dir).get(x_dir).isMine()) {
					return false;
				}				
				x_dir--;
			}
			return true;
		}
	}
	
	// put a simple stack(no shape)
		//V is vertical up->down
		//A is vertical down->up
		//H is horizontal left->right
		//O is horizontal right->left
	public void put_simple_stack(Stack stack,int x,int y, String dir) {
		int x_dir = x;
		int y_dir = y;
		String color = stack.getStackColor();
		if(dir.equalsIgnoreCase("V")) {
			for(int i = 0; i <stack.getSize(); i++) {
				stack.getStack().get(i).setX(x_dir);
				stack.getStack().get(i).setY(y_dir);
				Boolean shown = stack.getStack().get(i).isShown();			
				boolean keep = stack.getStack().get(i).isKeep();				
				this.Squares.get(y_dir).get(x_dir).setColor(color);
				this.Squares.get(y_dir).get(x_dir).setMine(true);
				this.Squares.get(y_dir).get(x_dir).setShown(shown);
				this.Squares.get(y_dir).get(x_dir).setOwner(stack);
				
				this.Squares.get(y_dir).get(x_dir).setkeep(keep);
				this.Squares.get(y_dir).get(x_dir).setOwner(stack);
				y_dir++;
			}
		}
		else if(dir.equalsIgnoreCase("H")){
			for(int i = 0; i <stack.getSize(); i++) {
				stack.getStack().get(i).setX(x_dir);
				stack.getStack().get(i).setY(y_dir);				
				Boolean shown = stack.getStack().get(i).isShown();				
				boolean keep = stack.getStack().get(i).isKeep();				
				this.Squares.get(y_dir).get(x_dir).setColor(color);
				this.Squares.get(y_dir).get(x_dir).setMine(true);
				this.Squares.get(y_dir).get(x_dir).setShown(shown);
				this.Squares.get(y_dir).get(x_dir).setOwner(stack);				
				this.Squares.get(y_dir).get(x_dir).setkeep(keep);
				this.Squares.get(y_dir).get(x_dir).setOwner(stack);			
				x_dir++;
			}
		}
		else if(dir.equalsIgnoreCase("A")) {
			for(int i = 0; i <stack.getSize(); i++) {
				stack.getStack().get(i).setX(x_dir);
				stack.getStack().get(i).setY(y_dir);				
				Boolean shown = stack.getStack().get(i).isShown();			
				boolean keep = stack.getStack().get(i).isKeep();				
				this.Squares.get(y_dir).get(x_dir).setColor(color);
				this.Squares.get(y_dir).get(x_dir).setMine(true);
				this.Squares.get(y_dir).get(x_dir).setShown(shown);
				this.Squares.get(y_dir).get(x_dir).setOwner(stack);			
				this.Squares.get(y_dir).get(x_dir).setkeep(keep);
				this.Squares.get(y_dir).get(x_dir).setOwner(stack);				
				y_dir--;
			}
		}
		else {
			for(int i = 0; i <stack.getSize(); i++) {
				stack.getStack().get(i).setX(x_dir);
				stack.getStack().get(i).setY(y_dir);				
				Boolean shown = stack.getStack().get(i).isShown();
				boolean keep = stack.getStack().get(i).isKeep();			
				this.Squares.get(y_dir).get(x_dir).setColor(color);
				this.Squares.get(y_dir).get(x_dir).setMine(true);
				this.Squares.get(y_dir).get(x_dir).setShown(shown);
				this.Squares.get(y_dir).get(x_dir).setOwner(stack);
				this.Squares.get(y_dir).get(x_dir).setkeep(keep);
				this.Squares.get(y_dir).get(x_dir).setOwner(stack);
				
				x_dir--;
			}
		}		
	}
	
	//Check if it is valid to put a stack with shape
	public boolean validputStack(ArrayList<Stack> stacks, int x, int y, String dir) {
		if(stacks.size() == 1) {
			Stack stack = stacks.get(0);
			if(!valid_put_simple(stack, x, y , dir)) {
				return false;
			}
			if(dir.equalsIgnoreCase("V") || dir.equalsIgnoreCase("H") ) {
				return true;
			}
			else {
				System.out.println("invalid input");
				return false;
			}
		}
		else {
			Stack first_stack = stacks.get(0); 
			Stack second_stack = stacks.get(1);
			if(!dir.equalsIgnoreCase("U") && !dir.equalsIgnoreCase("D") &&!dir.equalsIgnoreCase("R") &&!dir.equalsIgnoreCase("L")) {
				return false;
			}
			if(first_stack.getShape() == "Superstacks") {
				if(dir.equalsIgnoreCase("U")) {
					if(!valid_put_simple(first_stack, x-1, y+1,"H")) {
						return false;
					}
					if(!valid_put_simple(second_stack, x, y, "H")) {						
						return false;
					}
				}
				else if(dir.equalsIgnoreCase("R")) {
					if(!valid_put_simple(first_stack, x, y,"V")) {						
						return false;
					}
					if(!valid_put_simple(second_stack, x+1, y+1, "V")) {						
						return false;
					}					
				}
				else if(dir.equalsIgnoreCase("D")) {
					if(!valid_put_simple(first_stack, x+2, y,"O")) {						
						return false;
					}
					if(!valid_put_simple(second_stack, x+1, y+1, "H")) {						
						return false;
					}					
				}
				else if(dir.equalsIgnoreCase("L")) {
					if(!valid_put_simple(first_stack, x, y+2,"O")) {						
						return false;
					}
					if(!valid_put_simple(second_stack, x-1, y+1, "V")) {
						
						return false;
					}					
				}
				else {
					return false;
				}				
			}
			else{
				if(dir.equalsIgnoreCase("U")) {
					if(!valid_put_simple(first_stack, x, y , "V")) {					
						return false;
					}
					if(!valid_put_simple(second_stack, x+1, y+2 , "V")) {						
						return false;
					}					
				}
				else if(dir.equalsIgnoreCase("R")) {
					if(!valid_put_simple(first_stack, x, y , "H")) {						
						return false;
					}
					if(!valid_put_simple(second_stack, x+2, y-1 , "H")) {						
						return false;
					}					
				}
				else if(dir.equalsIgnoreCase("D")) {
					if(!valid_put_simple(first_stack, x-1, y+4 , "A")) {					
						return false;
					}
					if(!valid_put_simple(second_stack, x, y+2 , "A")) {						
						return false;
					}					
				}
				else if(dir.equalsIgnoreCase("L")) {
					if(!valid_put_simple(first_stack, x+4, y+1 , "O")) {						
						return false;
					}
					if(!valid_put_simple(second_stack, x+2, y , "O")) {						
						return false;
					}					
				}
				else {

					return false;
				}
			}
			
		}
		return true;
	}
	
	//put stack on board 
	public void putStack(ArrayList<Stack> stacks, int x, int y, String dir) {
		if(stacks.size() == 1) {
			Stack stack = stacks.get(0);
			put_simple_stack(stack, x, y, dir);	
		}
		//if it is not a simple shape, take apart the stack and put them
		else {
			Stack first_stack = stacks.get(0); 
			Stack second_stack = stacks.get(1);
			if(first_stack.getShape() == "Superstacks") {
				if(dir.equalsIgnoreCase("U")) {
					put_simple_stack(first_stack, x-1, y+1, "H");
					put_simple_stack(second_stack, x, y, "H");
				}
				else if(dir.equalsIgnoreCase("R")) {
					
					put_simple_stack(first_stack, x, y, "V");
					put_simple_stack(second_stack, x+1, y+1, "V");
				}
				else if(dir.equalsIgnoreCase("D")) {
					
					put_simple_stack(first_stack, x+2, y, "O");
					put_simple_stack(second_stack, x+1, y+1, "H");
				}
				else if(dir.equalsIgnoreCase("L")) {
					put_simple_stack(first_stack, x, y+2, "A");
					put_simple_stack(second_stack, x-1, y+1, "V");
				}
				else {
					return;
				}
				
			}
			else{
				if(dir.equalsIgnoreCase("U")) {
					put_simple_stack(first_stack, x, y, "V");
					put_simple_stack(second_stack, x+1, y+2, "V");
				}
				else if(dir.equalsIgnoreCase("R")) {
					
					put_simple_stack(first_stack, x, y, "H");
					put_simple_stack(second_stack, x+2, y-1, "H");
				}
				else if(dir.equalsIgnoreCase("D")) {
					
					put_simple_stack(first_stack, x-1, y+4, "A");
					put_simple_stack(second_stack, x, y+2, "A");
				}
				else if(dir.equalsIgnoreCase("L")) {
					
					put_simple_stack(first_stack, x+4, y+1, "O");
					put_simple_stack(second_stack, x+2, y, "O");
				}
				else {

					return;
				}
			}
			
		}
		return;		
	}

	//check square to be shown
	public void setSquare(int x, int y) {
		this.Squares.get(y).get(x).setShown(true);
		this.Squares.get(y).get(x).setkeep(false);
	}
	
	//set the square to be unshown
	public void desetSquare(int x, int y) {
		this.Squares.get(y).get(x).setShown(false);
	}
	
	//set the square to be not mine
	public void desetmine(int x, int y) {
		this.Squares.get(y).get(x).setMine(false);;
	}
	
	//set the owner of the square is null
	public void desetowner(int x,int y) {
		this.Squares.get(y).get(x).setOwner(null);
	}
	
	//check if the baord is be solved 
	public boolean isSolved() {
		for(int i = 0; i < this.Squares.size();i++) {
			for(int j = 0; j < this.Squares.get(i).size(); j++) {
				if(this.Squares.get(i).get(j).isMine() && !this.Squares.get(i).get(j).isShown()) {
					this.solved = false;
					return false;
				}
			}
		}
		this.solved = true;
		return true;
	}
	
	//get a square from the board
	public Square getSquare(int x, int y) {
		return this.Squares.get(y).get(x);
	}
	
	//check if the square is a mine or not
	public void check(int x, int y) {
		if(this.Squares.get(y).get(x).isMine()) {
			System.out.println("You found a stack!\n");
		}
		else {
			System.out.println("You missed!\n");
		}
		setSquare(x,y);
	}
	
	//find the owner of the square
	public Stack find_stack_by_cell(int x, int y) {
		return this.Squares.get(y).get(x).getOwner();
	}
	
	//shallow remove, only for checking if put a stack is valid 
	public void remove_test(ArrayList<Stack> stacks) {
		for(int i = 0; i < stacks.size();i++) {
			Stack stack = stacks.get(i);
			for(int j = 0; j < stack.getSize();j++) {
				Square cell = stack.getStack().get(j);
				int x = cell.getX();
				int y = cell.getY();
				this.desetmine(x, y);
			}
		}		
	}
	
	//deep move, de-set the squares and copy the status of these squares back to stack
	public void remove_stack(ArrayList<Stack> stacks) {
		for(int j = 0; j<stacks.size();j++) {
			Stack stack = stacks.get(j);
			ArrayList<Square> Squares = stack.getStack();
			for(int  i = 0; i < stack.getSize(); i++) {
				Square cell = Squares.get(i);
				int x = cell.getX();
				int y = cell.getY();				
				cell.setShown(this.Squares.get(y).get(x).isShown());
				cell.setkeep(true);				
				this.desetmine(cell.getX(), cell.getY());
				this.desetowner(cell.getX(), cell.getY());
				this.desetSquare(x, y);
				this.Squares.get(y).get(x).setColor(" ");
				this.Squares.get(y).get(x).setkeep(true);
			}
		}
		
	}
	
	//copy the stack from the board.
	public ArrayList<Square> copy_stack_from_board(ArrayList<Stack> stacks){
		ArrayList<Square> squares = new ArrayList<Square>();
		for(int j = 0; j<stacks.size();j++) {
			Stack stack = stacks.get(j);
			ArrayList<Square> Squares = stack.getStack();
			for(int  i = 0; i < stack.getSize(); i++) {
				Square cell = Squares.get(i);
				int x = cell.getX();
				int y = cell.getY();
				Square board_cell = this.Squares.get(y).get(x);
				String color = board_cell.getColor();
				Square e =  new Square(x,y,color);
				squares.add(e);
			}
		}
		return squares;
	}
	
	//If the remove_test is not true, then should restore the status for the squares as before
	public void restore_stack(ArrayList<Square> squares) {
		for(int j = 0; j<squares.size();j++) {
			Square cell = squares.get(j);
			int x = cell.getX();
			int y = cell.getY();
			String color = cell.getColor();
			Square board_cell = this.Squares.get(y).get(x);
			board_cell.setColor(color);
			board_cell.setMine(true);
			board_cell.setShown(cell.isShown());
			
		}
		
		return;
	}
}
