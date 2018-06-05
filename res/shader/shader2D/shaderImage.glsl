#shader vertex
#version 400 core

in vec3 pos;
in vec2 tc;

uniform mat4 proj;
uniform mat4 imageTrans;
uniform mat4 trans;
uniform mat4 view;

out vec2 out_tc;

void main(){
	gl_Position = view*proj*trans*vec4(pos, 1);
	out_tc = (imageTrans*vec4(tc, 0, 1)).xy;
}
#shader fragment
#version 400 core

in vec2 out_tc;

uniform sampler2D sampler;

out vec4 out_colour;

void main(){
	out_colour = texture(sampler, out_tc);
}

#shader end
