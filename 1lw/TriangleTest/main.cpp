#include "stdafx.h"
#include <string>
#include <io.h>
#include <fcntl.h>
#include <sstream>
#include <limits>

using namespace std;

void LoadArgumentsToStringStream(stringstream & stringStream, char *argv[], int argCount)
{
	for (int i = 1; i < argCount; ++i)
	{
		stringStream << string(argv[i]) << " ";
	}
}

bool IsValidArgument(const string & numberStr)
{
	bool dotProcessed = false;
	if (numberStr.length() == 0)
	{
		return false;
	}
	for (char ch : numberStr)
	{
		if (ch < '0' || ch > '9')
		{
			if (ch == '.' && !dotProcessed)
			{
				dotProcessed = true;
				continue;
			}
			return false;
		}
	}
	return true;
}

bool AreValidArguments(char *argv[], int argCount)
{
	for (int i = 1; i < argCount; ++i)
	{
		if (!IsValidArgument(argv[i]))
		{
			return false;
		}
	}
	return true;
}

int main(int argc, char *argv[])
{
	setlocale(LC_ALL, "Russian");

	if (argc != 4)
	{
		cout << "Укажите длины сторон в качестве параметров. Формат ввода: triangle.exe a b c";
		return 1;
	}
	if (!AreValidArguments(argv, 4))
	{
		cout << "Длины треугольника должны быть числами. Для разделения целой части числа от дробной используйте точку";
		return 1;
	}
	stringstream stringStream;
	LoadArgumentsToStringStream(stringStream, argv, 4);
	double a = 0;
	double b = 0;
	double c = 0;
	if (!(stringStream >> a >> b >> c))
	{
		cout << "Тип данных для хранения длины треугольника не может превышать " + to_string(numeric_limits<double>::max());
		return 1;
	}
	if (a < 0 || b < 0 || c < 0)
	{
		cout << "Длины треугольника должны быть числами. Для разделения целой части числа от дробной используйте точку";
		return 1;
	}
	if (a == 0 || b == 0 || c == 0 || (a + b) <= c || (a + c) <= b || (b + c) <= a)
	{
		cout << "Не треугольник";
		return 0;
	}
	if (a != b && b != c && a != c)
	{
		cout << "Обычный";
		return 0;
	}
	if (a == b && b == c && a == c)
	{
		cout << "Равносторонний";
		return 0;
	}
	if (a == b || b == c || a == c)
	{
		cout << "Равнобедренный";
		return 0;
	}
	return 0;
}
