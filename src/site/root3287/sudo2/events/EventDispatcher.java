package site.root3287.sudo2.events;

import java.util.ArrayList;
import java.util.List;

public class EventDispatcher {
	private EventType type;
	private List<Listener> listeners = new ArrayList<>();
	
	public EventDispatcher(EventType e){
		this.type = e;
	}
	
	public void addListener(Listener l){
		this.listeners.add(l);
	}
	
	public void removeListener(Listener l){
		this.listeners.remove(l);
	}
	
	public void removeListener(int l){
		this.listeners.remove(l);
	}
	
	public boolean hasListener(Listener l){
		if(listeners.contains(l)){
			return true;
		}
		return false;
	}
	
	public void execute(Event e){
		if(!type.getName().equals(e.getType().getName())){
			return;
		}
		for(Listener l : listeners){
			if(!e.hasHandled())
				e.setHandled(l.onEvent(e));
		}
	}
}
