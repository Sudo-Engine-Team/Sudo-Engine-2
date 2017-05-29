package site.root3287.sudo2.display;

import org.lwjgl.glfw.GLFW;

public class GameLauncher implements Runnable{
	private Thread game;
	public GameLauncher() {
		game = new Thread(this);
		game.setDaemon(false);
		game.start();
	}
	
	@Override
	public void run() {
		DisplayManager.init();
		while(!GLFW.glfwWindowShouldClose(DisplayManager.WINDOW)){
			DisplayManager.loop();
		}
		DisplayManager.dispose();
		stop();
	}
	private void stop(){
		game.interrupt();
	}
}
