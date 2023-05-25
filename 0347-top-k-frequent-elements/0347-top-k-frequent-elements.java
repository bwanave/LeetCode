// Priority Queue
// Time Complexity: nlogk, n = nums.length
// Space Complexity: n + k
class Solution1 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) count.put(num, count.getOrDefault(num, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> count.get(a) - count.get(b));
        for (int num : count.keySet()) {
            pq.offer(num);
            if (pq.size() > k)
                pq.poll();
        }

        int i = 0;
        int[] result = new int[k];
        while (!pq.isEmpty())
            result[i++] = pq.poll();
        return result;
    }
}

// ----------------------------------------------------------------------------------------------------------------------------------------

// Bucket sort
// Time Complexity: n, n = nums.length
// Space Complexity: n + k
class Solution2 {
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

// ----------------------------------------------------------------------------------------------------------------------------------------

// Quick Select
// Time Complexity: n, n = nums.length, in worst case it takes n^2
// Space Complexity: n
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) count.put(num, count.getOrDefault(num, 0) + 1);

        int[] arr = new int[count.size()];
        int i = 0;
        for (int num : count.keySet()) arr[i++] = num;

        int pivot = quickSelect(0, arr.length - 1, arr, count, arr.length - k);
        return Arrays.copyOfRange(arr, pivot, arr.length);
    }

    private int quickSelect(int left, int right, int[] arr, Map<Integer, Integer> count, int k) {
        int j = left, pivot = right;
        for (int i = left; i < right; i++) {
            if (count.get(arr[i]) < count.get(arr[pivot])) {
                swap(i, j, arr);
                j++;
            }
        }

        swap(j, pivot, arr);
        if (k == j) return j;
        else if (k > j) return quickSelect(j + 1, right, arr, count, k);
        else return quickSelect(left, j - 1, arr, count, k);
    }

    private void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

