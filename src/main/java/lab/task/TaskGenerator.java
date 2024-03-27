package lab.task;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskGenerator {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    private TaskGenerator() {}

    public static Task generateRandomTask() {
        int ticks = random.nextInt(5,100);
        PriorityLevel[] priorities = PriorityLevel.values();
        PriorityLevel priority = priorities[random.nextInt(priorities.length)];

        return Task.builder()
                .ticks(ticks)
                .priority(priority)
                .build();
    }
}
