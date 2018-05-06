#shader vertex
#version 400 core

in vec3 pos;

uniform mat4 proj;
uniform mat4 trans;

void main(){
	gl_Position = trans*proj*vec4(pos, 1);
}

#shader fragment
#version 400 core

uniform vec4 colour;

out vec4 out_colour;

void main(){
	out_colour = colour;
}
#shader end