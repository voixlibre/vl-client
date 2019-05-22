package org.greenwin.VLclient.beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {

    public Option(String option, Campaign campaign){
        this.option = option;
        this.campaign = campaign;
    }

    private int id;

    private String option;

    @JsonIgnore
    private List<Vote> votes;

    private int campaignId;

    @JsonIgnore
    private Campaign campaign;
}
