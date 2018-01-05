package site.root3287.sudo2.audio;

import org.lwjgl.openal.AL10;

public class AudioListener {
	public static void setListenerData(){
		AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
}
