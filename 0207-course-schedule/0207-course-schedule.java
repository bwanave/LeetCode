class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] p : prerequisites)
            adjList.computeIfAbsent(p[1], key -> new ArrayList<>()).add(p[0]);

        int[] colors = new int[numCourses]; // 1: BLACK (Visited), 0: WHITE (Unvisited), -1: GRAY (Visiting)
        for (int course = 0; course < numCourses; course++) {
            if (hasCycle(course, adjList, colors)) return false;
        }
        return true;
    }

    private boolean hasCycle(int course, Map<Integer, List<Integer>> adjList, int[] colors) {
        if (colors[course] == 1) return false;
        if (colors[course] == -1) return true; // Found cycle
        colors[course] = -1;
        for (int nextCourse : adjList.getOrDefault(course, Collections.emptyList())) {
            if (hasCycle(nextCourse, adjList, colors)) return true;
        }
        colors[course] = 1;
        return false;
    }
}