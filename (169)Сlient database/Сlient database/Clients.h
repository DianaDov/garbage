#pragma once

#include <string>
#include <fstream>
#include <vector>
#include <ctime>
#include <algorithm>

//—труктура данных клиента
struct Client {
	std::string surname;
	std::string name;
	std::string patronymic;
	int mode_list;//режим дополнительного списка: 0 - пусто, 1 - черный, 2 - есть скидка
	int series;
	int number;
	std::string name_of_the_goods;
	std::string date;
};

class Clients
{
	//ѕол€ класса
private: //закрыта€ часть класса
	std::vector<Client> data;

	//ћетоды класса
public: //открыта€ часть класса
	Clients();//конструктур по умолчанию - создает пустой объект класса
	~Clients();//деструктор - удал€ет объект класса
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

