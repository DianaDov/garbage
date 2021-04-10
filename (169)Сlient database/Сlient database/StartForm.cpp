#include "Functions.h"

using namespace System;
using namespace System::Windows::Forms;

[STAThreadAttribute]
void main(array<String^>^ args) {
	Application::EnableVisualStyles();
	Application::SetCompatibleTextRenderingDefault(false);

	Ñlientdatabase::StartForm form;
	Application::Run(% form);
}

Ñlientdatabase::StartForm::StartForm(void)
{
	InitializeComponent();

	//Çàäàåì ôèëüò
	openFileDialog->Filter = "Òåêñòîâûé äîêóìåíò| *.txt";
}

System::Void Ñlientdatabase::StartForm::buttonOpen_Click(System::Object^ sender, System::EventArgs^ e)
{
	openFileDialog->Title = "Îòêðûòü ôàéë!";
	if (openFileDialog->ShowDialog() != Windows::Forms::DialogResult::OK)
		return;

	String^ filename = openFileDialog->FileName;
	
	//Ñîçäàåì ôîðìó
	WorkForm^ form = gcnew WorkForm();
	form->filename = filename;
	form->load = true;
	form->Show();
	this->Hide();
}

System::Void Ñlientdatabase::StartForm::buttonCreate_Click(System::Object^ sender, System::EventArgs^ e)
{
	openFileDialog->Title = "Âûáðàòü ïóñòîé ôàéë!";
	if (openFileDialog->ShowDialog() != Windows::Forms::DialogResult::OK)
		return;

	String^ filename = openFileDialog->FileName;

	//Ñîçäàåì ôîðìó
	WorkForm^ form = gcnew WorkForm();
	form->filename = filename;
	form->load = false;
	form->Show();
	this->Hide();
}
