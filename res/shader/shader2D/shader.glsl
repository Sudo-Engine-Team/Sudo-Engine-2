#shader vertex
#version 400 core

in vec3 pos;

uniform mat4 proj;
uniform mat4 trans;
uniform mat4 view;

void main(){
	gl_Position = view*proj*trans*vec4(pos, 1);
}

#shader fragment
#version 400 core

uniform vec4 colour;

out vec4 out_colour;

void main(){
	out_colour = colour;
}
#shader end