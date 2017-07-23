package site.root3287.sudo2.events.event;

import site.root3287.sudo2.events.types.KeyboardDownEventType;

public class KeyboardDownEvent extends KeyboardEvent{

	public KeyboardDownEvent(int key) {
		super(new KeyboardDownEventType(), key);
	}

}
