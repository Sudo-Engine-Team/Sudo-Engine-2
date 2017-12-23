package site.root3287.sudo2.engine;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class VBO {
	
	public static void bind(int id, boolean ind){
		if(ind){
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, id);
		}else{
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
		}
	}
	
	public static void unbind(boolean ind){
		if(ind){
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		}else{
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		}
	}
	
	public static void dispose(int id){
		GL15.glDeleteBuffers(id);
	}
	
	public static int create(){
		return GL15.glGenBuffers();
	}
	
	public static VBO create(boolean ind){
		return new VBO(ind);
	}
	
	private int id, dataSize;
	private boolean isInd;
	private Buffer data;
	
	public VBO(){
		this.id = GL15.glGenBuffers();
		this.isInd = false;
	}
	
	public VBO(boolean isInd){
		this.id = GL15.glGenBuffers();
		this.isInd = isInd;
	}
	
	public void bind(boolean ind){
		bind(this.id, ind);
	}
	
	public void bind(){
		bind(isInd);
	}
	
	public void unbind(){
		unbind(isInd);
	}
	
	public void bindData(){
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, (IntBuffer)this.data, GL15.GL_STATIC_DRAW);
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
	
	public boolean isIndiceBuffer(){
		return this.isInd;
	}
	
	public void setData(float[] data){
		this.dataSize = data.length;
		FloatBuffer b = BufferUtils.createFloatBuffer(data.length);
		b.put(data);
		b.flip();
		this.isInd = false;
		this.data = b;
	}
	public void setData(int[] data){
		this.dataSize = data.length;
		IntBuffer b = BufferUtils.createIntBuffer(data.length);
		b.put(data);
		b.flip();
		this.isInd = true;
		this.data = b;
	}
	
	public int getDataSize() {
		return dataSize;
	}
	
	public void dispose(){
		GL15.glDeleteBuffers(id);
	}
}
