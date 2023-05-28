```
// time n | space 1
class Solution {
public boolean isPalindrome(String s) {
int start = 0, end = s.length() - 1;
while (start < end) {
int startAscii = Character.toLowerCase(s.charAt(start));
int endAscii = Character.toLowerCase(s.charAt(end));
if (!isAlphanumeric(startAscii)) {
start++;
continue;
}
if (!isAlphanumeric(endAscii)) {
end--;
continue;
}
​
if (startAscii != endAscii) return false;
start++;
end--;
}
return true;
}
​
private boolean isAlphanumeric(int ascii) {
return (ascii >= 48 && ascii <= 57) || (ascii >= 97 && ascii <= 122);