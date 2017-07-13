package site.root3287.sudo2.utils;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.events.event.KeybaordKeyEvent;
import site.root3287.sudo2.events.event.MouseClickEvent;
import site.root3287.sudo2.events.types.KeyboardKeyEventType;
import site.root3287.sudo2.events.types.MouseClickEventType;

public class Input {
	
	public static class Keyboard{
		private static EventDispatcher keyDispatcher = new EventDispatcher(new KeyboardKeyEventType());
		
		public static enum State{
			KEYBOARD_KEY_DOWN,
			KEYBOARD_KEY_PRESSED,
			KEYBOARD_KEY_RELEASED,
		}
		private static boolean[] key = new boolean[GLFW.GLFW_KEY_LAST];
		
		public static boolean isKeyDown(int key){
			if(GLFW.glfwGetKey(DisplayManager.WINDOW, key) == GLFW.GLFW_TRUE){
				keyDispatcher.execute(new KeybaordKeyEvent(key, State.KEYBOARD_KEY_DOWN));
			}
			return GLFW.glfwGetKey(DisplayManager.WINDOW, key) == GLFW.GLFW_TRUE?true:false;
		}
		
		public static boolean isKeyPressed(int currentKey){
			if(isKeyDown(currentKey) && !key[currentKey]){
				keyDispatcher.execute(new KeybaordKeyEvent(currentKey, State.KEYBOARD_KEY_PRESSED));
			}
			return (isKeyDown(currentKey) && !key[currentKey])?true:false;
		}
		
		public static boolean isKeyReleased(int key){
			if(!isKeyDown(key) && Input.Keyboard.key[key]){
				keyDispatcher.execute(new KeybaordKeyEvent(key, State.KEYBOARD_KEY_RELEASED));
			}
			return (!isKeyDown(key) && Input.Keyboard.key[key])?true:false;
		}
		
		public static void addKeyListener(Listener l){
			keyDispatcher.addListener(l);
		}
	}
	
	public static class Mouse{
		
		public static enum State{
			MOUSE_PRESS,
			MOUSE_RELEASE,
			MOUSE_DOWN
		}
		
		private static EventDispatcher clickDispatcher = new EventDispatcher(new MouseClickEventType());
		
		private static boolean grabbed, hidden;
		
		private static double x,y, dx, dy;
		
		private static boolean[] mouse = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
		
		private static double DWheel;
		
		public static boolean isMouseDown(int key){
			if(GLFW.glfwGetMouseButton(DisplayManager.WINDOW, key) == GLFW.GLFW_TRUE){
				clickDispatcher.execute(new MouseClickEvent((float)getX(), (float)getY(), key, State.MOUSE_DOWN));
			}
			return GLFW.glfwGetMouseButton(DisplayManager.WINDOW, key) == GLFW.GLFW_TRUE?true:false;
		}
		
		public static boolean isMousePressed(int key){
			if(isMouseDown(key) && !Input.Mouse.mouse[key]){
				clickDispatcher.execute(new MouseClickEvent((float)getX(), (float)getY(), key, State.MOUSE_PRESS));
			}
			return (isMouseDown(key) && !Input.Mouse.mouse[key])?true:false;
		}
		
		public static boolean isMouseReleased(int key){
			if(!isMouseDown(key) && Input.Mouse.mouse[key]){
				clickDispatcher.execute(new MouseClickEvent((float)getX(), (float)getY(), key, State.MOUSE_RELEASE));
			}
			return (!isMouseDown(key) && Input.Mouse.mouse[key])?true:false;
		}
		
		public static double getX(){
			return x;
		}
		
		public static double getY(){
			return y;
		}

		public static double getDX(){
			return dx;
		}
		
		public static double getDY(){
			return dy;
		}
		
		public static void setMousePosition(double x, double y){
			GLFW.glfwSetCursorPos(DisplayManager.WINDOW, x, y);
		}
		
		public static void setDWheel(double y){
			DWheel = y;
		}
		
		public static double getDWheel(){
			return DWheel;
		}
		
		public static void setHidden(boolean hide){
			hidden = hide;
			if(grabbed)
				return;
			GLFW.glfwSetInputMode(DisplayManager.WINDOW, GLFW.GLFW_CURSOR, (hidden)?GLFW.GLFW_CURSOR_HIDDEN:GLFW.GLFW_CURSOR_NORMAL);;
		}
		public static void setGrabbed(boolean grab){
			grabbed = grab;
			if(hidden)
				return;
			GLFW.glfwSetInputMode(DisplayManager.WINDOW, GLFW.GLFW_CURSOR, (grabbed)?GLFW.GLFW_CURSOR_DISABLED:GLFW.GLFW_CURSOR_NORMAL);;
		}
		public static boolean isGrabbed(){
			return grabbed;
		}
		public static Vector2f getNormalizedMouseCoords(){
			float x = (float) (-1.0f + 2.0f * (Mouse.getX()/DisplayManager.WIDTH));
			float y = (float) (-1.0f + 2.0f * (Mouse.getY()/DisplayManager.HEIGHT));
			return new Vector2f(x, y);
		}
		public static Vector2f getMouse(){
			return new Vector2f((float)Mouse.getX(), (float)Mouse.getY());
		}
		public static Vector2f getTranslatedMouseCorrds(float x, float y){
			return getMouse().translate(x, y);
		}
		public static void addClickListener(Listener l){
			clickDispatcher.addListener(l);
		}
	}
	
	public static void update(){
		DoubleBuffer mouseX = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer mouseY = BufferUtils.createDoubleBuffer(1);
		
		GLFW.glfwGetCursorPos(DisplayManager.WINDOW, mouseX, mouseY);
		
		double x = mouseX.get();
		double y = mouseY.get();
		
		Mouse.dx = Mouse.x - x;
		Mouse.dy = Mouse.y - y;
		
		Mouse.x = x;
		Mouse.y = y;
		
		for(int i = 0; i<GLFW.GLFW_KEY_LAST; i++){
			Keyboard.key[i] = Keyboard.isKeyDown(i);
		}
		for(int i = 0; i<GLFW.GLFW_MOUSE_BUTTON_LAST; i++){
			Mouse.mouse[i] = Mouse.isMouseDown(i);
		}
		
		Mouse.setDWheel(0);
	}
}
