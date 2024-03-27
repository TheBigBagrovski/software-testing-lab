package lab.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task implements Comparable<Task> {

    private PriorityLevel priorityLevel;
    private State currentState = State.READY;

    @Override
    public int compareTo(Task task) {
        return comparePriority(task);
    }

    public int comparePriority(Task o) {
        return o.priorityLevel.ordinal() - this.priorityLevel.ordinal();
    }


}
