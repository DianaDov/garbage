#pragma once

#include <string>
#include <fstream>
#include <vector>
#include <ctime>
#include <algorithm>

//��������� ������ �������
struct Client {
	std::string surname;
	std::string name;
	std::string patronymic;
	int mode_list;//����� ��������������� ������: 0 - �����, 1 - ������, 2 - ���� ������
	int series;
	int number;
	std::string name_of_the_goods;
	std::string date;
};

class Clients
{
	//���� ������
private: //�������� ����� ������
	std::vector<Client> data;

	//������ ������
public: //�������� ����� ������
	Clients();//����������� �� ��������� - ������� ������ ������ ������
	~Clients();//���������� - ������� ������ ������
	bool Load(std::string filename);
	bool Save(std::string filename);
	void Add(Client c);
	bool Del(int i);
	bool Set(int i, Client c);
	Client Get(int i);
	bool CreateBlackList(std::string filename);
	bool CreateListWithDiscounts(std::string filename);
	bool CreateGoods(int i);
	bool Sort(int mode);
	void Swap(int i, int j);
	void Clear() { data.clear(); }
	int Size() { return data.size(); }
};

