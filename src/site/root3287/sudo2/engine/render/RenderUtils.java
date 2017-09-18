package site.root3287.sudo2.engine.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.display.DisplayManager;

public class RenderUtils {
	private static boolean wireframe = false;
	
	public static void enableCulling(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	public static void disableCulling(){
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	public static void enableDepthTest() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
	public static void disableDepthTest() {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
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
	public static void toggleWireframe(){
		if(wireframe){
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
			wireframe = false;
		}else{
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
			wireframe = true;
		}
	}
	public static boolean isWireframe(){
		return wireframe;
	}
	public static void bindTexture(int index, int textureID) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
	}
	public static void unbindTexture(int index, int textureID) {
		int base = 0x84c0;
		if(index > 31) {
			return;
		}
		GL13.glActiveTexture(base+index);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
	}
	
}
