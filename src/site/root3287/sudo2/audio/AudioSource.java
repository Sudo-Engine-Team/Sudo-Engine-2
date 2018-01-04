package site.root3287.sudo2.audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.vector.Vector3f;

public class AudioSource {
	private int id;
	private Vector3f position, velocity;
	private float gain;
	private float volume;
	private float pitch;
	
	public AudioSource() {
		this.id = AL10.alGenSources();
	}
}
