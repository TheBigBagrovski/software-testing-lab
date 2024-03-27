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
    private Integer ticks;
    private final PriorityLevel priority;
    private final String uuid = UUID.randomUUID().toString();

    @Override
    public int compareTo(Task o) {
        return 0;
    }

    @Override
    public Task call() throws Exception {
        return null;
    }

    @Override
    public String toString() {
        return "Task{" +
                "priority=" + priority +
                ", ticks=" + ticks +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}

