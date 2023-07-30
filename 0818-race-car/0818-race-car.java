class Solution {

    public int racecar(int target) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 1, 0 }); // pos, speed, move

        while (!queue.isEmpty()) {
            int[] entry = queue.poll();
            int pos = entry[0], speed = entry[1], move = entry[2];
            if (pos == target) return move;

            int nextPos = pos + speed, nextSpeed = speed * 2;
            queue.offer(new int[] { nextPos, nextSpeed, move + 1 });

            if ((nextPos > target && speed > 0) || (nextPos < target && speed < 0)) 
                queue.offer(new int[] { pos, speed > 0 ? -1 : 1, move + 1 });
        }
        return -1;
    }
}
