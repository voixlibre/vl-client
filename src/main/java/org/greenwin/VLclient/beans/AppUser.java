package org.greenwin.VLclient.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    private int id;

    private String email;

    private String password;

    private List<AppRole> appRole;

    private String name;

    private String firstName;

    private String tel;

    private LocalDate birthday;

    private LocalDateTime registrationDate;

    private int addressId;

    private Boolean active = true;

}
