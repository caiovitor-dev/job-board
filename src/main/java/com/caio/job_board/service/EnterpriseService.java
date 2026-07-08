package com.caio.job_board.service;

import com.caio.job_board.entity.Enterprise;
import com.caio.job_board.repository.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;

    public Enterprise saveEnterprise(Enterprise enterprise){
        return  enterpriseRepository.save(enterprise);
    }
    public Optional<Enterprise> getEnterprise(UUID id){
        return enterpriseRepository.findById(id);
    }
}
