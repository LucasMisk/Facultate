package testbench;

import bench.cpu.CPUThreadedRoots;
import bench.cpu.CPUFixedPoint;
import bench.iBenchmark;
import logging.ConsoleLogger;
import logging.TimeUnit;
import logging.iLog;
import timing.Timer;
import timing.iTimer;


public class TestCPUThreadedRoots
{
    public static void main(String[] args) {
        iTimer timer = new Timer();
        iLog log = new ConsoleLogger();
        TimeUnit Second = TimeUnit.Sec;
        iBenchmark bench = new CPUThreadedRoots();
        int workload=10000000;
        bench.initialize(workload);
        bench.warmup();
        for (int i = 1; i <= 16; i = i * 2) {
            timer.start();
            bench.run(i);
            long time = timer.stop();
            log.writeTime("t[=" + i + "] Finished in ", time, Second);
            long score = bench.score(workload, i, time);
            log.write("Final score : " + score);
        }

        bench.clean();
        log.close();
    }
}