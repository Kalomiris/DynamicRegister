package com.dynamic.register.wrapper.response;

import com.dynamic.register.wrapper.BaseModel;
import lombok.Data;

@Data
public class ResponseWrapper {

    private ResponseData responseData;
    private BaseModel baseModel;

    public ResponseWrapper(ResponseData responseData, BaseModel baseModel) {
        this.responseData = responseData;
        this.baseModel = baseModel;
    }

}
