package libedit.eagle.models.factories;

import java.util.ArrayList;
import java.util.List;

import libedit.eagle.models.abstractobjects.EagleObj;
import libedit.eagle.models.containers.Pkg;
import libedit.editor.models.patterns.Pattern;
import libedit.editor.models.patterns.SMDPattern;
import libedit.editor.models.patterns.ThruHolePattern;
import libedit.editor.views.PackagePreviewer;
import libedit.editor.views.abstracts.PatternEditor;

public class PackageBuilder {

    List<PackagePreviewer> observers = new ArrayList<PackagePreviewer>();
    List<PatternEditor>    models    = new ArrayList<PatternEditor>();
    Pkg                    pkg;

    public void update() {
        System.out.println("Updating package builder");
        List<EagleObj> pkgChildren = new ArrayList<EagleObj>();
        int startingPin = 1;
        for (PatternEditor m : models) {
            for (Pattern p : m.getPatterns()) {
                switch (p.getType()) {
                case WIRE:
                    break;
                case SMD:
                    SMDPattern smdPattern = (SMDPattern) p;
                    pkgChildren.addAll(PatternFactory.smdListFromPattern(smdPattern, startingPin));
                    startingPin += smdPattern.getPadCount().totalPads();
                    break;
                case THRU:
                    ThruHolePattern thruPattern = (ThruHolePattern) p;
                    pkgChildren.addAll(PatternFactory.thruPadListFromPattern(thruPattern, startingPin));
                    startingPin += thruPattern.getPadCount().totalPads();
                    break;
                default:
                    break;
                }
            }
        }
        pkg = new Pkg("default", pkgChildren);
        updateObservers();
    }

    public void registerObserver(PackagePreviewer observer) {
        observers.add(observer);

    }

    public void registerModel(PatternEditor model) {
        models.add(model);
        model.registerObserver(this);
    }

    private void updateObservers() {
        System.out.println("Package builder updating observers");
        for (PackagePreviewer o : observers) {
            o.setPackage(pkg);
        }
    }

    public Pkg getPackage() {
        return pkg;
    }
}
