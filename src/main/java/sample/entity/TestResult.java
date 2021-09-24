package sample.entity;

import sample.common.ResultTemplate;

import java.util.ArrayList;
import java.util.List;

public class TestResult implements ITestResult {

    private Long id;
    private String text;
    private String executorText;
    private String normText;
    private String value;
    private String note;
    private String units;
    private String counterHotkey;
    private String format;
    private int validationStatus;
    private int pathologyStatus;
    private int reportStatus;
    private int suppressStatus;
    private int optionalStatus;
    private boolean isCurrentWorkplace;
    private boolean isEditable;
    private ResultType resultType;
    private long testId;
//    private List<ResultHistoryItem> history = new ArrayList<>();
    private List<ResultTemplate> templateList = new ArrayList<>();
//    private TestFunc func;


    public String getExecutorText() {
        return executorText;
    }

    public void setExecutorText(String executorText) {
        this.executorText = executorText;
    }

    public String getNormText() {
        return normText;
    }

    public void setNormText(String normText) {
        this.normText = normText;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getCounterHotkey() {
        return counterHotkey;
    }

    public void setCounterHotkey(String counterHotkey) {
        this.counterHotkey = counterHotkey;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(int validationStatus) {
        this.validationStatus = validationStatus;
    }

    public int getPathologyStatus() {
        return pathologyStatus;
    }

    public void setPathologyStatus(int pathologyStatus) {
        this.pathologyStatus = pathologyStatus;
    }

    public int getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    public int getSuppressStatus() {
        return suppressStatus;
    }

    public void setSuppressStatus(int suppressStatus) {
        this.suppressStatus = suppressStatus;
    }

    public int getOptionalStatus() {
        return optionalStatus;
    }

    public void setOptionalStatus(int optionalStatus) {
        this.optionalStatus = optionalStatus;
    }

    public boolean isCurrentWorkplace() {
        return isCurrentWorkplace;
    }

    public void setCurrentWorkplace(boolean currentWorkplace) {
        isCurrentWorkplace = currentWorkplace;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    @Override
    public ResultType getResultType() {
        return resultType;
    }

    @Override
    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public String getText() {
        return text;
    }

    @Override
    public void setId(Long id) {

    }

    public Long getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static class Pathology {

        public static final int NORMAL = 0;
        public static final int LOW = -1;
        public static final int PAT_LOW = -2;
        public static final int CRIT_LOW = -3;
        public static final int HIGH = 1;
        public static final int PAT_HIGH = 2;
        public static final int CRIT_HIGH = 3;
        public static final int PATHOLOGY = 4;

    }

    public List<ResultTemplate> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<ResultTemplate> templateList) {
        this.templateList = templateList;
    }
}
