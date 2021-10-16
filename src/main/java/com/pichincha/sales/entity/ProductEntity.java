package com.pichincha.sales.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "must not be empty")
    private String name;

    @NotNull(message = "must not be empty")
    private Double price;

    @NotNull(message = "must not be empty")
    private Integer stock;

    @NotNull
    @JsonIgnoreProperties({"products", "hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private SupplierEntity supplier;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    public Date createdAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    public Date updateAt;

    @PrePersist
    private void setData() {
        this.createdAt = new Date();

    }

    public Date setDateUpdate() {
        return new Date();
    }
}
