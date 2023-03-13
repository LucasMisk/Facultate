package bench.cpu;

import bench.*;
import java.math.BigDecimal;
import java.math.BigInteger;
public class CPUDigitsOfPi implements iBenchmark {
    private double PILowPrecision(double PI, double n,
                              double sign)
    {
        for (int i = 0; i <= 1000000; i++) {
            PI = PI + (sign * (4 / ((n) * (n + 1)
                    * (n + 2))));


            sign = sign * (-1);

            n += 2;
        }

        return PI;
    }

    private static BigDecimal PiLongPrecision(int scale) {
        int digits = scale + 10;
        BigDecimal a = BigDecimal.valueOf(1L << digits).setScale(0, BigDecimal.ROUND_HALF_UP);
        BigDecimal b = BigDecimal.valueOf(0);
        BigDecimal c = BigDecimal.valueOf(1);
        BigDecimal d = BigDecimal.valueOf(1);
        BigDecimal e = BigDecimal.valueOf(0);
        BigDecimal f = BigDecimal.valueOf(0);
        BigDecimal g = BigDecimal.valueOf(0);
        BigInteger h;
        int i = 0;
        while (true) {
            h = BigInteger.valueOf(2L).multiply(BigInteger.valueOf(i)).add(BigInteger.ONE);
            a = a.multiply(c).divide(d, digits, BigDecimal.ROUND_HALF_UP);
            b = b.multiply(c).divide(d, digits, BigDecimal.ROUND_HALF_UP);
            d = d.add(e);
            c = c.add(f);
            e = e.add(g);
            f = f.add(BigDecimal.valueOf(1L));
            g = g.add(BigDecimal.valueOf(2L));
            i++;
            if (a.compareTo(b) >= 0) {
                break;
            }
        }
        return a.add(b).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public void run() {

    }

    @Override
    public void run(Object... params) {
        int options = (Integer)params[0];

        switch(options) {
            case 0:
                double result=PILowPrecision(3,2,1);
                System.out.println(result);
                break;
            case 1:
                BigDecimal pilong=PiLongPrecision(1000);
                System.out.println(pilong);
                break;
            case 2:
                //alg3();
                break;
            default:
                throw new IllegalArgumentException("Unknown option");
        }
    }

    @Override
    public void initialize(Object... params)
    {

    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }
}
