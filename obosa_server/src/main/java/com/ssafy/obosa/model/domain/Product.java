package com.ssafy.obosa.model.domain;

import com.ssafy.obosa.model.domain.auditing.DateEntity;
import com.ssafy.obosa.model.dto.ProductRegistrationDto;
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
    public static Product setProductByProductRegistrationDto(ProductRegistrationDto productRegistrationDto, User user)
    {
        return Product.builder()
                .pname(productRegistrationDto.getPname())
                .pdescription(productRegistrationDto.getPdescription())
                .user(user)
                .build();
    }
}
