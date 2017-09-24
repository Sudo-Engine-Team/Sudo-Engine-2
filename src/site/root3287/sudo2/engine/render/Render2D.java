package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.model.Model;
import site.root3287.sudo2.engine.shader.programs.Shader2D;
import site.root3287.sudo2.engine.texture.ImageModel;
import site.root3287.sudo2.utils.SudoMaths;

public class Render2D extends Renderable{
	
	List<ImageModel> images = new ArrayList<>();
	private static Model vaoModel;
	
	public Render2D(){
		this.shader = new Shader2D();
		vaoModel = Loader.getInstance().loadToVAO(
				new float[]{
						-1,1,
						-1,-1,
						1,1,
						1,-1
				}, 
				new float[]{
						0,0,
						0,1,
						1,0,
						1,1
				}, 
				new int[]{
						0,1,2,
						2,1,3
				}
		);
	}
	
	public void render() {
		for(ImageModel model : images){
			render(model);
		}
		images.clear();
	}
	
	public void render(ImageModel model){
		((Shader2D)shader).proj.loadMatrix(projection);
		((Shader2D)shader).trans.loadMatrix(SudoMaths.createTransformationMatrix(model.getPosition(), model.getScale(), model.getRotation()));
		((Shader2D)shader).useImage.loadBoolean(true);
		((Shader2D)shader).overrideColour.loadVector(new Vector4f(1, 1, 1, 1));
		Matrix4f m = new Matrix4f();
		m.scale(new Vector3f(1/model.getTextureSize().x,1/model.getTextureSize().y,0));
		m.translate(new Vector3f(model.getOffset().x, model.getOffset().y, 0));
		((Shader2D)shader).tcTrans.loadMatrix(m);
		
		RenderUtils.bindVAO(vaoModel.getVaoID());
		RenderUtils.bindTexture(0, model.getTexture().getTextureID());
		RenderUtils.enableAlpha();
		RenderUtils.enableVertexAttribsArray(0);
		RenderUtils.enableVertexAttribsArray(1);
		RenderUtils.renderElements(GL11.GL_TRIANGLES, vaoModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
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
