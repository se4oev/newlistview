package sample.common;

/**
 * Created by andrej on 09/07/2021.
 * Description:
 */
public enum PathologyStatus {

    /**
     * Патолоия количественных показаелей,
     * 3 уровня - повышенный/пониженый, патологический, критический
     * отрицательный уровень - значение ниже нормы, положительный - выше
     */
    CRIT_LOW(-3),
    PAT_LOW(-2),
    LOW(-1),
    NORMAL(0),
    HIGH(1),
    PAT_HIGH(2),
    CRIT_HIGH(3),

    /**
     * Патолоия качественного показаеля
     */
    PATHOLOGY(4);

    public static PathologyStatus ofStatus(int status) {
        for (PathologyStatus pathology : values())
            if (pathology.status == status)
                return pathology;
        throw new IllegalArgumentException("Enum constant not found for pathology status: " + status);
    }

    private final int status;

    PathologyStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
