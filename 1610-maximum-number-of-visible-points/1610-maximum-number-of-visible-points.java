class Solution {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int samePoint = 0;
        List<Double> angles = new ArrayList<>();
        for (List<Integer> point : points) {
            if (point.get(0) == location.get(0) && point.get(1) == location.get(1)) samePoint++;
            else angles.add(getPolarAngle(point, location));
        }

        Collections.sort(angles);
        List<Double> temp = new ArrayList<>(angles);
        for (double a : angles) temp.add(a + 360);
        angles = temp;

        // Sliding window
        int count = 0;
        int start = 0;
        for (int end = 0; end < angles.size(); end++) {
            while (angles.get(end) - angles.get(start) > angle) 
                start++;
            count = Math.max(end - start + 1, count);            
        }
        return count + samePoint;
    }

    private double getPolarAngle(List<Integer> point, List<Integer> location) {
        double radian = Math.atan2(point.get(1) - location.get(1), point.get(0) - location.get(0));
        double angle = radian * (180 / Math.PI);
        return angle;
    }
}