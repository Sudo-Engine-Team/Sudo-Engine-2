package site.root3287.sudo2.display;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.Callbacks.*;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.logger.LogLevel;
import site.root3287.sudo2.logger.Logger;
import site.root3287.sudo2.utils.Input;

public class DisplayManager {
	public static String TITLE = "SUDO-Engine 2";
	public static float WIDTH = 900;
	public static float HEIGHT = WIDTH/16*9;
	public static final float FOV = 90;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 100f;
	public static Vector4f BACKGROUND_COLOUR = new Vector4f(0.25f, 0.25f, 0.25f, 1);
	public static long WINDOW;
	public static Screen SCREEN;
	public static boolean fullscreen = false;
	public static double DELTA;
	private static double lastTime;
	private static boolean resized;
	
	public static void init(){
		init(WIDTH, HEIGHT, TITLE);
	}
	public static void init(float width, float height, String title){
		Logger.log(LogLevel.INFO, "Loading LWJGL natives for "+System.getProperty("os.name"));
		Logger.log(LogLevel.INFO, "System.getProperty('os.name') == " + System.getProperty("os.name"));
		Logger.log(LogLevel.INFO, "System.getProperty('os.version') == " + System.getProperty("os.version"));
		Logger.log(LogLevel.INFO, "System.getProperty('os.arch') == " + System.getProperty("os.arch"));
		Logger.log(LogLevel.INFO, "System.getProperty('java.version') == " + System.getProperty("java.version"));
		Logger.log(LogLevel.INFO, "System.getProperty('java.vendor') == " + System.getProperty("java.vendor"));
		Logger.log(LogLevel.INFO, "System.getProperty('sun.arch.data.model') == " + System.getProperty("sun.arch.data.model"));
		
		if(!glfwInit())
			throw new IllegalStateException("Cannot start GLFW");
		
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		WINDOW = glfwCreateWindow((int)WIDTH, (int)HEIGHT, TITLE, fullscreen?glfwGetPrimaryMonitor():0, 0);
		if(WINDOW == 0){
			throw new RuntimeException("Failed to create window!");
		}
		
		glfwMakeContextCurrent(WINDOW);
		GL.createCapabilities();
		glfwSwapInterval(1);
		glfwShowWindow(WINDOW);
		
		GL11.glViewport(0, 0, (int)WIDTH, (int)HEIGHT);
		
		glfwSetWindowSizeCallback(WINDOW, new GLFWWindowSizeCallback() {
			
			@Override
			public void invoke(long window, int width, int height) {
				WIDTH = width;
				HEIGHT = height;
				resized= true;
				GL11.glViewport(0, 0, width, height);
			}
		});

		SCREEN.init();
	}
	
	public static void loop(){
		double current = currentTimeMillis();
		DELTA = (current - lastTime)/1000;
		lastTime = current;
		SCREEN.update();
		SCREEN.render();
		glfwSwapBuffers(WINDOW);
		Input.update();
		glfwPollEvents();
		resized = false;
	}
	
	public static void dispose(){
		SCREEN.destory();
		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(WINDOW);
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
	
	public static void setScreen(Screen screen){
		boolean wasNull = false;
		if(SCREEN != null){
			SCREEN.destory();
			wasNull = true;
		}
		SCREEN = screen;
		if(!wasNull){
			SCREEN.init();
		}
	}
	
	public static double getDelta(){
		return DELTA;
	}
	
	public static boolean hasResized(){
		return resized;
	}
}
