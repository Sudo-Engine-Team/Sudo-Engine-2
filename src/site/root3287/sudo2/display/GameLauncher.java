package site.root3287.sudo2.display;

import org.lwjgl.glfw.GLFW;

import site.root3287.sudo2.engine.render.Render;

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
		
		/**
		 * Start components
		 */
		Render r = new Render();
		while(!GLFW.glfwWindowShouldClose(DisplayManager.WINDOW)){
			DisplayManager.loop();
			/**
			 * Update components
			 */
			
			/**
			 * Render
			 */
			r.render();
		
		}
		DisplayManager.dispose();
		
		/**
		 * Dispose componets
		 */
		r.dispose();
		
		stop();
	}
	private void stop(){
		game.interrupt();
	}
}
