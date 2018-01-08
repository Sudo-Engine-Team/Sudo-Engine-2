package site.root3287.sudo2.audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.vector.Vector3f;

public class AudioSource {
	public static void dispose(int id){
		AL10.alDeleteSources(id);
	}
	private int id;
	private Vector3f position, velocity;
	private float gain;
	private float pitch;
	private float rolloff;
	private boolean loop;
	private boolean relativeSound;
	private boolean absoluteSound;
	
	public AudioSource() {
		this.id = AL10.alGenSources();
		setVolume(1);
		setPitch(1);
		setPosition(new Vector3f());
		setVelocity(new Vector3f());
		setLoop(false);
	}
	
	public void play(AudioBuffer buffer){
		if(!isPlaying()){
			AL10.alSourcei(id, AL10.AL_BUFFER, buffer.getID());
			AL10.alSourcePlay(id);
		}
	}
	
	public void pause(){
		AL10.alSourcePause(id);
	}
	
	public void play(){
		if(!isPlaying())
			AL10.alSourcePlay(id);
	}
	
	public void stop(){
		AL10.alSourceStop(id);
	}
	
	public boolean isPlaying(){
		return AL10.alGetSourcef(id, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
	}

	public void setLoop(boolean b){
		this.loop = b;
		AL10.alSourcef(id, AL10.AL_LOOPING, b?AL10.AL_TRUE:AL10.AL_FALSE);
	}
	
	public boolean isLooping(){
		return loop;
	}
	
	public void setPosition(Vector3f v){
		this.position = v;
		AL10.alSource3f(id, AL10.AL_POSITION, v.x, v.y, v.z);
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPitch(float f){
		this.pitch = f;
		AL10.alSourcef(id, AL10.AL_PITCH, f);
	}

	public void setVelocity(Vector3f v){
		this.velocity = v;
		AL10.alSource3f(id, AL10.AL_VELOCITY, v.x, v.y, v.z);
	}

	public Vector3f getVelocity() {
		return velocity;
	}

	public void setVolume(float f){
		this.gain = f;
		AL10.alSourcef(id, AL10.AL_GAIN, f);
	}

	public float getVolume() {
		return gain;
	}

	public float getPitch() {
		return pitch;
	}
	
	public void setRolloff(float f) {
		this.rolloff = f;
		AL10.alSourcef(this.id, AL10.AL_ROLLOFF_FACTOR, rolloff);
	}
	
	public float getRolloff() {
		return this.rolloff;
	}
	
	public void setRelativeSound(boolean b) {
		if(b && absoluteSound) {
			setAbsoluteSound(false);
		}
		this.relativeSound = b;
		AL10.alSourcef(this.id, AL10.AL_SOURCE_RELATIVE, b?AL10.AL_TRUE:AL10.AL_FALSE);
	}
	
	public boolean isRelativeSound() {
		return relativeSound;
	}
	
	public void setAbsoluteSound(boolean b) {
		if(b && relativeSound) {
			setRelativeSound(false);
		}
		this.absoluteSound = b;
		AL10.alSourcef(this.id, AL10.AL_SOURCE_ABSOLUTE, b?AL10.AL_TRUE:AL10.AL_FALSE);
	}
	
	public boolean isAbsoluteSound() {
		return this.absoluteSound;
	}
	
	public void dispose(){
		dispose(id);
	}
}
