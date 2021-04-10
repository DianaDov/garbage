#include "Functions.h"

std::string _filename;
Clients _clients;

System::Void Сlientdatabase::WorkForm::WorkForm_Load(System::Object^ sender, System::EventArgs^ e)
{
	Convert_String_to_string(filename, _filename);

	if (load) {
		if (_clients.Load(_filename)) {
			MessageBox::Show("Данные успешно загружены!","Внимание!");
			OutputData();
		}
		else {
			MessageBox::Show("Ошибка загрузки данных!", "Ошибка!");
		}
	}

	//Задаем фильт
	openFileDialog->Filter = "Текстовый документ| *.txt";

	dataGridView->TopLeftHeaderCell->Value = "№";
}

System::Void Сlientdatabase::WorkForm::добавитьToolStripMenuItem_Click_1(System::Object^ sender, System::EventArgs^ e)
{
	SetData();
}

System::Void Сlientdatabase::WorkForm::удалитьToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	//Проверяем выбранную строку	
	if (dataGridView->SelectedRows->Count != 1) {
		MessageBox::Show("Выберите одну строку для удаления","Внимание!");
	}

	int index = dataGridView->CurrentRow->Index;

	dataGridView->Rows->RemoveAt(index);
	if (_clients.Del(index))
		MessageBox::Show("Данные удалены!","Внимание!");
}

System::Void Сlientdatabase::WorkForm::сохранитьToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();

	if (_clients.Save(_filename)) {
		MessageBox::Show("Данные сохранены!","Внимание!");
	}
	else {
		MessageBox::Show("Ошибка сохранения данных!", "Ошибка!");
	}
}

System::Void Сlientdatabase::WorkForm::сформироватьЧерныйСписокToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	openFileDialog->Title = "Выберите файл для формирования черного списка!";
	if (openFileDialog->ShowDialog() != Windows::Forms::DialogResult::OK)
		return;

	SetData();

	String^ file;
	file = openFileDialog->FileName;

	std::string _file;
	Convert_String_to_string(file, _file);

	if (_clients.CreateBlackList(_file))
		MessageBox::Show("Черный список сформирован!","Внимание!");
	else
		MessageBox::Show("Ошибка формирования черного списка!", "Ошибка!");
}

System::Void Сlientdatabase::WorkForm::сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	openFileDialog->Title = "Выберите файл для формирования черного списка!";
	if (openFileDialog->ShowDialog() != Windows::Forms::DialogResult::OK)
		return;

	SetData();

	String^ file;
	file = openFileDialog->FileName;

	std::string _file;
	Convert_String_to_string(file, _file);

	if (_clients.CreateListWithDiscounts(_file))
		MessageBox::Show("Список клиентов со скидками сформирован!", "Внимание!");
	else
		MessageBox::Show("Ошибка формирования списка со скидками!", "Ошибка!");
}

System::Void Сlientdatabase::WorkForm::вернутьсяToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	if (MessageBox::Show("Продолжить?", "Внимание!", MessageBoxButtons::YesNo) == Windows::Forms::DialogResult::Yes) {
		StartForm^ form = gcnew StartForm();
		form->Show();
		this->Hide();
	}
}

System::Void Сlientdatabase::WorkForm::поФамилииToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();
	if (_clients.Sort(1)) {
		MessageBox::Show("Данные отсортированы!","Внимание!");
		OutputData();
	}
	else {
		MessageBox::Show("Ошибка сортировки!", "Ошибка!");
	}
}

System::Void Сlientdatabase::WorkForm::поДополнительнымСпискамToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();
	if (_clients.Sort(2)) {
		MessageBox::Show("Данные отсортированы!", "Внимание!");
		OutputData();
	}
	else {
		MessageBox::Show("Ошибка сортировки!", "Ошибка!");
	}
}

System::Void Сlientdatabase::WorkForm::поКоличествоТоваровToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();
	if (_clients.Sort(3)) {
		MessageBox::Show("Данные отсортированы!", "Внимание!");
		OutputData();
	}
	else {
		MessageBox::Show("Ошибка сортировки!", "Ошибка!");
	}
}

System::Void Сlientdatabase::WorkForm::поДатеДобавленияToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();
	if (_clients.Sort(4)) {
		MessageBox::Show("Данные отсортированы!", "Внимание!");
		OutputData();
	}
	else {
		MessageBox::Show("Ошибка сортировки!", "Ошибка!");
	}
}

void Сlientdatabase::WorkForm::SetData()
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
		
		if (dataGridView->Rows[i]->Cells[3]->Value->ToString() == "пусто")
			buf.mode_list = 0;
		else if(dataGridView->Rows[i]->Cells[3]->Value->ToString() == "в черном списке")
			buf.mode_list = 1;
		else if (dataGridView->Rows[i]->Cells[3]->Value->ToString() == "в списке со скидками")
			buf.mode_list = 2;
		else {
			buf.mode_list = 0;
			dataGridView->Rows[i]->Cells[3]->Value == "пусто";
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

void Сlientdatabase::WorkForm::OutputData()
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
			str5 = "пусто";
		else if (_clients.Get(i).mode_list == 1)
			str5 = "в черном списке";
		else if (_clients.Get(i).mode_list == 2)
			str5 = "в списке со скидками";

		dataGridView->Rows->Add(str, str1, str2, str5, _clients.Get(i).series, _clients.Get(i).number, str3, str4);
		dataGridView->Rows[i]->HeaderCell->Value = Convert::ToString(i + 1);
	}
}

System::Void Сlientdatabase::WorkForm::выходToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	if (MessageBox::Show("Продолжить?", "Внимание!", MessageBoxButtons::YesNo) == Windows::Forms::DialogResult::Yes) {
		Application::Exit();
	}
}

System::Void Сlientdatabase::WorkForm::оПрограммеToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	MessageBox::Show("Данная программа предназначена для работы данными клиентов!","Информация о программе!");
}

String^ Сlientdatabase::WorkForm::GetDateString(String^ date)
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

System::Void Сlientdatabase::WorkForm::перейтиКГрафикуToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e)
{
	if (groupBoxDate->Visible) {
		groupBoxDate->Visible = false;
	}
	else {
		groupBoxDate->Visible = true;
	}
}

System::Void Сlientdatabase::WorkForm::buttonInChart_Click(System::Object^ sender, System::EventArgs^ e)
{
	SetData();

	//Получаем выделенные даты
	List<String^> dates;

	String^ buf = GetDateString(monthCalendar->SelectionStart.ToString());
	Date date = ParseDate(buf);
	int days = (monthCalendar->SelectionRange->End - monthCalendar->SelectionRange->Start).Days + 1;
	
	int* counts = new int[days];
	for (int i = 0; i < days; i++) {
		counts[i] = 0;
	}

	//Проходим по все выбранным датам
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

	//Проверяем сколько добавили в какой день
	for (int i = 0; i < dataGridView->Rows->Count - 1; i++) {
		for (int j = 0; j < days; j++) {
			if (dataGridView->Rows[i]->Cells[7]->Value->ToString() == dates[j])
				counts[j]++;
		}
	}
	
	ChartForm^ form = gcnew ChartForm();

	//Отправляем ключевые точки в форму графика
	for (int i = 0; i < dates.Count; i++)
		form->AddPointString(dates[i]);

	for (int j = 0; j < days; j++)
		form->AddPointInt(counts[j]);

	//Показываем созданную форму
	form->Show();

	//Скрываем календарь
	groupBoxDate->Visible = false;
}
