package site.root3287.sudo2.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.opengl.GL30;

public class VAO {
	public static void bind(int vao){
		GL30.glBindVertexArray(vao);
	}
	public static void bind(VAO vao){
		bind(vao.getID());
	}
	public static void unbindAll(){
		bind(0);
	}
	public static void dispose(int id){
		if(ALLVAO.get(id) == null)
			return;
		Logger.getLogger("Sudo").log(Level.INFO, "Disposing VAO "+id);
		int i = 0;
		for(VBO b : VAO_VBO.get(ALLVAO.get(id))){
			b.dispose();
			i++;
		}
		ALLVAO.get(id).ibo.dispose();
		i++;
		Logger.getLogger("Sudo").log(Level.INFO, "Disposed "+(i)+" VBO");
		GL30.glDeleteVertexArrays(id);
		VAO_VBO.remove(ALLVAO.get(id));
		ALLVAO.remove(id);
	}
	public static int create(){
		return GL30.glGenVertexArrays();
	}
	
	public static HashMap<Integer, VAO> ALLVAO = new HashMap<>();
	public static HashMap<VAO, List<VBO>> VAO_VBO = new HashMap<>();
	private int id;
	private int size;
	private IBO ibo;
	
	public VAO(){
		this.id = create();
		List<VBO> vbos = new ArrayList<>();
		ALLVAO.put(id, this);
		VAO_VBO.put(this, vbos);
		
		Logger.getLogger("Sudo").log(Level.INFO, "Created VAO with the ID of "+id);
	}
	
	public int getID(){
		return id;
	}
	
	public void bind(){
		bind(id);
	}
	public void unbind() {
		unbindAll();
	}
	
	public void addVBO(int attribPtr, int size, VBO vbo){
		vbo.bind();
		this.bind();
		vbo.bindData(attribPtr, size);
		unbind();
		VAO_VBO.get(this).add(vbo);
	}
	
	public void addIBO(IBO vbo){
		this.size = vbo.getDataSize();
		this.setIBO(vbo);
		this.bind();
		vbo.bind();
		vbo.bindData();
		unbind();
		vbo.unbind();
	}
	
	public void dispose(){
		dispose(id);
		id = 0;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	public int getSize(){
		return size;
	}
	public IBO getIBO() {
		return ibo;
	}
	public void setIBO(IBO ibo) {
		this.ibo = ibo;
	}
}
