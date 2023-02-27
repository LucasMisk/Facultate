package logging;

public interface iLog {
    void write(long value);

    void write(String string);

    void write(Object... values);

    void close();
}