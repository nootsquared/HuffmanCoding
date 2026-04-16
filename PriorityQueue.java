
// TODO: copy header + add comments

import java.util.ArrayList;

/* A class that models a fair priority queue */
public class PriorityQueue<E extends Comparable<E>> {

    ArrayList<E> queue;

    public PriorityQueue() {
        queue = new ArrayList<E>(); // Q: initial capacity?

    }
    
    public void enqueue(E elm) {
        int index = 0;

        while (index <= queue.size() - 1 && queue.get(index).compareTo(elm) > 0) {
            index++;
        }

        queue.add(index, elm);

    }

    public E peek() {
        return queue.isEmpty() ? null : queue.remove(queue.size() - 1);
    }

    public E dequeue() {
        return queue.isEmpty() ? null : queue.remove(queue.size() - 1);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public String toString() {
        StringBuilder q = new StringBuilder("[");

        if (!queue.isEmpty()) {
            for (int i = queue.size() - 1; i >= 0; i--) {
                q.append(queue.get(i));
            }
            q.delete(queue.size() - 2, queue.size())
        }
        return q.toString() + "]";

    }
}
