public class UnofficialTesting {
    
    public static void main(String[] args) {
        

        int[] arr = {0, 1, 2, 3, 4, 5, 6};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.enqueue(0);
        pq.enqueue(4);
        pq.enqueue(2);
        pq.enqueue(3);
        
        System.out.println(pq);
        System.out.println(pq.dequeue());
        System.out.println(pq);
        System.out.println(pq.dequeue());
        System.out.println(pq.dequeue());
        System.out.println(pq.dequeue());
        System.out.println(pq);



        HuffmanCode hc = new HuffmanCode(arr);

        System.out.println(hc);

    }


}
