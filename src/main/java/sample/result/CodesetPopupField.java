package sample.result;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import sample.entity.TestResult;
import sample.common.ResultTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by taranenko on 05.10.2020
 * Описание: Выпадающий список кодовых наборов
 * Класс основан на PopupField, но в некоторых местах имет существенные различия
 * в поведении
 */
public class CodesetPopupField {

    private static final StringBuffer BASE_ITEM_KEY = new StringBuffer(CodesetPopupField.class + ".resultTemplate");
    private static final String menuStyle = "" +
            "-fx-pref-width : %d;\n" +
            "-fx-max-height: 400;\n";

    private final TextArea valueField;
    private final Button btnTemplate;
    private final List<ResultTemplate> resultTemplateList;

    private final SimpleObjectProperty<MenuItem> selectedItem = new SimpleObjectProperty<>();

    private ContextMenu menu;
    private FilteredList<MenuItem> filteredItems = new FilteredList<>(FXCollections.observableArrayList());

    private final AbstractResultCell testResultCell;
    private final TestResult result;

    public CodesetPopupField(TextArea valueField, Button btnTemplate, List<ResultTemplate> resultTemplateList, AbstractResultCell testResultCell, TestResult result) {
        this.valueField = valueField;
        this.btnTemplate = btnTemplate;
        this.resultTemplateList = resultTemplateList;
        this.testResultCell = testResultCell;
        this.result = result;
        initialize();
    }

