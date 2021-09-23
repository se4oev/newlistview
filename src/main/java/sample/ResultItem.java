package sample;

public interface ResultItem {

    void setId(Long id);

    Long getId();

    void setText(String text);

    String getText();

    void setResultType(ResultType type);

    ResultType getResultType();
}
