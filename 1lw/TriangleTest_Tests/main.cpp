#include <iostream>
#include <fstream>
#include <array>
#include <stdio.h>
#include "windows.h"
#include <string>

std::string getOutput(std::string& arguments) {
	std::string result;
	std::string cmd = "TriangleTest.exe " + arguments;
	std::array<char, 256> buffer;
	FILE* _pipe;
	_pipe = _popen(cmd.c_str(), "r");

	fgets(buffer.data(), 256, _pipe);
	result += buffer.data();
	return result;
}

int main(int argc, char* argv[]) {
	setlocale(LC_ALL, "Russian");
	std::string expectedOutput = "";
	std::string arguments = "";
	int counter = 0;
	if (argc != 2) {
		std::cout << "Enter test file name!";
		return 0;
	}

	std::fstream input(argv[1]);
	std::ofstream output;
	while (getline(input, arguments)) {
		counter++;
		getline(input, expectedOutput);
		std::cout << "Test" << counter << ": " << getOutput(arguments) + " = " + expectedOutput << std::endl;
		if (expectedOutput == getOutput(arguments)) {
			std::cout << "Passed" << std::endl;
		}
		else {
			std::cout << "Failed" << std::endl;
		}

	}
}