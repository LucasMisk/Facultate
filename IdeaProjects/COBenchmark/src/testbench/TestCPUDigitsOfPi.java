package testbench;

import bench.cpu.CPUDigitsOfPi;
import logging.*;
import timing.*;
import bench.*;
public class TestCPUDigitsOfPi
{
    public static void main(String[] args)
    {
        iTimer timer = new Timer();
        iLog log = new ConsoleLogger();
        TimeUnit Milisecond = TimeUnit.Mili;
        TimeUnit Microsecond = TimeUnit.Micro;
        TimeUnit Nanosecond = TimeUnit.Nano;
        TimeUnit Second = TimeUnit.Sec;
        iBenchmark bench = new CPUDigitsOfPi();
        bench.initialize();
        bench.warmup();
        timer.start();
        bench.run(1,100000);
        long time = timer.stop();
        log.writeTime("Finished in", time, Milisecond);
        log.close();
        bench.clean();
    }
}
