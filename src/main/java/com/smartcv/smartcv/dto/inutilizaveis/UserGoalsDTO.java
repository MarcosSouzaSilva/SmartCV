package com.smartcv.smartcv.dto.inutilizaveis;

public class UserGoalsDTO {

    private String object;

    public String getObject() {

        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "UserGoalsDTO{" +
                "object='" + object + '\'' +
                '}';
    }
}
