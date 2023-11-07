import java.util.*;

public class AlphaBetaPruning {
    // Define constants for maximum and minimum values
    static int MAX = 1000;
    static int MIN = -1000;

    // The main function for the Alpha-Beta Pruning algorithm
    static int minmax(int depth, int nodeIndex, Boolean maximizingPlayer, int[] values, int alpha, int beta) {
        // Base case: if we've reached the specified depth in the game tree, return the value of the current node
        if (depth == 3) {
            return values[nodeIndex];
        }

        if (maximizingPlayer) {
            int best = MIN; // Initialize the best value for the maximizing player

            // Explore the child nodes (assuming there are 2 children per node)
            for (int i = 0; i < 2; i++) {
                // Recursively call minmax for the child node with the opposite player (minimizing)
                int val = minmax(depth + 1, nodeIndex * 2 + i, false, values, alpha, beta);
                best = Math.max(best, val); // Update the best value for the maximizing player
                alpha = Math.max(alpha, best); // Update alpha for pruning

                // If beta is less than or equal to alpha, prune the remaining subtree and break
                if (beta <= alpha) {
                    break;
                }
            }
            return best; // Return the best value for the maximizing player
        } else {
            int best = MAX; // Initialize the best value for the minimizing player

            // Explore the child nodes (assuming there are 2 children per node)
            for (int i = 0; i < 2; i++) {
                // Recursively call minmax for the child node with the opposite player (maximizing)
                int val = minmax(depth + 1, nodeIndex * 2 + i, true, values, alpha, beta);
                best = Math.min(best, val); // Update the best value for the minimizing player
                beta = Math.min(beta, best); // Update beta for pruning

                // If beta is less than or equal to alpha, prune the remaining subtree and break
                if (beta <= alpha) {
                    break;
                }
            }
            return best; // Return the best value for the minimizing player
        }
    }

    public static void main(String[] args) {
        // Define an array of values for the game tree
        int[] values = { 3, 5, 6, 9, 1, 2, 0, -1 };
        
        // Call the minmax function to find the optimal value for the root node
        System.out.println("The optimal value is: " + minmax(0, 0, true, values, MIN, MAX));
    }
}
