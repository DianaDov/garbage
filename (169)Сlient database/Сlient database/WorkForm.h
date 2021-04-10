#pragma once

namespace Сlientdatabase {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Collections::Generic;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Сводка для WorkForm
	/// </summary>
	public ref class WorkForm : public System::Windows::Forms::Form
	{
	public:
		WorkForm(void)
		{
			InitializeComponent();
			//
			//TODO: добавьте код конструктора
			//
		}

	protected:
		/// <summary>
		/// Освободить все используемые ресурсы.
		/// </summary>
		~WorkForm()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::MenuStrip^ menuStrip1;
	protected:
	private: System::Windows::Forms::ToolStripMenuItem^ менюToolStripMenuItem;

	private: System::Windows::Forms::ToolStripMenuItem^ удалитьToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^ сохранитьToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^ сформироватьЧерныйСписокToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^ сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^ вернутьсяToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^ сортировкаToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^ поФамилииToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^ оПрограммеToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^ выходToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^ поДополнительнымСпискамToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^ поКоличествоТоваровToolStripMenuItem;
	private: System::Windows::Forms::ToolStripMenuItem^ поДатеДобавленияToolStripMenuItem;
	private: System::Windows::Forms::DataGridView^ dataGridView;









	private: System::Windows::Forms::ToolStripMenuItem^ добавитьToolStripMenuItem;
	private: System::Windows::Forms::OpenFileDialog^ openFileDialog;
	private: System::Windows::Forms::DataGridViewTextBoxColumn^ Column1;
	private: System::Windows::Forms::DataGridViewTextBoxColumn^ Column2;
	private: System::Windows::Forms::DataGridViewTextBoxColumn^ Column3;
	private: System::Windows::Forms::DataGridViewTextBoxColumn^ Column4;
	private: System::Windows::Forms::DataGridViewTextBoxColumn^ Column5;
	private: System::Windows::Forms::DataGridViewTextBoxColumn^ Column6;
	private: System::Windows::Forms::DataGridViewTextBoxColumn^ Column7;
	private: System::Windows::Forms::DataGridViewTextBoxColumn^ Column8;
	private: System::Windows::Forms::ToolStripMenuItem^ перейтиКГрафикуToolStripMenuItem;
	private: System::Windows::Forms::GroupBox^ groupBoxDate;
	private: System::Windows::Forms::Button^ buttonInChart;
	private: System::Windows::Forms::MonthCalendar^ monthCalendar;






















	private:
		/// <summary>
		/// Обязательная переменная конструктора.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Требуемый метод для поддержки конструктора — не изменяйте 
		/// содержимое этого метода с помощью редактора кода.
		/// </summary>
		void InitializeComponent(void)
		{
			this->menuStrip1 = (gcnew System::Windows::Forms::MenuStrip());
			this->менюToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->добавитьToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->удалитьToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->сохранитьToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->сформироватьЧерныйСписокToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->сортировкаToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->поФамилииToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->поДополнительнымСпискамToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->поКоличествоТоваровToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->поДатеДобавленияToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->вернутьсяToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->перейтиКГрафикуToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->оПрограммеToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->выходToolStripMenuItem = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->dataGridView = (gcnew System::Windows::Forms::DataGridView());
			this->Column1 = (gcnew System::Windows::Forms::DataGridViewTextBoxColumn());
			this->Column2 = (gcnew System::Windows::Forms::DataGridViewTextBoxColumn());
			this->Column3 = (gcnew System::Windows::Forms::DataGridViewTextBoxColumn());
			this->Column4 = (gcnew System::Windows::Forms::DataGridViewTextBoxColumn());
			this->Column5 = (gcnew System::Windows::Forms::DataGridViewTextBoxColumn());
			this->Column6 = (gcnew System::Windows::Forms::DataGridViewTextBoxColumn());
			this->Column7 = (gcnew System::Windows::Forms::DataGridViewTextBoxColumn());
			this->Column8 = (gcnew System::Windows::Forms::DataGridViewTextBoxColumn());
			this->openFileDialog = (gcnew System::Windows::Forms::OpenFileDialog());
			this->groupBoxDate = (gcnew System::Windows::Forms::GroupBox());
			this->buttonInChart = (gcnew System::Windows::Forms::Button());
			this->monthCalendar = (gcnew System::Windows::Forms::MonthCalendar());
			this->menuStrip1->SuspendLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->dataGridView))->BeginInit();
			this->groupBoxDate->SuspendLayout();
			this->SuspendLayout();
			// 
			// menuStrip1
			// 
			this->menuStrip1->Items->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(3) {
				this->менюToolStripMenuItem,
					this->оПрограммеToolStripMenuItem, this->выходToolStripMenuItem
			});
			this->menuStrip1->Location = System::Drawing::Point(0, 0);
			this->menuStrip1->Name = L"menuStrip1";
			this->menuStrip1->Size = System::Drawing::Size(924, 24);
			this->menuStrip1->TabIndex = 0;
			this->menuStrip1->Text = L"menuStrip1";
			// 
			// менюToolStripMenuItem
			// 
			this->менюToolStripMenuItem->DropDownItems->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(8) {
				this->добавитьToolStripMenuItem,
					this->удалитьToolStripMenuItem, this->сохранитьToolStripMenuItem, this->сформироватьЧерныйСписокToolStripMenuItem, this->сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem,
					this->сортировкаToolStripMenuItem, this->вернутьсяToolStripMenuItem, this->перейтиКГрафикуToolStripMenuItem
			});
			this->менюToolStripMenuItem->Name = L"менюToolStripMenuItem";
			this->менюToolStripMenuItem->Size = System::Drawing::Size(53, 20);
			this->менюToolStripMenuItem->Text = L"Меню";
			// 
			// добавитьToolStripMenuItem
			// 
			this->добавитьToolStripMenuItem->Name = L"добавитьToolStripMenuItem";
			this->добавитьToolStripMenuItem->Size = System::Drawing::Size(326, 22);
			this->добавитьToolStripMenuItem->Text = L"Добавить";
			this->добавитьToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::добавитьToolStripMenuItem_Click_1);
			// 
			// удалитьToolStripMenuItem
			// 
			this->удалитьToolStripMenuItem->Name = L"удалитьToolStripMenuItem";
			this->удалитьToolStripMenuItem->Size = System::Drawing::Size(326, 22);
			this->удалитьToolStripMenuItem->Text = L"Удалить";
			this->удалитьToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::удалитьToolStripMenuItem_Click);
			// 
			// сохранитьToolStripMenuItem
			// 
			this->сохранитьToolStripMenuItem->Name = L"сохранитьToolStripMenuItem";
			this->сохранитьToolStripMenuItem->Size = System::Drawing::Size(326, 22);
			this->сохранитьToolStripMenuItem->Text = L"Сохранить";
			this->сохранитьToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::сохранитьToolStripMenuItem_Click);
			// 
			// сформироватьЧерныйСписокToolStripMenuItem
			// 
			this->сформироватьЧерныйСписокToolStripMenuItem->Name = L"сформироватьЧерныйСписокToolStripMenuItem";
			this->сформироватьЧерныйСписокToolStripMenuItem->Size = System::Drawing::Size(326, 22);
			this->сформироватьЧерныйСписокToolStripMenuItem->Text = L"Сформировать черный список";
			this->сформироватьЧерныйСписокToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::сформироватьЧерныйСписокToolStripMenuItem_Click);
			// 
			// сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem
			// 
			this->сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem->Name = L"сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem";
			this->сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem->Size = System::Drawing::Size(326, 22);
			this->сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem->Text = L"Сформировать список клиентов со скидками";
			this->сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem_Click);
			// 
			// сортировкаToolStripMenuItem
			// 
			this->сортировкаToolStripMenuItem->DropDownItems->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(4) {
				this->поФамилииToolStripMenuItem,
					this->поДополнительнымСпискамToolStripMenuItem, this->поКоличествоТоваровToolStripMenuItem, this->поДатеДобавленияToolStripMenuItem
			});
			this->сортировкаToolStripMenuItem->Name = L"сортировкаToolStripMenuItem";
			this->сортировкаToolStripMenuItem->Size = System::Drawing::Size(326, 22);
			this->сортировкаToolStripMenuItem->Text = L"Сортировка";
			// 
			// поФамилииToolStripMenuItem
			// 
			this->поФамилииToolStripMenuItem->Name = L"поФамилииToolStripMenuItem";
			this->поФамилииToolStripMenuItem->Size = System::Drawing::Size(240, 22);
			this->поФамилииToolStripMenuItem->Text = L"По фамилии";
			this->поФамилииToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::поФамилииToolStripMenuItem_Click);
			// 
			// поДополнительнымСпискамToolStripMenuItem
			// 
			this->поДополнительнымСпискамToolStripMenuItem->Name = L"поДополнительнымСпискамToolStripMenuItem";
			this->поДополнительнымСпискамToolStripMenuItem->Size = System::Drawing::Size(240, 22);
			this->поДополнительнымСпискамToolStripMenuItem->Text = L"По дополнительным спискам";
			this->поДополнительнымСпискамToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::поДополнительнымСпискамToolStripMenuItem_Click);
			// 
			// поКоличествоТоваровToolStripMenuItem
			// 
			this->поКоличествоТоваровToolStripMenuItem->Name = L"поКоличествоТоваровToolStripMenuItem";
			this->поКоличествоТоваровToolStripMenuItem->Size = System::Drawing::Size(240, 22);
			this->поКоличествоТоваровToolStripMenuItem->Text = L"По количество товаров ";
			this->поКоличествоТоваровToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::поКоличествоТоваровToolStripMenuItem_Click);
			// 
			// поДатеДобавленияToolStripMenuItem
			// 
			this->поДатеДобавленияToolStripMenuItem->Name = L"поДатеДобавленияToolStripMenuItem";
			this->поДатеДобавленияToolStripMenuItem->Size = System::Drawing::Size(240, 22);
			this->поДатеДобавленияToolStripMenuItem->Text = L"По дате добавления";
			this->поДатеДобавленияToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::поДатеДобавленияToolStripMenuItem_Click);
			// 
			// вернутьсяToolStripMenuItem
			// 
			this->вернутьсяToolStripMenuItem->Name = L"вернутьсяToolStripMenuItem";
			this->вернутьсяToolStripMenuItem->Size = System::Drawing::Size(326, 22);
			this->вернутьсяToolStripMenuItem->Text = L"Вернуться";
			this->вернутьсяToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::вернутьсяToolStripMenuItem_Click);
			// 
			// перейтиКГрафикуToolStripMenuItem
			// 
			this->перейтиКГрафикуToolStripMenuItem->Name = L"перейтиКГрафикуToolStripMenuItem";
			this->перейтиКГрафикуToolStripMenuItem->Size = System::Drawing::Size(326, 22);
			this->перейтиКГрафикуToolStripMenuItem->Text = L"Перейти к графику";
			this->перейтиКГрафикуToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::перейтиКГрафикуToolStripMenuItem_Click);
			// 
			// оПрограммеToolStripMenuItem
			// 
			this->оПрограммеToolStripMenuItem->Name = L"оПрограммеToolStripMenuItem";
			this->оПрограммеToolStripMenuItem->Size = System::Drawing::Size(94, 20);
			this->оПрограммеToolStripMenuItem->Text = L"О программе";
			this->оПрограммеToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::оПрограммеToolStripMenuItem_Click);
			// 
			// выходToolStripMenuItem
			// 
			this->выходToolStripMenuItem->Name = L"выходToolStripMenuItem";
			this->выходToolStripMenuItem->Size = System::Drawing::Size(54, 20);
			this->выходToolStripMenuItem->Text = L"Выход";
			this->выходToolStripMenuItem->Click += gcnew System::EventHandler(this, &WorkForm::выходToolStripMenuItem_Click);
			// 
			// dataGridView
			// 
			this->dataGridView->ColumnHeadersHeightSizeMode = System::Windows::Forms::DataGridViewColumnHeadersHeightSizeMode::AutoSize;
			this->dataGridView->Columns->AddRange(gcnew cli::array< System::Windows::Forms::DataGridViewColumn^  >(8) {
				this->Column1,
					this->Column2, this->Column3, this->Column4, this->Column5, this->Column6, this->Column7, this->Column8
			});
			this->dataGridView->Dock = System::Windows::Forms::DockStyle::Fill;
			this->dataGridView->Location = System::Drawing::Point(0, 24);
			this->dataGridView->Name = L"dataGridView";
			this->dataGridView->Size = System::Drawing::Size(924, 412);
			this->dataGridView->TabIndex = 1;
			// 
			// Column1
			// 
			this->Column1->HeaderText = L"Фамилия";
			this->Column1->Name = L"Column1";
			// 
			// Column2
			// 
			this->Column2->HeaderText = L"Имя";
			this->Column2->Name = L"Column2";
			// 
			// Column3
			// 
			this->Column3->HeaderText = L"Отчество";
			this->Column3->Name = L"Column3";
			// 
			// Column4
			// 
			this->Column4->HeaderText = L"Дополнительный список";
			this->Column4->Name = L"Column4";
			this->Column4->Width = 200;
			// 
			// Column5
			// 
			this->Column5->HeaderText = L"Серия паспорта ";
			this->Column5->Name = L"Column5";
			this->Column5->Width = 150;
			// 
			// Column6
			// 
			this->Column6->HeaderText = L"Номер паспорта";
			this->Column6->Name = L"Column6";
			this->Column6->Width = 150;
			// 
			// Column7
			// 
			this->Column7->HeaderText = L"Наиболее просматриваемые товары";
			this->Column7->Name = L"Column7";
			this->Column7->Width = 250;
			// 
			// Column8
			// 
			this->Column8->HeaderText = L"Дата добавления";
			this->Column8->Name = L"Column8";
			this->Column8->Width = 150;
			// 
			// openFileDialog
			// 
			this->openFileDialog->FileName = L"openFileDialog";
			// 
			// groupBoxDate
			// 
			this->groupBoxDate->Controls->Add(this->buttonInChart);
			this->groupBoxDate->Controls->Add(this->monthCalendar);
			this->groupBoxDate->Location = System::Drawing::Point(12, 47);
			this->groupBoxDate->Name = L"groupBoxDate";
			this->groupBoxDate->Size = System::Drawing::Size(185, 235);
			this->groupBoxDate->TabIndex = 2;
			this->groupBoxDate->TabStop = false;
			this->groupBoxDate->Text = L"Выберите период ";
			this->groupBoxDate->Visible = false;
			// 
			// buttonInChart
			// 
			this->buttonInChart->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 9.75F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->buttonInChart->Location = System::Drawing::Point(35, 199);
			this->buttonInChart->Name = L"buttonInChart";
			this->buttonInChart->Size = System::Drawing::Size(118, 28);
			this->buttonInChart->TabIndex = 1;
			this->buttonInChart->Text = L"Продолжить";
			this->buttonInChart->UseVisualStyleBackColor = true;
			this->buttonInChart->Click += gcnew System::EventHandler(this, &WorkForm::buttonInChart_Click);
			// 
			// monthCalendar
			// 
			this->monthCalendar->Location = System::Drawing::Point(12, 25);
			this->monthCalendar->Name = L"monthCalendar";
			this->monthCalendar->TabIndex = 0;
			// 
			// WorkForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(924, 436);
			this->Controls->Add(this->groupBoxDate);
			this->Controls->Add(this->dataGridView);
			this->Controls->Add(this->menuStrip1);
			this->MainMenuStrip = this->menuStrip1;
			this->Name = L"WorkForm";
			this->StartPosition = System::Windows::Forms::FormStartPosition::CenterScreen;
			this->Text = L"База данных клиентов";
			this->Load += gcnew System::EventHandler(this, &WorkForm::WorkForm_Load);
			this->menuStrip1->ResumeLayout(false);
			this->menuStrip1->PerformLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->dataGridView))->EndInit();
			this->groupBoxDate->ResumeLayout(false);
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	public: bool load;
	public: String^ filename;

	private: System::Void WorkForm_Load(System::Object^ sender, System::EventArgs^ e);
	private: System::Void добавитьToolStripMenuItem_Click_1(System::Object^ sender, System::EventArgs^ e);
	private: System::Void удалитьToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);
	private: System::Void сохранитьToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);
	private: System::Void сформироватьЧерныйСписокToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);
	private: System::Void сформироватьСписокКлиентовСоСкидкамиToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);
	private: System::Void вернутьсяToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);
	private: System::Void поФамилииToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);
	private: System::Void поДополнительнымСпискамToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);
	private: System::Void поКоличествоТоваровToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);
	private: System::Void поДатеДобавленияToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);
	private: void SetData();
	private: void OutputData();
	private: System::Void выходToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);
	private: System::Void оПрограммеToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);
	private: String^ GetDateString(String^ date);
	private: System::Void перейтиКГрафикуToolStripMenuItem_Click(System::Object^ sender, System::EventArgs^ e);	
	private: System::Void buttonInChart_Click(System::Object^ sender, System::EventArgs^ e);
};
}