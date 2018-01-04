package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.VBO;
import site.root3287.sudo2.engine.shader.programs.Shader2D;
import site.root3287.sudo2.engine.texture.ImageModel;
import site.root3287.sudo2.utils.SudoMaths;

public class Render2D extends Renderable{
	
	List<ImageModel> images = new ArrayList<>();
	private static VAO vaoModel;
	
	public Render2D(){
		vaoModel = new VAO();
		VBO pos = new VBO();
		pos.setData(new float[] {
				-1,1,0,
				-1,-1,0,
				1,1,0,
				1,-1,0
		});
		VBO ind = new VBO(true);
		ind.setData(new int[] {
				0,1,2,2,1,3
		});
		VBO tc = new VBO();
		tc.setData(new float[] {
				0,0,
				0,1,
				1,0,
				1,1
		});
		vaoModel.addVBO(ind);
		vaoModel.addVBO(0,3,pos);
		vaoModel.addVBO(1,2,tc);
		this.shader = new Shader2D();
	}
	
	public void render() {
		shader.start();
		RenderUtils.bindVAO(vaoModel.getID());
		RenderUtils.disableDepthTest();
		for(ImageModel model : images){
			((Shader2D)shader).proj.loadMatrix(projection);
			((Shader2D)shader).trans.loadMatrix(SudoMaths.createTransformationMatrix(model.getPosition(), model.getScale(), model.getRotation()));
			((Shader2D)shader).isOverrideColour.loadBoolean(model.isOverrideColour());
			((Shader2D)shader).overrideColour.loadVector(model.getColour());
			Matrix4f m = new Matrix4f();
			m.scale(new Vector3f(1/model.getTextureSize().x,1/model.getTextureSize().y,0));
			m.translate(new Vector3f(model.getOffset().x, model.getOffset().y, 0));
			((Shader2D)shader).tcTrans.loadMatrix(m);
			
			if(!model.isOverrideColour()) {
				RenderUtils.bindTexture(0, model.getTexture().getTextureID());
				RenderUtils.enableAlpha();
			}
			RenderUtils.enableVertexAttribsArray(0);
			RenderUtils.enableVertexAttribsArray(1);
			RenderUtils.renderElements(GL11.GL_TRIANGLES, vaoModel.getSize(), GL11.GL_UNSIGNED_INT, 0);
			RenderUtils.disableVertexAttribsArray(0);
			RenderUtils.disableVertexAttribsArray(1);
			RenderUtils.disableAlpha();
		}
		RenderUtils.enableDepthTest();
		RenderUtils.unbindVAO();
		shader.stop();
		images.clear();
	}
	
	public void render(ImageModel model){
		((Shader2D)shader).proj.loadMatrix(projection);
		((Shader2D)shader).trans.loadMatrix(SudoMaths.createTransformationMatrix(model.getPosition(), model.getScale(), model.getRotation()));
		((Shader2D)shader).isOverrideColour.loadBoolean(model.isOverrideColour());
		((Shader2D)shader).overrideColour.loadVector(model.getColour());
		Matrix4f m = new Matrix4f();
		m.scale(new Vector3f(1/model.getTextureSize().x,1/model.getTextureSize().y,0));
		m.translate(new Vector3f(model.getOffset().x, model.getOffset().y, 0));
		((Shader2D)shader).tcTrans.loadMatrix(m);
		
		RenderUtils.bindVAO(vaoModel.getID());
		if(!model.isOverrideColour()) {
			RenderUtils.bindTexture(0, model.getTexture().getTextureID());
			RenderUtils.enableAlpha();
		}
		RenderUtils.enableVertexAttribsArray(0);
		RenderUtils.enableVertexAttribsArray(1);
		RenderUtils.renderElements(GL11.GL_TRIANGLES, vaoModel.getSize(), GL11.GL_UNSIGNED_INT, 0);
		RenderUtils.disableVertexAttribsArray(0);
		RenderUtils.disableVertexAttribsArray(1);
		RenderUtils.disableAlpha();
		RenderUtils.unbindVAO();
	}
	
	public void addTexture(ImageModel t){
		images.add(t);
	}
	
	@Override
	public void dispose() {
		shader.dispose();
	}

}
