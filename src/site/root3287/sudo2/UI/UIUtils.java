package site.root3287.sudo2.UI;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.UI.events.type.UIClickEventType;
import site.root3287.sudo2.display.Game;
import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.VBO;
import site.root3287.sudo2.engine.camera.Camera;
import site.root3287.sudo2.engine.interfaces.Disposable;
import site.root3287.sudo2.engine.interfaces.Updateable;
import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.utils.Input;

public class UIUtils implements Updateable, Disposable{
	private static UIUtils _instance;
	private Camera camera;
	public static Game game;
	public EventDispatcher clickDispatcher = new EventDispatcher(new UIClickEventType());
	public VAO vao;
	
	private UIUtils() {
		this.vao = new VAO();
		VBO pos = new VBO();
		pos.setData(new float[]{
				-1,1,0,
				-1,-1,0,
				1,1,0,
				1,-1,0
		});
		VBO ind = new VBO(true);
		ind.setData(new int[]{
				0,1,2,2,1,3
		});
		VBO tc = new VBO(false);
		tc.setData(new float[]{
				0,0,
				1,0,
				0,1,
				1,1,
		});
		this.vao.addVBO(0, 3, pos);
		this.vao.addVBO(1,2,tc);
		this.vao.addVBO(ind);
		
	}
	public static UIUtils getInstance() {
		if(_instance == null) {
			_instance = new UIUtils();
		}
		return _instance;
	}
	
	public void setCamera(Camera c){
		this.camera = c;
	}
	
	public Camera getUICamera(){
		return this.camera;
	}
	
	public VAO getVAO(){
		return this.vao;
	}
	
	public Vector2f getMousePosition(){
		return getMousePosition(this.camera);
	}
	
	public Vector2f getMousePosition(Camera c){
		Vector2f mousePos = new Vector2f();
		Vector3f pos = Input.Mouse.getMouseProjection(c, new Vector2f(game.getWidth(), game.getHeight()));
		mousePos.x = pos.x;
		mousePos.y = pos.y;
		return mousePos;
	}
	
	public void update(float delta){
		
	}
	
	public void dispose(){
		if(vao.getID() != 0)
			this.vao.dispose();
	}
	
	public boolean mouseInBounds(UIWidget w, Vector2f mouse){
		Vector2f pos = w.getAbsolutePosition();
		Vector2f size = w.getScale();
		if(!((mouse.y > pos.y - (size.y)) && (mouse.y < pos.y + (size.y))))
			return false;
		if(!((mouse.x > pos.x - (size.x)) && (mouse.x < pos.x + (size.x))))
			return false;
		return true;
	}
}
