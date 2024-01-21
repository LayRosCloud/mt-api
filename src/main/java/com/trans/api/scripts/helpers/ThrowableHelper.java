package com.trans.api.scripts.helpers;

import com.trans.api.scripts.exception.BadRequestException;
import com.trans.api.scripts.exception.NotFoundException;

public class ThrowableHelper {
    public static NotFoundException throwNotFoundException(String id){
        return throwNotFoundException("Error! Object with id '%s' is not founded", id);
    }

    public static NotFoundException throwNotFoundException(String message, Object ...args){
        return new NotFoundException(
                String.format(message, args)
        );
    }

    public static BadRequestException throwBadRequestException(){
        return throwBadRequestException("Error! Object has no valid values, please fixed this!");
    }

    public static BadRequestException throwBadRequestException(String message, Object ...args){
        return new BadRequestException(
                String.format(message, args)
        );
    }
}
