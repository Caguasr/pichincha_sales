package com.pichincha.sales.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@AllArgsConstructor
public class SupplierEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotEmpty(message = "must not be empty")
    @Column(nullable = false, unique = true)
    public String ruc;

    @NotEmpty(message = "must not be empty")
    @Column(nullable = false)
    public String name;

    public Integer active;


    @JsonIgnoreProperties({"supplier", "hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier", cascade = CascadeType.ALL)
    public List<ProductEntity> product;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    public Date createdAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    public Date updateAt;

    SupplierEntity(){
        this.product = new ArrayList<>();
    }

    @PrePersist
    private void setData() {
        this.createdAt = new Date();
        this.active = 1;
    }

    public Date setDateUpdate (){
        return new Date();
    }
}
