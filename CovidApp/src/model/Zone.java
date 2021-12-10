package model;

public class Zone {
	String id;
	String name;
	int capacity;
	int receivedSlot;

	public Zone(String id, String name, int capacity, int receivedSlot) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.receivedSlot = receivedSlot;
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

	public int getReceivedSlot() {
		return receivedSlot;
	}

	public void setreceivedSlot(int receivedSlot) {
		this.receivedSlot = receivedSlot;
	}

	@Override
	public String toString() {
		return id;
	}
}