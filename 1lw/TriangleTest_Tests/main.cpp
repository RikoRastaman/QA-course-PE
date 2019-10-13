#include <iostream>
#include <array>
#include <stdio.h>
#include <stdlib.h>

int main() {
	/*string cmd = "..\..\TriangleTest\x64\Release\TriangleTest.exe";
	int out1 = system("cmd");
	return out1;*/
	setlocale(LC_ALL, "Russian");

	std::string result;
	std::string cmd = "TriangleTest.exe 3 4 6";
	std::array<char, 256> buffer;
	FILE * _pipe;
	_pipe = _popen(cmd.c_str(), "r");
	
	fgets(buffer.data(), 256, _pipe);
	result += buffer.data();

	std::cout << result << std::endl;
}