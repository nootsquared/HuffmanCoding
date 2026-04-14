public class UnofficialTesting {
    
    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 1};
        PriorityQueue pq = new PriorityQueue(arr);

        System.out.println(pq);
        System.out.println(pq.debugString());

        System.out.println(pq.peek());
        System.out.println(pq.poll());
        System.out.println(pq.debugString());
    }


}
