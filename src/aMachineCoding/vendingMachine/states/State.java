package aMachineCoding.vendingMachine.states;

public interface State {
    void insertCoin(int amount) throws Exception;

    void selectItem(String itemCode) throws Exception;

    void dispenseItem() throws Exception;

    void cancelTransaction() throws Exception;
}
