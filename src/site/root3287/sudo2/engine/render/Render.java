package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.component.functions.ModelComponet;
import site.root3287.sudo2.component.functions.TransposeComponent;
import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.engine.TexturedModel;
import site.root3287.sudo2.engine.font.FontText;
import site.root3287.sudo2.engine.font.GUIText;
import site.root3287.sudo2.engine.gui.GuiTexture;
import site.root3287.sudo2.engine.shader.programs.EntityShader;
import site.root3287.sudo2.engine.shader.programs.Shader2D;
import site.root3287.sudo2.entities.Camera;
import site.root3287.sudo2.entities.Entity;
import site.root3287.sudo2.entities.Light;
import site.root3287.sudo2.logger.LogLevel;
import site.root3287.sudo2.logger.Logger;
import site.root3287.sudo2.utils.SudoMaths;

public class Render {
	private Camera camera;
	
	private static Matrix4f projectionMatrix;
	private static Matrix4f orthographicMatrix;
	private Matrix4f viewMatrix;
	
	private List<Light> lights = new ArrayList<>();
	private List<GuiTexture> guis = new ArrayList<>();
	private Map<TexturedModel, List<Entity>> entities = new HashMap<>();

	// Start the shader
	// Init render varible.
	EntityShader entityShader = new EntityShader();
	EntityRender entityRender;
	
	Shader2D guiShader = new Shader2D();
	Render2D guiRender;

	FontText fontRender;

	public Render() {
		enableCulling();
		projectionMatrix = SudoMaths.createProjectionMatrix();
		orthographicMatrix = SudoMaths.createOrthoMatrix();
		this.entityRender = new EntityRender(entityShader, projectionMatrix);
		this.fontRender = new FontText();
		this.guiRender = new Render2D(guiShader, projectionMatrix);
	}
	
	public Render(Camera c) {
		this.camera = c;
		enableCulling();
		projectionMatrix = SudoMaths.createProjectionMatrix();
		orthographicMatrix = SudoMaths.createOrthoMatrix();
		viewMatrix = SudoMaths.createViewMatrix(camera);
		this.entityRender = new EntityRender(entityShader, projectionMatrix);
		this.fontRender = new FontText();
		this.guiRender = new Render2D(guiShader, orthographicMatrix);
	}

	public void prepare() {
		Logger.log(LogLevel.DEBUG_RENDER, "Preparing render");
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(DisplayManager.BACKGROUND_COLOUR.x, DisplayManager.BACKGROUND_COLOUR.y,
				DisplayManager.BACKGROUND_COLOUR.z, DisplayManager.BACKGROUND_COLOUR.w);
	}

	public void render() {
		prepare();
		
		viewMatrix = SudoMaths.createViewMatrix(camera);
		
		entityShader.start();
		entityShader.loadViewMatrix(viewMatrix);
		entityShader.loadLight(lights);
		entityRender.render(entities);
		entityShader.stop();
		
		fontRender.render();
		
		guiShader.start();
		guiRender.render(guis);
		guiShader.stop();
		
		entities.clear();
		lights.clear();
		guis.clear();
	}
	
	public void updateCamera(Camera c){
		this.camera = c;
	}

	public void addEntity(Entity entity) {
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
	
	public void addLight(Light l) {
		lights.add(l);
	}
	
	public void addText(GUIText text){
		this.fontRender.loadText(text);
	}
	
	public void addGui(GuiTexture text){
		this.guis.add(text);
	}
	
	public void removeText(int index){
		this.fontRender.removeText(index);
	}
	
	public List<GUIText> getAllText(){
		return fontRender.getAllText();
	}

	public void dispose() {
		// Dispose all the shaders...
		this.entityShader.dispose();
		this.fontRender.dispose();
		this.guiRender.dispose();
	}

	public static void enableCulling() {
		Logger.log(LogLevel.DEBUG_RENDER, "Enabling Culling");
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}

	public static void disableCulling() {
		Logger.log(LogLevel.DEBUG_RENDER, "Disabling Culling");
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void resetOrthographicMatrix() {
		
	}

	public static void setOrthographicMatrix() {
		orthographicMatrix = SudoMaths.createOrthoMatrix();
	}
	
	public Matrix4f getProspectiveMatrix() {
		return projectionMatrix;
	}
	
	public Matrix4f getViewMatrix() {
		return this.viewMatrix;
	}
}
