package site.root3287.sudo2.engine.newTexture;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

public class Texture {
	public static void bind(int target, int id){
		GL11.glBindTexture(target, id);
	}
	public static void bind(int id){
		bind(GL11.GL_TEXTURE_2D, id);
	}
	public static void unbind(){
		bind(0);
	}
	
	private int id;
	private int width, height;
	private ByteBuffer image;
	private boolean flip = false;
	
	public Texture(String src, boolean flip){
		this.flip = flip;
		IntBuffer w = MemoryStack.stackMallocInt(1);
		IntBuffer h = MemoryStack.stackMallocInt(1);
		IntBuffer comp = MemoryStack.stackMallocInt(1);
		
		STBImage.stbi_set_flip_vertically_on_load(this.flip);
		
		image = STBImage.stbi_load(src, w, h, comp, 4);
		
		if (image == null) {
		    throw new RuntimeException("Failed to load a texture file!"
		            + System.lineSeparator() + STBImage.stbi_failure_reason());
		}
		
		width  = w.get();
		height = w.get();
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);
	}
	
	public Texture(int id, int width, int height){
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	public void bind(){
		bind(this.id);
	}
	
	/**
	 * Change the image value
	 * @param type - The parameter to be set
	 * @param n - the value
	 */
	public void setPramaters(int type, float n){
		bind();
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, type, n);
		unbind();
	}
	
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public ByteBuffer getImage(){return image;}
}
