#version 400 core
in vec2 ptc;
uniform sampler2D image;
out vec4 out_colour;

void main(){
	out_colour = texture(image, ptc*vec2(1,1));
	//out_colour = texture(image, pt);
}
