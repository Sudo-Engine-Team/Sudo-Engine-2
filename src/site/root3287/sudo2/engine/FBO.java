package site.root3287.sudo2.engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class FBO {
	public static void dispose(int id){
		GL30.glDeleteFramebuffers(id);
	}
	public static void bind(int id){
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, id);
	}
	
	private int id;
	
	public FBO(){
		this.id = GL30.glGenFramebuffers();
		bind();
		GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
	}
	
	public void bind(){
		bind(id);
	}
}
