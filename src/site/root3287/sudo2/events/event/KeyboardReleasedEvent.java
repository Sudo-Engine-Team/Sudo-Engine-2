package site.root3287.sudo2.events.event;

import site.root3287.sudo2.events.types.KeyboardReleaseEventType;

public class KeyboardReleasedEvent extends KeyboardEvent{
	public KeyboardReleasedEvent(int key) {
		super(new KeyboardReleaseEventType(), key);
	}
}
