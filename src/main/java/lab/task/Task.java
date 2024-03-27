package lab.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.Callable;

@Slf4j
@Getter
@AllArgsConstructor
@Builder
public class Task implements Callable<Task>, Comparable<Task> {

    private State currentState;
    private Integer ticksToComplete;
    private final PriorityLevel priority;
    private final String uuid = UUID.randomUUID().toString();

    @Override
    public int compareTo(Task o) {
        return o.priority.ordinal() - this.priority.ordinal();
    }

    @Override
    public Task call() throws Exception {
        return null;
    }

    @Override
    public String toString() {
        return "Task{" +
                "priority=" + priority +
                ", ticks=" + ticksToComplete +
//                ", uuid='" + uuid + '\'' +
                '}';
    }
}

