public class GenericArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int top;
    
    
    @SuppressWarnings("unchecked")
    public GenericArrayStack(int capacity) {
    
//        @SuppressWarnings("unchecked")
        elems = (E[]) new Object[capacity];
        top = 0;
    }
    
    public boolean isEmpty() {
      boolean result = false;
      if (top == 0) {
     result = true;
      }
        return result;
    }
    
    
    public E peek() {
        return elems[top-1];
    }
    
    
    public void push(E element) {
        elems[top] = element;
        top = top + 1;
    }
    
    
    public E pop() {
        E saved;
        top = top - 1;
        saved = elems[top];
        elems[top] = null;
        return saved;
    }
    
 
}