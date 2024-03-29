package lab.task;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Task implements Runnable, Comparable<Task> {

    private int id;
    private int requiredTicks;
    private PriorityLevel priority;
    private State currentState = State.SUSPENDED;
    private boolean running;

    private final Processor processor;

    public Task(int id, int requiredTicks, PriorityLevel priority, Processor processor) {
        this.id = id;
        this.requiredTicks = requiredTicks;
        this.priority = priority;
        this.processor = processor;
    }

    @Override
    public void run() {
        start();
        setRunning(true);
        while (isRunning()) {
            try {
                Processor.waitForTickComplete();
                if (getRequiredTicks() > 0) {
                    decTicks();
                    System.out.println("До завершения задачи " + this + " осталось " + getRequiredTicks() + " тактов");
                    if (getRequiredTicks() == 0) {
                        System.out.println("ЗАДАЧА " + this + " ВЫПОЛНЕНА на такте " + processor.getCurrentTick());
                        preempt();
                        setRunning(false);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(this + " прервана на такте " + processor.getCurrentTick() + ", осталось тактов " + requiredTicks);
                terminate();
                setRunning(false);
            }
        }
    }

    public void decTicks() {
        requiredTicks--;
    }

    public void preempt() {
        if (currentState == State.RUNNING) {
            currentState = State.READY;
        } else {
            System.out.println("WRONG TRANSITION running-ready " + this);
        }
    }

    public void start() {
        if (currentState == State.READY) {
            currentState = State.RUNNING;
        } else {
            System.out.println("WRONG TRANSITION ready-running " + this + "was: " + getCurrentState());
        }
    }

    public void terminate() {
        if (currentState == State.RUNNING) {
            currentState = State.SUSPENDED;
        } else {
            System.out.println("WRONG TRANSITION running-suspended " + this);
        }
    }

    public void activate() {
        if (currentState == State.SUSPENDED) {
            currentState = State.READY;
        } else {
            System.out.println("WRONG TRANSITION suspended-ready " + this + "was: " + this.currentState);
        }
    }

    public void release() {}

    public void waiting() {}

    @Override
    public int compareTo(Task o) {
        return o.priority.ordinal() - this.priority.ordinal();
    }

    @Override
    public String toString() {
        return "Task #" + id + "{" +
                ", requiredTicks=" + requiredTicks +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && requiredTicks == task.requiredTicks && priority == task.priority && currentState == task.currentState && Objects.equals(processor, task.processor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requiredTicks, priority, currentState, processor);
    }

    public Task goWaiting() {
        return this;
    }

}