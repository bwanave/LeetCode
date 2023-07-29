class Logger {
    private final Map<String, Integer> map;
    private int currTime;

    public Logger() {
        map = new LinkedHashMap<>(100, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, Integer> leastRecentlyAccessedEntry) {
                return currTime - leastRecentlyAccessedEntry.getValue() >= 10;
            }
        };
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        currTime = timestamp;
        Integer prevTimestamp = map.get(message);
        if (prevTimestamp == null || timestamp - prevTimestamp >= 10) {
            map.put(message, timestamp);
            return true;
        }
        return false;
    }
}