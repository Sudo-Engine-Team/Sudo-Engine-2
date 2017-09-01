package site.root3287.sudo2.display;

import java.util.logging.Logger;

import org.lwjgl.glfw.GLFW;

import site.root3287.sudo2.utils.ConsoleHandler;

public class Application{
	public static Logger LOGGER = DisplayManager.LOGGER;
	public static Logger CLIENT_LOGGER = Logger.getLogger("SUDO-CLIENT");
	public static Logger SERVER_LOGGER = Logger.getLogger("SUDO-CLIENT");
	public Application(String title) {
		this(title, DisplayManager.WIDTH, DisplayManager.HEIGHT);
	}
	public Application(String title, float width, float height) {
		DisplayManager.setTitle(title);
		DisplayManager.WIDTH = width;
		DisplayManager.HEIGHT = height;
		CLIENT_LOGGER.setUseParentHandlers(false);
		CLIENT_LOGGER.addHandler(new ConsoleHandler());
		SERVER_LOGGER.setUseParentHandlers(false);
		SERVER_LOGGER.addHandler(new ConsoleHandler());
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
}
