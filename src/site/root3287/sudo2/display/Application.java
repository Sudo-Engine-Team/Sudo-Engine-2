package site.root3287.sudo2.display;

import java.util.logging.Logger;

import org.lwjgl.glfw.GLFW;

import site.root3287.sudo2.utils.ConsoleHandler;

public class Application{
	public static Logger LOGGER = DisplayManager.LOGGER;
	public Application(String title) {
		this(title, DisplayManager.WIDTH, DisplayManager.HEIGHT);
	}
	public Application(String title, float width, float height) {
		DisplayManager.setTitle(title);
		DisplayManager.WIDTH = width;
		DisplayManager.HEIGHT = height;
	}
	public Application setScreen(Screen s){
		DisplayManager.setScreen(s);
		return this;
	}
	public void run(){
		DisplayManager.init();
		while(!GLFW.glfwWindowShouldClose(DisplayManager.WINDOW)){
			DisplayManager.loop();
		}
		DisplayManager.dispose();
	}
	public static Logger getServerLogger(){
		Logger log = Logger.getLogger("SUDO-SERVER");
		log.setUseParentHandlers(false);
		log.addHandler(new ConsoleHandler());
		return log;
	}
	public static Logger getClientLogger(){
		Logger log = Logger.getLogger("SUDO-CLIENT");
		log.setUseParentHandlers(false);
		log.addHandler(new ConsoleHandler());
		return log;
	}
}
