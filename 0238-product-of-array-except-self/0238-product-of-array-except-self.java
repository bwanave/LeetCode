/*
Time Complexity: O(n)
Space Complexity: O(n)
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] prefixProduct = new int[nums.length];
        prefixProduct[0] = 1;
        for (int i = 1; i < nums.length; i++) prefixProduct[i] = prefixProduct[i - 1] * nums[i - 1];

        int[] sufixProduct = new int[nums.length];
        sufixProduct[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) sufixProduct[i] = sufixProduct[i + 1] * nums[i + 1];

        int[] result = new int[nums.length];
        for (int i = 0; i < result.length; i++) result[i] = prefixProduct[i] * sufixProduct[i];
        return result;
    }
}
