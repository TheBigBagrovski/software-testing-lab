package lab.task;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Callable;

@Getter
@Setter
public class Task implements Comparable<Task>, Callable<Task> {

    private PriorityLevel priorityLevel;
    private State currentState = State.READY;

    @Override
    public Task call() throws Exception {
        return null;
    }

    @Override
    public int compareTo(Task task) {
        return comparePriority(task);
    }

    public int comparePriority(Task o) {
        return o.priorityLevel.ordinal() - this.priorityLevel.ordinal();
    }

}
