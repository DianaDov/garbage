#pragma once

#include "Clients.h"
#include "StartForm.h"
#include "WorkForm.h"
#include "ChartForm.h"

using namespace System;

struct Date {
	int day,
		month,
		year;
};

/*	��������� �������	*/
std::string& Convert_String_to_string(String^ s, std::string& os);//������������ System::string ^ � std::string
String^ Convert_string_to_String(std::string& os, String^ s);//������������ std::string � System::string ^ 
Date ParseDate(String^);
