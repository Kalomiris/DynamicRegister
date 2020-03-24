package com.dynamic.register.wrapper;

import java.io.Serializable;
import java.util.List;

public class BaseWrapper<D,M> implements Serializable {

    private List<D> dataList;

    private List<M> modelList;

    private BaseParameters baseParameters;

    public BaseWrapper(List<D> dataList, List<M> modelList) {
        this.dataList = dataList;
        this.modelList = modelList;
    }

    public List<D> getDataList() {
        return dataList;
    }

    public void setDataList(List<D> dataList) {
        this.dataList = dataList;
    }

    public List<M> getModelList() {
        return modelList;
    }

    public void setModelList(List<M> modelList) {
        this.modelList = modelList;
    }

    public BaseParameters getBaseParameters() {
        return baseParameters;
    }

    public void setBaseParameters(BaseParameters baseParameters) {
        this.baseParameters = baseParameters;
    }
}
