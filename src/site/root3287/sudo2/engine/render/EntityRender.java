package site.root3287.sudo2.engine.render;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.component.functions.TransposeComponent;
import site.root3287.sudo2.engine.ModelTexture;
import site.root3287.sudo2.engine.RawModel;
import site.root3287.sudo2.engine.TexturedModel;
import site.root3287.sudo2.engine.shader.programs.EntityShader;
import site.root3287.sudo2.entities.Entity;
import site.root3287.sudo2.logger.LogLevel;
import site.root3287.sudo2.logger.Logger;
import site.root3287.sudo2.utils.SudoMaths;

public class EntityRender {
	private EntityShader shader;
	public EntityRender(EntityShader shader, Matrix4f projectionMatrix){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		this.shader = shader;
		this.shader.start();
		this.shader.loadProjectionMatrix(projectionMatrix);
		this.shader.stop();
	}
	public void render(Map<TexturedModel, List<Entity>> entities) {
		Logger.log(LogLevel.DEBUG_RENDER, "Rendering a entites");
		for (TexturedModel model : entities.keySet()) {
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for (Entity entity : batch) {
				prepareTexturedModel(model);
				boolean render = true;
				/*if(Render.culler !=null && entity.hasComponent(AABBComponent.class)){
					if(Render.culler.isAABBinFrustum(entity.getComponent(AABBComponent.class).aabb)){
						render = true;
					}
				}else{
					render = true;
				}*/
				if(render){
					prepareTexturedModel(model);
					prepareInstance(entity);
					GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
				}
				unbindTexturedModel();
			}
		}
	}

	private void prepareTexturedModel(TexturedModel model) {
		Logger.log(LogLevel.DEBUG_RENDER, "Preparing the texture model");
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		ModelTexture texture = model.getTexture();
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
	}

	private void unbindTexturedModel() {
		Logger.log(LogLevel.DEBUG_RENDER, "Unbinding model");
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}

	private void prepareInstance(Entity entity) {
		Logger.log(LogLevel.DEBUG_RENDER, "Preparing entity to be rendered");
		Vector3f position = entity.getComponent(TransposeComponent.class).position;
		Vector3f rotation = entity.getComponent(TransposeComponent.class).rotation;
		float scale = entity.getComponent(TransposeComponent.class).scale;
		Matrix4f transformationMatrix = SudoMaths.createTransformationMatrix(position, rotation, scale);
		shader.loadTransformationMatrix(transformationMatrix);
		
		Logger.log(LogLevel.DEBUG_RENDER, "Rendering Entity");
	}
}
