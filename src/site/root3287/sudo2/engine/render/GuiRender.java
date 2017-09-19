package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.model.Model;
import site.root3287.sudo2.engine.shader.programs.GuiShader;
import site.root3287.sudo2.gui.GuiWidget;

public class GuiRender {
	private GuiShader shader;
	private Matrix4f projection;
	private List<GuiWidget> guis = new ArrayList<>();
	private Model cachedModelTextured = Loader.getInstance().loadToVAO(new float[]{-1,1,-1,-1,1,1,1,-1}, new float[]{0,0, 0,1, 1,0,1,1}, new int[] {0,1,2,2,1,3});
	private Model cachedModel = Loader.getInstance().loadToVAO(new float[]{-1,1,-1,-1,1,1,1,-1}, new int[] {0,1,2,2,1,3});
	public GuiRender() {
		shader = new GuiShader();
	}
	
	public void dispose(){
		this.shader.dispose();
	}

	public Matrix4f getProjection() {
		return projection;
	}

	public void setProjection(Matrix4f projection) {
		this.projection = projection;
		shader.start();
		shader.loadProjection(projection);
		shader.stop();
	}
	
	/**
	 * Recursively add GuiWidget it's children to the renderer;
	 * @param g
	 */
	public void addGui(GuiWidget g){
		guis.add(g);
		for(GuiWidget gs : g.getChildren()){
			addGui(gs);
		}
	}
	
	public void render(){
		shader.start();
		for(GuiWidget g : guis){
			GL30.glBindVertexArray(cachedModelTextured.getVaoID());
			GL20.glEnableVertexAttribArray(0);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDrawElements(GL11.GL_TRIANGLES, cachedModelTextured.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_BLEND);
			GL20.glDisableVertexAttribArray(0);
			GL30.glBindVertexArray(0);
		}
		shader.stop();
		
		guis.clear();
	}
}
