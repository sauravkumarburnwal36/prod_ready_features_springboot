package com.example.prod_ready_features.prod_ready_features.advices;


import lombok.Data;


import java.time.LocalDateTime;

@Data
public class APIResponse<T> {

    //@JsonFormat(pattern="hh:mm:ss MM:DD:YYYY")
   // private LocalDateTime timeStamp;

    private T data;

    private ApiError apiError;

    public APIResponse()
    {
       // this.timeStamp=LocalDateTime.now();
    }
    public APIResponse(T data){
        this();
        this.data=data;
    }

    public APIResponse(ApiError apiError)
    {

        this();
        this.apiError=apiError;
    }
}
