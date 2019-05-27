package org.greenwin.VLclient.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    public Category(String name){
        this.name = name;
    }

    private int id;

    private String name;

    @JsonIgnore
    private List<Campaign> campaigns = new ArrayList<>();
}
