// TODO: add header

import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashMap;

/* A class that generates a Huffman Code based on a frequency Priority Queue */
public class HuffmanCode {

    private TreeNode root;
    private HashMap<Integer, String> huffCode;

    public HuffmanCode(int[] charFreqs) {
        
        PriorityQueue<TreeNode> pq = new PriorityQueue<>();

        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0) {
                pq.enqueue(new TreeNode(i, charFreqs[i]));
            }
        }
        root = generateHuffMap(pq);
        // huffCode = generateHuffCode();
    }

    private TreeNode generateHuffMap(PriorityQueue<TreeNode> pq) {

        while (pq.size() > 1) {
            TreeNode left = pq.dequeue();
            TreeNode right = pq.dequeue();

            TreeNode result = new TreeNode(left, left.getFrequency() + right.getFrequency(),right);
            pq.enqueue(result);
        }

        return pq.dequeue();
    }

    public TreeNode getRoot() {
        return root;
    }

    public HashMap<Integer, String> generateHuffCode() {
        HashMap<Integer, String> codes = new HashMap<Integer, String>();

        huffCodeHelper(codes, root, "");
        
        return codes;
    }

    private void huffCodeHelper(HashMap<Integer, String> codes, TreeNode node, String path) {
        if (node.isLeaf()) {
            codes.put(node.getValue(), path);
        }  

        huffCodeHelper(codes, node.getLeft(), path + "0");
        huffCodeHelper(codes, node.getLeft(), path + "1");
    }

    public void debugHuff(TreeNode node, String path) {
        if (node.isLeaf()) {
            System.out.println(node.getValue() + " : " + path);
        }  

        debugHuff(node.getLeft(), path + "0");
        debugHuff(node.getRight(), path + "1");
    }

    public String toString() {
        return huffCode.toString();
    }

}