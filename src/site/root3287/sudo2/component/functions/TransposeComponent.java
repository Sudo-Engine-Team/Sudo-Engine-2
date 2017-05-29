package site.root3287.sudo2.component.functions;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.component.EntityComponent;

public class TransposeComponent extends EntityComponent{
	public Vector3f position;
	public Vector3f rotation;
	public Vector3f direction;
	public float scale = 1, pitch = 0, yaw = 0, roll = 0;
}
