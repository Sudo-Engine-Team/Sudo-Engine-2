package site.root3287.sudo2.display;

import org.lwjgl.glfw.GLFW;

public class GameLauncher{
	public GameLauncher() {
		run();
	}
	public void run() {
		DisplayManager.init();
		while(!GLFW.glfwWindowShouldClose(DisplayManager.WINDOW)){
			DisplayManager.loop();
		}
		DisplayManager.dispose();
	}
}
