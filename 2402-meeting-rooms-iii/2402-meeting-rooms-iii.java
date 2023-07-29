class Solution {
    public int mostBooked(int n, int[][] meetings) {
        PriorityQueue<int[]> meetingQueue = new PriorityQueue<>((m1, m2) -> m1[1] == m2[1] ? m1[2] - m2[2] : m1[1] - m2[1]);
        PriorityQueue<Integer> roomQueue = new PriorityQueue<>();
        for (int room = 0; room < n; room++) roomQueue.offer(room);
        
        Arrays.sort(meetings, (m1, m2) -> m1[0] - m2[0]);
        int[] meetingCount = new int[n];
        for (int[] m : meetings) {
            int start = m[0], end = m[1];
            while (!meetingQueue.isEmpty() && meetingQueue.peek()[1] <= start) roomQueue.offer(meetingQueue.poll()[2]);
            
            if (roomQueue.isEmpty()) {
                int diff = end - start;
                int[] completedMeeting = meetingQueue.poll();
                roomQueue.offer(completedMeeting[2]);
                start = completedMeeting[1];
                end = start + diff;
            }
            
            int room = roomQueue.poll();
            meetingQueue.offer(new int[] {start, end, room});
            meetingCount[room]++;
        }
        
        System.out.println(Arrays.toString(meetingCount));
        
        int roomWithMostMeetings = 0;
        for (int room = 1; room < n; room++) 
            if (meetingCount[room] > meetingCount[roomWithMostMeetings]) roomWithMostMeetings = room;
        return roomWithMostMeetings;
    }
}