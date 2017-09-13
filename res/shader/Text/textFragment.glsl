#version 400 core
in vec2 ptc;
uniform sampler2D image;
uniform vec4 colour;
out vec4 out_colour;

void main(){
	vec4 imageColour = texture(image, ptc);
	if(imageColour.a == 0){
		discard;
	}
	out_colour = vec4(colour.rgb, mix(colour.a, imageColour.a,1));
	//out_colour = texture(image, pt);
}
