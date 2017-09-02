package site.root3287.sudo2.net.event;

import site.root3287.sudo2.events.Event;

public class CommandEvent extends Event{
	public String[] args;
	public CommandEvent(String[] args) {
		super(new CommandEventType());
		this.args = args;
	}
	
}
