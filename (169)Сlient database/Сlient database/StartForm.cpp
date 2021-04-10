#include "Functions.h"

using namespace System;
using namespace System::Windows::Forms;

[STAThreadAttribute]
void main(array<String^>^ args) {
	Application::EnableVisualStyles();
	Application::SetCompatibleTextRenderingDefault(false);

	�lientdatabase::StartForm form;
	Application::Run(% form);
}

�lientdatabase::StartForm::StartForm(void)
{
	InitializeComponent();

	//������ �����
	openFileDialog->Filter = "��������� ��������| *.txt";
}

System::Void �lientdatabase::StartForm::buttonOpen_Click(System::Object^ sender, System::EventArgs^ e)
{
	openFileDialog->Title = "������� ����!";
	if (openFileDialog->ShowDialog() != Windows::Forms::DialogResult::OK)
		return;

	String^ filename = openFileDialog->FileName;
	
	//������� �����
	WorkForm^ form = gcnew WorkForm();
	form->filename = filename;
	form->load = true;
	form->Show();
	this->Hide();
}

System::Void �lientdatabase::StartForm::buttonCreate_Click(System::Object^ sender, System::EventArgs^ e)
{
	openFileDialog->Title = "������� ������ ����!";
	if (openFileDialog->ShowDialog() != Windows::Forms::DialogResult::OK)
		return;

	String^ filename = openFileDialog->FileName;

	//������� �����
	WorkForm^ form = gcnew WorkForm();
	form->filename = filename;
	form->load = false;
	form->Show();
	this->Hide();
}
