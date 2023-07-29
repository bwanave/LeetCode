class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // end time
        int rooms = 0;
        for (int[] interval : intervals) {
            while (!pq.isEmpty() && pq.peek() <= interval[0]) 
                pq.poll();
            pq.offer(interval[1]);
            rooms = Math.max(rooms, pq.size());
        }
        return rooms;
    }
}