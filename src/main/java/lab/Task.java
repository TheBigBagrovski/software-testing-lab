//package lab.task;
//
//import lab.Processor;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.UUID;
//import java.util.concurrent.Callable;
//
//@Slf4j
//@Getter
//@Setter
//@AllArgsConstructor
//@Builder
//public class Task implements Comparable<Task>, Callable<Task> {
//
//    private final Processor processor;
//
//    private State currentState;
//    private Integer ticksToComplete;
//    private final PriorityLevel priority;
//    private final String uuid = UUID.randomUUID().toString();
//
//    @Override
//    public Task call() throws Exception {
//        start();
//        synchronized (processor) {
//            try {
//                for (int i = 0; i < ticksToComplete; i++) {
//                    processor.wait(); // Ждем уведомления о новом такте
//                }
//                System.out.println("Task finished after " + ticksToComplete + " ticks");
//            } catch (InterruptedException e) {
//                System.out.println("Interrupted");
//            }
//        }
//        terminate();
//        return this;
//    }
//
//    @Override
//    public int compareTo(Task o) {
//        return o.priority.ordinal() - this.priority.ordinal();
//    }
//
//    @Override
//    public String toString() {
//        return "Task{" +
//                "приоритет=" + priority +
//                ", тактов=" + ticksToComplete +
////                ", uuid='" + uuid + '\'' +
//                '}';
//    }
//
//    public void preempt(){
//        if (currentState == State.RUNNING) currentState = State.READY;
//        else System.err.println("WRONG TRANSITION");
//    }
//
//    public void start(){
//        if (currentState == State.READY) currentState = State.RUNNING;
//        else System.err.println("WRONG TRANSITION");
//    }
//
//    public void terminate(){
//        if (currentState == State.RUNNING) currentState = State.SUSPENDED;
//        else System.err.println("WRONG TRANSITION");
//    }
//
//    public void activate(){
//        if (currentState == State.SUSPENDED) currentState = State.READY;
//        else System.err.println("WRONG TRANSITION");
//    }
//
//
//}
//