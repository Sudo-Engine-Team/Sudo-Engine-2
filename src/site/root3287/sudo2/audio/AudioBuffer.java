package site.root3287.sudo2.audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

public class AudioBuffer {
	public static void dispose(int id){
		AL10.alDeleteBuffers(id);
	}
	
	private int id;
	
	public AudioBuffer(String file) {
		this.id = AL10.alGenBuffers();
		WaveData d = WaveData.create(Class.class.getResourceAsStream(file));
		AL10.alBufferData(id, d.format, d.data, d.samplerate);
		d.dispose();
	}
	
	public void dispose(){
		dispose(id);
	}
	
	public int getID(){
		return id;
	}
}
