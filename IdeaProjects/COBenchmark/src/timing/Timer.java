package timing;

public class Timer implements iTimer
{
    private long StartTime;
    private long EndTime;
    private long StoredTime;

    private TimerState State;

    @Override
    public void start() {
        StoredTime=0;
        resume();
    }

    @Override
    public long stop() {
        if (State == TimerState.Running)
        {
            EndTime=System.nanoTime();
            StoredTime=+(EndTime-StartTime);
            State=TimerState.Stopped;
        }
        return StoredTime;
    }

    @Override
    public void resume() {
        State=TimerState.Running;
        StartTime=System.nanoTime();
    }

    @Override
    public long pause() {
        EndTime=System.nanoTime();
        StoredTime=+(EndTime-StartTime);
        State=TimerState.Paused;
        return StoredTime;
    }
}