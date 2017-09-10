#version 400 core
in vec2 pass_texture;
uniform sampler2D fontAtlas;
out vec4 out_colour;

void main(){
	vec3 colour = vec3(255,255,255);
	//out_colour = vec4(colour, texture(fontAtlas, pass_textureCoords).a);
	out_colour = vec4(texture(fontAtlas, pass_texture).xyz,1);
}
