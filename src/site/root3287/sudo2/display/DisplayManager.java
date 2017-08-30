package site.root3287.sudo2.display;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LAST;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LAST;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.events.event.KeyboardPressedEvent;
import site.root3287.sudo2.events.event.KeyboardReleasedEvent;
import site.root3287.sudo2.events.event.MouseClickEvent;
import site.root3287.sudo2.events.event.WindowResizeEvent;
import site.root3287.sudo2.events.types.WindowResizeEventType;
import site.root3287.sudo2.utils.ConsoleHandler;
import site.root3287.sudo2.utils.Input;
import site.root3287.sudo2.utils.Input.Mouse.State;

public class DisplayManager {
	public static String TITLE = "SUDO-Engine 2";
	public static float WIDTH = 900;
	public static float HEIGHT = WIDTH/16*9;
	public static final float FOV = 90;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 100f;
	public static Vector4f BACKGROUND_COLOUR = new Vector4f(0.25f, 0.25f, 0.25f, 1);
	public static long WINDOW;
	public static Screen SCREEN = null;
	public static boolean fullscreen = false;
	public static double DELTA;
	private static double lastTime;
	private static boolean resized;
	public static Logger LOGGER = Logger.getLogger("SUDO");
	
	private static EventDispatcher resizeDispatcher = new EventDispatcher(new WindowResizeEventType());
	
	public static void init(){
		init(WIDTH, HEIGHT, TITLE);
	}
	public static void init(float width, float height, String title){
		LOGGER.setUseParentHandlers(false);
		LOGGER.addHandler(new ConsoleHandler());
		LOGGER.log(Level.INFO, "Loading LWJGL natives for "+System.getProperty("os.name"));
		LOGGER.log(Level.INFO, "System.getProperty('os.name') == " + System.getProperty("os.name"));
		LOGGER.log(Level.INFO, "System.getProperty('os.version') == " + System.getProperty("os.version"));
		LOGGER.log(Level.INFO, "System.getProperty('os.arch') == " + System.getProperty("os.arch"));
		LOGGER.log(Level.INFO, "System.getProperty('java.version') == " + System.getProperty("java.version"));
		LOGGER.log(Level.INFO, "System.getProperty('java.vendor') == " + System.getProperty("java.vendor"));
		LOGGER.log(Level.INFO, "System.getProperty('sun.arch.data.model') == " + System.getProperty("sun.arch.data.model"));
		
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
		
		glfwSetWindowSizeCallback(WINDOW, new GLFWWindowSizeCallback() {
			
			@Override
			public void invoke(long window, int width, int height) {
				WIDTH = width;
				HEIGHT = height;
				resized = true;
				resizeDispatcher.execute(new WindowResizeEvent(width, height));
				//GL11.glViewport(0, 0, width, height);
			}
		});
		
		glfwSetScrollCallback(WINDOW, new GLFWScrollCallback() {
			
			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				Input.Mouse.setDWheel(yoffset);
			}
		});
		
		//GL11.glViewport(0, 0, (int)WIDTH, (int)HEIGHT);

		SCREEN.init();
	}
	
	public static void loop(){
		double current = currentTimeMillis();
		DELTA = (current - lastTime)/1000;
		lastTime = current;
		for(int i = 0; i < GLFW_KEY_LAST; i++){
			if(Input.Keyboard.isKeyPressed(i)){
				Input.Keyboard.keyPressedDispatcher.execute(new KeyboardPressedEvent(i));
			}
			if(Input.Keyboard.isKeyReleased(i)){
				Input.Keyboard.keyReleasedDispatcher.execute(new KeyboardReleasedEvent(i));
			}
		}
		for(int i = 0; i < GLFW_MOUSE_BUTTON_LAST; i++){
			if(Input.Mouse.isMousePressed(i)){
				Input.Mouse.clickDispatcher.execute(new MouseClickEvent((float)Input.Mouse.getX(), (float)Input.Mouse.getY(), i, State.MOUSE_PRESS));
			}
			if(Input.Mouse.isMouseReleased(i)){
				Input.Mouse.clickDispatcher.execute(new MouseClickEvent((float)Input.Mouse.getX(), (float)Input.Mouse.getY(), i, State.MOUSE_RELEASE));
			}
		}
		SCREEN.update((float)DisplayManager.getDelta());
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
		setScreen(screen, false, false);
	}
	public static void setScreen(Screen screen, boolean init){
		setScreen(screen, init, false);
	}
	public static void setScreen(Screen screen, boolean init, boolean destroy){
		if(destroy){
			SCREEN.destory();
		}
		SCREEN = screen;
		if(init){
			SCREEN.init();
		}
	}
	
	public static double getDelta(){
		return DELTA;
	}
	
	public static boolean hasResized(){
		return resized;
	}
	
	public static Vector2f getNormalisedScreen(){
		return new Vector2f();
	}
	
	public static void addResizeEvent(Listener l){
		resizeDispatcher.addListener(l);
	}
	
	public static void setTitle(String title){
		DisplayManager.TITLE = title;
		GLFW.glfwSetWindowTitle(WINDOW, title);
	}
}
