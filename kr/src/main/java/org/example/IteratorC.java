package org.example;

// Объекты, содержащие ссылки на элементы структур данных и используемые
// для перебора элементов этих структур, обычно называются итераторами

public class IteratorC {
    // current ссылается на текущий элемент списка
    // сначала он содержит first (тоесть ссылку на первый элемент списка)
    private NewClient current;
    // Предыдущий элемент списка
    private NewClient previous;
    // Связный список
    private LinkListC ourList;

    //Конструктор для создания итератора
    public IteratorC(LinkListC list) {
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

    // Переход к следующему элементу
    public void nextLink() {
        // Присваивание текущего элемента previous
        previous = current;
        // Присваивание текущему элементу ссылку на следующий элемент (next)
        current = current.getNext();
    }

    //Получение текущего элемента
    public NewClient getCurrent() {
        return current;
    }

    //Вставка перед текущим элементом
    public void insertBefore(NewClient client) {
        //для методов вставки в список
        NewClient newElement = new NewClient(client);
        //если текущий элемент является первым
        if (previous == null) {
            // тогда новому элементу переопределяем указатели
            // его поле next указывает на первый элемент списка
            newElement.setNext(ourList.getFirst());
            // а в списке поле first указывает на текущий элемент
            ourList.setFirst(newElement);
            reset();
            //если текущий элемент не первый
        } else {
            // поле next вставялемого элемента указывает на текущий элемент
            newElement.setNext(previous.getNext());
            // поле next элемента перед текущим указывает на вставляемый элемент
            previous.setNext(newElement);
            // текущим становится вставляемый элемент
            current = newElement;
        }
        //увеличение размера списка
        LinkListC.size++;
    }

    //Вставка после текущего элемента
    public void insertAfter(NewClient clients) {
        //для методов вставки в LinkList
        NewClient newElement = new NewClient(clients);
        //Если список пуст
        if (ourList.isEmpty()) {
            // то вставляемый элемент устанавливаем первым
            ourList.setFirst(newElement);
            // а текущим ставим вставляемый элемент
            this.current = newElement;
            //Если список не пуст
        } else {
            // поле next вставялемого элемента указывает на следующий после текущего элемент
            newElement.setNext(this.current.getNext());
            // поле next текущего элемента указывает на вставляемый элемент
            this.current.setNext(newElement);
            nextLink();
        }
        //увеличение размера списка
        LinkListC.size++;
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
        LinkListC.size--;
    }

}
