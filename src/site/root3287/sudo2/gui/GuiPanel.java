package site.root3287.sudo2.gui;

import org.lwjgl.util.vector.Vector2f;

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

}
