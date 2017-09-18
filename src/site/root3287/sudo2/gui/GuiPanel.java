package site.root3287.sudo2.gui;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.texture.AbstractTexture;

public class GuiPanel extends GuiWidget{

	public GuiPanel(){
		this(null, new Vector2f(), new Vector2f());
	}
	
	public GuiPanel(GuiWidget parent){
		this(parent, new Vector2f(), new Vector2f());
	}
	
	public GuiPanel(GuiWidget parent, Vector2f position, Vector2f size){
		this.parent = parent;
		this.position = position;
		this.size = size;
		if(this.parent !=null)
			this.parent.addChild(this);
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public AbstractTexture getTexture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AbstractTexture> getTextures() {
		// TODO Auto-generated method stub
		return null;
	}

}
