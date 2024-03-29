package lab.task;

public class ExtendedTask extends Task {

    private int needTicksBeforeWait;
    private boolean needToWait = true;
    private int waitTicks;

    private final Processor processor;

    public ExtendedTask(int id, int requiredTicks, PriorityLevel priority, Processor processor, int needTicksBeforeWait, int waitTicks) {
        super(id, requiredTicks, priority, processor);
        this.needTicksBeforeWait = needTicksBeforeWait;
        this.waitTicks = waitTicks;
        this.processor = processor;
    }

    @Override
    public void run() {
        start();
        setRunning(true);
        while (isRunning()) {
            try {
                Processor.waitForTickComplete();
                if (getCurrentState() != State.WAITING && getRequiredTicks() > 0) {
                    decTicks();
                    System.out.println("До завершения задачи " + this + " осталось " + getRequiredTicks() + " тактов");
                    if (getRequiredTicks() == 0) {
                        System.out.println("ЗАДАЧА " + this + " ВЫПОЛНЕНА на такте " + processor.getCurrentTick());
                        preempt();
                        setRunning(false);
                    }
                }
                if (needToWait) {
                    needTicksBeforeWait--;
                    System.out.println("До начала ожидания задачи " + this + " " + needTicksBeforeWait + " тактов");
                    if (needTicksBeforeWait == 0) {
                        waiting();
                        needToWait = false;
                    }
                }
//                if (needToWait && needTicksBeforeWait == 0) {
//                    waiting();
//                    System.err.println("вейтинг установлен");
//                }
            } catch (InterruptedException e) {
                if (getCurrentState() == State.WAITING) {
//                    needToWait = false;
                    System.out.println("Задача " + this + " уходит в ожидание на " + waitTicks + " тактов");
                    setRunning(false);
                } else {
                    System.out.println(this + " прервана на такте " + processor.getCurrentTick() + ", осталось тактов " + getRequiredTicks());
                    terminate();
                    setRunning(false);
                }
            }
        }
    }

    @Override
    public Task goWaiting()  {
        while (waitTicks > 0) {
            try {
                Processor.waitForTickComplete();
                waitTicks--;
            } catch (InterruptedException e) {
                System.err.println("Попытка прервать задачу " + this + " в режиме ожидания");
            }
        }
//        release();
        System.out.println(this + " выходит из режима ожидания");
        return this;
    }

    @Override
    public void waiting() {
        if (getCurrentState() == State.RUNNING) {
            setCurrentState(State.WAITING);
        } else {
            System.err.println("WRONG TRANSITION running-waiting " + this);
        }
    }

    @Override
    public void release() {
        if (getCurrentState() == State.WAITING) {
            setCurrentState(State.READY);
        } else {
            System.err.println("WRONG TRANSITION waiting-ready " + this);
        }
    }

}
