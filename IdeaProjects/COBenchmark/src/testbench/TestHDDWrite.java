package testbench;
import bench.hdd.*;
import logging.*;
import timing.*;
import bench.*;
public class TestHDDWrite {
    public static void main(String[] args)
    {
        iBenchmark bench= new HDDWriteSpeed();
        bench.run("fb", true);
        bench.clean();
    }
}
