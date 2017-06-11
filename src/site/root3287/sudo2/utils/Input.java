package site.root3287.sudo2.utils;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import site.root3287.sudo2.display.DisplayManager;

public class Input {
	
	public static class Keyboard{
		private static boolean[] key = new boolean[GLFW.GLFW_KEY_LAST];
		
		public static boolean isKeyDown(int key){
			return GLFW.glfwGetKey(DisplayManager.WINDOW, key) == GLFW.GLFW_TRUE?true:false;
		}
		
		public static boolean isKeyPressed(int currentKey){
			return (isKeyDown(currentKey) && !key[currentKey])?true:false;
		}
		
		public static boolean isKeyReleased(int key){
			return (!isKeyDown(key) && Input.Keyboard.key[key])?true:false;
		}
	}
	
	public static class Mouse{
		
		private static boolean grabbed, hidden;
		
		private static double x,y, dx, dy;
		
		private static boolean[] mouse = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
		
		private static double DWheel;
		
		public static boolean isMouseDown(int key){
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
