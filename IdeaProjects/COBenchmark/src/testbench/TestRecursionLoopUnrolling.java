package testbench;

import bench.cpu.*;
import bench.DemoBenchmark;
import bench.iBenchmark;
import logging.ConsoleLogger;
import logging.TimeUnit;
import logging.iLog;
import timing.Timer;
import timing.iTimer;

public class TestRecursionLoopUnrolling {
    public static void main(String[] args)
    {
        iTimer timer = new Timer();
        iLog log = new ConsoleLogger();
        TimeUnit Milisecond = TimeUnit.Mili;
        TimeUnit Microsecond = TimeUnit.Micro;
        TimeUnit Second = TimeUnit.Sec;

        iBenchmark bench = new CPURecursionLoopUnrolling();
        bench.initialize(1, 1000000000);
        timer.start();
        bench.run(false);
        long time = timer.stop();
        log.writeTime("Finished in", time, Milisecond);
        timer.start();
        bench.run(true,5);
        long time1 = timer.stop();
        log.writeTime("Finished in", time1, Milisecond);
        log.close();
        bench.clean();
    }
}
