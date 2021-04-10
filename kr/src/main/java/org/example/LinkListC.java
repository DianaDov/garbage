package org.example;

// связный список для объектов класса NewClient
public class LinkListC {

    //счётчик для количества элементов в нашем списке
    public static int size = 0;
    // класс LinkedList содержит всего один элемент данных
    // ссылку на первый элемент списка
    // это единственная информации о местанахождении элементов, которая хранится в списке
    private NewClient first;
    // остальные элементы отслеживаются по цепочке ссылок next, начиная с элемента first

    //конструктор для создания списка
    public LinkListC() {
        // конструктор инициализирует first значением null
        first = null;
        // список пока не содержит элементов
        // Если бы списке был хоть один элемент, в first хранилась бы ссылка на него.
        // Метод isEmpty() использует этот факт для проверки отсутствия элементов в списке.
    }

    //метод для получения первого элемента списка
    public NewClient getFirst() {
        return first;
    }

    //метод для установки первого значения списка
    public void setFirst(NewClient students) {
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
    public void add(int number, NewClient client) {

        IteratorC iterator = NewClientController_3.iterator;
        boolean flag = true;
        // Сброс итератора и возврат к первому значению
        iterator.reset();

        //если вставляемый элемент является первым
        if (number == 0) {
            // то вставляем первым без проверки на следующий элемент
            iterator.insertBefore(client);
        } else {
            // обнуляем индекс
            int i = 0;
            // проходим по всем элементам списка в поисках нужного по счёту элемента
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
                iterator.insertAfter(client);
            } catch (NullPointerException npe) {
                System.out.println("Не удалось добавить элемент");
            }
        }
    }

    // добавление элемента в начало списка
    public void addFirst(NewClient students) {

        NewClientController_3.list.add(0, students);
    }

    // добавление элемента в конец списка
    public void addLast(NewClient students) {
        IteratorC iterator = NewClientController_3.iterator;
        boolean flag = true;
        //Сброс итератора и возврат к первому значению
        iterator.reset();

        //если текущий элемент является первым
        if (NewClientController_3.list.isEmpty()) {
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
            NewClientController_3.list.add(i - 1, students);
        }
    }

    // удаление элемента по индексу
    public void remove(int number) {
        IteratorC iterator = NewClientController_3.iterator;
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
    public NewClient get(int number) {
        IteratorC iterator = NewClientController_3.iterator;
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
            return (NewClient) iterator.getCurrent();
        } catch (NullPointerException npe) {
            System.out.println("Не удалось получить элемент");
            return null;
        }

    }

    // установить значение по индексу
    public void set(int number, NewClient students) {
        NewClientController_3.list.remove(number);
        NewClientController_3.list.add(number, students);
    }

}

