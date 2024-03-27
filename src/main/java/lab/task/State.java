package lab.task;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

@Getter
public enum State {

    RUNNING,
    SUSPENDED,
    READY,
    WAITING

//    static {
//        RUNNING.nextPossibleStates = EnumSet.of(READY, SUSPENDED, WAITING);
//        SUSPENDED.nextPossibleStates = EnumSet.of(READY);
//        READY.nextPossibleStates = EnumSet.of(RUNNING);
//        WAITING.nextPossibleStates = EnumSet.of(READY);
//    }
//
//    private Set<State> nextPossibleStates;

}
