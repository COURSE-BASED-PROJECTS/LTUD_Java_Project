package model;

public class Zone {
	String id;
	String name;
	int capacity;
	int emptySlot;
	public Zone(String id, String name, int capacity, int emptySlot) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.emptySlot = emptySlot;
	}
	public Zone(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getEmptySlot() {
		return emptySlot;
	}
	public void setEmptySlot(int emptySlot) {
		this.emptySlot = emptySlot;
	}
	@Override
	public String toString() {
		return id;
	}
	
}
