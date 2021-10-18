package com.pichincha.sales.repository;

import com.pichincha.sales.entity.SupplierEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class ISupplierRepositoryTest {

    @Autowired
    private ISupplierRepository iSupplierRepository;


    @Test
    void findSupplierActive() {
        List<SupplierEntity> suppliers = iSupplierRepository.findSupplierActive();
    }

    @Test
    void alreadyUserCreated() {

        SupplierEntity entity = iSupplierRepository.alreadyUserCreated("1715869911001");
    }
}