package libedit.editor.models.gui;

import java.util.ArrayList;
import java.util.List;

import libedit.eagle.models.enums.Unit;
import libedit.editor.views.PackagePreviewer;
import libedit.helpers.FloatField;

public class EditorSettings {

    private static EditorSettings  instance        = null;

    private List<FloatField>       unitObservers   = new ArrayList<FloatField>();
    private List<PackagePreviewer> gridObservers   = new ArrayList<PackagePreviewer>();

    private Unit                   unit;
    private final Unit             DEFAULT_UNIT    = Unit.MM;

    private float                  gridSpacing;
    private float                  DEFAULT_SPACING = 1f;

    private EditorSettings() {
        this.unit = DEFAULT_UNIT;
        this.gridSpacing = DEFAULT_SPACING;
    }

    public static synchronized EditorSettings getInstance() {
        if (instance == null) {
            instance = new EditorSettings();
        }
        return instance;
    }

    public void registerUnitObserver(FloatField observer) {
        unitObservers.add(observer);
        updateObservers();
    }

    public void registerGridObserver(PackagePreviewer previewer) {
        gridObservers.add(previewer);
        updateObservers();
    }

    public List<FloatField> getUnitObservers() {
        return unitObservers;
    }

    public List<PackagePreviewer> getGridObservers() {
        return gridObservers;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        for (FloatField c : unitObservers) {
            switch (unit) {
            case INCH:
                c.setPlaces(3);
                break;
            case MM:
                c.setPlaces(2);
                break;
            default:
                c.setPlaces(0);
                break;
            }
            float newVal = Unit.convert(c.getVal(), this.unit, unit);
            c.setVal(newVal);
        }
        for (PackagePreviewer p : gridObservers) {
            float newSpacing = Unit.convert(p.getGridSpacing(), this.unit, unit);
            p.setGridSpacing(newSpacing);
        }
        this.unit = unit;
    }

    public float getGridSpacing() {
        return gridSpacing;
    }

    public void setGridSpacing(float gridSpacing) {
        for (PackagePreviewer p : gridObservers) {
            p.setGridSpacing(gridSpacing);
        }
        this.gridSpacing = gridSpacing;
    }

    public void updateObservers() {
        setUnit(unit);
        setGridSpacing(gridSpacing);
    };

}
