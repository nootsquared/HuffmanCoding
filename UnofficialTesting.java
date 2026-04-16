public class UnofficialTesting {
    
    public static void main(String[] args) {
        

        int[] arr = {4, 3, 0, 2, 4};
        
        HuffmanCode hc = new HuffmanCode(arr);

        BitInputStream bitInputStream = new BitInputStream("test.txt");

        int inbits = bitInputStream.readBits(8);

        bitInputStream.close();
    }


}
