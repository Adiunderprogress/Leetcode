class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest x and y coordinates on the rectangle to the circle's center
        int nearestX = Math.max(x1, Math.min(xCenter, x2));
        int nearestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Calculate the difference along x and y axes
        int dx = nearestX - xCenter;
        int dy = nearestY - yCenter;
        
        // Check if the squared distance is within the squared radius
        return (dx * dx + dy * dy) <= radius * radius;
    }
}