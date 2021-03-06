package site.root3287.sudo2.engine;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class VBO {
	
	public static void bind(int id){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
	}
	
	public static void dispose(int id){
		GL15.glDeleteBuffers(id);
	}
	
	public static int create(){
		return GL15.glGenBuffers();
	}
	
	private int id, dataSize;
	private Buffer data;
	
	public VBO(){
		this.id = GL15.glGenBuffers();
		Logger.getLogger("Sudo").log(Level.INFO, "Created VBO with the ID of "+id);
	}
	
	public void bind(){
		bind(this.id);
	}
	
	public void bindData(int attributeNumber, int size){
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, (FloatBuffer)this.data, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, size, GL11.GL_FLOAT, false, 0, 0);
	}
	
	public int getID(){
		return id;
	}
	
	public Buffer getBufferedData(){
		return data;
	}
	
	public void setData(float[] data){
		this.dataSize = data.length;
		FloatBuffer b = BufferUtils.createFloatBuffer(data.length);
		b.put(data);
		b.flip();
		this.data = b;
	}
	
	public int getDataSize() {
		return dataSize;
	}
	
	public void dispose(){
		GL15.glDeleteBuffers(id);
	}
}
