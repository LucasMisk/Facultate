package logging;

public class ConsoleLogger implements iLog
{
    @Override
    public void write(long value) {
        System.out.println(value);
    }

    @Override
    public void write(String string) {
        System.out.println(string);
    }

    @Override
    public void write(Object... values) {
        for(Object o:values)
        {
            System.out.println((Object)o.toString()+" ");
        }
    }

    @Override
    public void writeTime(String string, long value, TimeUnit unit) {
        System.out.println(string + " " + TimeUnit.ChangeTimeUnit(value,unit) + TimeUnit.generateString(unit));
    }

    @Override
    public void close() {

    }
}