package logging;

public interface iLog {
    void write(long value);

    void write(String string);

    void write(Object... values);

    void writeTime(String string, long value, TimeUnit unit);


    void close();
}