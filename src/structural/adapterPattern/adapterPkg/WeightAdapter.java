package structural.adapterPattern.adapterPkg;

import structural.adapterPattern.legacyPkg.OldWeighingMachine;

public class WeightAdapter implements NewWeighingMachine {

    private final OldWeighingMachine adaptee;

    public WeightAdapter(OldWeighingMachine adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public double getWeightInPounds() {
        return adaptee.getWeightInKilograms() * 2.20462;
    }
}
