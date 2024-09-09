package structural.facadePattern;

import structural.facadePattern.facadePkg.BuyCryptoFacade;

public class FacadeMain {

    public static void main(String[] args) {
        
        BuyCryptoFacade buyCrypto = new BuyCryptoFacade();
        buyCrypto.buyCryptocurrency(1000, "BTC");
    }
}
