#include "Functions.h"

/*	Реализация функций	*/
//Конвертируем System::string ^ в std::string
std::string& Convert_String_to_string(System::String^ s, std::string& os) {
	using namespace Runtime::InteropServices;
	const char* chars =
		(const char*)(Marshal::StringToHGlobalAnsi(s)).ToPointer();
	os = chars;
	Marshal::FreeHGlobal(IntPtr((void*)chars));

	return os;
}

//Конвертируем std::string в System::string ^ 
System::String^ Convert_string_to_String(std::string& os, System::String^ s) {
	s = gcnew System::String(os.c_str()); // конвертируем

	return s;
}

Date ParseDate(String^ str)
{
	Date date;
	String^ buf = "";
	int rank = 1;

	for (int i = 0; i < str->Length; i++) {
		if (str[i] != '.') {
			buf += str[i];
		}
		else {
			if(rank == 1)
				date.day = Convert::ToInt32(buf);
			else 
				date.month = Convert::ToInt32(buf);

			buf = "";
			rank++;
		}
	}

	date.year = Convert::ToInt32(buf);

	return date;
}
