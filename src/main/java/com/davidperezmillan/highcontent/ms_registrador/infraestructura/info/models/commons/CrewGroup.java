package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons;

import lombok.Data;

import java.util.List;

@Data
public class CrewGroup {

    private int groupId;
    private String groupName;
    private List<Person> persons;
}