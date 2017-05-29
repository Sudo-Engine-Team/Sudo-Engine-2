package site.root3287.sudo2.entities;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.component.functions.ColourComponent;
import site.root3287.sudo2.component.functions.TransposeComponent;

public class Light extends Entity {
	
	public Light(Vector3f position, Vector4f colour) {
		TransposeComponent tc = new TransposeComponent();
		tc.position = position;
		ColourComponent cc = new ColourComponent();
		cc.colour = colour;
		addComponent(tc);
		addComponent(cc);
	}
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

}
