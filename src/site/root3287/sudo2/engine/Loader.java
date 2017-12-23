package site.root3287.sudo2.engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.engine.texture.AbstractTexture;

public class Loader {
	private static Loader _instance = null;
	private List<Integer> textures = new ArrayList<Integer>();
	private Map<String, Integer> textureCache = new HashMap<>();
	
	public Loader(){
		
	}
	
	public static Loader getInstance(){
		if(_instance == null){
			_instance = new Loader();
		}
		return _instance;
	}

	public int loadTexture(String fileName){
		PNGDecoder texture = null;
		int textureID = GL11.glGenTextures();
		if(!textureCache.containsKey(fileName)){
			try {
				texture = new PNGDecoder(new FileInputStream(fileName));
				ByteBuffer buf = ByteBuffer.allocateDirect(4*texture.getWidth()*texture.getHeight());
				texture.decode(buf, texture.getWidth()*4, Format.RGBA);
				buf.flip();
				/*
				 * This is the default prameter that I'm going to set
				 * To change these prameters just use the following code
				 * 
				 * GL11.glBindTexture(GL11.GL_TEXTURE_2D. textureID);
				 * GL11.glTextPrameteri(GL11.GL_TEXTURE_2D, pram1, pram2);
				 * ...
				 * GL11.glBindTexture(Gl11.GL_TEXTURE_2D, 0);
				 */
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
				GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, texture.getWidth(), texture.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
				GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
				GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.4f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textures.add(textureID);
			textureCache.put(fileName, textureID);
		}else{
			textureID = textureCache.get(fileName);
		}
		return textureID;
	}
	
	public AbstractTexture textureObject(String fileName){
		PNGDecoder texture = null;
		int textureID = GL11.glGenTextures();
		if(!textureCache.containsKey(fileName)){
			try {
				texture = new PNGDecoder(new FileInputStream(fileName));
				ByteBuffer buf = ByteBuffer.allocateDirect(4*texture.getWidth()*texture.getHeight());
				texture.decode(buf, texture.getWidth()*4, Format.RGBA);
				buf.flip();
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
				GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, texture.getWidth(), texture.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
				GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
				GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.4f);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textures.add(textureID);
			textureCache.put(fileName, textureID);
		}else{
			textureID = textureCache.get(fileName);
		}
		
		return new AbstractTexture(textureID, texture.getWidth(),texture.getHeight());
	}
	
	public static void addTexturePramaters(int tid, int pname, int pram){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, tid);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, pname, pram);
	}
	public void removeTexture(int textureID){
		DisplayManager.LOGGER.log(Level.INFO,"Removing Texture form memory "+textureID);
		GL11.glDeleteTextures(textureID);
		textures.remove(new Integer(textureID));
	}
	public void destory(){
		DisplayManager.LOGGER.log(Level.INFO, "Disposing Loader");
		int texturesSize = 0;
		for(int texture:textures){
			GL11.glDeleteTextures(texture);
			texturesSize++;
		}
		DisplayManager.LOGGER.log(Level.INFO,"Deleted "+texturesSize+" textures");
	} 
}
