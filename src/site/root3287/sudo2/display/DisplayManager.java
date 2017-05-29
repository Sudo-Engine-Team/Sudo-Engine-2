package site.root3287.sudo2.display;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

public class DisplayManager {
	public static String TITLE = "SUDO-Engine 2";
	public static float WIDTH = 900;
	public static float HEIGHT = WIDTH/16*9;
	public static final float FOV = 90;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 10000f;
	public static Vector4f BACKGROUND_COLOUR = new Vector4f(0.5f, 0.5f, 0.5f, 1);
	public static long WINDOW;
	public static void init(){
		init(WIDTH, HEIGHT, TITLE);
	}
	public static void init(float width, float height, String title){
		if(!glfwInit())
			throw new IllegalStateException("Cannot start GLFW");
		
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		WINDOW = glfwCreateWindow((int)WIDTH, (int)HEIGHT, TITLE, 0, 0);
		if(WINDOW == 0){
			throw new RuntimeException("Failed to create window!");
		}
		
		glfwMakeContextCurrent(WINDOW);
		GL.createCapabilities();
		glfwSwapInterval(1);
		glfwShowWindow(WINDOW);
	}
	
	public static void loop(){
		glfwSwapBuffers(WINDOW);
		glfwPollEvents();
	}
	
	public static void dispose(){
		// Free the window callbacks and destroy the window
		glfwDestroyWindow(WINDOW);
		// Terminate GLFW and free the error callback
		glfwTerminate();
	}
	
	public static double currentTimeMillis() {
	    return glfwGetTime() * 1000;
	}
	
	public static Vector2f getCurrentWindowSize(){
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		glfwGetWindowSize(WINDOW, w, h);
		return new Vector2f(w.get(0), h.get(0));
	}
}
