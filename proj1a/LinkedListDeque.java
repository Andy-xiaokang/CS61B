public class LinkedListDeque<T> {
    private int size;
    private Node sentinel;

    public class Node{
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n){
            prev = p;
            item = i;
            next = n;
        }

        //constructor for sentinel node
        public Node(Node p, Node n){
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque(){
        size = 0;
        sentinel = new Node(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other){
        size = 0;
        sentinel = new Node(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        for (int i = 0; i < other.size; i += 1){
            addLast((T) other.get(i));
        }
    }

    public T helper(int index, Node ptr){
        if (index == 0){
            return ptr.item;
        }
        return helper(index - 1, ptr.next);
    }
    public T getRecursive(int index){
        if (size == 0 || index > size - 1){
            return null;
        }
        Node ptr = sentinel.next;
        return helper(index, ptr);
    }

    public void addFirst(T item){
        Node first = new Node(null, item, null);
        first.next = sentinel.next;
        sentinel.next.prev = first;
        sentinel.next = first;
        first.prev = sentinel;
        size += 1;
    }

    public void addLast(T item){
        Node last = new Node(null, item, null);
        sentinel.prev.next = last;
        last.prev = sentinel.prev;
        last.next = sentinel;
        sentinel.prev = last;
        size += 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node ptr = sentinel.next;
        while(ptr != sentinel){
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.print("\n");
    }

    public T removeFirst(){
        if (size == 0){
            return null;
        }
        Node tmp = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return tmp.item;
    }
    public T removeLast(){
        if (size == 0){
            return null;
        }
        Node tmp = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return tmp.item;
    }
    public T get(int index){
        if (size == 0 || index > size - 1){
            return null;
        }
        Node ptr = sentinel.next;
        for (int i = 0; i < index; i += 1){
            ptr = ptr.next;
        }
        return ptr.item;
    }
}
