package bench.cpu;

import bench.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

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

    private BigDecimal PIHighPrecision(int precision) {
        BigDecimal PI = BigDecimal.ZERO;
        BigDecimal sign = BigDecimal.ONE;
        BigDecimal numerator = BigDecimal.valueOf(4);

        for (int n = 1; n < precision; n=n+2) {
            BigDecimal denominator = BigDecimal.valueOf(n + 1L)
                    .multiply(BigDecimal.valueOf(n + 2L))
                    .multiply(BigDecimal.valueOf(n + 3L));
            BigDecimal term = numerator.divide(denominator, precision, RoundingMode.HALF_UP)
                    .multiply(sign);
            PI = PI.add(term);
            sign = sign.negate();
        }

        return PI.add(BigDecimal.valueOf(3));
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
                BigDecimal result1=PIHighPrecision((Integer)params[1]);
                System.out.println(result1);
                break;
            case 2:
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

    public void warmup()
    {
        BigDecimal result=PIHighPrecision(10000);
    }
}
