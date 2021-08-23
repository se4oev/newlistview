package sample;

public enum ValueType {

    TEXT("Текст"),
    NUM("Число"),
    LIST("Список"),
    FIX_LIST("Фиксированный список"),
    COUNTER("Счетчик");

    private final String name;

    ValueType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
