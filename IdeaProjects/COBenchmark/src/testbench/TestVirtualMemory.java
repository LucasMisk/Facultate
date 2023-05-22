package testbench;

import bench.*;
import bench.hdd.HDDRandomAccess;
import bench.ram.VirtualMemoryBenchmark;
import logging.*;
public class TestVirtualMemory {
    public static void main(String[] args)
    {
        iLog log = new ConsoleLogger();
        iBenchmark bench = new VirtualMemoryBenchmark();
        long fileSize = 10 * 1024 * 1024 * 1024L; //1GB
        int bufferSize = 4 * 1024; // 4KB

        bench.run(fileSize, bufferSize);
        log.write(bench.getResult());

        bench.clean();
        log.close();
    }
}
