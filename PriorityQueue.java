
// TODO: copy header + add comments

import java.util.ArrayList;

/* A class that models a fair priority queue */
public class PriorityQueue {

    ArrayList<TreeNode> queue;

    public PriorityQueue(int[] charFreqs) {

        queue = new ArrayList<>(charFreqs.length); //Q: this good for extra capacity
        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0) {
                insertSorted(new TreeNode(i, charFreqs[i]));
            }
        }

    }
    
    private void insertSorted(TreeNode elm) {
        int index = 0;

        while (index <= queue.size() - 1 && queue.get(index).getFrequency() <= elm.getFrequency()) {
            index++;
        }

        queue.add(index, elm);

    }

    public TreeNode peek() {
        return queue.isEmpty() ? null : queue.get(0);
    }

    // Q: ts inneficient since itll always be n, but is the best way to go about this to reverse the pq?
    public TreeNode poll() {
        return queue.isEmpty() ? null : queue.remove(0);
    }

    public String debugString() {
        StringBuilder s = new StringBuilder("[");

        if (!queue.isEmpty()) {
            for (TreeNode node : queue) {
                s.append("" + node.getFrequency() + ", ");
            }
            s.delete(s.length() - 2, s.length());
        }
        return s + "]";
    }

    public String toString() {
        return queue.toString();
    }
}
