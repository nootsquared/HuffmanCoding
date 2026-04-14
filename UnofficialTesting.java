public class UnofficialTesting {
    
    public static void main(String[] args) {
        int[] arr = {1,2 ,3, 4};
        PriorityQueue pq = new PriorityQueue(arr);

        HuffmanCode hc = new HuffmanCode(pq);

        System.out.println(hc.huffMapString());
    }


}
