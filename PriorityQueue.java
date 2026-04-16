
/*  Student information for assignment:
 *
 *  On our honor, Anishka and Pranav,
 *  this programming assignment is our own work
 *  and we have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1: Anishka Chokshi
 *  UTEID: arc6369
 *  email address: arc6369@my.utexas.edu
 *
 *  Student 2:
 *  UTEID:
 *  email address:
 *
 *  Grader name: Issac
 *  Section number: 52970
 */

import java.util.LinkedList;

/* A class that models a fair priority queue */
public class PriorityQueue<E extends Comparable<E>> {

    LinkedList<E> queue;

    /**
     * Constructs an instance of a fair priority queue
     */
    public PriorityQueue() {
        queue = new LinkedList<>();
    }
    
    /**
     * Adds an element to the priority queue in ascending order. If two elements have the same priority, 
     * tiebreaker is used
     * @param elm is the element to be enqueued
     * @throws IllegalArgumentException if elm is null
     */
    public void enqueue(E elm) {
        if (elm == null) {
            throw new IllegalArgumentException("elm cannot be null");
        }

        int index = 0;
        while (index < queue.size() && queue.get(index).compareTo(elm) < 0) {
            index++;
        }
        queue.add(index, elm);

    }

    /**
     * Returns the first elm of the queue (without removing it)
     * @return the first elm of the queue
     */
    public E peek() {
        return queue.peekFirst();
    }

    /**
     * Removes and returns the first elm of the queue
     * @return the elm that was removed
     */
    public E dequeue() {
        return queue.removeFirst();
    }

    /**
     * Returns if the queue is empty
     * @return true if queue is empty, false if otherwise
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returns the size of the queue
     * @return integer queue size
     */
    public int size() {
        return queue.size();
    }

    /**
     * Returns String representation of the queue
     * @return linked list class String representation of queue
     */
    public String toString() {
        return queue.toString();
    }
}
