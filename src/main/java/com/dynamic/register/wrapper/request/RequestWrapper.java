package com.dynamic.register.wrapper.request;

import com.dynamic.register.wrapper.BaseModel;
import com.dynamic.register.wrapper.BaseParameters;

import java.io.Serializable;
import java.util.List;

public class RequestWrapper<MODEL extends BaseModel> {

    private MODEL model;

    private BaseParameters baseParameters;

    public RequestWrapper(MODEL model, BaseParameters baseParameters) {
        this.model = model;
        this.baseParameters = baseParameters;
    }

    public MODEL getModel() {
        return model;
    }

    public void setModel(MODEL model) {
        this.model = model;
    }

    public BaseParameters getBaseParameters() {
        return baseParameters;
    }

    public void setBaseParameters(BaseParameters baseParameters) {
        this.baseParameters = baseParameters;
    }
}
