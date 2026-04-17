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

    // three parameters to keep track of the tree, the number of nodes, and the number of leaves
    private TreeNode root;
    private int huffTreeSize;
    private int numLeaves;

    // hashmap keeps track of the huffman code
    private HashMap<Integer, String> huffCode;

    /**
     * Constructs an instance of this huffman code object
     * @param charFreqs is an integer array for all the character frequencies
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

        // enqueue the PEOF character manually
        pq.enqueue(new TreeNode(IHuffConstants.PSEUDO_EOF, 1));

        // generate huff map and code
        root = generateHuffMap(pq);
        huffCode = generateHuffCode();
    }

    /**
     * Generates a huffMap based on a priority queue and returns the root node
     * @param pq priority queue of TreeNodes that includes each character and frequency
     * @return TreeNode root that is the root of the huffMap
     */
    private TreeNode generateHuffMap(PriorityQueue<TreeNode> pq) {

        // while pq still has elements
        while (pq.size() > 1) {
            TreeNode left = pq.dequeue();
            TreeNode right = pq.dequeue();

            // take the first two elms and conjoin them, then add them back into the queue
            TreeNode result = new TreeNode(left, left.getFrequency() + right.getFrequency(),right);
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
     * @return HashMap of codes in {character = path} format
     */
    public HashMap<Integer, String> generateHuffCode() {
        HashMap<Integer, String> codes = new HashMap<Integer, String>();

        huffCodeHelper(codes, root, "");
        return codes;
    }

    /**
     * Private helper method for generateHuffCode()
     * @param codes is a HashMap of all the huff codes, when it is initially passed in it has no values
     * @param node keeps track of the position in the tree
     * @param path is a String representation of the shortened binary code
     */
    private void huffCodeHelper(HashMap<Integer, String> codes, TreeNode node, String path) {
        // if node is a leaf, then add it to codes
        if (node.isLeaf()) {
            codes.put(node.getValue(), path);
        }  else {
            // recursive calls for left/right child
            huffCodeHelper(codes, node.getLeft(), path + "0");
            huffCodeHelper(codes, node.getRight(), path + "1");
        }
        
    }

     /**
     * This method is chopped does not work must workshop
     */
    public int countBits(int[] charFreqs) {
        int numBits = 0;
        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0){
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