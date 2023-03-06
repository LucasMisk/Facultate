package testbench;


import logging.*;
import timing.*;
import bench.*;

public class TestDemoBench
{
    static void main(String[] args)
    {
        iTimer timer = new Timer();
        iLog log = new ConsoleLogger();
        iBenchmark bench = new DemoBenchmark();
        bench.initialize(100);
        timer.start();
        bench.run();
        long time = timer.stop();
        log.write("Finished in", time, "ns");
        log.close();
        bench.clean();
    }
}
