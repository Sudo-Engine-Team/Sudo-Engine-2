package site.root3287.sudo2.events;

public abstract class Event {
	private EventType type;
	private boolean handled = false;
	
	public Event(EventType type){
		this.type = type;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public boolean hasHandled(){
		return this.handled;
	}
	public void setHandled(boolean handle){
		this.handled = handle;
	}
}
