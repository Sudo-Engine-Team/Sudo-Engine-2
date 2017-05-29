package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.engine.TexturedModel;
import site.root3287.sudo2.engine.font.FontText;
import site.root3287.sudo2.engine.font.GUIText;
import site.root3287.sudo2.engine.shader.programs.EntityShader;
import site.root3287.sudo2.entities.Entity;
import site.root3287.sudo2.entities.Light;
import site.root3287.sudo2.logger.LogLevel;
import site.root3287.sudo2.logger.Logger;
import site.root3287.sudo2.utils.SudoMaths;

public class Render {
	private Matrix4f projectionMatrix;
	private Matrix4f orthographicMatrix;

	private List<Light> lights = new ArrayList<>();
	private Map<TexturedModel, List<Entity>> entities = new HashMap<>();

	// Start the shader
	// Init render varible.
	EntityShader entityShader = new EntityShader();
	EntityRender entityRender;

	FontText fontRender;

	public Render() {
		enableCulling();
		this.projectionMatrix = SudoMaths.createProjectionMatrix();
		this.orthographicMatrix = SudoMaths.createOrthoMatrix();
		this.entityRender = new EntityRender(entityShader, projectionMatrix);
		this.fontRender = new FontText();
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

		entityShader.start();
		entityShader.loadLight(lights);
		entityRender.render(entities);
		entityShader.stop();
		
		fontRender.render();
	}

	public void addLight(Light l) {
		lights.add(l);
	}
	
	public void addText(GUIText text){
		this.fontRender.loadText(text);
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

	public Matrix4f getOrthographicMatrix() {
		return this.orthographicMatrix;
	}
}
