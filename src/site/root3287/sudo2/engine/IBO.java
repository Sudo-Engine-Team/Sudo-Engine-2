package site.root3287.sudo2.engine;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

public class IBO {
	public static void bind(int id){
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, id);
	}
	
	public static void dispose(int id){
		GL15.glDeleteBuffers(id);
	}
	
	public static int create(){
		return GL15.glGenBuffers();
	}
	
	private int id, dataSize;
	private Buffer data;
	
	public IBO(){
		this.id = GL15.glGenBuffers();
		Logger.getLogger("Sudo").log(Level.INFO, "Created VBO with the ID of "+id);
	}
	
	public void bind(){
		bind(this.id);
	}
	
	public void unbind(){
		bind(0);
	}
	
	public void bindData(){
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, (IntBuffer)this.data, GL15.GL_STATIC_DRAW);
	}
	
	public int getID(){
		return id;
	}
	
	public Buffer getBufferedData(){
		return data;
	}
	
	public void setData(int[] data){
		this.dataSize = data.length;
		IntBuffer b = BufferUtils.createIntBuffer(data.length);
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
