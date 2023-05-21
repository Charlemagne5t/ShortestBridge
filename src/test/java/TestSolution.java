import org.example.Solution;
import org.junit.Assert;
import org.junit.Test;

public class TestSolution {
    @Test
    public void shortestBridgeTest1() {
        int[][] grid = {
                {0, 1},
                {1, 0}
        };
        Assert.assertEquals(1, new Solution().shortestBridge(grid));
    }

    @Test
    public void shortestBridgeTest2() {
        int[][] grid = {
                {0, 1, 0},
                {0, 0, 0},
                {0, 0, 1}
        };
        Assert.assertEquals(2, new Solution().shortestBridge(grid));
    }

    @Test
    public void shortestBridgeTest3() {
        int[][] grid = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        Assert.assertEquals(1, new Solution().shortestBridge(grid));
    }
}
