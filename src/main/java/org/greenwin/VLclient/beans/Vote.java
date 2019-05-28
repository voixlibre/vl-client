package org.greenwin.VLclient.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

    private int id;

    private int userId;

    private AppUser user;

    private LocalDate date;

    private  Campaign campaign;

    private int campaignId;

    private Option option;

}
