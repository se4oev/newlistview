package sample;

import sample.common.ResultTemplate;

import java.util.ArrayList;
import java.util.List;

public class TestResultLoader {

    private static int counter = 1;

    public TestResult getTestResult() {
        int random = (int) (Math.random() * 4);
        if (random == 0) return getNumResult();
        if (random == 1) return getTextResult();
        if (random == 2) return getFixListResult();
        if (random == 3) return getListResult();
        return null;
    }

    private TestResult getNumResult() {
        TestResult testResult = new TestResult();
        testResult.setId((long) counter++);
        testResult.setValue("12.5");
        testResult.setNote("fdsfds");
        testResult.setValueType(ValueType.NUM);
        testResult.setEditable(true);
        testResult.setSuppressStatus(0);
        testResult.setReportStatus(0);
        testResult.setExecutorText("Погосян Давид Арутюнович");
        testResult.setText("Анализ с числом");
        testResult.setPathologyStatus(2);
        testResult.setTestId(1234L);
        testResult.setNormText("12-15");
        testResult.setUnits("г/л");
        return testResult;
    }

    private TestResult getTextResult() {
        TestResult testResult = new TestResult();
        testResult.setId((long) counter++);
        testResult.setValue("жидкий");
        testResult.setNote("fdsfds");
        testResult.setValueType(ValueType.TEXT);
        testResult.setEditable(true);
        testResult.setSuppressStatus(0);
        testResult.setReportStatus(0);
        testResult.setExecutorText("Владилен Мамбетович Кучугуров");
        testResult.setText("Анализ с текстом");
        testResult.setPathologyStatus(2);
        testResult.setTestId(1234L);
        testResult.setNormText("120");
        testResult.setUnits("ед.");
        return testResult;
    }

    private TestResult getFixListResult() {
        TestResult testResult = new TestResult();
        testResult.setId((long) counter++);
        testResult.setValue("три пять");
        testResult.setNote("fdsfds");
        testResult.setValueType(ValueType.FIX_LIST);
        testResult.setEditable(true);
        testResult.setSuppressStatus(0);
        testResult.setReportStatus(0);
        testResult.setExecutorText("Погосян Давид Арутюнович");
        testResult.setText("Анализ с фиксированными списком");
        testResult.setPathologyStatus(2);
        testResult.setTestId(1234L);
        testResult.setNormText("желтый");
        testResult.setTemplateList(getTemlateList());
        return testResult;
    }

    private TestResult getListResult() {
        TestResult testResult = new TestResult();
        testResult.setId((long) counter++);
        testResult.setValue("пять два");
        testResult.setNote("fdsfds");
        testResult.setValueType(ValueType.LIST);
        testResult.setEditable(true);
        testResult.setSuppressStatus(0);
        testResult.setReportStatus(0);
        testResult.setExecutorText("Сорокина Алла Леонидовна");
        testResult.setText("Анализ со списком");
        testResult.setPathologyStatus(2);
        testResult.setTestId(1234L);
        testResult.setNormText("синий");
        testResult.setTemplateList(getTemlateList());
        return testResult;
    }

    private List<ResultTemplate> getTemlateList() {
        List<ResultTemplate> templates = new ArrayList<>();
        int random = (int) (Math.random() * 10 + 1);
        for (int i = 0; i < random; i++) {
            ResultTemplate resultTemplate = new ResultTemplate();
            resultTemplate.setId(i + 1000);
            resultTemplate.setCode(i + 1 + "");
            resultTemplate.setValue("Шаблон " + (i + 1));
            templates.add(resultTemplate);
        }
        return templates;
    }
}
