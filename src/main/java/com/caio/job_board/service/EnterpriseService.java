package com.caio.job_board.service;

import com.caio.job_board.dto.EnterprisePatchDTO;
import com.caio.job_board.dto.EnterpriseUpdateDTO;
import com.caio.job_board.entity.Enterprise;
import com.caio.job_board.exception.ExistsEmailException;
import com.caio.job_board.exception.ExistsCnpjException;
import com.caio.job_board.mapper.EnterpriseMapper;
import com.caio.job_board.repository.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseMapper enterpriseMapper;

    public Enterprise saveEnterprise(Enterprise enterprise){

        if(enterpriseRepository.existsByCnpj(enterprise.getCnpj())){
            throw new ExistsCnpjException("Existe este cnpj");
        }
        if(enterpriseRepository.existsByEmail(enterprise.getEmail())){
            throw new ExistsEmailException("Já existe este email cadastrado");
        }

        return enterpriseRepository.save(enterprise);
    }

    public Optional<Enterprise> getEnterprise(UUID id){
        return enterpriseRepository.findById(id);
    }

    public Optional<Enterprise> updateEnterprise(EnterpriseUpdateDTO dto, UUID id){


        return enterpriseRepository.findById(id)
                .map(enterprise -> {

                    if(dto.email() != null && enterpriseRepository.existsByEmailAndIdNot(dto.email(),id)){
                        throw new ExistsCnpjException("Este email já existe");
                    }

                    if(dto.cnpj() != null && enterpriseRepository.existsByCnpjAndIdNot(dto.cnpj(), id)){
                        throw new ExistsEmailException("Este cnpj já foi cadastrado");
                    }

                    enterprise.setDescription(dto.description());
                    enterprise.setEmail(dto.email());
                    enterprise.setCnpj(dto.cnpj());
                    enterprise.setName(dto.name());

                    return enterpriseRepository.save(enterprise);
                });

    }

    public Optional<Enterprise> updatePartial(UUID id , EnterprisePatchDTO dto){
        return enterpriseRepository.findById(id)
                .map(enterprise -> {

                    if(enterpriseRepository.existsByEmailAndIdNot(dto.email(),id)){
                        throw new ExistsCnpjException("Este email já existe");
                    }

                    if(enterpriseRepository.existsByCnpjAndIdNot(dto.cnpj(), id)){
                        throw new ExistsEmailException("Este cnpj já foi cadastrado");
                    }

                    enterpriseMapper.updatePartial(dto, enterprise);


                    return enterpriseRepository.save(enterprise);
                });
    }

    public void deleteEnterprise(Enterprise enterprise){
        enterpriseRepository.delete(enterprise);
    }

    public Page<Enterprise> searchPaginated (int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return  enterpriseRepository.findAll(pageable);
    }

}
