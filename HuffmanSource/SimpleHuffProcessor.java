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
 *  Grader name:
 *  Section number:
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleHuffProcessor implements IHuffProcessor {

    private IHuffViewer myViewer;
    private HuffmanCode huffCode;

    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     * @param in is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind of
     * header to use, standard count format, standard tree format, or
     * possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     * Note, to determine the number of
     * bits saved, the number of bits written includes
     * ALL bits that will be written including the
     * magic number, the header format number, the header to
     * reproduce the tree, AND the actual data.
     * @throws IOException if an error occurs while reading from the input file.
     */
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
        BitInputStream bitInputStream = new BitInputStream(in);
        int initialBits = 0;
        int finalBits = BITS_PER_INT + BITS_PER_INT;
        int[] charFreqs = new int[ALPH_SIZE];
        int inbits = 0;
        // parse through each byte in the file and increment the letter in the charFreqs array
        inbits = bitInputStream.readBits(BITS_PER_WORD);
        while (inbits != -1) {
            charFreqs[inbits]++;
            initialBits += BITS_PER_WORD;
            inbits = bitInputStream.readBits(BITS_PER_WORD);
        }
        huffCode = new HuffmanCode(charFreqs);
        if (headerFormat == IHuffConstants.STORE_COUNTS) {
            finalBits += (BITS_PER_INT * ALPH_SIZE);
        } else if (headerFormat == IHuffConstants.STORE_TREE) {
            finalBits += BITS_PER_INT;
            // 1 bit (1) + 9 bits following
            finalBits += (huffCode.treeSize()) + (huffCode.getNumLeafNodes() * (1 + (BITS_PER_WORD + 1)));
        }
        finalBits += huffCode.countBits(charFreqs);
        return initialBits - finalBits; 
    }

    /**
	 * Compresses input to output, where the same InputStream has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br> pre: <code>preprocessCompress</code> must be called before this method
     * @param in is the stream being compressed (NOT a BitInputStream)
     * @param out is bound to a file/stream to which bits are written
     * for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than the input file.
     * If this is false do not create the output file if it is larger than the input file.
     * @return the number of bits written.
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        BitInputStream bis = new BitInputStream(in);
        // making my input stream
        // write out header info
        // read the input stream, for char output coded version
        // at the end, manually add pseudo eof
        if (force) {
            
            return 1;
        }
        return 0;
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     * @param in is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {
            BitInputStream bis = new BitInputStream(in);
            BitOutputStream bos = new BitOutputStream(out);
            int[] freq;
            TreeNode root;
            int magicNumber = bis.readBits(BITS_PER_INT);
            if (magicNumber == -1){
                throw new IOException("ended input before the PSEUDO_EOF");
            }
            else if (magicNumber != MAGIC_NUMBER){
                throw new IOException("Not a valid Huffman file (missing the huffman magic number)");
            }
            int headerType = bis.readBits(BITS_PER_INT);
            if (headerType == -1){
                throw new IOException("ended input before the PSEUDO_EOF");
            }
            if (headerType == STORE_COUNTS){
                freq = new int[ALPH_SIZE];
                for (int k = 0; k < ALPH_SIZE; k++){
                    int freqInOriginalFile = bis.readBits(BITS_PER_INT);
                    if (freqInOriginalFile == -1){
                        throw new IOException("ended input before the PSEUDO_EOF");
                    }
                    freq[k] = freqInOriginalFile;
                }
                HuffmanCode rebuiltCode = new HuffmanCode(freq);
                root = rebuiltCode.getRoot();
            }
            else if (headerType == STORE_TREE) {
                int treeBitCount = bis.readBits(BITS_PER_INT);
                if (treeBitCount == -1){
                    throw new IOException("ended input before the PSEUDO_EOF");
                }
                root = readTree(bis);
            }
            else{
                throw new IOException("Invalid header type");
            }
            return decode(bis, bos, root);
    }

    /**
     * Decodes the compressed bits using the huffman tree and writes origional bytes
     * 
     * pre: bis != null, bos != null, root != null
     * post: decoded bytes are written to bos until PSEUDO_EOF
     * @param bis input stream of compressed bits
     * @param bos output stream of decoded bits
     * @param root root of the Huffman tree used for decoding
     * @return number of bits written to the output
     * @throws IOException if input ends before PSEUDO_EOF
     */
    private int decode(BitInputStream bis, BitOutputStream bos, TreeNode root) throws IOException{
       boolean eof = false;
       int bitsWritten = 0;
       TreeNode tempNode = root;
       while (!eof) {
        int bitInput = bis.readBits(1);
        if (bitInput == -1){
            throw new IOException("ended input before the PSEUDO_EOF");
        }
        if (bitInput == 0){
            tempNode = tempNode.getLeft();

        }
        else if (bitInput == 1) {
            tempNode = tempNode.getRight();
        }
        // do I need to check for null here
        if (tempNode == null){
            throw new IOException("Invalid tree structure, reached a null node");
        }
        if (tempNode.isLeaf()) {
            if (tempNode.getValue() == PSEUDO_EOF){
                eof = true;
            }
            else{
                bos.writeBits(BITS_PER_WORD, tempNode.getValue());
                tempNode = root;
                bitsWritten += BITS_PER_WORD;
            }
        }
       }
       bos.flush();
       return bitsWritten;
    }

    /**
     * Rebuilds a Huffman tree from STF bits using a preorder traversal
     * 
     * pre: bis != null
     * post: returns the root of the rebuild subtree/tree
     * @param bis bit input stream to read the bits from
     * @return root node of the rebuilt tree/subtree
     * @throws IOException if the steam ends early or the tree data is not valid
     */
    private TreeNode readTree(BitInputStream bis) throws IOException{
        int bit = bis.readBits(1);
        if (bit == -1){
            throw new IOException("ended early while reading the tree");
        }
        if (bit == 1){
            int value = bis.readBits(BITS_PER_WORD + 1);
            if (value == -1){
                throw new IOException("ended early while reading the tree");
            }
            return new TreeNode(value, 0);
        }
        else {
            TreeNode leftNode = readTree(bis);
            TreeNode rightNode = readTree(bis);
            return new TreeNode(leftNode, 0, rightNode);
        }
    }

    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    private void showString(String s){
        if (myViewer != null) {
            myViewer.update(s);
        }
    }
}
