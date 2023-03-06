package testbench;


import logging.*;
import timing.*;
import bench.*;

public class TestDemoBench
{
    public static void main(String[] args)
    {
        iTimer timer = new Timer();
        iLog log = new ConsoleLogger();
        TimeUnit Milisecond = TimeUnit.Mili;
        TimeUnit Microsecond = TimeUnit.Micro;
        TimeUnit Second = TimeUnit.Sec;

        iBenchmark bench = new DemoBenchmark();
        bench.initialize(100);
        timer.start();
        bench.run();
        long time = timer.stop();
        log.writeTime("Finished in", time, Microsecond);
        log.writeTime("Finished in", time, Milisecond);
        log.writeTime("Finished in", time, Second);
        log.close();
        bench.clean();
    }
}
