#include "Functions.h"

std::string _filename;
Clients _clients;

System::Void �lientdatabase::WorkForm::WorkForm_Load(System::Object^ sender, System::EventArgs^ e)
{
	Convert_String_to_string(filename, _filename);

	if (load) {
		if (_clients.Load(_filename)) {
			MessageBox::Show("������ ������� ���������!","��������!");
			OutputData();
		}
		else {
			MessageBox::Show("������ �������� ������!", "������!");
		}
	}

	//������ �����
	openFileDialog->Filter = "��������� ��������| *.txt";

	dataGridView->TopLeftHeaderCell->Value = "�";
}

System::Void �lientdatabase::WorkForm::��������ToolStripMenuItem_Click_1(System::Object^ sender, System::EventArgs^ e)
{
	SetData();
}

System::Void �lientdatabase::WorkForm::�������ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	//��������� ��������� ������	
	if (dataGridView->SelectedRows->Count != 1) {
		MessageBox::Show("�������� ���� ������ ��� ��������","��������!");
	}

	int index = dataGridView->CurrentRow->Index;

	dataGridView->Rows->RemoveAt(index);
	if (_clients.Del(index))
		MessageBox::Show("������ �������!","��������!");
}

System::Void �lientdatabase::WorkForm::���������ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();

	if (_clients.Save(_filename)) {
		MessageBox::Show("������ ���������!","��������!");
	}
	else {
		MessageBox::Show("������ ���������� ������!", "������!");
	}
}

System::Void �lientdatabase::WorkForm::������������������������ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	openFileDialog->Title = "�������� ���� ��� ������������ ������� ������!";
	if (openFileDialog->ShowDialog() != Windows::Forms::DialogResult::OK)
		return;

	SetData();

	String^ file;
	file = openFileDialog->FileName;

	std::string _file;
	Convert_String_to_string(file, _file);

	if (_clients.CreateBlackList(_file))
		MessageBox::Show("������ ������ �����������!","��������!");
	else
		MessageBox::Show("������ ������������ ������� ������!", "������!");
}

System::Void �lientdatabase::WorkForm::������������������������������������ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	openFileDialog->Title = "�������� ���� ��� ������������ ������� ������!";
	if (openFileDialog->ShowDialog() != Windows::Forms::DialogResult::OK)
		return;

	SetData();

	String^ file;
	file = openFileDialog->FileName;

	std::string _file;
	Convert_String_to_string(file, _file);

	if (_clients.CreateListWithDiscounts(_file))
		MessageBox::Show("������ �������� �� �������� �����������!", "��������!");
	else
		MessageBox::Show("������ ������������ ������ �� ��������!", "������!");
}

System::Void �lientdatabase::WorkForm::���������ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	if (MessageBox::Show("����������?", "��������!", MessageBoxButtons::YesNo) == Windows::Forms::DialogResult::Yes) {
		StartForm^ form = gcnew StartForm();
		form->Show();
		this->Hide();
	}
}

System::Void �lientdatabase::WorkForm::���������ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();
	if (_clients.Sort(1)) {
		MessageBox::Show("������ �������������!","��������!");
		OutputData();
	}
	else {
		MessageBox::Show("������ ����������!", "������!");
	}
}

System::Void �lientdatabase::WorkForm::�����������������������ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();
	if (_clients.Sort(2)) {
		MessageBox::Show("������ �������������!", "��������!");
		OutputData();
	}
	else {
		MessageBox::Show("������ ����������!", "������!");
	}
}

System::Void �lientdatabase::WorkForm::�������������������ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();
	if (_clients.Sort(3)) {
		MessageBox::Show("������ �������������!", "��������!");
		OutputData();
	}
	else {
		MessageBox::Show("������ ����������!", "������!");
	}
}

System::Void �lientdatabase::WorkForm::����������������ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();
	if (_clients.Sort(4)) {
		MessageBox::Show("������ �������������!", "��������!");
		OutputData();
	}
	else {
		MessageBox::Show("������ ����������!", "������!");
	}
}

void �lientdatabase::WorkForm::SetData()
{
	_clients.Clear();
	Client buf;

	for (int i = 0; i < dataGridView->Rows->Count - 1; i++) {
		if (dataGridView->Rows[i]->HeaderCell->Value == nullptr) {
			dataGridView->Rows[i]->HeaderCell->Value = Convert::ToString(i + 1);
			dataGridView->Rows[i]->Cells[7]->Value = GetDateString(DateTime::Now.ToString());
		}

		Convert_String_to_string(dataGridView->Rows[i]->Cells[0]->Value->ToString(), buf.surname);
		Convert_String_to_string(dataGridView->Rows[i]->Cells[1]->Value->ToString(), buf.name);
		Convert_String_to_string(dataGridView->Rows[i]->Cells[2]->Value->ToString(), buf.patronymic);
		
		if (dataGridView->Rows[i]->Cells[3]->Value->ToString() == "�����")
			buf.mode_list = 0;
		else if(dataGridView->Rows[i]->Cells[3]->Value->ToString() == "� ������ ������")
			buf.mode_list = 1;
		else if (dataGridView->Rows[i]->Cells[3]->Value->ToString() == "� ������ �� ��������")
			buf.mode_list = 2;
		else {
			buf.mode_list = 0;
			dataGridView->Rows[i]->Cells[3]->Value == "�����";
		}

		buf.series = Convert::ToInt32(dataGridView->Rows[i]->Cells[4]->Value);
		buf.number = Convert::ToInt32(dataGridView->Rows[i]->Cells[5]->Value);

		if (dataGridView->Rows[i]->Cells[6]->Value != nullptr) {
			Convert_String_to_string(dataGridView->Rows[i]->Cells[6]->Value->ToString(), buf.name_of_the_goods);
		}
		Convert_String_to_string(dataGridView->Rows[i]->Cells[7]->Value->ToString(), buf.date);

		_clients.Add(buf);

		if (dataGridView->Rows[i]->Cells[6]->Value == nullptr) {
			_clients.CreateGoods(i);
			String^ str = Convert_string_to_String(_clients.Get(i).name_of_the_goods, str);
			dataGridView->Rows[i]->Cells[6]->Value = str;
		}
	}
}

