class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int totalMeetingRoomsRequired = 0;
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] interval : intervals) {
            while (!pq.isEmpty() && pq.peek() <= interval[0]) pq.poll();
            pq.offer(interval[1]);
            totalMeetingRoomsRequired = Math.max(totalMeetingRoomsRequired, pq.size());
        }
        return totalMeetingRoomsRequired;
    }
}