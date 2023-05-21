package org.example;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    public int shortestBridge(int[][] grid) {
        //TODO
        int i = 0;
        int j = 0;
        for (; i < grid.length; i++) {
            for (; j < grid[i].length; j++) {
                if(grid[i][j] == 1) break;
            }
        }
        // i and j is coordinates of the start of the first island. Now we need to find its boundaries.
        // Let's try BFS and store the island tiles coords presented { i, j} in set.

        return 0;
    }

    private static Set<Integer[]> bfs(int[][] grid, int i, int j){
        Set<Integer[]> firstIsland = new HashSet<>();
        Integer[] start = {i, j};

        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()){
            Integer[] currentCoordinate = queue.poll();
            if (currentCoordinate[0] != 0 && grid[currentCoordinate[0] - 1][currentCoordinate[1]] == 1 && !firstIsland.contains(new Integer[]{currentCoordinate[0] - 1, currentCoordinate[1]})){
                Integer[] tileBelongs = new Integer[]{currentCoordinate[0] - 1, currentCoordinate[1]};
                queue.offer(tileBelongs);
                firstIsland.add(tileBelongs);
            }

            if (currentCoordinate[0] != grid.length - 1 && grid[currentCoordinate[0] + 1][currentCoordinate[1]] == 1 && !firstIsland.contains(new Integer[]{currentCoordinate[0] + 1, currentCoordinate[1]})) {
                Integer[] tileBelongs = new Integer[]{currentCoordinate[0] + 1, currentCoordinate[1]};
                queue.offer(tileBelongs);
                firstIsland.add(tileBelongs);
            }

            if (currentCoordinate[1] != 0 && grid[currentCoordinate[0]][currentCoordinate[1] - 1] == 1 && !firstIsland.contains(new Integer[]{currentCoordinate[0], currentCoordinate[1] - 1})) {
                Integer[] tileBelongs = new Integer[]{currentCoordinate[0], currentCoordinate[1] - 1};
                queue.offer(tileBelongs);
                firstIsland.add(tileBelongs);
            }

            if (currentCoordinate[1] != grid[0].length - 1 && grid[currentCoordinate[0]][currentCoordinate[1] + 1] == 1 && !firstIsland.contains(new Integer[]{currentCoordinate[0], currentCoordinate[1] + 1})) {
                Integer[] tileBelongs = new Integer[]{currentCoordinate[0], currentCoordinate[1] + 1};
                queue.offer(tileBelongs);
                firstIsland.add(tileBelongs);
            }
            }
        return firstIsland;
        }


    }
}
