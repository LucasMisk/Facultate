package testbench;

import bench.cpu.CPUDigitsOfPi;
import bench.cpu.CPUFixedPoint;
import bench.iBenchmark;
import logging.ConsoleLogger;
import logging.TimeUnit;
import logging.iLog;
import timing.Timer;
import timing.iTimer;

public class TestCpuFixedPoint {
    public static void main(String[] args)
    {
        int size = 10000;
        iTimer timer = new Timer();
        iLog log = new ConsoleLogger();
        TimeUnit Milisecond = TimeUnit.Mili;
        TimeUnit Microsecond = TimeUnit.Micro;
        TimeUnit Nanosecond = TimeUnit.Nano;
        TimeUnit Second = TimeUnit.Sec;
        iBenchmark bench = new CPUFixedPoint();
        bench.initialize();
        bench.warmup();
        timer.start();
        bench.run(1, size);
        long time = timer.stop();
        log.writeTime("Finished in", time,Nanosecond);
        double timer2=time/1000000000.0;
        double MOPS = (9.0*size)/(timer2*1e6);
        log.write("MOPS value:", MOPS);
        log.close();
        bench.clean();
    }
}
