/*
Approach: prefix product and postfix product

Time Complexity: O(n)
Space Complexity: O(1)
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int left = 1;
        for (int i = 0; i < result.length; i++) {
            result[i] = left;
            left *= nums[i];
        }

        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }
        return result;
    }
}