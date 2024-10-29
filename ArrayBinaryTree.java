import java.util.Iterator;

public class ArrayBinaryTree<E> extends AbstractBinaryTree<E>
{
    public static class Cell<E> implements Position<E>
    {
        private E element;
        private int index;

        public Cell(E e, int i)
        {
            element = e;
            index = i;
        }
        public E getElement(){ return element; }
        public int getIndex(){ return index; }
        public void setElement(E element){ this.element = element; }
        public void setIndex(int index){ this.index = index; }
    }

    private E[] data;

    private int size;

    public static final int CAPACITY=32;

    public ArrayBinaryTree(int capacity)
    {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public ArrayBinaryTree() { this(CAPACITY); }

    //O(1)
    public int size() { return size; }

    //O(n)
    private void resize(int capacity)
    {
        E[ ] temp = (E[ ]) new Object[capacity];
        for ( int i = 0; i < size; i++) temp[i] = data[i];
        data = temp;
    }

    //O(1) - all linear
    private Cell<E> validate(Position<E> p) throws IllegalArgumentException
    {
        if (!(p instanceof Cell))
            throw new IllegalArgumentException("Not valid position type");

        if ( ((Cell<E>) p).getIndex() < 0 )
            throw new IllegalArgumentException("p is no longer in the tree");

        return (Cell<E>) p;
    }

    //O(1)
    public Position<E> addRoot(E e) throws IllegalStateException
    {
        if (size>0) throw new IllegalStateException("Tree is not empty");

        Cell<E> root = new Cell<>(e,0);
        data[0] = root.getElement();
        size = 1;

        return root;
    }

    // O(1) - no loops, same for right
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException
    {
        Cell<E> parent = validate(p);

        int index = 2 * parent.getIndex() + 1;

        if (index >= data.length) resize(data.length*2);

        if (data[index] != null)
            throw new IllegalArgumentException("Left child already exists");

        Cell<E> newCell = new Cell<>(e, index);

        data[index] = newCell.getElement();
        size++;

        return newCell;
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException
    {
        Cell<E> parent = validate(p);

        int index = 2 * parent.getIndex() + 2;

        if (index >= data.length) resize(2 * data.length);

        if (data[index] != null)
            throw new IllegalArgumentException("Left child already exists");

        Cell<E> newCell = new Cell<>(e, index);

        data[index] = newCell.getElement();
        size++;

        return newCell;
    }

    //O(1) - all linear
    public E remove(Position<E> p) throws IllegalArgumentException
    {
        Cell<E> cell = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children");

        int leftIndex = 2 * cell.getIndex() + 1;
        int rightIndex = 2 * cell.getIndex() + 2;

        Cell<E> child = null;
        if (leftIndex < data.length && data[leftIndex] != null)
            child = new Cell<>(data[leftIndex], leftIndex);
        else if (rightIndex < data.length && data[rightIndex] != null)
            child = new Cell<>(data[rightIndex], rightIndex);

        if (child != null) {
            data[cell.getIndex()] = child.getElement();
            child.setIndex(cell.getIndex());
        }
        else data[cell.getIndex()] = null;
        
        size--;

        cell.setIndex(-1);
        
        return cell.getElement();
    }

    // O(1) - all linear
    public E set(Position<E> p, E e) throws IllegalArgumentException
    {
        Cell<E> cell = validate(p);

        E temp = cell.getElement();
        cell.setElement(e);

        return temp;
    }

    // O(1) - all linear, same for right
    public Position<E> left(Position<E> p) throws IllegalArgumentException
    {
        Cell<E> cell = validate(p);
        int index = 2 * cell.getIndex() + 1;
        if (index >= data.length || data[index] == null) return null;
        return new Cell<>(data[index], index);
    }

    public Position<E> right(Position<E> p) throws IllegalArgumentException
    {
        Cell<E> cell = validate(p);
        int index = 2 * cell.getIndex() + 2;
        if (index >= data.length || data[index] == null) return null;
        return new Cell<>(data[index], index);
    }

    // O(1)
    public Position<E> root() {
        return size == 0 ? null : new Cell<>(data[0],0);
    }

    // O(1) - all linear
    public Position<E> parent(Position<E> p) throws IllegalArgumentException
    {
        Cell<E> cell = validate(p);

        int index = cell.getIndex();

        if (index == 0) return null;

        return new Cell<>(data[(index-1)/2], (index-1)/2);
    }

    public Iterator<E> inorderAfter() {
        return null; // it's 23:55 already, I will do this later (never)
    }

    public static void main(String[] args)
    {
        ArrayBinaryTree<Integer> tree = new ArrayBinaryTree<>();

        Position<Integer> root = tree.addRoot(5);

        Position<Integer> l1 = tree.addLeft(root,15);
        Position<Integer> l2 = tree.addLeft(l1,47);

        Position<Integer> r1 = tree.addRight(root,36);
        Position<Integer> r2 = tree.addRight(r1,-5);

        System.out.println(tree.left(tree.left(root)).getElement() + r2.getElement());
    }
}
