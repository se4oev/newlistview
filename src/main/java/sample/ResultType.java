package sample;

public enum ResultType {

    TEXT("Текст"),
    NUM("Число"),
    LIST("Список"),
    FIX_LIST("Фиксированный список"),
    COUNTER("Счетчик"),
    TEST_GROUP("Группа тестов");

    private final String name;

    ResultType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
