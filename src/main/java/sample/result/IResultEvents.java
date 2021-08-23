package sample.result;

import javafx.event.EventType;
import sample.TestResult;
import sample.common.BaseEvent;

public interface IResultEvents {

    public static final EventType<BaseEvent<Void>> VALIDATION
            = BaseEvent.createEventType(IResultEvents.class, "VALIDATION");
    public static final EventType<BaseEvent<Void>> RESULT_INPUT
            = BaseEvent.createEventType(IResultEvents.class, "RESULT_INPUT");
    public static final EventType<BaseEvent<TestResult>> RESULT_CHANGED
            = BaseEvent.createEventType(IResultEvents.class, "RESULT_CHANGED");
    public static final EventType<BaseEvent<Void>> NEXT_CELL
            = BaseEvent.createEventType(IResultEvents.class, "NEXT_CELL");
}
