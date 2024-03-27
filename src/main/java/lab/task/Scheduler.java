package lab.task;

public class Scheduler {

    private static final int TICK_DURATION_MS = 1; // Длительность одного тика в миллисекундах

    private int ticksLeft; // Оставшееся количество тиков для выполнения текущей задачи

    public Scheduler() {
        this.ticksLeft = 0; // Изначально нет активной задачи
    }

    public void startTask(int ticks) {
        if (ticksLeft > 0) {
            throw new IllegalStateException("Another task is already running.");
        }
        ticksLeft = ticks;
    }

    public void tick() {
        if (ticksLeft > 0) {
            ticksLeft--;
        }
    }

    public boolean isTaskRunning() {
        return ticksLeft > 0;
    }

    public static int getTickDuration() {
        return TICK_DURATION_MS;
    }

}
