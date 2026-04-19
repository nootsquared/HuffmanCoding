/*  Student information for assignment:
 *
 *  On our honor, Anishka and Pranav,
 *  this programming assignment is our own work
 *  and we have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1: Anishka Chokshi
 *  UTEID: arc6369
 *  email address: arc6369@my.utexas.edu
 *
 *  Student 2: Pranav Maringanti
 *  UTEID: prm2384
 *  email address: prm2384@eid.utexas.edu
 *
 *  Grader name: Issac
 *  Section number: 52970
 */

import java.util.HashMap;

/* A class that generates a Huffman Code based on a frequency Priority Queue */
public class HuffmanCode {

    // root of the completed huffman tree
    private TreeNode root;
    // size of huffmanTree
    private int huffTreeSize;
    // number of leaf nodes
    private int numLeaves;
    // maps each char value to its huffman bit encoding
    private HashMap<Integer, String> huffCode;

    /**
     * Constructs an instance of this huffman code object
     *
     * pre: charFreqs != null, charFreqs.length == IHuffConstants.ALPH_SIZE
     * post: root, huffTreeSize, numLeaves, and huffCode are all initialized
     * @param charFreqs
     *         is an integer array for all the character frequencies
     */
    public HuffmanCode(int[] charFreqs) {
        PriorityQueue<TreeNode> pq = new PriorityQueue<>();
        huffTreeSize = 0;
        numLeaves = 0;
        // add all the non-zero frequencies into the priority queue
        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0) {
                pq.enqueue(new TreeNode(i, charFreqs[i]));
                numLeaves++;
            }
        }
        pq.enqueue(new TreeNode(IHuffConstants.PSEUDO_EOF, 1));
        numLeaves++;
        root = generateHuffMap(pq);
        huffCode = generateHuffCode();
    }

    /**
     * Generates a huffMap based on a priority queue and returns the root node
     *
     * pre: pq != null, pq.size >= 1
     * post: returns the root of the fully formed huffman tree
     * @param pq
     *         priority queue of TreeNodes that includes each character and frequency
     * @return TreeNode root that is the root of the huffMap
     */
    private TreeNode generateHuffMap(PriorityQueue<TreeNode> pq) {
        // while pq still has elements
        while (pq.size() > 1) {
            TreeNode left = pq.dequeue();
            TreeNode right = pq.dequeue();
            // take the first two elms and conjoin them, then add them back into the queue
            TreeNode result = new TreeNode(left, left.getFrequency() + right.getFrequency(), right);
            pq.enqueue(result);
            huffTreeSize++;
        }
        return pq.dequeue();
    }

    /**
     * Returns the number of leaf nodes in the huff tree
     */
    public int getNumLeafNodes() {
        return numLeaves;
    }

    /**
     * Returns the size of the huffTree
     */
    public int treeSize() {
        return huffTreeSize;
    }

    /**
     * Returns the root of the huff tree
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Generates a HashMap huffCode based on the huff tree
     *
     * pre: root != null
     * post: returns a map where every char with non-zero freq is mapped to binary code string
     * @return HashMap of codes in {character = path} format
     */
    public HashMap<Integer, String> generateHuffCode() {
        HashMap<Integer, String> codes = new HashMap<Integer, String>();

        huffCodeHelper(codes, root, "");
        return codes;
    }

    /**
     * Private helper method for generateHuffCode()
     *
     * pre: codes != null, node != null path != null
     * post: has a value to bits entry for every leaf
     * @param codes
     *         is a HashMap of all the huff codes, when it is initially passed in it has no values
     * @param node
     *         keeps track of the position in the tree
     * @param path
     *         is a String representation of the shortened binary code
     */
    private void huffCodeHelper(HashMap<Integer, String> codes, TreeNode node, String path) {
        // if node is a leaf, then add it to codes
        if (node.isLeaf()) {
            codes.put(node.getValue(), path);
        } else {
            // recursive calls for left/right child
            huffCodeHelper(codes, node.getLeft(), path + "0");
            huffCodeHelper(codes, node.getRight(), path + "1");
        }

    }

    /**
     * Counts the total number of bits needed to encode the given char frequencies
     * 
     * pre: charFreqs != null, charFreqs.length == IHuffConstants.ALPH_SIZE, huffCode != null
     * post: returns the total num of bits needed to encode all chars plus EOF
     * @param charFreqs integer array of char frequencies
     * @return total number of bits needed to encode the data including the PSEUDO_EOF
     */
    public int countBits(int[] charFreqs) {
        int numBits = 0;
        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0) {
                numBits += huffCode.get(i).length() * charFreqs[i];
            }
        }
        numBits += huffCode.get(IHuffConstants.PSEUDO_EOF).length();
        return numBits;
    }

    /**
     * String representation of huffCode HashMap
     */
    public String toString() {
        return "" + huffCode;
    }

}