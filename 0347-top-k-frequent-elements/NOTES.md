```
// Approach1: Bucket Sort
// Time Complexity: O(n)
class Solution {
public int[] topKFrequent(int[] nums, int k) {
Map<Integer, Integer> frequencyMap = new HashMap<>();
for (int num : nums)
frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
​
List<Integer>[] buckets = new ArrayList[nums.length + 1];
for (int num : frequencyMap.keySet()) {
int frequency = frequencyMap.get(num);
if (buckets[frequency] == null) buckets[frequency] = new ArrayList<>();
buckets[frequency].add(num);
}
​
List<Integer> result = new ArrayList<>();
for (int i = buckets.length - 1; i >= 0; i--) {
if (buckets[i] != null) {
result.addAll(buckets[i]);
if (result.size() == k) break;
}
}
return result.stream().mapToInt(a -> a).toArray();
}
}