public class UnofficialTesting {
    
    public static void main(String[] args) {
        PriorityQueue<TreeNode> pq = new PriorityQueue();

        pq.enqueue(new TreeNode(0,4));
        pq.enqueue(new TreeNode(1,3));
        pq.enqueue(new TreeNode(3,2));
        pq.enqueue(new TreeNode(4,4));
        System.out.println(pq);


        int[] arr = {4, 3, 0, 2, 4};
        HuffmanCode hc = new HuffmanCode(arr);
        hc.debugHuff(hc.getRoot(), "");

        //System.out.println(hc);
    }


}
