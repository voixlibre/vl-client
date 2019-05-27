package org.greenwin.VLclient.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {

    private int id;

    String question;

    Category category;

    private Topic topic;

    private int TopicId;

    private Set<Vote> votes = new HashSet<>();

    private LocalDate startDate;

    private LocalDate endDate;

    private List<Option> options;
}
