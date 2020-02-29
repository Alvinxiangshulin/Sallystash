
//class of the square
public class Square {
	private int x;
	private int y;
	private boolean Mine = false;
	private boolean Shown = false;
	private String color = " ";
	private Stack owner;
	private boolean keep = false;
	private String previous_state = " ";

	//get the previous state of the square
	public String getState() {
		return this.previous_state;
	}
	
	//set the previos state
	public void setState(String b) {
		this.previous_state = b;
	}
	
	//constructor
	public Square(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.color = color;
	} 
	
	//check if keep the previous state
	public boolean isKeep() {
		return this.keep;
	}
	
	//set if keep the previous state
	public void setkeep(boolean b) {
		this.keep = b;
	}
	
	//check is mine?
	public boolean isMine() {
		return this.Mine;
	}
	
	//check is shown
	public boolean isShown() {
		return this.Shown;
	}
	
	//get x
	public int getX() {
		return this.x;
	}
	
	//get y
	public int getY() {
		return this.y;
	} 
	
	//set x
	public void setX(int x) {
		this.x = x;
	}
	
	//set y
	public void setY(int y) {
		this.y = y;
	}
	
	//get color
	public String getColor() {
		return this.color;
	}
	
	//set mine
	public void setMine(boolean b) {
		this.Mine = b;
	}
	
	//set shown
	public void setShown(boolean b) {
		this.Shown = b;
		
	}
	
	//get color
	public void setColor(String color) {
		this.color = color;
	}
	
	//get onwer
	public Stack getOwner() {
		return this.owner;
	}
	
	//set owner
	public void setOwner(Stack stack) {
		this.owner = stack;
	}
}
