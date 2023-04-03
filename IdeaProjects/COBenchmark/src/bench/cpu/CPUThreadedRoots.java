
package bench.cpu;

import bench.iBenchmark;

public class CPUThreadedRoots implements iBenchmark {

    private double result;
    private int size;
    private boolean running;

    @Override
    public void initialize(Object... params) {
        // save size from params array
        size = (int) params[0];
    }

    @Override
    public void warmup() {
        int cores = Runtime.getRuntime().availableProcessors();
        run(cores);
    }

    @Override
    public long score(Object... params) {
        int workload = (int) params[0];
        int cores = (int) params[1];
        long time = (long) ((long)params[2]/1000000.0);
        return (long) (workload/(cores * time));
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Method not implemented. Use run(Objects...) instead");
    }

    @Override
    public void run(Object... options) {
        // options[0] -> number of threads
        // ...

        int nThreads = (int) options[0];
        Thread[] threads = new Thread[nThreads];

        // e.g. 1 to 10,000 on 4 threads = 2500 jobs per thread
        final int jobPerThread = size / nThreads;

        running = true; // flag used to stop all started threads
        // create a thread for each runnable (SquareRootTask) and start it
        int j = 1;
        for (int i = 0; i < nThreads; ++i) {
            Thread thread = new Thread(new SquareRootTask(j, j + jobPerThread));
            j = j + jobPerThread;
            thread.start();
            threads[i] = thread;
        }

        // join threads
        for (int i = 0; i < nThreads; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void clean() {
        // only implement if needed
    }

    @Override
    public void cancel() {
        running = false;
    }


    @Override
    public String getResult() {
        return String.valueOf(result);
    }

    class SquareRootTask implements Runnable {

        private int from, to;
        private final double precision = 1e-4; // fixed
        private double result = 0.0;

        public SquareRootTask(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            for (int i = from; i < to && running; i++) {
                result += getNewtonian(i);
            }
            addResult(result);
        }

        private synchronized void addResult(double value) {
            result += value;
        }

        private double getNewtonian(double x) {
            double guess = x;
            double epsilon = 0.0001;
            double error = Math.abs(guess * guess - x);
            if (error <= epsilon) {
                return guess; // if so, return the guess as the square root
            } else {
                double newGuess = (guess + x / guess) / 2.0;
                return getNewtonian(newGuess);
            }
        }
    }
}