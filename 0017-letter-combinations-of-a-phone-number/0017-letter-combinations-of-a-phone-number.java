class Solution {

    private final Map<Character, String> digitToLettersMap = Map.of('2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz");

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();

        List<String> result = new ArrayList<>();
        backtrack(0, digits, new StringBuilder(), result, digitToLettersMap);
        return result;
    }

    private void backtrack(int digitIdx, String digits, StringBuilder currPath, List<String> result, Map<Character, String> digitToLettersMap) {
        if (digitIdx == digits.length()) {
            result.add(currPath.toString());
            return;
        }

        for (char c : digitToLettersMap.get(digits.charAt(digitIdx)).toCharArray()) {
            currPath.append(c);
            backtrack(digitIdx + 1, digits, currPath, result, digitToLettersMap);
            currPath.deleteCharAt(currPath.length() - 1);
        }
    }
}