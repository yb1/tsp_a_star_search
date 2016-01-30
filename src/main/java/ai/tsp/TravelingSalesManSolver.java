package ai.tsp;

import ai.AStarSearch;
import ai.Node;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by youngbinkim on 1/26/16.
 */
public class TravelingSalesManSolver {
    public static void main(String ...args) {

        TravelingSalesManSolver solver = new TravelingSalesManSolver();


        for (int i = 1; i <= 16; i++) {
            long start = System.currentTimeMillis();
            solver.processFilesInDir(String.valueOf(i));
            long end = System.currentTimeMillis();
            System.out.println("Time taken : " + (((end - start) * 1.0) / 1000));
        }

        solver.processFile("5/instance_2.txt");
    }

    public void processFilesInDir(final String dirName) {
        File folder = new File(getClass().getClassLoader().getResource(dirName).getFile());
        double sum = 0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                System.err.println("What is directory doing in here? " + fileEntry.getName());
            } else {
                sum += processFile(dirName + "/" + fileEntry.getName());
            }
        }
        System.out.println("Total average for dir : " + dirName + ": " + sum / folder.listFiles().length);
    }

    public int processFile(final String fileName) {
        TravelingSalesManProblem problem = new TravelingSalesManProblem();
        problem.initiateProblem(fileName);

        AtomicInteger numNodes = new AtomicInteger(1);
        Node node = new AStarSearch().run(problem, numNodes);
        //System.out.println("# of Node: " +numNodes);
        node.printPath();
        return numNodes.get();
    }
}
