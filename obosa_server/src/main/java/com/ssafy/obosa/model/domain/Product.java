package com.ssafy.obosa.model.domain;

import com.ssafy.obosa.model.domain.auditing.DateEntity;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
public class Product extends DateEntity
{
    @Id
    private int pid;

    @NotNull
    private String pname;

    private String pdescription;

    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

    @ColumnDefault("0")
    private int imgCount;
}
