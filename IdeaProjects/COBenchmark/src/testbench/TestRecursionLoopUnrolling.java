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
        long size = 1000000000;
        iBenchmark bench = new CPURecursionLoopUnrolling();
        bench.initialize(1, (long)size);
        timer.start();
        bench.run(false);
        long time = timer.stop();
        log.writeTime("Finished in", time, Milisecond);
        bench.initialize(1, (long)size);
        int unrollLevel=5;
        timer.start();
        bench.run(true,unrollLevel);
        long time1 = timer.stop();
        log.writeTime("Finished in", time1, Milisecond);
        long score=bench.score(time1,unrollLevel);
        log.write("Score : " + score);
        log.close();
        bench.clean();
    }
}

//score = calls/time
