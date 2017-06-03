package site.root3287.sudo2.display.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.component.functions.AABBComponent;
import site.root3287.sudo2.component.functions.TransposeComponent;
import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.display.Screen;
import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.frustum.Frustum;
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
	
	@Override
	public void init() {
		this.camera = new ProspectiveCamera();
		this.render = new Render(camera);
		this.light = new Light(new Vector3f(0,10,0), new Vector4f(1, 1, 1, 1));
		Input.Mouse.setGrabbed(true);
		
		for (int i=0; i<1000; i++){
			CubeOBJEntity cube = new CubeOBJEntity();
			cube.getComponent(TransposeComponent.class).position = new Vector3f(new Random().nextFloat() * 100, new Random().nextFloat() *100, new Random().nextFloat() *100);
			allEntity.add(cube);
		}
		
		Matrix4f pv = new Matrix4f();
		Matrix4f.mul(render.getProspectiveMatrix(), render.getViewMatrix(), pv);
		frustum  = new Frustum(pv);
	}

	@Override
	public void update() {
		for(Entity e:allEntity){
			e.update((float) DisplayManager.getDelta());
		}
		this.camera.update((float) DisplayManager.getDelta());
		
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
		this.render.addLight(this.light);
		this.render.render();
	}

	@Override
	public void destory() {
		this.render.dispose();
		Loader.getInstance().destory();
	}
}
