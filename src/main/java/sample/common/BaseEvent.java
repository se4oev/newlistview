package sample.common;

import javafx.event.Event;
import javafx.event.EventType;

public class BaseEvent<T> extends Event {
    public static final EventType<BaseEvent> ANY = new EventType(BaseEvent.class.getName());
    private final T data;

    public BaseEvent(Object source, EventType<BaseEvent<T>> eventType) {
        this(source, eventType, null);
    }

    public BaseEvent(Object source, EventType<BaseEvent<T>> eventType, T data) {
        super(source, NULL_SOURCE_TARGET, eventType);
        this.data = data;
    }

    public static <T> EventType<BaseEvent<T>> createEventType(String name) {
        return new EventType(ANY, name);
    }

    public static <T> EventType<BaseEvent<T>> createEventType(Class sourceType, String name) {
        return new EventType(ANY, sourceType.getName() + "." + name);
    }

    public T getData() {
        return this.data;
    }
}
