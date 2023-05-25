// Priority Queue
// TC - nlogk, n = nums.length
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) count.put(num, count.getOrDefault(num, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> count.get(a) - count.get(b));
        for (int num : count.keySet()) {
            pq.offer(num);
            if (pq.size() > k)
                pq.poll();
        }

        System.out.println(pq);

        int i = 0;
        int[] result = new int[k];
        while (!pq.isEmpty())
            result[i++] = pq.poll();
        return result;
    }
}