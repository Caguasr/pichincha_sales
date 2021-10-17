package com.pichincha.sales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "detail_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailOrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private Integer quantity;

    @NotNull(message = "must not be empty")
    @JsonIgnoreProperties({"supplier","hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;



}
