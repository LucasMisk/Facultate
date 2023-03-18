package logging;

public enum TimeUnit
{
    Micro, Mili, Sec, Nano;
    public static double ChangeTimeUnit( long time, TimeUnit unit)
    {
        switch (unit)
        {
            case Micro:
                return time/1000.0;
            case Mili:
                return time/1000000.0;
            case Sec:
                return time/1000000000.0;
            default:
                return time;
        }
    }
    public static String generateString(TimeUnit unit)
    {
        switch (unit)
        {
            case Micro:
                return "us";
            case Mili:
                return "ms";
            case Sec:
                return "s";
            default:
                return "ns";
        }
    }
}