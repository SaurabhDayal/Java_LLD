package structural.adapterPattern;

import structural.adapterPattern.adapterPkg.NewWeighingMachine;
import structural.adapterPattern.adapterPkg.WeightAdapter;
import structural.adapterPattern.legacyPkg.OldWeighingMachine;

public class AdapterMain {

    public static void main(String[] args) {

        OldWeighingMachine oldWeighingMachine = new OldWeighingMachine();

        NewWeighingMachine adaptedWeighingMachine = new WeightAdapter(oldWeighingMachine);

        System.out.println("Weight in pounds: " + adaptedWeighingMachine.getWeightInPounds());
    }
}
