package site.root3287.sudo2.display.screens;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.display.Screen;
import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.render.Render;
import site.root3287.sudo2.entities.Camera;
import site.root3287.sudo2.entities.CubeEntity;
import site.root3287.sudo2.entities.Light;
import site.root3287.sudo2.entities.ProspectiveCamera;

public class TestScreen implements Screen {

	private Render render;
	private CubeEntity cube;
	private Light light;
	private Camera camera;
	
	//private StringBuilder sb = new StringBuilder();
	
	@Override
	public void init() {
		this.camera = new ProspectiveCamera();
		this.render = new Render();
		cube = new CubeEntity();
		this.light = new Light(new Vector3f(0,10,0), new Vector4f(1, 1, 1, 1));
	}

	@Override
	public void update() {
		this.camera.update(1);
		this.render.updateCamera(camera);
		this.render.addEntity(cube);
		this.render.addLight(this.light);
		/*for(int i = 0; i<GLFW.GLFW_KEY_LAST; i++){
			if(Input.isKeyPressed(i)){
				if(i == GLFW.GLFW_KEY_ENTER){
					sb.append("\n");
				}else if(i == GLFW.GLFW_KEY_BACKSPACE){
					sb.deleteCharAt(sb.length()-1);
				}else if(i== GLFW.GLFW_KEY_SPACE){
					sb.append(" ");
				}else if(i == GLFW.GLFW_KEY_UNKNOWN){
					
				}else{
					sb.append(GLFW.glfwGetKeyName(i, 0));
				}
			}
		}
		
		
		for(int i = 0; i<50; i++){
			System.out.println();
		}
		System.out.println(sb);*/
	}
	
	@Override
	public void render() {
		this.render.render();
	}

	@Override
	public void destory() {
		this.render.dispose();
		Loader.getInstance().destory();
	}
}
