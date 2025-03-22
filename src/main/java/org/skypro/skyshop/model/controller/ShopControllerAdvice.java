package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.exeptions.NoSuchProductException;
import org.skypro.skyshop.model.exeptions.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException
            (NoSuchProductException e){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ShopError("407","Такого продукта не существует"));

    }
}
