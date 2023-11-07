package com.LabPractical;

import java.util.function.BiFunction;
import java.util.*;

class Hill_Climbing {

    // Function to find the minimum of a given function using Hill Climbing
    public static double findMinimum(BiFunction<Double, Double, Double> f) {
        // Start at the origin (0,0) as the initial guess
        double curX = 0;
        double curY = 0;
        // Calculate the value of the function at the initial point
        double curF = f.apply(curX, curY);

        // Loop for optimizing the function value
        for (double step = 1e6; step > 1e-7;) {
            double bestF = curF;
            double bestX = curX;
            double bestY = curY;
            boolean find = false;

            // Try moving in six different directions (hexagon pattern)
            for (int i = 0; i < 6; i++) {
                double a = 2 * Math.PI * i / 6;
                // Calculate the next potential position by moving in the current direction
                double nextX = curX + step * Math.cos(a);
                double nextY = curY + step * Math.sin(a);
                // Calculate the value of the function at the potential position
                double nextF = f.apply(nextX, nextY);
                // If the function value at the potential position is lower, update the best values
                if (bestF > nextF) {
                    bestF = nextF;
                    bestX = nextX;
                    bestY = nextY;
                    find = true;
                }
            }

            // If no better solution is found in any direction, reduce the step size
            if (!find) {
                step /= 2;
            } else {
                // Update the current position with the best found position
                curX = bestX;
                curY = bestY;
                curF = bestF;
            }
        }
        
        // Print the final minimum position (x, y) and return the minimum function value
        System.out.println("Minimum at: (" + curX + ", " + curY + ")");
        return curF;
    }

    public static void main(String[] args) {
        // Example usage: find the minimum of the function (x - 2)^2 + (y - 3)^2
        System.out.println(findMinimum((x, y) -> (x - 2) * (x - 2) + (y - 3) * (y - 3)));
    }
}
