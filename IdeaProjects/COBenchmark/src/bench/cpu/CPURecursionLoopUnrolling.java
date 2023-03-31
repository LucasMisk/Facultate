package bench.cpu;

import bench.*;


public class CPURecursionLoopUnrolling implements iBenchmark{
    long start;
    long size;
    private boolean prime(long number)
    {
        if(number == 0)
            return false;
        if(number==1)
            return false;
        if(number == 2)
            return true;
        if(number % 2 == 0)
            return false;
        for(long i = 3; i *i < number; i+=2)
        {
            if(number%i==0)
                return false;
        }
        return true;

    }
    private long find_next_prime(long number, long size)
    {
        for (long i=number+1; i < size; i++)
        {
            if(prime(i))
                return i;
        }
        return -1;
    }
    private long recursive(long start, long size, int counter) {
        try {
            if (start >= size) {
                return 0;
            }
            long next_prime = find_next_prime(start, size);
            counter++;
            return next_prime + recursive(next_prime + 1, size, counter);
        } catch (StackOverflowError e) {
            //System.out.println("Reached nr " + start + "/" + size + " after " + counter + " calls.");
            return 0;
        }
        catch (NoClassDefFoundError e)
        {
            return 0;
        }
    }
    private long recursiveUnrolled(long start, int unrollLevel, long size, int counter) {
        try {
            long sum = 0;
            long next_prime = 0;
            while (start < size && unrollLevel > 0) {
                next_prime = find_next_prime(start, size);
                sum += next_prime;
                start = next_prime + 1;
                unrollLevel--;
            }
            counter++;
            if (start < size) {
                sum += recursiveUnrolled(start, unrollLevel, size, counter);
            }
            return sum;
        } catch (StackOverflowError e) {
            System.out.println("Reached nr " + start + "/" + size + " after " + counter + " calls.");
            return 0;
        }
    }
    @Override
    public void run() {

    }

    @Override
    public void run(Object... params) {
        Boolean option = (Boolean)params[0];

        if(!option)
            recursive(start, size, 0);
        else
            recursiveUnrolled(start, (Integer) params[1], size,0);
    }

    @Override
    public void initialize(Object... params) {
        start = (Integer)params[0];
        size = (Integer)params[1];
    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void warmup() {

    }
}
