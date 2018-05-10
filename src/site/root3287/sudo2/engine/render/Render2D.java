package site.root3287.sudo2.engine.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.engine.Renderable2D;
import site.root3287.sudo2.engine.camera.Camera;
import site.root3287.sudo2.engine.interfaces.Disposable;
import site.root3287.sudo2.engine.shader.programs.Shader2D;
import site.root3287.sudo2.engine.shader.programs.Shader2DImage;

public class Render2D implements Disposable{
	private Shader2DImage imageShader;
	private Shader2D shader;
	private Camera camera;
	
	public Render2D() {
		this.shader = new Shader2D();
		this.imageShader = new Shader2DImage();
	}
	
	public void setCamera(Camera c) {
		this.camera = c;
		this.shader.start();
		this.shader.proj.loadMatrix(camera.getCombind());
		this.shader.stop();
		this.imageShader.start();
		this.imageShader.proj.loadMatrix(camera.getCombind());
		this.imageShader.stop();
	}
	
	public void add(Renderable2D obj) {
		
	}
	
	public void render(Renderable2D obj) {
		Matrix4f translation = (Matrix4f) new Matrix4f().setIdentity();
		translation.translate(obj.getPosition());
		translation.scale(new Vector3f(obj.getScale().x,obj.getScale().y, 0));
		translation.rotate((float) Math.toRadians(obj.getRotation().x), new Vector3f(1,0,0));
		translation.rotate((float) Math.toRadians(obj.getRotation().y), new Vector3f(0,1,0));
		translation.rotate((float) Math.toRadians(obj.getRotation().z), new Vector3f(0,0,1));
		
		RenderUtils.disableDepthTest();
		obj.getModel().bind();
		RenderUtils.enableVertexAttribsArray(0);
		if(obj.hasImage()) {
			Matrix4f imageTransform = (Matrix4f) new Matrix4f().setIdentity();
			imageTransform.scale(new Vector3f(
					((obj.getImage().getTextureWidth())/(obj.getImage().getImageWidth()+0.5f) ), 
					((obj.getImage().getTextureHeight())/(obj.getImage().getImageHeight())), 1));
			imageTransform.translate(obj.getImage().getOffset());
			
			this.imageShader.start();
			this.imageShader.trans.loadMatrix(translation);
			this.imageShader.imageTrans.loadMatrix(imageTransform);
			//RenderUtils.enableAlpha();
			RenderUtils.enableVertexAttribsArray(1);
			RenderUtils.bindTexture(0, obj.getImage().getID());
			RenderUtils.renderElements(GL11.GL_TRIANGLES, obj.getModel().getSize(), GL11.GL_UNSIGNED_INT, 0);
			RenderUtils.disableVertexAttribsArray(1);
			//RenderUtils.disableAlpha();
			this.imageShader.stop();
		}else {
			this.shader.start();
			this.shader.colour.loadVector(obj.getColour());
			this.shader.trans.loadMatrix(translation);
			RenderUtils.renderElements(GL11.GL_TRIANGLES, obj.getModel().getSize(), GL11.GL_UNSIGNED_INT, 0);
			this.shader.stop();
		}
		RenderUtils.disableVertexAttribsArray(0);
		obj.getModel().unbind();
		RenderUtils.enableDepthTest();
	}

	@Override
	public void dispose() {
		this.shader.dispose();
		this.imageShader.dispose();
	}

}
