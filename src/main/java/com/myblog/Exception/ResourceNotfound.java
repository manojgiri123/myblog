package com.myblog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)

public class ResourceNotfound extends RuntimeException{

    public ResourceNotfound(String msg){
        super(msg);

    }
}
