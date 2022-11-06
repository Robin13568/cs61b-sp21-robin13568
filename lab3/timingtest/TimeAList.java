package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6; // The fourth column microsec/op gives the number of microseconds it took on average to complete each call to addLast.
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>(); // The first column N gives the size of the data structure (how many elements it contains).
        AList<Double> times = new AList<>(); // The second column time (s) gives the time required to complete all operations.
        AList<Integer> opCounts = new AList<>(); // The third column # ops gives the number of calls to addLast made during the timing experiment.

        int N = 500;
        for (int i = 1; i <= 8; i++) {
            N = N * 2;
            Ns.addLast(N);

            AList<Integer> al = new AList<>();
            Stopwatch sw = new Stopwatch();
            for (int j = 1; j <= N; j++) {
                al.addLast(1);
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(N);
        }

        printTimingTable(Ns, times, opCounts);
    }
}
