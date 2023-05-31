class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : t.toCharArray()) count.put(c, count.getOrDefault(c, 0) + 1);

        int i = 0, j = Integer.MAX_VALUE - 1; // window start, window end (inclusive)
        int matched = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (count.containsKey(c)) {
                count.put(c, count.get(c) - 1);
                if (count.get(c) == 0) matched++;
            }

            while (matched == count.size()) {
                if (end - start + 1 < j - i + 1) {
                    i = start;
                    j = end;
                }

                char ch = s.charAt(start);
                if (count.containsKey(ch)) {
                    if (count.get(ch) == 0)
                        matched--;
                    count.put(ch, count.get(ch) + 1);
                }
                start++;
            }
        }
        return j == Integer.MAX_VALUE - 1 ? "" : s.substring(i, j + 1);
    }
}