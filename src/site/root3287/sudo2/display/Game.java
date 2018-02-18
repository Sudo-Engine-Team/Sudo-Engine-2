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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.UI.UIUtils;
import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.events.event.KeyboardPressedEvent;
import site.root3287.sudo2.events.event.KeyboardReleasedEvent;
import site.root3287.sudo2.events.event.MouseClickEvent;
import site.root3287.sudo2.events.event.WindowResizeEvent;
import site.root3287.sudo2.events.types.WindowResizeEventType;
import site.root3287.sudo2.utils.ConsoleHandler;
import site.root3287.sudo2.utils.Input;
import site.root3287.sudo2.utils.Input.Mouse.State;

public class Game {
	public static final float FOV = 90;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 9999f;
	
	private long windowID;
	private String title;
	private int width = 900;
	private int height = width / 16*9;
	private Vector4f background_colour = new Vector4f(0.25f, 0.25f, 0.25f, 1);
	private Screen screen;
	private Logger logger = Logger.getLogger("Sudo");
	private boolean fullscreen = false;
	private boolean resized = false;
	private static EventDispatcher resizeDispatcher = new EventDispatcher(new WindowResizeEventType());
	private float delta, lastTime;
	
	public Game(String title) {
		this.title = title;
	}
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	private void init() {
		logger.setLevel(Level.ALL);
		logger.setUseParentHandlers(false);
		logger.addHandler(new ConsoleHandler());
		logger.log(Level.INFO, "Loaded LWJGL "+Version.getVersion()+" natives for "+System.getProperty("os.name"));
		logger.log(Level.INFO, "System.getProperty('os.name') == " + System.getProperty("os.name"));
		logger.log(Level.INFO, "System.getProperty('os.version') == " + System.getProperty("os.version"));
		logger.log(Level.INFO, "System.getProperty('os.arch') == " + System.getProperty("os.arch"));
		logger.log(Level.INFO, "System.getProperty('java.version') == " + System.getProperty("java.version"));
		logger.log(Level.INFO, "System.getProperty('java.vendor') == " + System.getProperty("java.vendor"));
		logger.log(Level.INFO, "System.getProperty('sun.arch.data.model') == " + System.getProperty("sun.arch.data.model"));
		
		if(!glfwInit())
			throw new IllegalStateException("Cannot start GLFW");
		
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		windowID = glfwCreateWindow((int)this.width, (int)this.height, this.title, fullscreen?glfwGetPrimaryMonitor():0, 0);
		if(windowID == 0){
			throw new RuntimeException("Failed to create window!");
		}
		
		Input.WINDOW = windowID;
		
		glfwMakeContextCurrent(windowID);
		GL.createCapabilities();
		glfwSwapInterval(1);
		glfwShowWindow(windowID);
		
		glfwSetWindowSizeCallback(windowID, new GLFWWindowSizeCallback() {
			
			@Override
			public void invoke(long window, int width, int height) {
				resized = true;
				int[] w = new int[1], h = new int[1];
				int[] wid = new int[1], hei = new int[1];
				GLFW.glfwGetFramebufferSize(window, w, h);
				GLFW.glfwGetWindowSize(windowID, wid, hei);
				float lastW = width, lastH = height;
				
				width = wid[0];
				height = hei[0];
				GL11.glViewport(0, 0, (int)w[0], (int)h[0]);
				
				logger.log(Level.INFO, "The window has been resized to "+w[0] +", "+h[0]);
				resizeDispatcher.execute(new WindowResizeEvent(wid[0], hei[0], width, height, lastW/width, lastH/height));
				screen.resize(wid[0], hei[0],w[0], h[0], width/lastW, height/lastH);
			}
		});
		
		glfwSetScrollCallback(windowID, new GLFWScrollCallback() {
			
			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				Input.Mouse.setDWheel(yoffset);
			}
		});
		
		int[] w = new int[1], h = new int[1];
		GLFW.glfwGetFramebufferSize(windowID, w, h);
		GL11.glViewport(0, 0, w[0], h[0]);

		UIUtils.game = this;
		
		screen.init();
	}
	
	public Vector4f getBackgroundColour() {
		return this.background_colour;
	}
	public boolean hasResized() {
		return this.resized;
	}
	
	private void loop() {
		double current = currentTimeMillis();
		this.delta = (float)(current - lastTime)/1000f;
		this.lastTime = (float)current;
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
		this.screen.update((float)DisplayManager.getDelta());
		this.screen.render();
		glfwSwapBuffers(windowID);
		Input.update();
		glfwPollEvents();
		resized = false;
	}
	private void destory() {
		screen.destory();
		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(this.windowID);
		glfwDestroyWindow(this.windowID);
		// Terminate GLFW and free the error callback
		glfwTerminate();
	}
	
	public void setTitle(String title){
		this.title = title;
		if(windowID != 0)
			GLFW.glfwSetWindowTitle(windowID, title);
	}
	
	public void run() {
		init();
		while(!GLFW.glfwWindowShouldClose(windowID)) {
			loop();
		}
		destory();
	}
	
	public float getDelta(){
		return delta;
	}
	
	public void setScreen(Screen screen){
		setScreen(screen, false, false);
	}
	public void setScreen(Screen screen, boolean init){
		setScreen(screen, init, false);
	}
	public void setScreen(Screen screen, boolean init, boolean destroy){
		if(destroy){
			screen.destory();
		}
		this.screen = screen;
		if(init){
			screen.init();
		}
	}
	
	private static double currentTimeMillis() {
	    return glfwGetTime() * 1000;
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	
	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
}
