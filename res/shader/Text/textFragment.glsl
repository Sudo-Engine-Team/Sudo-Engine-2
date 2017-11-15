#version 400 core
in vec2 ptc;
uniform sampler2D image;
uniform vec4 colour;
//uniform float idDF;
//uniform float edge;
//uniform float width;
//uniform float borderWidth;
//unifrom float borderEdge;
//uniform vec4 outlineColour;
out vec4 out_colour;

const float isDF = 1;
const float width = 0.5; //How big the letter should be
const float edge = 0.1; // width+edge then Smoothed
const vec4 outlineColour = vec4(1,1,1,0);
const float borderWidth = 1;
const float borderEdge = 0.5;

void main(){
	vec4 img = texture(image, ptc);
	if(img.a < 0.5){
		discard;
	}
	if(isDF == 0){
		out_colour =vec4(colour.rgb, mix(colour.a, img.a, 1));
	}else{
		float distance = 1.0 - img.a;
		float alpha = 1.0-smoothstep(width, width+edge, distance);
		out_colour = vec4(colour.rgb, alpha);
	}
}
