package structural.adapterPattern.adapterPkg;

import structural.adapterPattern.legacyPkg.OldWeighingMachine;

public class WeightAdapter implements NewWeighingMachine {

    private OldWeighingMachine oldWeighingMachine;

    public WeightAdapter(OldWeighingMachine oldWeighingMachine) {
        this.oldWeighingMachine = oldWeighingMachine;
    }

    @Override
    public double getWeightInPounds() {

        double weightInKilograms = oldWeighingMachine.getWeightInKilograms();
        return weightInKilograms * 2.20462;
    }
}
