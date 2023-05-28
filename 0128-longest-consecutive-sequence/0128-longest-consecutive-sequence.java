// Time: O(n) | Space: O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).forEach(num -> set.add(num));

        int maxStreak = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) { // Found start of streak
                int count = 0;
                while (set.contains(num++)) count++;
                maxStreak = Math.max(maxStreak, count);
            }
        }
        return maxStreak;
    }
}