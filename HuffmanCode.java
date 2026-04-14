// TODO: add header

import java.util.TreeSet;
import java.util.HashMap;

/* A class that generates a Huffman Code based on a frequency Priority Queue */
public class HuffmanCode {

    private TreeSet<TreeNode> huffMap;
    private HashMap<Integer, Integer> huffCode;

    public HuffmanCode(PriorityQueue pq) {
        huffMap = generateHuffMap(pq);
        huffCode = new HashMap<>();
    }

    private TreeSet<TreeNode> generateHuffMap(PriorityQueue pq) {

        while (pq.size() > 1) {
            TreeNode left = pq.poll();
            TreeNode right = pq.poll();

            TreeNode result = new TreeNode(left, left.getFrequency() + right.getFrequency(),right);
            pq.insert(result);
        }

        // Q: stuck :(
        return pq.poll;
    }

    public String huffMapString() {
        return huffMap.toString();
    }

    
}