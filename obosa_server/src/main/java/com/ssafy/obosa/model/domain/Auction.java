package com.ssafy.obosa.model.domain;

import com.ssafy.obosa.model.domain.auditing.DateEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auction extends DateEntity
{
//    aid int(11) NOT NULL AUTO_INCREMENT,
//    pid int(11) NOT NULL,
//    uid int(11) NOT NULL,
//    lowPrice int(11) NOT NULL,
//    highPrice int(11) NOT NULL DEFAULT '2000000000',
//    description text,
//    registeredDate datetime NOT NULL,
//    endDate datetime NOT NULL,
//    aucState char(1) NOT NULL DEFAULT '0',
//    upPrice int(11) NOT NULL DEFAULT '1000',
    @Id
    private int pid;
    private String pname;
    private String pdescription;

    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

}
