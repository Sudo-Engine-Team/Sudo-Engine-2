package site.root3287.sudo2.engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.engine.model.Model;

public class Loader {
	private static Loader _instance = null;
	private HashMap<Integer, List<Integer>> vaos= new HashMap<>();
	private List<Integer> textures = new ArrayList<Integer>();
	public Map<Integer, List<Integer>> vaoText = new HashMap<>();
	private Map<String, Integer> textureCache = new HashMap<>();
	
	public Loader(){
		
	}
	
	public static Loader getInstance(){
		if(_instance == null){
			_instance = new Loader();
		}
		return _instance;
	}
	
	public Model loadToVAO(float[] positions){
		int vaoID = createVAO();
		List<Integer> vbos = new ArrayList<>();
		vbos.add(storeDataInAttributeList(0, 3, positions));
		vaos.put(vaoID, vbos);
		unbindVAO();
		return new Model(vaoID, positions.length/2);
	}
	public Model loadToVAO(float[] positions, int[] indices){
		int vaoID = createVAO();
		List<Integer> vbos = new ArrayList<>();
		vbos.add(bindIndicesBuffer(indices));
		vbos.add(storeDataInAttributeList(0, 3, positions));
		vaos.put(vaoID, vbos);
		unbindVAO();
		return new Model(vaoID, indices.length);
	}
	public int loadToVAO(float[] positions, float[] textureCoords){
		int vaoID = createVAO();
		List<Integer> vbos = new ArrayList<>();
		vbos.add(storeDataInAttributeList(0, 2, positions));
		vbos.add(storeDataInAttributeList(1, 2, textureCoords));
		vaos.put(vaoID, vbos);
		unbindVAO();
		return vaoID;
	}
	public Model loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices){ //3d models
		int vaoID = createVAO();
		List<Integer> vbos = new ArrayList<>();
		vbos.add(bindIndicesBuffer(indices));
		vbos.add(storeDataInAttributeList(0, 3, positions));
		vbos.add(storeDataInAttributeList(1, 2, textureCoords));
		vbos.add(storeDataInAttributeList(2, 3, normals));
		vaos.put(vaoID, vbos);
		unbindVAO();
		return new Model(vaoID, indices.length);
	}
	public int loadText(float[] position, float[] textureCoords){
		int vaoID = loadToVAO(position, textureCoords);
		return vaoID;
	}
	private int createVAO(){
		int vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	private int bindIndicesBuffer(int[] indices){
		int vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer b = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, b, GL15.GL_STATIC_DRAW);
		return vboID;
	}
	private int storeDataInAttributeList(int attributeNumber, int coordnateSize, float[] data){
		int vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, coordnateSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return vboID;
	}
	private void unbindVAO(){
		GL30.glBindVertexArray(0);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	private IntBuffer storeDataInIntBuffer(int[] data){
		IntBuffer b = BufferUtils.createIntBuffer(data.length);
		b.put(data);
		b.flip();
		return b;
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
	public void removeTextFromMemory(int vao){
		removeVAO(vao);
	}
	public void removeVAO(int vaoID){
		DisplayManager.LOGGER.log(Level.INFO,"Removing VAO from memory: "+vaoID);
		List<Integer> vbos = vaos.remove(vaoID);
		for(int vbo : vbos){
			GL15.glDeleteBuffers(vbo);
		}
		GL30.glDeleteVertexArrays(vaoID);
	}
	public void removeTexture(int textureID){
		DisplayManager.LOGGER.log(Level.INFO,"Removing Texture form memory "+textureID);
		GL11.glDeleteTextures(textureID);
		textures.remove(new Integer(textureID));
	}
	public void destory(){
		DisplayManager.LOGGER.log(Level.INFO, "Disposing Loader");
		int vboSize = 0;
		int vaoSize = 0;
		for(int vao : vaos.keySet()){
			for(int vbo : vaos.get(vao)){
				GL15.glDeleteBuffers(vbo);
				vboSize++;
			}
			GL30.glDeleteVertexArrays(vao);
			vaoSize++;
		}
		DisplayManager.LOGGER.log(Level.INFO,"Deleted "+vaoSize+" VAOS");
		DisplayManager.LOGGER.log(Level.INFO, "Deleted "+vboSize+" VBOS");
		
		int texturesSize = 0;
		for(int texture:textures){
			GL11.glDeleteTextures(texture);
			texturesSize++;
		}
		DisplayManager.LOGGER.log(Level.INFO,"Deleted "+texturesSize+" textures");
	} 
}
