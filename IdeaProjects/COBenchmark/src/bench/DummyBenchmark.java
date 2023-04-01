package bench;

public class DummyBenchmark implements iBenchmark
{

    @Override
    public long score(Object... params) {
        return 0;
    }

    @Override
    public void run() {

    }

    @Override
    public void warmup() {

    }

    @Override
    public void run(Object... params) {

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
}