    private void initialize(){
        btnTemplate.setOnAction(e -> {
            showFullMenu();
        });

        valueField.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, e -> {
            showFullMenu();
            e.consume();
        });
        setItems(resultTemplateList);
    }


    private boolean searchOnCode(String text){
        for (ResultTemplate resultTemplate : resultTemplateList) {
            if(resultTemplate.getCode().toLowerCase().trim().equals(text)){
                return true;
            }
        }
        return false;
    }

    private void setItems(List<ResultTemplate> items) {
        List<ResultTemplate> sortedItems =
                items.stream()
                        .sorted(Comparator.comparingInt((ResultTemplate o) -> o.getCode().length())
                                .thenComparing(ResultTemplate::getCode))
                        .collect(Collectors.toList());

        List<MenuItem> menuItems = sortedItems.stream()
                .map(this::createMenuItem)
                .collect(Collectors.toList());
        filteredItems = new FilteredList<>(FXCollections.observableList(menuItems));

        if (getSelectedItem() != null) {
            MenuItem found = findItem(mi -> getMenuItem(mi).getId() == getSelectedItem().getId());
            if (found == null)
                clearValue();
        }
    }

    private void setMenuItems(List<? extends MenuItem> items) {
        List<? extends MenuItem> sortedItems = new ArrayList<>(items);
        menu.getItems().setAll(sortedItems);
        showMenu();
    }

    private void clearValue() {
        hideMenu();
        selectedItem.set(null);
        updateText();
    }

    public void setValue(String text){
        valueField.setText(text);
    }

    private boolean hasChanged() {
        return !getValueText().toLowerCase().trim().equals(itemToString(selectedItem.get()).toLowerCase());
    }

    private String getValueText() {
        return valueField.getText() == null ? "" : valueField.getText();
    }

    private void updateText() {
        valueField.setText(itemToString(selectedItem.get()));
    }

    private void updateText(String text) {
        valueField.setText(text);
        hideMenu();
    }

    private void textChanged() {
        if(testResultCell.getItem() != result)
            return;
        if(getValueText() != null) {
            String text = getValueText().trim().replaceAll("ё", "e");
            if (text.isEmpty()) {
                hideMenu();
                selectedItem.set(null);
                setItems(resultTemplateList);
                return;
            }

            showFilteredMenu();
            MenuItem found = findItem(menuItem ->
                    ((CodesetPopupGraphic) menuItem.getGraphic()).getText().equalsIgnoreCase(text));
            selectedItem.set(found);
        }
    }

    private MenuItem findItem(Predicate<MenuItem> predicate) {
        return filteredItems.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    private ResultTemplate getSelectedItem() {
        if (selectedItem.get() == null)
            return null;
        return getMenuItem(selectedItem.get());
    }

    private void createMenu() {
        if (menu == null)
            menu = new ContextMenu();
    }

    private void showMenu() {
        if(testResultCell.getItem() != result)
            return;
        if (filteredItems == null || filteredItems.size() == 0)
            return;
        if (menu.isShowing())
            return;

        hideMenu();
        menu.setStyle(String.format(menuStyle, Math.round(valueField.getWidth())));
        Bounds bounds = valueField.getBoundsInLocal();
        bounds = valueField.localToScreen(bounds);
        if(bounds == null)
            return;
        menu.show(valueField, bounds.getMinX(), bounds.getMaxY());

        Skin<?> skin = menu.getSkin();
        if (skin != null) {
            Node selectedNode = skin.getNode().lookup(".menu-item");
            if (selectedNode != null)
                selectedNode.requestFocus();
        }
        menu.requestFocus();
    }

    private void showFullMenu() {
        createMenu();
        setMenuItems(filteredItems.getSource());
        showMenu();
    }

    private void showFilteredMenu() {
        if(searchOnCode(getValueText().toLowerCase().trim())){
            String code = getValueText().toLowerCase().trim();
            for(ResultTemplate resultTemplate : resultTemplateList){
                if(code.equals(resultTemplate.getCode())){
                    filteredItems.setPredicate(this::findMenuItem);
                }
            }
        } else {
            filteredItems.setPredicate(item -> ((CodesetPopupGraphic) item.getGraphic()).getText()
                    .toLowerCase().replaceAll("ё", "е").trim()
                    .contains(getValueText().toLowerCase().replaceAll("ё", "е").trim()));
        }
        createMenu();
        setMenuItems(filteredItems);
        showMenu();

    }

    private boolean findMenuItem(MenuItem item){
        String menuCode = ((CodesetPopupGraphic) item.getGraphic()).getText().trim().split("]")[0];
        menuCode = menuCode.trim().substring(1,2);
        return getValueText().trim().equals(menuCode);
    }

    private void hideMenu() {
        if (menu != null)
            menu.hide();
    }

    private ResultTemplate getMenuItem(MenuItem menuItem) {
//        UserData userData = UserData.of(menuItem);
        return (ResultTemplate) menuItem.getUserData();
    }

    private MenuItem createMenuItem(ResultTemplate item) {
        String text = "[" + item.getCode() + "] " + item.getValue();

        CodesetPopupGraphic graphic = new CodesetPopupGraphic(text);

        DoubleProperty width = graphic.prefWidthProperty();
        width.bind(Bindings.createDoubleBinding(() ->
                valueField.getWidth() - 12, valueField.widthProperty()));

        MenuItem menuItem = new MenuItem("", graphic);
        menuItem.getStyleClass().add("codeset-menu-item");

//        UserData.of(menuItem).put(BASE_ITEM_KEY, item);
        menuItem.setOnAction(e -> selectMenuItem(menuItem, item.getValue()));
        return menuItem;
    }

    private void selectMenuItem(MenuItem menuItem, String value) {
        selectedItem.set(menuItem);
        updateText(value);
        valueField.selectAll();
    }

    private String itemToString(MenuItem item) {
        return item == null ? "" : ((CodesetPopupGraphic) item.getGraphic()).getText().toLowerCase().trim();
    }

    public void setValueFromCodeSet(){
        String text = getValueText().trim();
        if(text.isEmpty() || selectedItem.getValue() == null)
            return;

        String itemText = ((CodesetPopupGraphic) selectedItem.getValue().getGraphic()).getText();
        for(ResultTemplate resultTemplate : resultTemplateList){
            String str = "[" + resultTemplate.getCode() + "] " + resultTemplate.getValue();
            if(str.equals(itemText))
                valueField.setText(resultTemplate.getValue());
        }
    }

    public String getValueFromCodeSet(){
        String text = getValueText().trim();
        String lowerText = text.toLowerCase();
        for(ResultTemplate resultTemplate : resultTemplateList){
            if(resultTemplate.getCode().equals(lowerText) || resultTemplate.getValue().equals(lowerText))
                return resultTemplate.getValue();
        }
        return text;
    }

    private void onSelectAction(MouseEvent e) {
        if (!valueField.isFocused()) {
            valueField.requestFocus();
            valueField.selectAll();
            e.consume();
        }
    }
}
