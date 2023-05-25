// // Priority Queue
// // Time Complexity: nlogk, n = nums.length
// // Space Complexity: n + k
// class Solution {
//     public int[] topKFrequent(int[] nums, int k) {
//         Map<Integer, Integer> count = new HashMap<>();
//         for (int num : nums) count.put(num, count.getOrDefault(num, 0) + 1);

//         PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> count.get(a) - count.get(b));
//         for (int num : count.keySet()) {
//             pq.offer(num);
//             if (pq.size() > k)
//                 pq.poll();
//         }

//         int i = 0;
//         int[] result = new int[k];
//         while (!pq.isEmpty())
//             result[i++] = pq.poll();
//         return result;
//     }
// }

// Bucket sort
// Time Complexity: n, n = nums.length
// Space Complexity: n + k
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) count.put(num, count.getOrDefault(num, 0) + 1);

        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        for (int num : count.keySet()) {
            int frequency = count.get(num);
            if (bucket[frequency] == null) bucket[frequency] = new ArrayList<>();
            bucket[frequency].add(num);
        }

        int idx = 0;
        int[] result = new int[k];
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == null) continue;
            for (int num : bucket[i]) {
                result[idx++] = num;
                if (idx == k) return result;
            }
        }
        return null;
    }
}