package org.example;

// двунаправленный список, где каждый элемент структуры содержит указатели на предыдущий и следующий элементы
// список для объектов класса NewRequest
public class LinkListR {

    //счётчик для количества элементов в нашем списке
    public static int size = 0;
    // ссылка на первый элемент списка
    private NewRequest first;

    //конструктор для создания списка
    public LinkListR() {
        first = null;
    }

    //метод для получения первого элемента списка
    public NewRequest getFirst() {
        return first;
    }

    //метод для установки первого значения списка
    public void setFirst(NewRequest students) {
        first = students;
    }

    // Возвращает количество элементов в этом списке.
    public int size() {
        return size;
    }

    //возвращает true, если список пуст
    public boolean isEmpty() {
        return first == null;
    }


    // Вставляет указанный элемент в указанную позицию в этом списке.
    public void add(int number, NewRequest students) {

        IteratorR iterator = NewRequestController_4.iterator;
        boolean flag = true;
        // Сброс итератора и возврат к первому значению
        iterator.reset();

        //если текущий элемент является первым
        if (number == 0) {
            // то вставляем первым без проверки на следующий элемент
            iterator.insertBefore(students);
        } else {
            // обнуляем индекс
            int i = 0;
            // если флаг==false => мы дошли до конца списка
            while ((flag) && (i != number - 1)) {
                // инкриментируем индекс вставки
                i++;
                try {
                    // Перемещение итератора к следующему элементу
                    iterator.nextLink();
                } catch (NullPointerException npe) {
                    //  если следующего элемента нет, то меняем значение флага
                    flag = false;
                }
            }
            try {
                // если индекс равен последнеднему элементу, то мы вставляем элемент в конец
                iterator.insertAfter(students);
            } catch (NullPointerException npe) {
                System.out.println("Не удалось добавить элемент");
            }
        }
    }

    // добавление элемента в начало списка
    public void addFirst(NewRequest students) {
        NewRequestController_4.list.add(0, students);
    }

    // добавление элемента в конец списка
    public void addLast(NewRequest students) {
        IteratorR iterator = NewRequestController_4.iterator;
        boolean flag = true;
        //Сброс итератора и возврат к первому значению
        iterator.reset();

        //если текущий элемент является первым
        if (NewRequestController_4.list.isEmpty()) {
            iterator.insertBefore(students);
        } else {
            int i = 0;
            while (flag) {
                i++;
                try {
                    // Перемещение итератора к следующему элементу
                    iterator.nextLink();
                } catch (NullPointerException npe) {
                    flag = false;
                }
            }
            NewRequestController_4.list.add(i - 1, students);
        }
    }

    // удаление элемента по индексу
    public void remove(int number) {
        IteratorR iterator = NewRequestController_4.iterator;
        boolean flag = true;
        iterator.reset();

        if (number == 0) {
            iterator.deleteCurrent();
        } else {
            int i = 0;
            while ((flag) && (i != number)) {
                i++;
                try {
                    iterator.nextLink();
                } catch (NullPointerException npe) {
                    flag = false;
                }
            }
            try {
                iterator.deleteCurrent();
            } catch (NullPointerException npe) {
                System.out.println("Не удалось удалить элемент");
            }
        }
    }

    // получение элемента по индексу
    public NewRequest get(int number) {
        IteratorR iterator = NewRequestController_4.iterator;
        boolean flag = true;
        iterator.reset();

        int i = 0;
        while ((flag) && (i != number)) {
            i++;
            try {
                iterator.nextLink();
            } catch (NullPointerException npe) {
                flag = false;
            }
        }
        try {
            return (NewRequest) iterator.getCurrent();
        } catch (NullPointerException npe) {
            System.out.println("Не удалось получить элемент");
            return null;
        }

    }

    // установить значение по индексу
    public void set(int number, NewRequest students) {
        NewRequestController_4.list.remove(number);
        NewRequestController_4.list.add(number, students);
    }

}

