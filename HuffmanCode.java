// TODO: add header + comments

import java.util.TreeSet;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

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
        pq.enqueue(new TreeNode(IHuffConstants.PSEUDO_EOF, 1));
        root = generateHuffMap(pq);
        huffCode = generateHuffCode();
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
        }  else {
            huffCodeHelper(codes, node.getLeft(), path + "0");
            huffCodeHelper(codes, node.getRight(), path + "1");
        }
        
    }

    public int countBits(int[] charFreqs) {
        int numBits = 0;
        for (int i = 0; i < charFreqs.length; i++) {
            numBits += i * charFreqs[i];
        }
        return numBits;
    }

    public void debugHuff(TreeNode node, String path) {
        if (node.isLeaf()) {
            System.out.println(node + " : " + path);
        } else {
            debugHuff(node.getLeft(), path + "0");
            debugHuff(node.getRight(), path + "1");
        }
    }

    public void printCodes() {
        for (Integer val : huffCode.keySet()) {
            System.out.println(val + " : " + huffCode.get(val));
        }
    }

    public String toString() {
        return "" + huffCode;
    }

}