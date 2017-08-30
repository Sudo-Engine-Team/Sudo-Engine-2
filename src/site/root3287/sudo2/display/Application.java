package site.root3287.sudo2.display;

import org.lwjgl.glfw.GLFW;

public class Application{
	public Application(String title) {
		DisplayManager.setTitle(title);
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
}
