package timing;

public interface iTimer {
    void start();

    long stop();

    void resume();

    long pause();
}