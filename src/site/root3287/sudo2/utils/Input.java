package site.root3287.sudo2.utils;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.engine.camera.Camera;
import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.events.event.KeyboardDownEvent;
import site.root3287.sudo2.events.event.MouseClickEvent;
import site.root3287.sudo2.events.event.MouseMoveEvent;
import site.root3287.sudo2.events.types.KeyboardDownEventType;
import site.root3287.sudo2.events.types.KeyboardPressedEventType;
import site.root3287.sudo2.events.types.KeyboardReleaseEventType;
import site.root3287.sudo2.events.types.MouseClickEventType;
import site.root3287.sudo2.events.types.MouseMoveEventType;

public class Input {
	
	public static class Keyboard{
		public static EventDispatcher keyDownDispatcher = new EventDispatcher(new KeyboardDownEventType());
		public static EventDispatcher keyReleasedDispatcher = new EventDispatcher(new KeyboardReleaseEventType());
		public static EventDispatcher keyPressedDispatcher = new EventDispatcher(new KeyboardPressedEventType());
		
		public static enum State{
			KEYBOARD_KEY_DOWN,
			KEYBOARD_KEY_PRESSED,
			KEYBOARD_KEY_RELEASED,
		}
		private static boolean[] key = new boolean[GLFW.GLFW_KEY_LAST];
		
		public static boolean isKeyDown(int key){
			boolean finalReturn = false;
			if(GLFW.glfwGetKey(DisplayManager.WINDOW, key) == GLFW.GLFW_TRUE){
				finalReturn = true;
			}
			if(finalReturn){
				keyDownDispatcher.execute(new KeyboardDownEvent(key));
			}
			return finalReturn;
		}
		
		public static boolean isKeyPressed(int currentKey){
			return ((GLFW.glfwGetKey(DisplayManager.WINDOW, currentKey) == GLFW.GLFW_TRUE)?true:false) && !key[currentKey];
		}
		
		public static boolean isKeyReleased(int key){
			return !((GLFW.glfwGetKey(DisplayManager.WINDOW, key) == GLFW.GLFW_TRUE)?true:false) && Input.Keyboard.key[key];
		}
		
		public static void addKeyDownListener(Listener l){
			keyDownDispatcher.addListener(l);
		}
		public static void addKeyReleasedListener(Listener l){
			keyReleasedDispatcher.addListener(l);
		}
		public static void addKeyPressedListener(Listener l){
			keyPressedDispatcher.addListener(l);
		}
		public static char glfwToChar(int i){
			return (char)i;
		}
	}
	
	public static class Mouse{
		
		public static enum State{
			MOUSE_PRESS,
			MOUSE_RELEASE,
			MOUSE_DOWN
		}
		
		public static EventDispatcher clickDispatcher = new EventDispatcher(new MouseClickEventType());
		public static EventDispatcher moveDispatcher = new EventDispatcher(new MouseMoveEventType());
		
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
			return (isMouseDown(key) && !Input.Mouse.mouse[key])?true:false;
		}
		
		public static boolean isMouseReleased(int key){
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
			float x = (float) (2*getX() / DisplayManager.WIDTH)-1;
			float y = (float) (2*getY() / DisplayManager.HEIGHT)-1;
			return new Vector2f(x, y);
		}
		public static Vector2f getNormalizedMouseCoords(float x, float y){
			float nx = (float) (2*x / DisplayManager.WIDTH)-1;
			float ny = (float) (2*y / DisplayManager.HEIGHT)-1;
			return new Vector2f(nx, ny);
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
		
		public static void addMoveListener(Listener listener) {
			moveDispatcher.addListener(listener);
		}
		
		public static Vector3f getMouseProjection(Camera c) {
			//System.out.println("Coords: "+getMouse());
			Vector2f nmc = getNormalizedMouseCoords();
			//System.out.println("nmc: "+nmc);
			Vector4f pmc = new Vector4f(nmc.x, nmc.y, -1, 1);
			//System.out.println("pmc: "+pmc);
			Vector4f emc = getEyeCoords(c.getProjectionMatrix(), pmc);
			//System.out.println("emc: "+emc);
			Vector3f world = getWorld(c.getViewMatrix(), emc);
			
			return world;
		}
		public static Vector3f getMouseProjection(Vector2f mousePos, Camera c) {
			//System.out.println("Coords: "+getMouse());
			Vector2f nmc = getNormalizedMouseCoords(mousePos.x, mousePos.y);
			//System.out.println("nmc: "+nmc);
			Vector4f pmc = new Vector4f(nmc.x, nmc.y, -1, 1);
			//System.out.println("pmc: "+pmc);
			Vector4f emc = getEyeCoords(c.getProjectionMatrix(), pmc);
			//System.out.println("emc: "+emc);
			Vector3f world = getWorld(c.getViewMatrix(), emc);
			
			return world;
		}
		private static Vector4f getEyeCoords(Matrix4f proj, Vector4f clip) {
			Matrix4f invProj = Matrix4f.invert(proj, null);
			Vector4f clip2 = Matrix4f.transform(invProj, clip, null);
			return new Vector4f(clip2.x, clip2.y, -1, 0);
		}
		private static Vector3f getWorld(Matrix4f view, Vector4f eye) {
			Matrix4f iView = Matrix4f.invert(view, null);
			Vector4f rayWorld = Matrix4f.transform(iView, eye, null);
			return new Vector3f(rayWorld.x , -rayWorld.y, rayWorld.z);
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
		
		if(x != Mouse.x || y != Mouse.y){
			Mouse.moveDispatcher.execute(new MouseMoveEvent((float)x, (float)y, (float)Mouse.dx, (float)Mouse.dy));
		}
		
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
