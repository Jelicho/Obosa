package com.ssafy.obosa.model.domain;

import com.ssafy.obosa.model.domain.auditing.DateEntity;
import com.ssafy.obosa.model.dto.CreateProductDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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

    public static Product setProductByProductDto(CreateProductDto createProductDto, User user)
    {
        return Product.builder()
                .pname(createProductDto.getPname())
                .pdescription(createProductDto.getPdescription())
                .user(user)
                .build();
    }


}
