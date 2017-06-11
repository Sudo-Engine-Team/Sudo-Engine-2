package site.root3287.sudo2.display.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.component.functions.AABBComponent;
import site.root3287.sudo2.component.functions.TransposeComponent;
import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.display.Screen;
import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.frustum.Frustum;
import site.root3287.sudo2.engine.gui.Button;
import site.root3287.sudo2.engine.gui.GuiTexture;
import site.root3287.sudo2.engine.gui.components.GuiPanel;
import site.root3287.sudo2.engine.render.Render;
import site.root3287.sudo2.entities.Camera;
import site.root3287.sudo2.entities.CubeOBJEntity;
import site.root3287.sudo2.entities.Entity;
import site.root3287.sudo2.entities.Light;
import site.root3287.sudo2.entities.ProspectiveCamera;
import site.root3287.sudo2.utils.Input;

public class TestScreen implements Screen {

	private Render render;
	private Light light;
	private Camera camera;
	private Frustum frustum;
	private List<Entity> allEntity = new ArrayList<>();
	private List<GuiTexture> allTexture = new ArrayList<>();
	
	@Override
	public void init() {
		this.camera = new ProspectiveCamera();
		this.render = new Render(camera);
		this.light = new Light(new Vector3f(0,1000,0), new Vector4f(1, 1, 1, 0));
		Input.Mouse.setGrabbed(true);
		
		for (int i=0; i<200; i++){
			CubeOBJEntity cube = new CubeOBJEntity();
			cube.getComponent(TransposeComponent.class).position = new Vector3f(new Random().nextFloat() * 100, new Random().nextFloat() *100, new Random().nextFloat() *100);
			allEntity.add(cube);
		}
		
		GuiTexture crosshairX = new GuiTexture(Loader.getInstance().loadTexture("res/image/ui-grey-1.png"), new Vector2f(0f, 0f), new Vector2f(5f, 0.5f));
		GuiTexture crosshairY = new GuiTexture(Loader.getInstance().loadTexture("res/image/ui-grey-1.png"), new Vector2f(0f, 0f), new Vector2f(0.5f, 5f));
		GuiTexture inventoryBar = new GuiTexture(Loader.getInstance().loadTexture("res/image/ui-grey-1.png"), new Vector2f(0f, DisplayManager.HEIGHT/2.25f), new Vector2f(DisplayManager.WIDTH/4f, 25f));
		GuiPanel panel = new GuiPanel("res/image/GUIAtlas.png", new Vector2f(-512+128, 0), new Vector2f(32, 128), new Vector2f(3, 0), new Vector2f(4, 0), new Vector2f(5, 0));
		allTexture.addAll(panel.getNinePatch().getNinePatch());
		allTexture.add(crosshairX);
		allTexture.add(crosshairY);
		//allTexture.add(inventoryBar);
		//allTexture.add(guiTop);
		
		frustum  = new Frustum(render.getProspectiveMatrix(), render.getViewMatrix());
	}

	@Override
	public void update() {
		for(Entity e:allEntity){
			e.update((float) DisplayManager.getDelta());
		}
		this.camera.update((float) DisplayManager.getDelta());
		
		if(Input.Keyboard.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)){
			Input.Mouse.setGrabbed((Input.Mouse.isGrabbed())?false:true);
		}
		
		Matrix4f pv = new Matrix4f();
		Matrix4f.mul(render.getProspectiveMatrix(), render.getViewMatrix(), pv);
		frustum.update(pv);
	}
	
	@Override
	public void render() {
		this.render.updateCamera(camera);
		for(Entity e : allEntity){
			if(e.hasComponent(AABBComponent.class) && frustum.isAABBinFrustum(e.getComponent(AABBComponent.class).aabbBox)){
				this.render.addEntity(e);
			}
		}
		for(GuiTexture t:allTexture){
			render.addGui(t);
		}
		this.render.addLight(this.light);
		this.render.render();
	}

	@Override
	public void destory() {
		this.render.dispose();
		Loader.getInstance().destory();
	}
}
