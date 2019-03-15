package com.thougthworks.trains;

import com.thougthworks.trains.modules.Graphics;
import com.thougthworks.trains.modules.RoutePath;
import com.thougthworks.trains.service.PathFilter;
import com.thougthworks.trains.service.impl.*;
import com.thougthworks.trains.utils.GraphBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 测试类
 * Created by zhuxiaoxiang.zhu on 19-3-15.
 */
public class GraphTest {
    private Graphics graphics;

    @Before
    public void initGraphMap() {
        String graphStr = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        graphics = GraphBuilder.buildRoutePath(graphStr);
        assert graphics.getGraphPointDistance("A", "B") == 5;
    }

    // 1. The distance of the route A-B-C.
    @Test
    public void question1Test() {
        RoutePath containsPath = new RoutePath("A", "B", "C");
        PathFilter pathFilter = new ContainsPathFilter(containsPath);
        List<RoutePath> allPaths = graphics.getAllPaths("A", "C", pathFilter);
        assert allPaths.size() == 1;
        System.out.println("Output #1: " + allPaths.get(0).getTotalWeight());
        assert allPaths.get(0).getTotalWeight() == 9;
    }

    // 2. The distance of the route A-D.
    @Test
    public void question2Test() {
        RoutePath containsPath = new RoutePath("A", "D");
        PathFilter pathFilter = new ContainsPathFilter(containsPath);
        List<RoutePath> allPaths = graphics.getAllPaths("A", "D", pathFilter);
        assert allPaths.size() == 1;
        System.out.println("Output #2: " + allPaths.get(0).getTotalWeight());
        assert allPaths.get(0).getTotalWeight() == 5;
    }

    // 3. The distance of the route A-D-C.
    @Test
    public void question3Test() {
        RoutePath containsPath = new RoutePath("A", "D", "C");
        PathFilter pathFilter = new ContainsPathFilter(containsPath);
        List<RoutePath> allPaths = graphics.getAllPaths("A", "C", pathFilter);
        assert allPaths.size() == 1;
        System.out.println("Output #3: " + allPaths.get(0).getTotalWeight());
        assert allPaths.get(0).getTotalWeight() == 13;
    }

    // 4. The distance of the route A-E-B-C-D.
    @Test
    public void question4Test() {
        RoutePath containsPath = new RoutePath("A", "E", "B", "C", "D");
        PathFilter pathFilter = new ContainsPathFilter(containsPath);
        List<RoutePath> allPaths = graphics.getAllPaths("A", "D", pathFilter);
        assert allPaths.size() == 1;
        System.out.println("Output #4: " + allPaths.get(0).getTotalWeight());
        assert allPaths.get(0).getTotalWeight() == 22;
    }

    // 5. The distance of the route A-E-D.
    @Test
    public void question5Test() {
        RoutePath containsPath = new RoutePath("A", "E", "D");
        PathFilter pathFilter = new ContainsPathFilter(containsPath);
        List<RoutePath> allPaths = graphics.getAllPaths("A", "D", pathFilter);
        assert allPaths.size() == 0;
        System.out.println("Output #5: NO SUCH ROUTE");
    }

    // 6. The number of trips starting at C and ending at C with a maximum of 3 stops.
    //    In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops)
    @Test
    public void question6Test() {
        PathFilter pathFilter = new MaxStopPathFilter(3);
        List<RoutePath> allPaths = graphics.getAllPaths("C", "C", pathFilter);
        System.out.println("Output #6: " + allPaths.size());
        assert allPaths.size() == 2;
    }

    // 7. The number of trips starting at A and ending at C with exactly 4 stops.
    // In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
    @Test
    public void question7Test() {
        PathFilter pathFilter = new ExactlyStopPathFilter(4);
        List<RoutePath> allPaths = graphics.getAllPaths("A", "C", pathFilter);
        System.out.println("Output #7: " + allPaths.size());
        assert allPaths.size() == 3;
    }

    // 8. The length of the shortest route (in terms of distance to travel) from A to C.
    @Test
    public void question8Test() {
        PathFilter pathFilter = new ShortestPathFilter();
        List<RoutePath> allPaths = graphics.getAllPaths("A", "C", pathFilter);
        assert allPaths.size() == 1;
        System.out.println("Output #8: " + allPaths.get(0).getTotalWeight());
        assert allPaths.get(0).getTotalWeight() == 9;
    }

    // 9. The length of the shortest route (in terms of distance to travel) from B to B.
    @Test
    public void question9Test() {
        PathFilter pathFilter = new ShortestPathFilter();
        List<RoutePath> allPaths = graphics.getAllPaths("B", "B", pathFilter);
        assert allPaths.size() == 1;
        System.out.println("Output #9: " + allPaths.get(0).getTotalWeight());
        assert allPaths.get(0).getTotalWeight() == 9;
    }

    // 10. The number of different routes from C to C with a distance of less than 30.
    // In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
    @Test
    public void question10Test() {
        PathFilter pathFilter = new MaxDistancePathFilter(30);
        List<RoutePath> allPaths = graphics.getAllPaths("C", "C", pathFilter);
        System.out.println("Output #10: " + allPaths.size());
        assert allPaths.size() == 7;
    }

    @Test
    public void totalQuestionTest() {
        question1Test();
        question2Test();
        question3Test();
        question4Test();
        question5Test();
        question6Test();
        question7Test();
        question8Test();
        question9Test();
        question10Test();
    }
}
