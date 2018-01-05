package site.root3287.sudo2.engine.newTexture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

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
	private BufferedImage image;
	
	public Texture(String src){
		try {
			this.image = ImageIO.read(Class.class.getResourceAsStream(src));
			
			int[] rawPixel = image.getRGB(0, 0, this.image.getWidth(), this.image.getHeight(), null, 0, this.image.getWidth());
			
			ByteBuffer buffer = BufferUtils.createByteBuffer(this.image.getWidth() * this.image.getHeight() * 4);
			for (int i = 0; i < this.image.getWidth(); i++) {
				for (int j = 0; j < this.image.getHeight(); j++) {
					int pixel = rawPixel[i*this.image.getWidth()+j];
					buffer.put((byte) ((pixel >> 16) & 0xFF));
					buffer.put((byte) ((pixel >> 8) & 0xFF));
					buffer.put((byte) ((pixel) & 0xFF));
					buffer.put((byte) ((pixel >> 24) & 0xFF));
				}
			}
			buffer.flip();
			
			this.id = GL11.glGenTextures();
			this.bind();
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.image.getWidth(), this.image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
