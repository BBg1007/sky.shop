package org.skypro.skyshop.model.exeptions;

public class NoSuchProductException extends RuntimeException{
    public NoSuchProductException(){
        super("Такого продукта не существует.");
    }
}
