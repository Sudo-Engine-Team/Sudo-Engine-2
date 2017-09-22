#version 400 core

in vec2 ptc;

uniform vec4 overrideColour;
uniform float useImage;
uniform sampler2D img;

out vec4 colour;

void main(){
	vec4 textureColour = texture(img, ptc);
	if(textureColour.a < 0.2){
		discard;
	}
	if(useImage == 0){
		colour = overrideColour;
	}else{
		colour = mix(overrideColour, textureColour, 1);
	}
	//colour = vec4(1,1,1,1);
}
