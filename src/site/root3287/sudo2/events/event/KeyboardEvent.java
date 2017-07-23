package site.root3287.sudo2.events.event;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.EventType;

public abstract class KeyboardEvent extends Event{
	public KeyboardEvent(EventType type, int key) {
		super(type);
		this.key = key;
	}

	protected int key;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
}
