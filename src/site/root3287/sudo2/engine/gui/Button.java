package site.root3287.sudo2.engine.gui;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

public class Button {
	private Vector2f position, scale;
	private NinePatch image;
	public Button(){
		position = new Vector2f();
		scale = new Vector2f(400, 50);
		
		image = new NinePatch("res/image/GUIAtlas.png", position, scale, new Vector2f(3,0), new Vector2f(4,0), new Vector2f(5, 0), 64, 16);
	}
	
	public List<GuiTexture> getAllButtons(){
		return image.getNinePatch();
	}
}
