package bench;

public interface iBenchmark {
    void run();

    void run(Object... params);

    void initialize(Object... params);

    void clean();

    void cancel();

    void warmup();
}