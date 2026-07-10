package com.caio.job_board.repository;

import com.caio.job_board.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EnterpriseRepository extends JpaRepository<Enterprise, UUID> {

    boolean existsByEmailAndIdNot(String email,UUID id);

    boolean existsByCnpjAndIdNot(String cnpj, UUID id);

    boolean existsByEmail(String email);

    boolean existsByCnpj(String cnpj);


}
