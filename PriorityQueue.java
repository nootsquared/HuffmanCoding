
// TODO: copy header + add comments

import java.util.LinkedList;

/* A class that models a fair priority queue */
public class PriorityQueue<E extends Comparable<E>> {

    LinkedList<E> queue;

    public PriorityQueue() {
        queue = new LinkedList<>();

    }
    
    public void enqueue(E elm) {
        int index = 0;

        
        while (index < queue.size() && queue.get(index).compareTo(elm) < 0) {
            index++;
        }

        queue.add(index, elm);

    }

    public E peek() {
        return queue.peekFirst();
    }

    public E dequeue() {
        return queue.removeFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    // Q: design of pq and if its ok
    public String toString() {
        return queue.toString();

    }
}
