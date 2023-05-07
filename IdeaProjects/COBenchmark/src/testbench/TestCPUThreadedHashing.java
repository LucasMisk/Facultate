package testbench;

import bench.cpu.CPUThreadedHashing;
import bench.cpu.CPUFixedPoint;
import bench.iBenchmark;
import logging.ConsoleLogger;
import logging.TimeUnit;
import logging.iLog;
import timing.Timer;
import timing.iTimer;
public class TestCPUThreadedHashing {
    public static void main(String[] args) {
        iBenchmark bench = new CPUThreadedHashing();
        int maxLength = 10;
        int nThreads = 16;
        int hashCode = 317266982;

        //317266982
        //1018655712 = break
        //
        TimeUnit Milisecond = TimeUnit.Mili;
        TimeUnit Microsecond = TimeUnit.Micro;
        TimeUnit Nanosecond = TimeUnit.Nano;
        TimeUnit Second = TimeUnit.Sec;
        iTimer timer = new Timer();
        iLog log = new ConsoleLogger();
        timer.start();

        bench.run(maxLength, nThreads, hashCode);
        long time = timer.stop();
        log.writeTime("Finished in", time, Second);
        log.write("Result is", bench.getResult());
    }
}
