package org.greenwin.VLclient.beans;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Campaign {

    private int id;

    private Topic topic;

    private int TopicId;

    Set<Vote> votes = new HashSet<>();

    private LocalDate startDate;

    private LocalDate endDate;

    private List<Option> options;
}
