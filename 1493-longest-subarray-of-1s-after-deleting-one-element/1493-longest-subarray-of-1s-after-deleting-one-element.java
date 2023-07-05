class Solution {
    public int longestSubarray(int[] nums) {
        int maxSize = 0;
        Queue<Integer> deletedCharIndexes = new LinkedList<>();
        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            if (nums[end] != 1) deletedCharIndexes.offer(end);
            
            while (deletedCharIndexes.size() > 1) 
                start = deletedCharIndexes.poll() + 1;
            
            maxSize = Math.max(maxSize, end - start + 1);
        }
        return maxSize - 1;
    }
}