package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.engine.shader.programs.GuiShader;
import site.root3287.sudo2.gui.GuiWidget;

public class GuiRender {
	private GuiShader shader;
	private Matrix4f projection;
	private List<GuiWidget> guis = new ArrayList<>();
	
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
		for(GuiWidget gs : g.children()){
			addGui(gs);
		}
	}
	
	public void render(){
		shader.start();
		for(GuiWidget g : guis){
			GL30.glBindVertexArray(g.getModel().getVaoID());
			GL20.glEnableVertexAttribArray(0);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			if(g.getTextures() != null && g.getTextures().size() > 1) { 
				for(int i = 0; i < g.getTextures().size(); i++) {
					RenderUtils.bindTexture(i, g.getTextures().get(i).getTextureID());
				}
			}else {
				RenderUtils.bindTexture(0, g.getTexture().getTextureID());
			}
			GL11.glDrawElements(GL11.GL_TRIANGLES, g.getModel().getVaoID(), GL11.GL_UNSIGNED_INT, 0);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_BLEND);
			GL20.glDisableVertexAttribArray(0);
			GL30.glBindVertexArray(0);
		}
		shader.stop();
		
		guis.clear();
	}
}
