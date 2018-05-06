#shader vertex
#version 400 core

in vec3 pos;
in vec2 tc;

uniform mat4 proj;

out vec2 out_tc;

void main(){
	gl_Position = proj*vec4(pos, 1);
	out_tc = tc;
}
#shader fragment
#version 400 core

in vec2 tc;

out vec4 out_colour;

void main(){
	out_colour = vec4(1);
}

#shader end