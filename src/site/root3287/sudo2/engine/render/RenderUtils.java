package site.root3287.sudo2.engine.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.display.DisplayManager;

public class RenderUtils {
	public static void enableCulling(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	public static void disableCulling(){
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	public static void clear(){
		clear(DisplayManager.BACKGROUND_COLOUR);
	}
	public static void clear(Vector4f colour){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(colour.x, colour.y,
				colour.z, colour.w);
	}
}
