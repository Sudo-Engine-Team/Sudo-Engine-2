package site.root3287.sudo2.utils;

import java.util.Random;

public class PerlinNoise {
	private long seed;
	private Random random;
	private float offsetX = 0, offsetY = 0;
	private float octives=3, roughness=0.3f, amplitude = 75;
	
	public PerlinNoise() {
		this.random = new Random();
		this.seed = this.random.nextLong();
		this.random.setSeed(this.seed);
	}
	public PerlinNoise(float offsetX, float offsetY, float vertexCount) {
		this.random = new Random();
		this.seed = this.random.nextLong();
		this.random.setSeed(this.seed);
		this.offsetX = offsetX * (vertexCount -1);
		this.offsetY = offsetY * (vertexCount -1);
	}
	public PerlinNoise(long seed) {
		this.seed = seed;
		this.random = new Random(this.seed);
	}
	public PerlinNoise(float offsetX, float offsetY, float vertexCount, long seed) {
		System.out.println(offsetX+" "+offsetY);
		this.seed = seed;
		this.random = new Random(this.seed);
		this.offsetX = offsetX * (vertexCount -1);
		this.offsetY = offsetY * (vertexCount -1);
	}
	
	public float getPerlin(float x, float y) {
		float total = 0;
	    float d = (float) Math.pow(2, octives-1);
	    for(int i=0;i<octives;i++){
	        float freq = (float) (Math.pow(2, i) / d);
	        float amp = (float) Math.pow(roughness, i) * amplitude;
	        total += getInterpolatedNoise((x+offsetX)*freq, (y + offsetY)*freq) * amp;
	    }
	    return total;
	}
	private float getInterpolatedNoise(float x, float z){
        int intX = (int) x;
        int intZ = (int) z;
        float fracX = x - intX;
        float fracZ = z - intZ;
         
        float v1 = getSmoothNoise(intX, intZ);
        float v2 = getSmoothNoise(intX + 1, intZ);
        float v3 = getSmoothNoise(intX, intZ + 1);
        float v4 = getSmoothNoise(intX + 1, intZ + 1);
        float i1 = interpolate(v1, v2, fracX);
        float i2 = interpolate(v3, v4, fracX);
        return interpolate(i1, i2, fracZ);
    }
     
    private float interpolate(float a, float b, float blend){
        double theta = blend * Math.PI;
        float f = (float)(1f - Math.cos(theta)) * 0.5f;
        return a * (1f - f) + b * f;
    }
 
    private float getSmoothNoise(int x, int z) {
        float corners = (getNoise(x - 1, z - 1) + getNoise(x + 1, z - 1) + getNoise(x - 1, z + 1)
                + getNoise(x + 1, z + 1)) / 16f;
        float sides = (getNoise(x - 1, z) + getNoise(x + 1, z) + getNoise(x, z - 1)
                + getNoise(x, z + 1)) / 8f;
        float center = getNoise(x, z) / 4f;
        return corners + sides + center;
    }
 
    private float getNoise(int x, int z) {
        random.setSeed(x * 49632 + z * 325176 + seed);
        return random.nextFloat() * 2f - 1f;
    }
	public long getSeed() {
		return this.seed;
	}
}
