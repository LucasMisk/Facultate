package bench;

import java.util.Random;

public class DemoBenchmark implements iBenchmark {
    private int n;
    private int[] array;

    @Override
    public long score(Object... params) {
        return 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < n-1; i++)
            for (int j = i+1; j < n; j++)
            {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
    }

    @Override
    public void warmup() {

    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public void run(Object... params)
    {

    }

    @Override
    public void initialize(Object... params) {
        Random random = new Random();
        // cast first parameter to integer
        this.n = (Integer) params[0];
        this.array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = random.nextInt(1000);

    }

    @Override
    public void clean() {
        array = null;
        System.gc();
    }

    @Override
    public void cancel() {

    }
}
