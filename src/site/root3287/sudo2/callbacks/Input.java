package site.root3287.sudo2.callbacks;

import org.lwjgl.glfw.GLFW;

import site.root3287.sudo2.display.DisplayManager;

public class Input {
	
	private static boolean[] key = new boolean[GLFW.GLFW_KEY_LAST];
	private static boolean[] mouse = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
	
	public static boolean isKeyDown(int key){
		return GLFW.glfwGetKey(DisplayManager.WINDOW, key) == GLFW.GLFW_TRUE?true:false;
	}
	public static boolean isMouseDown(int key){
		return GLFW.glfwGetMouseButton(DisplayManager.WINDOW, key) == GLFW.GLFW_TRUE?true:false;
	}
	
	public static boolean isKeyPressed(int key){
		return (isKeyDown(key) && !Input.key[key])?true:false;
	}
	
	public static boolean isKeyReleased(int key){
		return (!isKeyDown(key) && Input.key[key])?true:false;
	}
	
	public static boolean isMousePressed(int key){
		return (isKeyDown(key) && !Input.mouse[key])?true:false;
	}
	
	public static boolean isMouseReleased(int key){
		return (!isKeyDown(key) && Input.mouse[key])?true:false;
	}
	
	public static void update(){
		for(int i = 0; i<GLFW.GLFW_KEY_LAST; i++){
			key[i] = isKeyDown(i);
		}
		for(int i = 0; i<GLFW.GLFW_MOUSE_BUTTON_LAST; i++){
			mouse[i] = isMouseDown(i);
		}
	}
}
