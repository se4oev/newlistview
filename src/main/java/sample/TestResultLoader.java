package sample;

public class TestResultLoader {
    public TestResult getListResult() {
        TestResult testResult = new TestResult();
        testResult.setId((long) (Math.random() * Long.MAX_VALUE));
        testResult.setValue("Из списка");
        testResult.setNote("fdsfds");
        testResult.setValueType(ValueType.LIST);
        return testResult;
    }

    public TestResult getNumResult() {
        TestResult testResult = new TestResult();
        testResult.setId((long) (Math.random() * Long.MAX_VALUE));
        testResult.setValue("123");
        testResult.setNote("fdsfds");
        testResult.setValueType(ValueType.NUM);
        return testResult;
    }

    public TestResult getTextResult() {
        TestResult testResult = new TestResult();
        testResult.setId((long) (Math.random() * Long.MAX_VALUE));
        testResult.setValue("String");
        testResult.setNote("fdsfds");
        testResult.setValueType(ValueType.TEXT);
        return testResult;
    }

    public TestResult getFixListResult() {
        TestResult testResult = new TestResult();
        testResult.setId((long) (Math.random() * Long.MAX_VALUE));
        testResult.setValue("123");
        testResult.setNote("Фиксик");
        testResult.setValueType(ValueType.FIX_LIST);
        return testResult;
    }
}
