package lab;

import lab.task.PriorityLevel;
import lab.task.Task;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskGenerator {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    private TaskGenerator() {}

    public static Task generateRandomTask() {
        int ticks = random.nextInt(5,10);
        PriorityLevel[] priorities = PriorityLevel.values();
        PriorityLevel priority = priorities[random.nextInt(priorities.length)];

        return Task.builder()
                .ticksToComplete(ticks)
                .priority(priority)
                .build();
    }
}
