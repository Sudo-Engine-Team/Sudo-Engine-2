package site.root3287.sudo2.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import site.root3287.sudo2.component.Component;
import site.root3287.sudo2.component.EntityComponent;

public abstract class Entity {
	@SuppressWarnings("rawtypes")
	protected static HashMap<Class, HashMap<UUID, ? extends EntityComponent>> componentStores = new HashMap<>();
	public static List<UUID> allEntities = new ArrayList<>();
	protected UUID id;
	
	protected Entity(){
		this.id = UUID.randomUUID();
		allEntities.add(this.id);
	}

	public abstract void update(float delta);
	
	@SuppressWarnings("unchecked")
	public static <T extends EntityComponent> void addComponent(UUID entity, T component){

		synchronized (componentStores){
			HashMap<UUID, ? extends Component> store = componentStores.get(component.getClass());

			if (store == null){
				store = new HashMap<UUID, T>();
				componentStores.put(component.getClass(), (HashMap<UUID, ? extends EntityComponent>) store);
			}

			((HashMap<UUID, T>) store).put(entity, component);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends EntityComponent> void addComponent(T component){

		synchronized (componentStores){
			HashMap<UUID, ? extends Component> store = componentStores.get(component.getClass());

			if (store == null){
				store = new HashMap<UUID, T>();
				componentStores.put(component.getClass(), (HashMap<UUID, ? extends EntityComponent>) store);
			}

			((HashMap<UUID, T>) store).put(this.id, component);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getComponent(UUID e, Class<T> exampleClass ){
		HashMap<UUID, ? extends Component> store = componentStores.get( exampleClass );
		T result = (T) store.get(e);
		if( result == null )
			throw new IllegalArgumentException("GET FAIL: "+e.toString()+" does not possess Component of class\n missing: "+exampleClass);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getComponent(Class<T> exampleClass){ 
		HashMap<UUID, ? extends Component> store = componentStores.get(exampleClass);
		T result = (T) store.get(this.id);
		if(result == null)
			throw new IllegalArgumentException("Get Fail: "+this.id.toString()+" does not posses Component of Class \n missing: "+ exampleClass);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T> boolean hasComponent(Class<T> exampleClass){
		try{
			HashMap<UUID, ? extends EntityComponent> store = componentStores.get(exampleClass);
			T result = (T) store.get(this.id);
			if(result == null)
				return false;
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> boolean hasComponent(UUID id, Class<T> exampleClass){
		HashMap<UUID, ? extends EntityComponent> store = componentStores.get(exampleClass);
		T result = (T) store.get(id);
		if(result == null)
			return false;
		return true;
	}
	
	public UUID getID(){
		return this.id;
	}
}
