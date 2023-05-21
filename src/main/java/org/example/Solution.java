package org.example;

import java.util.*;

public class Solution {
    public int shortestBridge(int[][] grid) {
        if (grid == null) return 0;
        int i = 0;
        int j = 0;
        outerLoop:
        for (; i < grid.length; i++) {
            for (j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    break outerLoop;
                }
            }
        }
        // i and j is coordinates of the start of the first island. Now we need to find its boundaries.
        // Let's try BFS and store the island tiles coordinates presented {i, j} in set.
        Set<List<Integer>> firstIsland = bfsFindingIslandTiles(grid, i, j);
        //we have first island. Now we need to find the shortest path to any land (1) tile
        // that doesn't belong to the first island. Let's try it using bfs again.
        int shortestPath = Integer.MAX_VALUE;
        for (List<Integer> start : firstIsland) {
            int shortestPathForCurrentTile = shortestPathBFS(start, grid, firstIsland);
            if (shortestPathForCurrentTile != -1) {
                shortestPath = Math.min(shortestPath, shortestPathForCurrentTile);
            }
        }
        return shortestPath;
    }

    private Set<List<Integer>> bfsFindingIslandTiles(int[][] grid, int i, int j) {
        Set<List<Integer>> islandCoordinates = new HashSet<>();
        List<Integer> start = new ArrayList<>(List.of(i, j));
        islandCoordinates.add(start);
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(start);

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            List<Integer> currentCoordinate = queue.poll();
            int x = currentCoordinate.get(0);
            int y = currentCoordinate.get(1);

            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 1 && !visited[newX][newY]) {
                    List<Integer> tileBelongs = new ArrayList<>(List.of(newX, newY));
                    queue.offer(tileBelongs);
                    islandCoordinates.add(tileBelongs);
                    visited[newX][newY] = true;
                }
            }
        }

        return islandCoordinates;
    }

    private int shortestPathBFS(List<Integer> start, int[][] grid, Set<List<Integer>> island) {
        Queue<List<Integer>> queue = new LinkedList<>();
        boolean[][] visited= new boolean[grid.length][grid[0].length];

        visited[start.get(0)][start.get(1)] = true;

        queue.offer(start);
        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                List<Integer> currentPosition = queue.poll();
                if (grid[currentPosition.get(0)][currentPosition.get(1)] == 1 && !island.contains(currentPosition))
                    return pathLength - 1;
                // NORTH
                if (currentPosition.get(0) != 0 && !visited[currentPosition.get(0) - 1][currentPosition.get(1)]
                        && !island.contains(new ArrayList<>(List.of(currentPosition.get(0) - 1, currentPosition.get(1))))) {
                    queue.offer(new ArrayList<>(List.of(currentPosition.get(0) - 1, currentPosition.get(1))));
                    visited[currentPosition.get(0) - 1][currentPosition.get(1)] = true;
                }
                //SOUTH

                if (currentPosition.get(0) != grid.length - 1 && !visited[currentPosition.get(0) + 1][currentPosition.get(1)]
                        && !island.contains(new ArrayList<>(List.of(currentPosition.get(0) + 1, currentPosition.get(1))))) {
                    queue.offer(new ArrayList<>(List.of(currentPosition.get(0) + 1, currentPosition.get(1))));
                    visited[currentPosition.get(0) + 1][currentPosition.get(1)] = true;
                }
                //WEST
                if (currentPosition.get(1) != 0 && !visited[currentPosition.get(0)][ currentPosition.get(1) - 1]
                        && !island.contains(new ArrayList<>(List.of(currentPosition.get(0), currentPosition.get(1) - 1)))) {
                    queue.offer(new ArrayList<>(List.of(currentPosition.get(0), currentPosition.get(1) - 1)));
                    visited[currentPosition.get(0)][currentPosition.get(1) - 1] = true;
                }
                //EAST
                if (currentPosition.get(1) != grid[0].length - 1 && !visited[currentPosition.get(0)][currentPosition.get(1) + 1]
                        && !island.contains(new ArrayList<>(List.of(currentPosition.get(0), currentPosition.get(1) + 1)))) {
                    queue.offer(new ArrayList<>(List.of(currentPosition.get(0), currentPosition.get(1) + 1)));
                    visited[currentPosition.get(0)][currentPosition.get(1) + 1] = true;
                }

            }
            pathLength++;
        }
        return -1;
    }
}


