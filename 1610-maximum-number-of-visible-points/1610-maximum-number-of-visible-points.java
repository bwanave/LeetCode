class Solution {
    public int visiblePoints(List<List<Integer>> points, int allowedAngle, List<Integer> location) {
        int samePoints = 0;
        List<Double> angles = new ArrayList<>();
        for (List<Integer> point : points) {
            if (point.get(0) == location.get(0) && point.get(1) == location.get(1)) samePoints++;
            else angles.add(getPolarAngle(point, location));
        }
        Collections.sort(angles);
        int n = angles.size();
        for (int i = 0; i < n; i++) angles.add(angles.get(i) + 360);
        
        int visiblePoints = 0;
        int start = 0;
        for (int end = 0; end < angles.size(); end++) {
            while (angles.get(end) - angles.get(start) > allowedAngle)
                start++;
            visiblePoints = Math.max(visiblePoints, end - start + 1);
        }
        return visiblePoints + samePoints;
    }
    
    private double getPolarAngle(List<Integer> point, List<Integer> location) {
        double radian = Math.atan2(point.get(1) - location.get(1), point.get(0) - location.get(0));
        double angle = radian * (180 / Math.PI);
        return angle;
    }
}