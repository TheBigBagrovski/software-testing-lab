package lab.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.Callable;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Task implements Comparable<Task> {

    private State currentState = State.SUSPENDED;
    private Integer ticksToComplete;
    private final PriorityLevel priority;
    private final String uuid = UUID.randomUUID().toString();

    @Override
    public int compareTo(Task o) {
        return o.priority.ordinal() - this.priority.ordinal();
    }

    @Override
    public String toString() {
        return "Task{" +
                "priority=" + priority +
                ", ticks=" + ticksToComplete +
//                ", uuid='" + uuid + '\'' +
                '}';
    }

    public void preempt(){
        currentState = State.READY;
    }

    public void start(){
        currentState = State.RUNNING;
    }

    public void terminate(){
        currentState = State.SUSPENDED;
    }

    public void activate(){
        currentState = State.READY;
    }


}

