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

/*	Прототипы функций	*/
std::string& Convert_String_to_string(String^ s, std::string& os);//конвертируем System::string ^ в std::string
String^ Convert_string_to_String(std::string& os, String^ s);//конвертируем std::string в System::string ^ 
Date ParseDate(String^);
