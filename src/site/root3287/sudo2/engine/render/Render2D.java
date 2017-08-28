package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.gui.GuiTexture;
import site.root3287.sudo2.engine.model.Model;
import site.root3287.sudo2.engine.shader.programs.Shader2D;
import site.root3287.sudo2.utils.SudoMaths;

public class Render2D{
	private Model model;
	private static final float[] positions = {-1,1,-1,-1,1,1,1,-1};
	private Shader2D shader;
	
	private List<GuiTexture> guis = new ArrayList<>();
	
	public Render2D() {
		model = Loader.getInstance().loadToVAO(positions);
		this.shader = new Shader2D();
	}
	
	public void loadProjection(Matrix4f projectionMatrix){
		this.shader.start();
		((Shader2D) this.shader).loadProjection(projectionMatrix);
		this.shader.stop();
	}
	
	public void render(){
		shader.start();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		for(GuiTexture gui : guis){
			((Shader2D) shader).useTextureAtlas(gui.textureAtlas, gui.rows, gui.offset);
			((Shader2D) shader).useProjection(gui.useProjection);
			((Shader2D) shader).colouredQuad(gui.isColoured(), gui.getColour());
			((Shader2D) shader).loadTransformation(SudoMaths.createTransformationMatrix(gui.position, gui.scale, gui.rotation));
			if(!gui.isColoured()){
				GL13.glActiveTexture(GL13.GL_TEXTURE0);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTexture());
			}
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, model.getVertexCount());
		}
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
		
		guis.clear();
	}
	
	public void addGui(GuiTexture t){
		guis.add(t);
	}

	public void dispose() {
		shader.dispose();
	}
}
