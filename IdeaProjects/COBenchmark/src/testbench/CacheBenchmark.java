package testbench;

import bench.cpu.*;
import bench.DemoBenchmark;
import bench.iBenchmark;
import logging.ConsoleLogger;
import logging.TimeUnit;
import logging.iLog;
import timing.Timer;
import timing.iTimer;

public class CacheBenchmark {
    public static void main(String[] args) {
        iTimer timer = new Timer();
        iLog log = new ConsoleLogger();
        TimeUnit Milisecond = TimeUnit.Mili;
        TimeUnit Microsecond = TimeUnit.Micro;
        TimeUnit Second = TimeUnit.Sec;
        iBenchmark bench = new CPURecursionLoopUnrolling();
        long sum=0;
        long size = 10;
        int unrollLevel = 5;
        bench.warmup();
        for (int i = 0; i < 10; i++)
        {
            bench.initialize(1, (long)size);
            timer.start();
            bench.run(true, unrollLevel);
            long time = timer.stop();
            long score = bench.score(time);
            sum+=score;
            //log.write("Score : " + score);
            size=size*4;
        }
        long finalScore = sum/10;
        log.write("Final score : " + finalScore);
        log.close();
        bench.clean();
    }
}
