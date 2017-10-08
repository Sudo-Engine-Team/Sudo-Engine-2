#version 400 core

in vec2 ptc;
in vec3 pn;

uniform sampler2D img;
uniform float forceColour;
uniform vec4 fcolour;
out vec4 colour;

void main(){
	colour = texture(img, ptc);
	if(forceColour > 0){
		colour = fcolour;
	}
}
