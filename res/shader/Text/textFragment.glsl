#version 400 core
in vec2 ptc;

uniform sampler2D image;
uniform vec4 colour;

//Distance Field
uniform float isDF;
const float smoothing = 1.0/128.0;

out vec4 out_colour;

void main(){
	vec4 img = texture(image, ptc);
	if(img.a < 0.5){
		discard;
	}
	if(isDF == 0){
		out_colour =vec4(colour.rgb, mix(colour.a, img.a, 1));
	}else{
		float distance = img.a;
		float alpha = smoothstep(0.5-smoothing, 0.5+smoothing, distance);
		out_colour = vec4(colour.rgb, colour.a*alpha);
	}
}
