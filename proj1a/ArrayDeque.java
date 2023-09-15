public class ArrayDeque<T> {
    private T[] array;
    private int first;
    private int last;
    private int size;
    private int length;

    public ArrayDeque(){
        array = (T[]) new Object[8];
        first = 4;
        last = 4;
        size = 0;
        length = 8;
    }

    public ArrayDeque(ArrayDeque other){
        array = (T[]) new Object[other.length];
        first = other.first;
        last = other.last;
        size = other.size;
        length = other.size;
        int ptr1 = first;
        int ptr2 = other.first;
        while (ptr1 != last){
            array[ptr1] =(T) other.array[ptr2];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, other.length);
        }
    }

    //helper function
    public int minusOne(int index){
        if (index == 0){
            return length - 1;
        }
        return index - 1;
    }
    public int plusOne(int index, int module){
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }

    public void upResize(){
        T[] newArray = (T[]) new Object[length * 2];
        int ptr1 = first;
        int ptr2 = length;
        while (ptr1 != last){
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length * 2);
        }
        first = length;
        last = ptr2;
        array = newArray;
        length = length * 2;
    }
    public void downResize(){
        T[] newArray = (T[]) new Object[length / 2];
        int ptr1 = first;
        int ptr2 = length/4;
        while (ptr1 != last){
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length/2);
        }
        first = length/4;
        last = ptr2;
        array = newArray;
        length = length/2;
    }

    public void addFirst(T item){
        if (size == length - 1) {
            upResize();
        }
        first = minusOne(first);
        array[first] = item;
        size += 1;
    }
    public void addLast(T item){
        if (size == length - 1) {
            upResize();
        }
        array[last] = item;
        last = plusOne(last, length);
        size += 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        int ptr = first;
        while (ptr != last){
            System.out.print(array[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
        System.out.println("\n");
    }
    public T removeFirst(){
        if (length >= 16 && length/size >=4){
            downResize();
        }
        if (size == 0){
            return null;
        }
        T item = array[first];
        first = plusOne(first, length);
        size -= 1;
        return item;
    }
    public T removeLast(){
        if (length >= 16 && length/size >= 4){
            downResize();
        }
        if (size == 0){
            return null;
        }
        T item = array[minusOne(last)];
        last = minusOne(last);
        size -= 1;
        return item;
    }
    public T get(int index){
        if (size == 0 || index > size - 1){
            return null;
        }
        int ptr = first;
        for (int i = 0; i < index; i += 1){
            ptr = plusOne(ptr, length);
        }
        return array[ptr];
    }
}
