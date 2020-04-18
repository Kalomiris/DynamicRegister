package com.dynamic.register.wrapper.response;

import com.dynamic.register.wrapper.BaseModel;

public class ResponseWrapper {

    private ResponseData responseData;
    private BaseModel baseModel;

    public ResponseWrapper(ResponseData responseData, BaseModel baseModel) {
        this.responseData = responseData;
        this.baseModel = baseModel;
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public BaseModel getBaseModel() {
        return baseModel;
    }

    public void setBaseModel(BaseModel baseModel) {
        this.baseModel = baseModel;
    }
}
