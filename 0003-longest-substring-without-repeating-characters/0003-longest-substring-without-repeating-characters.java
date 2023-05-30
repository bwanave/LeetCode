class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1);
            }
            map.put(c, end);
            len = Math.max(len, end - start + 1);
        }
        return len;
    }
}