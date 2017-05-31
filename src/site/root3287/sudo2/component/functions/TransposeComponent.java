package site.root3287.sudo2.component.functions;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.component.EntityComponent;

public class TransposeComponent extends EntityComponent{
	public Vector3f position = new Vector3f(0, 0, 0);
	public Vector3f rotation= new Vector3f(0, 0, 0);
	public Vector3f velocity = new Vector3f(0, 0, 0);
	public int direction =0;
	public float scale = 1, pitch = 0, yaw = 0, roll = 0;
}
