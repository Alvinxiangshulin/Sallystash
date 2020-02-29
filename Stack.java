
import java.util.ArrayList;
//class of stack
public class Stack {
	private ArrayList<Square> Squares;
	private int size;
	private String color;
	private String shape;
	private Player owner;
	
	//constructor
	public Stack(String color, int size, String type) {
		this.Squares = new ArrayList<Square>();
		for(int i = 0; i < size; i++) {
			Square cell = new Square(0,0, color);
			cell.setOwner(this);
			cell.setMine(true);
			this.Squares.add(cell);
		}
		this.color = color;
		this.size = size; 
		this.shape = type;
	}
	
	//get the shape
	public String getShape() {
		return this.shape;
	}
	
	//get the stacks
	public ArrayList<Square> getStack() {
		return this.Squares;
	}
	
	//get the color of stack
	public String getStackColor() {
		return this.color;
	}

	//get the size of stack
	public int getSize() {
		return this.size;
	}
	
}
