package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.RawModel;
import site.root3287.sudo2.engine.gui.GuiTexture;
import site.root3287.sudo2.engine.shader.programs.GuiShader;
import site.root3287.sudo2.gui.GuiWidget;
import site.root3287.sudo2.utils.SudoMaths;

public class GuiRender {
	private GuiShader shader;
	private Matrix4f projection;
	private List<GuiWidget> guis = new ArrayList<>();
	private RawModel model;
	private static final float[] positions = {-1,1,-1,-1,1,1,1,-1};
	
	public GuiRender() {
		model = Loader.getInstance().loadToVAO(positions);
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
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		for(GuiWidget gui : guis){
			for(GuiTexture textures: gui.getTexture().getNinePatch()){
				((GuiShader) shader).useTextureAtlas(textures.textureAtlas, textures.rows, textures.offset);
				((GuiShader) shader).useProjection((this.projection !=null)?true:false);
				//((GuiShader) shader).colouredQuad(gui.isColoured(), gui.getColour());
				((GuiShader) shader).loadTransformation(SudoMaths.createTransformationMatrix(textures.getPosition(), textures.getScale(), textures.getRotation()));
				GL13.glActiveTexture(GL13.GL_TEXTURE0);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures.getTexture());
				GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, model.getVertexCount());
			}
		}
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
		
		guis.clear();
	}
}
