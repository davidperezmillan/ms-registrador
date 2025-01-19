// StatusEnum.java
package com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.models;

public enum StatusEnum {
    CREATED("created"),
    RESTARTING("restarting"),
    RUNNING("running"),
    REMOVING("removing"),
    PAUSED("paused"),
    EXITED("exited"),
    DEAD("dead");

    private final String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}