public class Codec {

    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();
        for (String str : strs)
            encoded.append(str.length()).append('#').append(str);
        return encoded.toString();
    }

    public List<String> decode(String s) {
        List<String> decoded = new ArrayList<>();
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            if (s.charAt(end) == '#') {
                int length = Integer.parseInt(s.substring(start, end));
                decoded.add(s.substring(end + 1, end + 1 + length));
                end = end + length;
                start = end + 1;
            }
        }
        return decoded;
    }
}