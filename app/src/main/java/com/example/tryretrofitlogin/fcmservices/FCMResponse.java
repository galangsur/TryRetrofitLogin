package com.example.tryretrofitlogin.fcmservices;

import java.util.List;

public class FCMResponse {
    public String multicast;
    public String succes;
    public String failure;
    public String canonical_ids;
    public List<Result> resultList;

    public FCMResponse() {
    }

    public FCMResponse(String multicast, String succes, String failure, String canonical_ids, List<Result> resultList) {
        this.multicast = multicast;
        this.succes = succes;
        this.failure = failure;
        this.canonical_ids = canonical_ids;
        this.resultList = resultList;
    }

    public String getMulticast() {
        return multicast;
    }

    public void setMulticast(String multicast) {
        this.multicast = multicast;
    }

    public String getSucces() {
        return succes;
    }

    public void setSucces(String succes) {
        this.succes = succes;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public String getCanonical_ids() {
        return canonical_ids;
    }

    public void setCanonical_ids(String canonical_ids) {
        this.canonical_ids = canonical_ids;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }
}