void �lientdatabase::WorkForm::OutputData()
{
	dataGridView->Rows->Clear();

	String^ str;
	String^ str1;
	String^ str2;
	String^ str3;
	String^ str4;
	String^ str5;

	for (int i = 0; i < _clients.Size(); i++) {
		str = Convert_string_to_String(_clients.Get(i).surname, str);
		str1 = Convert_string_to_String(_clients.Get(i).name, str1);
		str2 = Convert_string_to_String(_clients.Get(i).patronymic, str2);
		str3 = Convert_string_to_String(_clients.Get(i).name_of_the_goods, str3);
		str4 = Convert_string_to_String(_clients.Get(i).date, str4);

		if (_clients.Get(i).mode_list == 0)
			str5 = "�����";
		else if (_clients.Get(i).mode_list == 1)
			str5 = "� ������ ������";
		else if (_clients.Get(i).mode_list == 2)
			str5 = "� ������ �� ��������";

		dataGridView->Rows->Add(str, str1, str2, str5, _clients.Get(i).series, _clients.Get(i).number, str3, str4);
		dataGridView->Rows[i]->HeaderCell->Value = Convert::ToString(i + 1);
	}
}

System::Void �lientdatabase::WorkForm::�����ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	if (MessageBox::Show("����������?", "��������!", MessageBoxButtons::YesNo) == Windows::Forms::DialogResult::Yes) {
		Application::Exit();
	}
}

System::Void �lientdatabase::WorkForm::����������ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	MessageBox::Show("������ ��������� ������������� ��� ������ ������� ��������!","���������� � ���������!");
}

String^ �lientdatabase::WorkForm::GetDateString(String^ date)
{
	String^ buf = date;
	String^ str = "";

	for (int i = 0; i < buf->Length; i++) {
		if (buf[i] != ' ') {
			str += buf[i];
		}
		else {
			break;
		}
	}

	return str;
}

System::Void �lientdatabase::WorkForm::���������������ToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	if (groupBoxDate->Visible) {
		groupBoxDate->Visible = false;
	}
	else {
		groupBoxDate->Visible = true;
	}
}

System::Void �lientdatabase::WorkForm::buttonInChart_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();

	//�������� ���������� ����
	List<String^> dates;

	String^ buf = GetDateString(monthCalendar->SelectionStart.ToString());
	Date date = ParseDate(buf);
	int days = (monthCalendar->SelectionRange->End - monthCalendar->SelectionRange->Start).Days + 1;
	
	int* counts = new int[days];
	for (int i = 0; i < days; i++) {
		counts[i] = 0;
	}

	//�������� �� ��� ��������� �����
	buf = date.day.ToString() + "." + date.month.ToString() + "." + date.year.ToString();
	dates.Add(buf);

	for (int i = 0; i < days - 1; i++) {
		if (date.month == 1) {
			if (date.day <= 31)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else if (date.month == 2) {
			if (date.day <= 28)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else if (date.month == 3) {
			if (date.day <= 31)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else if (date.month == 4) {
			if (date.day <= 30)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else if (date.month == 5) {
			if (date.day <= 31)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else if (date.month == 6) {
			if (date.day <= 30)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else if (date.month == 7) {
			if (date.day <= 31)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else if (date.month == 8) {
			if (date.day <= 31)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else if (date.month == 9) {
			if (date.day <= 30)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else if (date.month == 10) {
			if (date.day <= 31)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else if (date.month == 11) {
			if (date.day <= 30)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else if (date.month == 12) {
			if (date.day <= 30)
				date.day++;
			else {
				date.month++;
				date.day = 1;
			}
		}
		else {
			date.year++;
			date.month = 1;
			date.day = 1;
		}

		buf = date.day.ToString() + "." + date.month.ToString() + "." + date.year.ToString();
		dates.Add(buf);
	}

	//��������� ������� �������� � ����� ����
	for (int i = 0; i < dataGridView->Rows->Count - 1; i++) {
		for (int j = 0; j < days; j++) {
			if (dataGridView->Rows[i]->Cells[7]->Value->ToString() == dates[j])
				counts[j]++;
		}
	}
	
	ChartForm^ form = gcnew ChartForm();

	//���������� �������� ����� � ����� �������
	for (int i = 0; i < dates.Count; i++)
		form->AddPointString(dates[i]);

	for (int j = 0; j < days; j++)
		form->AddPointInt(counts[j]);

	//���������� ��������� �����
	form->Show();

	//�������� ���������
	groupBoxDate->Visible = false;
}
