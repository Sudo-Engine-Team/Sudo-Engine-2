#version 140

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D guiTexture;
uniform vec4 quadColour;
uniform float isColouredQuad;

void main(void){
	if(isColouredQuad == 0.0){
		out_Color = texture(guiTexture,textureCoords);
	}else{
		out_Color = quadColour;
	}
}
