package com.ssafy.obosa.model.domain;

import com.ssafy.obosa.model.domain.auditing.DateEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auction extends DateEntity
{

    @Id
    private int aid;

    @OneToOne
    @JoinColumn(name = "uid")
    private User user;

    @OneToOne
    @JoinColumn(name = "pid")
    private Product product;

    @NotNull
    private int lowPrice;

    @ColumnDefault("2000000000")
    private int highPrice;

    @NotNull
    private String description;

    @NotNull
    private String endDate;

    @ColumnDefault("0")
    private char aucState;

    @ColumnDefault("1000")
    private int upPrice;
}
