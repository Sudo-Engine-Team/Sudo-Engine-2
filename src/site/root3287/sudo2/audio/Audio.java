package site.root3287.sudo2.audio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.logging.Level;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;

import site.root3287.sudo2.display.Application;

public class Audio {
	private static long device;
	private static long context;
	public static void init() {
		device = ALC10.alcOpenDevice((ByteBuffer)null);
		ALCCapabilities deviceCaps = ALC.createCapabilities(device);
		context = ALC10.alcCreateContext(device, (IntBuffer)null);
		ALC10.alcMakeContextCurrent(context);
		AL.createCapabilities(deviceCaps);
	}
	public static void destroy() {
		ALC10.alcDestroyContext(context);
		ALC10.alcCloseDevice(device);
		ALC.destroy();
	}
	public static void clearErrors(){
		while(AL10.alGetError() != AL10.AL_NO_ERROR);
	}
	public static void getErrors(){
		int e = AL10.alGetError();
		while(e != AL10.AL_NO_ERROR){
			Application.getClientLogger().log(Level.SEVERE, "OpenAL has received an error! "+AL10.alGetString(e));
			e = AL10.alGetError();
		}
	}
}
