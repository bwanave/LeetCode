import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char task : tasks) frequencyMap.put(task, frequencyMap.getOrDefault(task, 0) + 1);

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);
        priorityQueue.addAll(frequencyMap.values());
        Queue<int[]> coolingQueue = new LinkedList<>();
        int time = 0;
        while (!priorityQueue.isEmpty()) {
            time++;
            int frequency = priorityQueue.poll();
            frequency--;
            // System.out.println("Executed on " + time + ", remaining: " + frequency);
            if (frequency > 0) coolingQueue.offer(new int[]{time + n, frequency});

            if (!coolingQueue.isEmpty()) {
                if (coolingQueue.peek()[0] == time) priorityQueue.offer(coolingQueue.poll()[1]);
                else if (priorityQueue.isEmpty()) {
                    int[] entry = coolingQueue.poll();
                    time = entry[0];
                    priorityQueue.offer(entry[1]);
                }
            }
        }
        return time;
    }
}