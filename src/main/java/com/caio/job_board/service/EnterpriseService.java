package com.caio.job_board.service;

import com.caio.job_board.dto.EnterpriseUpdateDTO;
import com.caio.job_board.entity.Enterprise;
import com.caio.job_board.exception.ExistsEmailException;
import com.caio.job_board.exception.ExitsCnpjException;
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

        if(enterpriseRepository.existsByCnpj(enterprise.getCnpj())){
            throw new ExitsCnpjException("Existe este cnpj");
        }
        if(enterpriseRepository.existsByEmail(enterprise.getEmail())){
            throw new ExistsEmailException("Já existe este email cadastrado");
        }

        return  enterpriseRepository.save(enterprise);
    }
    public Optional<Enterprise> getEnterprise(UUID id){
        return enterpriseRepository.findById(id);
    }
    public Optional<Enterprise> updateEnterprise(EnterpriseUpdateDTO dto, UUID id){


        return enterpriseRepository.findById(id)
                .map(enterprise -> {

                    if(enterpriseRepository.existsByEmailAndIdNot(dto.email(), id)){
                        throw new ExitsCnpjException("Este cnpj já existe");
                    }

                    if(enterpriseRepository.existsByCnpjAndIdNot(dto.cnpj(), id)){
                        throw new ExistsEmailException("Este email já foi cadastrado");
                    }

                    enterprise.setDescription(dto.description());
                    enterprise.setEmail(dto.email());
                    enterprise.setCnpj(dto.cnpj());
                    enterprise.setName(dto.name());

                    return enterpriseRepository.save(enterprise);
                });

    }

    public void deleteEnterprise(Enterprise enterprise){
        enterpriseRepository.delete(enterprise);
    }

}
