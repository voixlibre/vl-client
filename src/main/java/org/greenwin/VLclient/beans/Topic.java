package org.greenwin.VLclient.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    private int id;

    private String title;
    private String summary;
    private Date dateOfCreation;

    private AppUser editor;

    private AppUser voter;


}
