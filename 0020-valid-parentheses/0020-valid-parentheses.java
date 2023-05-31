class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> map = Map.of('{', '}', '(', ')', '[', ']');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) stack.push(c);
            else if (stack.isEmpty() || !map.get(stack.pop()).equals(c)) return false;
        }
        return stack.isEmpty();
    }
}