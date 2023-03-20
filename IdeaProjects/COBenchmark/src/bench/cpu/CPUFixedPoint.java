package bench.cpu;

import bench.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
public class CPUFixedPoint implements iBenchmark{

    void array_test(int size)
    {
        int k;
        int[] array1=new int [size];
        int[] array2=new int [size];
        for(int i=0; i<size; i++)
        {
            k=array2[i];
            array2[i]=array1[i];
            array1[i] = k;
        }
    }

    private void branch_test(int size)
    {
        int[] num = {0, 1, 2, 3};
        int count=0;
        int j=0;
        while (count<size)
        {
            if (j == 1) {
                j = num[2];
            } else {
                j = num[3];
            }
            if (j > 2) {
                j = num[0];
            } else {
                j = num[1];
            }
            if (j < 1) {
                j = num[1];
            } else {
                j = num[0];
            }
            count++;
        }
    }


    @Override
    public void run() {
        throw new IllegalStateException();
    }

    @Override
    public void run(Object... params) {
        int options = (Integer)params[0];

        switch(options) {
            case 0:
                branch_test((Integer)params[1]);
                break;
            case 1:
                array_test((Integer)params[1]);
                break;
            default:
                throw new IllegalArgumentException("Unknown option");
        }
    }

    @Override
    public void initialize(Object... params) {

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
