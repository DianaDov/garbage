#include "ChartForm.h"

void Сlientdatabase::ChartForm::AddPointString(String^ s)
{
	pointsString.Add(s);
}

void Сlientdatabase::ChartForm::AddPointInt(int s)
{
	pointsInt.Add(s);
}

System::Void Сlientdatabase::ChartForm::ChartForm_Load(System::Object^ sender, System::EventArgs^ e)
{
	//Общие свойства
	Chart^ myChart = gcnew Chart();
	myChart->Parent = this;
	myChart->Dock = Windows::Forms::DockStyle::Fill;
	myChart->Left = 10; myChart->Top = 10;
	myChart->Width = (this->ClientSize.Width - 10);
	myChart->Height = (this->ClientSize.Height - 20);
	myChart->BringToFront(); //или chart1->Visible = false;
	ChartArea^ myChartArea = gcnew ChartArea();
	
	//Область в которой будет построен график (их может быть несколько)
	myChartArea->Name = "myChartArea";
	myChart->ChartAreas->Add(myChartArea);
	
	//График (их может быть несколько)
	Series^ mySeries1 = gcnew Series();
	mySeries1->ChartType = SeriesChartType::Column;
	mySeries1->ChartArea = "myChartArea";
	myChart->Series->Add(mySeries1);
	
	//Исходные данные для графика
	array<int>^ y_values = gcnew array<int>(pointsInt.Count);	
	for (int i = 0; i < pointsInt.Count; i++) {
		y_values[i] = pointsInt[i];
	}

	array <System::String^>^ x_values = gcnew array<String^>(pointsString.Count);
	for (int i = 0; i < pointsString.Count; i++) {
		x_values[i] = pointsString[i];
	}

	//Задаем точки на графике
	mySeries1->Points->DataBindXY(x_values, y_values);
	
	//фон градиентом
	myChart->BackColor = System::Drawing::Color::MistyRose;
	myChart->BackGradientStyle = GradientStyle::DiagonalLeft;
	
	//границы в современном стиле
	myChart->BorderSkin->SkinStyle = BorderSkinStyle::Sunken;
	myChart->BorderSkin->PageColor = this->BackColor;
	
	//линии сетки покажем разными цветами
	myChartArea->AxisX->MajorGrid->LineColor = System::Drawing::SystemColors::ControlDark;
	myChartArea->AxisY->MajorGrid->LineColor = System::Drawing::SystemColors::ControlLight;
}
