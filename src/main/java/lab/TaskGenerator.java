package lab;

import lab.task.ExtendedTask;
import lab.task.PriorityLevel;
import lab.task.Processor;
import lab.task.State;
import lab.task.Task;

import java.util.concurrent.ThreadLocalRandom;

public class TaskGenerator {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    private static final int MAX_RANDOM_BEFORE_WAIT_TICKS = 4;

    private TaskGenerator() {}

    public static Task generateRandomTask(Processor processor, int id) {
        int ticks = random.nextInt(5,10);
        PriorityLevel[] priorities = PriorityLevel.values();
        PriorityLevel priority = priorities[random.nextInt(priorities.length)];
        if (random.nextInt(8) <= 1) {
            return new Task(id, ticks, priority, processor);
        } else {
            return new ExtendedTask(id, ticks, priority, processor, random.nextInt(1, MAX_RANDOM_BEFORE_WAIT_TICKS), random.nextInt(2, 6));
        }
    }
}
