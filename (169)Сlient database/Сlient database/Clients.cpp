#include "Clients.h"

Clients::Clients()
{
}

Clients::~Clients()
{
}

bool Clients::Load(std::string filename)
{
	//Создаем поток для чтения
	std::ifstream load(filename);

	//Проверяем открылся ли файл
	if(!load)
		return false;

	//Очищаем данные, если они были
	data.clear();

	//Временные данные
	Client buf;
	int num;
	std::string name;

	//Пока не конец файла
	while (!load.eof()) {
		load >> buf.surname;
		load >> buf.name;
		load >> buf.patronymic;
		load >> buf.mode_list;
		load >> buf.series;
		load >> buf.number;
		load >> buf.name_of_the_goods;
		load >> buf.date;

		//Добавляем данные
		Add(buf);
	}

	load.close();
	return true;
}

bool Clients::Save(std::string filename)
{
	//Создаем поток для перезаписи в файл
	std::ofstream save(filename, std::ios::out);

	for (int i = 0; i < data.size(); i++) {
		save << data[i].surname << std::endl;
		save << data[i].name << std::endl;
		save << data[i].patronymic << std::endl;
		save << data[i].mode_list << std::endl;
		save << data[i].series << std::endl;
		save << data[i].number << std::endl;
		save << data[i].name_of_the_goods << std::endl;
		save << data[i].date;
		if (i < data.size() - 1)
			save << std::endl;
	}

	save.close();
	return true;
}

void Clients::Add(Client c)
{
	data.push_back(c);
}

bool Clients::Del(int i)
{
	//Проверка индекса
	if(i < 0 || i>data.size()-1)
		return false;

	data.erase(data.begin() + i);
	return true;
}

bool Clients::Set(int i, Client c)
{
	//Проверка индекса
	if (i < 0 || i>data.size() - 1)
		return false;

	data[i] = c;
	return true;
}

Client Clients::Get(int i)
{
	//Проверка индекса
	if (i < 0 || i>data.size() - 1)
		return Client();

	return data[i];
}

bool Clients::CreateBlackList(std::string filename)
{
	//Создаем поток для перезаписи в файл
	std::ofstream save(filename, std::ios::out);

	save << "     *Черный список*" << std::endl;

	for (int i = 0; i < data.size(); i++) {
		if (data[i].mode_list == 1) {
			save << "Фамилия: " << data[i].surname << std::endl;
			save << "Имя: " << data[i].name << std::endl;
			save << "Отчество: " << data[i].patronymic << std::endl;
			save << "------------------------------" << std::endl;
		}
	}

	save.close();
	return true;
}

bool Clients::CreateListWithDiscounts(std::string filename)
{
	//Создаем поток для перезаписи в файл
	std::ofstream save(filename, std::ios::out);

	save << "     *Список клиентов с скидками*" << std::endl;

	for (int i = 0; i < data.size(); i++) {
		if (data[i].mode_list == 2) {
			save << "Фамилия: " << data[i].surname << std::endl;
			save << "Имя: " << data[i].name << std::endl;
			save << "Отчество: " << data[i].patronymic << std::endl;
			save << "------------------------------" << std::endl;
		}
	}

	save.close();
	return true;
}

bool Clients::CreateGoods(int i)
{
	//Проверка индекса
	if (i < 0 || i>data.size() - 1)
		return false;

	//Создаем поток для чтения
	std::ifstream load("Goods.txt");

	//Проверяем открылся ли файл
	if (!load)
		return false;

	std::string name;
	int num;
	std::vector<std::string> goods;

	//Считываем все товары из файла
	while (!load.eof()) {
		load >> name;
		goods.push_back(name);
	}

	//Перемешиваем массив
	std::random_shuffle(goods.begin(), goods.end());

	//Случайно получаем количество товаров
	srand(time(NULL));
	num = 1 + rand() % goods.size();

	data[i].name_of_the_goods = "";
	for (int q = 0; q < goods.size(); q++) {
		data[i].name_of_the_goods += goods[q] + ";";
	}

	load.close();
	return true;
}

bool Clients::Sort(int mode)
{
	//Проверяем выбранное поле для сортировки 
	switch (mode)
	{
	case 1://Фамилия
		for (int i = 0; i < data.size() - 1; i++) {
			for (int j = i + 1; j < data.size(); j++) {
				//Условие сортировки 
				if (data[i].surname > data[j].surname) {
					//Меняем местами
					Swap(i, j);
				}
			}
		}
		break;

	case 2://По дополнительным спискам
		for (int i = 0; i < data.size() - 1; i++) {
			for (int j = i + 1; j < data.size(); j++) {
				//Условие сортировки 
				if (data[i].mode_list > data[j].mode_list) {
					//Меняем местами
					Swap(i, j);
				}
			}
		}
		break;

	case 3://По количество товаров 
		for (int i = 0; i < data.size() - 1; i++) {
			for (int j = i + 1; j < data.size(); j++) {
				//Условие сортировки 
				if (data[i].name_of_the_goods.size() > data[j].name_of_the_goods.size()) {
					//Меняем местами
					Swap(i, j);
				}
			}
		}
		break;

	case 4://По дате добавления
		for (int i = 0; i < data.size() - 1; i++) {
			for (int j = i + 1; j < data.size(); j++) {
				//Условие сортировки 
				if (data[i].date > data[j].date) {
					//Меняем местами
					Swap(i, j);
				}
			}
		}
		break;

	default:
		return false;
	}

	return true;
}

void Clients::Swap(int i, int j)
{
	Client buf;
	buf = data[i];
	data[i] = data[j];
	data[j] = buf;
}
