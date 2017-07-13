package site.root3287.sudo2.events;

public class EventType {
	protected String name;
	public EventType(String name){
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
	public String getName(){
		return name;
	}
}
