package site.root3287.sudo2.engine.gui;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

public class RainbowQuad extends GuiTexture{

	private Vector4f colourHolder;
	private int speed = 1;
	
	public RainbowQuad(Vector2f position, Vector2f scale, float alpha) {
		super(position, scale, new Vector4f(1,0,0,alpha));
		this.colourHolder = new Vector4f(255, 0, 0, alpha);
	}
	
	public void update(float delta){
		Vector4f col = this.colourHolder;
		if(col.x >  0 && col.z == 0){
			col.x -= 1 * speed;
			col.y += 1 * speed;
		}
		if(col.y > 0 && col.x == 0){
			col.y -=1 * speed;
			col.z +=1 * speed;
		}
		if(col.z > 0 && col.y == 0){
			col.x +=1 * speed;
			col.z -=1 * speed;
		}
		this.colour = new Vector4f(col.x/255, col.y/255, col.z/255, col.w);
		System.out.println(colour);
	}
	
	public void setSpeed(int speed){
		this.speed = speed+(speed %2)+1;
		System.out.println(this.speed);
	}

}
