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
		
		private static double prevX, prevY, x, y;
		
		private static boolean[] mouse = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
		
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
		/**
		 * getDX
		 * movement since last DX call.
		 * @return double
		 */
		public static double getDX(){
			double current = x;
			double prev = prevX;
			double res = prev-current;
			prevX = x;
			return res;
		}
		
		public static double getDY(){
			double current = y;
			double prev = prevY;
			double res = prev-current;
			prevY = y;
			return res;
		}
		
		public static void setMousePosition(double x, double y){
			GLFW.glfwSetCursorPos(DisplayManager.WINDOW, x, y);
		}
	}
	
	public static void update(){
		DoubleBuffer mouseX = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer mouseY = BufferUtils.createDoubleBuffer(1);
		
		GLFW.glfwGetCursorPos(DisplayManager.WINDOW, mouseX, mouseY);
		
		Mouse.x = (int) mouseX.get();
		Mouse.y = (int) mouseY.get();
		
		for(int i = 0; i<GLFW.GLFW_KEY_LAST; i++){
			Keyboard.key[i] = Keyboard.isKeyDown(i);
		}
		for(int i = 0; i<GLFW.GLFW_MOUSE_BUTTON_LAST; i++){
			Mouse.mouse[i] = Mouse.isMouseDown(i);
		}
	}
}
