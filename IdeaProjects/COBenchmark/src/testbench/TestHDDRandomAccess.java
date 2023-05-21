package testbench;

import bench.*;
import bench.hdd.HDDRandomAccess;
import logging.*;
public class TestHDDRandomAccess {
    public static void main(String[] args)
    {
        iLog log = new ConsoleLogger();
        iBenchmark bench = new HDDRandomAccess();
        long fileSize = 1024 * 1024 * 1024L; //1GB
        int bufferSize = 1024 * 1024; // 4KB
        bench.initialize(fileSize);
        bench.run("r", "fs", bufferSize);
        log.write(bench.getResult());
        bench.clean();
        log.close();
    }
}
