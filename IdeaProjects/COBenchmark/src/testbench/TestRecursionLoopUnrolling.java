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
        int size = 1000000000;
        iBenchmark bench = new CPURecursionLoopUnrolling();
        bench.initialize(1, size);
        timer.start();
        bench.run(false);
        long time = timer.stop();
        log.writeTime("Finished in", time, Milisecond);
        bench.initialize(1, size);
        timer.start();
        bench.run(true,5);
        long time1 = timer.stop();
        log.writeTime("Finished in", time1, Milisecond);
        long score=bench.score(time1);
        log.write("Score : " + score);
        log.close();
        bench.clean();
    }
}

//score = calls/time
