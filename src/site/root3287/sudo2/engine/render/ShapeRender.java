package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import site.root3287.sudo2.engine.shader.programs.ShapeShader;
import site.root3287.sudo2.shape.Shape;

public class ShapeRender extends Renderable{
	private List<Shape> shapes = new ArrayList<>();
	
	public ShapeRender() {
		this.shader = new ShapeShader();
	}
	
	public void render(){
		this.shader.start();
		for(Shape s : shapes){
			GL30.glBindVertexArray(s.getModel().getID());
			GL20.glEnableVertexAttribArray(0);
			((ShapeShader) this.shader).loadTrasform(s.getPosition(), s.getScale());
			((ShapeShader) this.shader).loadProspectiveMatrix(this.projection);
			((ShapeShader) this.shader).loadColour(s.getColour());
			RenderUtils.clearGLErrors();
			GL11.glDrawElements(GL11.GL_TRIANGLES, s.getModel().getSize(), GL11.GL_UNSIGNED_INT, 0);
			assert(RenderUtils.checkGLError());
			GL20.glDisableVertexAttribArray(0);
			GL30.glBindVertexArray(0);
		}
		shapes.clear();
		this.shader.stop();
	}
	
	public void addShape(Shape s){
		shapes.add(s);
	}
	
	public void dispose(){
		this.shader.dispose();
	}
}
