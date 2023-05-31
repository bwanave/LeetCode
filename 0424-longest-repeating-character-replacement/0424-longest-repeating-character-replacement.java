class Solution {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        int maxRepeatingCharCount = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);

            maxRepeatingCharCount = Math.max(maxRepeatingCharCount, map.get(c));
            if (end - start + 1 - maxRepeatingCharCount > k) {
                c = s.charAt(start);
                map.put(c, map.get(c) - 1);
                start++;
            }

            len = Math.max(len, end - start + 1);
        }
        return len;
    }
}