class Solution {

    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (target == nums[mid]) return mid;

            // Left sorted
            if (nums[lo] <= nums[mid]) {
                if (target < nums[lo] || target > nums[mid])
                    lo = mid + 1;
                else
                    hi = mid - 1;
            }
            // Right sorted
            else {
                if (target < nums[mid] || target > nums[hi])
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
        }
        return -1;
    }
}
