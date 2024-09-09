package structural.adapterPattern.adapterPkg;


import structural.adapterPattern.legacyPkg.OldWeighingMachine;

public class WeightAdapter implements NewWeighingMachine {

    private OldWeighingMachine oldWeighingMachine;

    public WeightAdapter(OldWeighingMachine oldWeighingMachine) {
        this.oldWeighingMachine = oldWeighingMachine;
    }

    @Override
    public double getWeightInPounds() {
        // Convert kilograms to pounds (1 kg = 2.20462 lbs)
        double weightInKilograms = oldWeighingMachine.getWeightInKilograms();
        return weightInKilograms * 2.20462;
    }
}
