#version 400 core

in vec2 ptc;

uniform vec4 overrideColour;
uniform float isOverideColour;
uniform sampler2D img;

out vec4 colour;

void main(){
	vec4 textureColour = texture(img, ptc);
	
	colour = mix(overrideColour, textureColour, 1);
	
	if(textureColour.a < 0.2 && isOverideColour == 0){
		discard;
	}
	
	if(isOverideColour > 0){
		colour = overrideColour;
	}
}
