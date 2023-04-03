package bench.cpu;

import bench.*;


public class CPURecursionLoopUnrolling implements iBenchmark{
    private long start;
    private long size;

    private long helper;
    private long helper1;
    private boolean prime(long number)
    {
        if(number == 0)
            return false;
        if(number == 1)
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
        try
        {
            if (start >= size) {
                return 0;
            }
            long next_prime = find_next_prime(start, size);
            if (next_prime == -1) {
                return 0;
            }
            counter++;
            return next_prime + recursive(next_prime, size, counter);
        } catch (StackOverflowError ignored) {

        } catch (NoClassDefFoundError e) {
        }
        helper=counter;
        helper1=start;
        return 0;
    }
    private void print_recursive(long start, long size, long counter)
    {
        System.out.println("Reached nr " + start + "/" + size + " after " + counter + " calls.");
    }
    private long recursiveUnrolled(long start, int unrollLevel, long size, int counter) {
        try {
            long sum = 0;
            long next_prime = start;
            long copy=unrollLevel;
            while (start < size && copy > 0) {
                if(next_prime<0)
                    return 0;
                sum += next_prime;
                next_prime = find_next_prime(next_prime, size);
                copy--;
            }
            counter++;
            if(next_prime>0)
            {
                helper = counter;
                helper1 = next_prime;
            }
            if (start < size) {
                return sum + recursiveUnrolled(next_prime, unrollLevel, size, counter);
            }


        } catch (StackOverflowError e) {
        }
        catch (NoClassDefFoundError e) {
        }
        return 0;
    }
    private void print_recursiveUnrolled(long start, long size, long counter)
    {
        System.out.println("Reached nr " + start + "/" + size + " after " + counter + " calls.");
    }

    @Override
    public long score(Object... params) {
        double size1 = Math.log(size)*1000;
        double number = Math.cbrt(helper1)*1000;
        double time = Math.cbrt((long)params[0]/1000000.0);
        double up = Math.sqrt(number*size1);
        return (long)Math.cbrt(up*100000000/(time));
    }

    @Override
    public void run() {

    }

    @Override
    public void run(Object... params) {
        Boolean option = (Boolean)params[0];

        if(!option) {
            recursive(start, size, 0);
            //print_recursive(helper1, size,helper);
        }
        else {
            recursiveUnrolled(start, (Integer) params[1], size, 0);
            //print_recursiveUnrolled(helper1, size,helper);
        }
    }

    @Override
    public void initialize(Object... params) {
        start = (Integer)params[0];
        size = (long)params[1];
    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void warmup() {
        initialize(1,(long)1000000);
        run(true, 5);
    }

    @Override
    public String getResult() {
        return null;
    }
}
