package com.pichincha.sales.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"orders", "handler", "hibernateLazyInitializer"})
    private CustomerEntity customer;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private List<DetailOrderEntity> detail;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    
    OrderEntity(){
        this.detail = new ArrayList<>();
    }

    @PrePersist
    private void perPersist(){
        this.createdAt = new Date();
    }


}
