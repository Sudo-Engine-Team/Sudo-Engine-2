package site.root3287.sudo2.engine.gui.components;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.gui.NinePatch;

@Deprecated
public abstract class GuiPanel extends GUIComponent{
	
	private NinePatch image;
	
	public GuiPanel(String file, Vector2f postion, Vector2f size) {
		this.position = postion;
		this.scale = size;
		image = new NinePatch(file, postion, size, new Vector2f(0, 0), 64, 16);
	}
	
	public GuiPanel(String file, Vector2f postion, Vector2f size, Vector2f corner, Vector2f edge, Vector2f background) {
		this.position = postion;
		this.scale = size;
		image = new NinePatch(file, postion, size, corner, edge, background, 64,16);
	}
	
	public NinePatch getNinePatch(){
		if(isHidden){
			return null;
		}
		return image;
	}
}
