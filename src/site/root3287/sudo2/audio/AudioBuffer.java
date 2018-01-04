package site.root3287.sudo2.audio;

import org.lwjgl.openal.AL10;

public class AudioBuffer {
	private int id;
	
	public AudioBuffer(String file) {
		this.id = AL10.alGenBuffers();
	}
}
