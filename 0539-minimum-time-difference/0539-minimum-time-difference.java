class Solution {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> minutes = new ArrayList<>();
        for (String timePoint : timePoints) minutes.add(toMinutes(timePoint));
        Collections.sort(minutes);
        minutes.add(minutes.get(0) + 24 * 60);
        
        int diff = Integer.MAX_VALUE;
        for (int i = 1; i < minutes.size(); i++) 
            diff = Math.min(diff, minutes.get(i) - minutes.get(i - 1));
        return diff;
    }
    
    private int toMinutes(String timePoint) {
        String[] time = timePoint.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}