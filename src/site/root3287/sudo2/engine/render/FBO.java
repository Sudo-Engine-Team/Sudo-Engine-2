package site.root3287.sudo2.engine.render;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

import site.root3287.sudo2.display.DisplayManager;

public class FBO {
	private static List<Integer> fbo = new ArrayList<>();
	private static List<Integer> textures = new ArrayList<>();
	private static List<Integer> depthBuffers = new ArrayList<>();
	
	public static int createFBO(){
		int frameBuffer = GL30.glGenFramebuffers();
		//generate name for frame buffer
	    GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
	    //create the framebuffer
	    GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
	    //indicate that we will always render to color attachment 0
	    
	    FBO.fbo.add(frameBuffer);
	    
	    return frameBuffer;
	}
	
	public static int createFBOTexture(int width, int height){
		int texture = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height,
                0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, (ByteBuffer) null);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0,
                texture, 0);
        
        FBO.textures.add(texture);
        return texture;
	}
	
	public static int createFBODepthTexture(int width, int height){
		int depthBuffer = GL30.glGenRenderbuffers();
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, depthBuffer);
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL11.GL_DEPTH_COMPONENT, width,
                height);
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT,
                GL30.GL_RENDERBUFFER, depthBuffer);
        
        FBO.depthBuffers.add(depthBuffer);
        return depthBuffer;
	}
	
	public static void bindFBO(int fbo, int width, int height){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);//To make sure the texture isn't bound
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fbo);
		GL11.glViewport(0, 0, width, height);
	}
	
	public static void unbindFBO(){
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		GL11.glViewport(0, 0, (int)DisplayManager.WIDTH, (int)DisplayManager.HEIGHT);
	}
	
	public static void dispose(){
		for(int fb : fbo){
			GL30.glDeleteFramebuffers(fb);
		}
		for(int text : textures){
			GL11.glDeleteTextures(text);
		}
		for(int rb : depthBuffers){
			GL30.glDeleteRenderbuffers(rb);
		}
	}
	
	public static int getTexture(int index){
		return FBO.textures.get(index);
	}
}
