#include "Clients.h"

Clients::Clients()
{
}

Clients::~Clients()
{
}

bool Clients::Load(std::string filename)
{
	//������� ����� ��� ������
	std::ifstream load(filename);

	//��������� �������� �� ����
	if(!load)
		return false;

	//������� ������, ���� ��� ����
	data.clear();

	//��������� ������
	Client buf;
	int num;
	std::string name;

	//���� �� ����� �����
	while (!load.eof()) {
		load >> buf.surname;
		load >> buf.name;
		load >> buf.patronymic;
		load >> buf.mode_list;
		load >> buf.series;
		load >> buf.number;
		load >> buf.name_of_the_goods;
		load >> buf.date;

		//��������� ������
		Add(buf);
	}

	load.close();
	return true;
}

bool Clients::Save(std::string filename)
{
	//������� ����� ��� ���������� � ����
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
	//�������� �������
	if(i < 0 || i>data.size()-1)
		return false;

	data.erase(data.begin() + i);
	return true;
}

bool Clients::Set(int i, Client c)
{
	//�������� �������
	if (i < 0 || i>data.size() - 1)
		return false;

	data[i] = c;
	return true;
}

Client Clients::Get(int i)
{
	//�������� �������
	if (i < 0 || i>data.size() - 1)
		return Client();

	return data[i];
}

bool Clients::CreateBlackList(std::string filename)
{
	//������� ����� ��� ���������� � ����
	std::ofstream save(filename, std::ios::out);

	save << "     *������ ������*" << std::endl;

	for (int i = 0; i < data.size(); i++) {
		if (data[i].mode_list == 1) {
			save << "�������: " << data[i].surname << std::endl;
			save << "���: " << data[i].name << std::endl;
			save << "��������: " << data[i].patronymic << std::endl;
			save << "------------------------------" << std::endl;
		}
	}

	save.close();
	return true;
}

bool Clients::CreateListWithDiscounts(std::string filename)
{
	//������� ����� ��� ���������� � ����
	std::ofstream save(filename, std::ios::out);

	save << "     *������ �������� � ��������*" << std::endl;

	for (int i = 0; i < data.size(); i++) {
		if (data[i].mode_list == 2) {
			save << "�������: " << data[i].surname << std::endl;
			save << "���: " << data[i].name << std::endl;
			save << "��������: " << data[i].patronymic << std::endl;
			save << "------------------------------" << std::endl;
		}
	}

	save.close();
	return true;
}

bool Clients::CreateGoods(int i)
{
	//�������� �������
	if (i < 0 || i>data.size() - 1)
		return false;

	//������� ����� ��� ������
	std::ifstream load("Goods.txt");

	//��������� �������� �� ����
	if (!load)
		return false;

	std::string name;
	int num;
	std::vector<std::string> goods;

	//��������� ��� ������ �� �����
	while (!load.eof()) {
		load >> name;
		goods.push_back(name);
	}

	//������������ ������
	std::random_shuffle(goods.begin(), goods.end());

	//�������� �������� ���������� �������
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
	//��������� ��������� ���� ��� ���������� 
	switch (mode)
	{
	case 1://�������
		for (int i = 0; i < data.size() - 1; i++) {
			for (int j = i + 1; j < data.size(); j++) {
				//������� ���������� 
				if (data[i].surname > data[j].surname) {
					//������ �������
					Swap(i, j);
				}
			}
		}
		break;

	case 2://�� �������������� �������
		for (int i = 0; i < data.size() - 1; i++) {
			for (int j = i + 1; j < data.size(); j++) {
				//������� ���������� 
				if (data[i].mode_list > data[j].mode_list) {
					//������ �������
					Swap(i, j);
				}
			}
		}
		break;

	case 3://�� ���������� ������� 
		for (int i = 0; i < data.size() - 1; i++) {
			for (int j = i + 1; j < data.size(); j++) {
				//������� ���������� 
				if (data[i].name_of_the_goods.size() > data[j].name_of_the_goods.size()) {
					//������ �������
					Swap(i, j);
				}
			}
		}
		break;

	case 4://�� ���� ����������
		for (int i = 0; i < data.size() - 1; i++) {
			for (int j = i + 1; j < data.size(); j++) {
				//������� ���������� 
				if (data[i].date > data[j].date) {
					//������ �������
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
