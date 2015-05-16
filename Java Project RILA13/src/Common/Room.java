package Common;

public class Room {
	private int number;
	private int chair;
	
	public Room(int number, int chair) {
		super();
		this.number = number;
		this.chair = chair;
	}

	
	//CRUD
	public void createRoom(int number, int chair) {
		
	}
	
	public void readRoom(){
		
	}
	
	public void updateRoom(int number, int chair){
		
	}
	
	public void deleteRoom(){
		
	}

	
	//GETTERS & SETTERS
	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getChair() {
		return this.chair;
	}

	public void setChair(int chair) {
		this.chair = chair;
	}
	
	
}
