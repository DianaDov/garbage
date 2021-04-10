package org.example;

// Объекты, содержащие ссылки на элементы структур данных и используемые
// для перебора элементов этих структур, обычно называются итераторами

public class IteratorR {
    // Текущий элемент списка
    private NewRequest current;
    // Предыдущий элемент списка
    private NewRequest previous;
    // Связанный список
    private LinkListR ourList;

    //Конструктор для создания итератора
    public IteratorR(LinkListR list) {
        ourList = list;
        reset();
    }

    //Сброс итератора и возврат к первому значению
    public void reset() {
        // current --> first
        current = ourList.getFirst();
        // previous --> null
        previous = null;
    }

    //Переход к следующему элементу
    public void nextLink() {
        // Присваивание текущего элемента previous
        previous = current;
        // Присваивание текущему элементу next
        current = current.getNext();
    }

    //Получение текущего элемента
    public NewRequest getCurrent() {
        return current;
    }

    //Вставка перед текущим элементом
    public void insertBefore(NewRequest students) {
        //для методов вставки в LinkList
        NewRequest newStudents = new NewRequest(students);
        //если текущий элемент является первым
        if (previous == null) {
            newStudents.setNext(ourList.getFirst());
            ourList.setFirst(newStudents);
            reset();
            //если текущий элемент не первый
        } else {
            newStudents.setNext(previous.getNext());
            previous.setNext(newStudents);
            current = newStudents;
        }
        //увеличение размера списка
        LinkListR.size++;
    }

    //Вставка после текущего элемента
    public void insertAfter(NewRequest students) {
        //для методов вставки в LinkList
        NewRequest newStudents = new NewRequest(students);
        //Если список пуст
        if (ourList.isEmpty()) {
            ourList.setFirst(newStudents);
            current = newStudents;
            //Если список не пуст
        } else {
            newStudents.setNext(current.getNext());
            current.setNext(newStudents);
            nextLink();
        }
        //увеличение размера списка
        LinkListR.size++;
    }

    //определяет, является ли текущий элемент последним в списке
    public boolean atEnd() {
        //проверка, является ли элемент последним в списке
        return (current.getNext() == null);
    }

    //Удаление текущего элемента
    public void deleteCurrent() {
        //Если текущий элемент является первым
        if (previous == null) {
            ourList.setFirst(current.getNext());
            reset();
            //Если текущий элемент не первый
        } else {
            previous.setNext(current.getNext());
            if (atEnd())
                //Сброс, если удалили последний
                reset();
            else
                current = current.getNext();
        }
        //уменьшение размера
        LinkListR.size--;
    }

}
