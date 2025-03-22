package org.skypro.skyshop.model.exeptions;

public class ShopError {
    private String code;
    private String massage;

    public ShopError(String code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}
