package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.component.functions.ModelComponet;
import site.root3287.sudo2.component.functions.TransposeComponent;
import site.root3287.sudo2.engine.model.Model;
import site.root3287.sudo2.engine.model.TexturedModel;
import site.root3287.sudo2.engine.shader.programs.EntityShader;
import site.root3287.sudo2.entities.Entity;
import site.root3287.sudo2.entities.Light;
import site.root3287.sudo2.utils.SudoMaths;

public class EntityRender{
	private EntityShader shader;
	private Matrix4f projection;
	private Map<TexturedModel, List<Entity>> entities = new HashMap<>();
	private List<Light> lights = new ArrayList<>();
	
	public EntityRender(){
		RenderUtils.enableCulling();
		this.shader = new EntityShader();
	}
	
	public void loadProjectionMatrix(Matrix4f matrix){
		this.projection = matrix;
		shader.start();
		this.shader.loadProjectionMatrix(projection);
		shader.stop();
	}
	public void loadViewMatrix(Matrix4f matrix){
		shader.start();
		shader.loadViewMatrix(matrix);
		shader.stop();
	}
	
	public void render() {
		shader.start();
		shader.loadLight(lights);
		
		for (TexturedModel model : entities.keySet()) {
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for (Entity entity : batch) {
				prepareInstance(entity);
				GL11.glLineWidth(0.5f);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
				unbindTexturedModel();
			}
		}
		shader.stop();
		lights.clear();
		entities.clear();
	}

	private void prepareTexturedModel(TexturedModel model) {
		Model rawModel = model.getModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		shader.loadShineVariables(model.getShineDamper(), model.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
	}

	private void unbindTexturedModel() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}

	private void prepareInstance(Entity entity) {
		Vector3f position = entity.getComponent(TransposeComponent.class).position;
		Vector3f rotation = entity.getComponent(TransposeComponent.class).rotation;
		float scale = entity.getComponent(TransposeComponent.class).scale;
		Matrix4f transformationMatrix = SudoMaths.createTransformationMatrix(position, rotation, scale);
		shader.loadTransformationMatrix(transformationMatrix);
		//shader.useTextureAtlas(entity.getComponent(ModelComponet.class).model.getTexture().isTextureAtlas(), entity.getComponent(ModelComponet.class).model.getTexture().getRows(), entity.getComponent(ModelComponet.class).model.getTexture().getOffset());
	}
	
	public void addEntity(Entity entity){
		if(!entity.hasComponent(ModelComponet.class) && !entity.hasComponent(TransposeComponent.class)){
			return;
		}
		TexturedModel entityModel = entity.getComponent(ModelComponet.class).model;
		List<Entity> batch = entities.get(entityModel);
		if (batch != null) {
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	
	public void addLight(Light light){
		this.lights.add(light);
	}
	
	public void dispose(){
		this.shader.dispose();
	}
}
