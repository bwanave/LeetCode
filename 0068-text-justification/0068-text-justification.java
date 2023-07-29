class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int length = 0;
        int start = 0;
        for (int end = 0; end < words.length; end++) {
            length += words[end].length();
            if (length > maxWidth) {
                length -= words[end].length() + 1;
                result.add(justify(start, end, length, words, maxWidth, false));
                start = end;
                end--;
                length = 0;
                continue;
            }
            else if (end == words.length - 1) {
                result.add(justify(start, end + 1, length, words, maxWidth, true));
                break;
            }
            else
                length++;
        }
        return result;
    }
    
    private String justify (int start, int end, int length, String[] words, int maxWidth, boolean lastLine) {
        // System.out.println("start: "+start+", end: "+ end);
        if (length == maxWidth) return join(start,end, words, " ", 0);
        else {
            int extraSpaces = maxWidth - length;
            int wordCount = end - start;
            if (lastLine || wordCount == 1) return join(start, end, words, " ", 0) + spaces(extraSpaces);
            else {
                int totalSpaces = extraSpaces + wordCount - 1;
                int evenSpaces = totalSpaces / (wordCount - 1);
                int oddSpaces = totalSpaces % (wordCount - 1);
                String evenSpaceStr = spaces(evenSpaces);
                return join(start, end, words, evenSpaceStr, oddSpaces);
            }
        }
    }
    
    private String join(int start, int end, String[] words, String evenSpaceStr, int oddSpaces) {
        StringBuilder line = new StringBuilder();
        for (int i = start; i < end; i++) {
            line.append(words[i]);
            if (i < end - 1) line.append(evenSpaceStr);
            if (oddSpaces-- > 0) line.append(" ");
        }
        return line.toString();
    }
    
    private String spaces(int count) {
        return " ".repeat(count);
    }
}