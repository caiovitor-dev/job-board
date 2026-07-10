package com.caio.job_board.controller;

import com.caio.job_board.dto.EnterpriseCreateDTO;
import com.caio.job_board.dto.EnterpriseResponseDTO;
import com.caio.job_board.dto.EnterpriseUpdateDTO;
import com.caio.job_board.entity.Enterprise;
import com.caio.job_board.mapper.EnterpriseMapper;
import com.caio.job_board.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("enterprise")
public class EnterpriserController {

    private final EnterpriseService enterpriseService;
    private final EnterpriseMapper enterpriseMapper;

    @PostMapping
    public ResponseEntity<Void> createEnterprise(@RequestBody EnterpriseCreateDTO dto){

        Enterprise enterprise = enterpriseService.saveEnterprise(enterpriseMapper.toEntity(dto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(enterprise.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<EnterpriseResponseDTO> getEnterprise(@PathVariable UUID id){

        return enterpriseService.getEnterprise(id)
                .map(enterpriseMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity <?> updateEnterprise(@PathVariable UUID id, @RequestBody EnterpriseUpdateDTO dto){

        return enterpriseService.updateEnterprise(dto,id)
                .map(enterprise ->ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());

    }
//    @PatchMapping
//    public ResponseEntity<> updatePartialEnterprise(@PathVariable UUID id, @RequestBody EnterpriseUpdatePartialDTO dto){
//
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnterprise(@PathVariable UUID id){
        return enterpriseService.getEnterprise(id).map(enterprise -> {

            enterpriseService.deleteEnterprise(enterprise);
            return ResponseEntity.noContent().build();

        }).orElse(ResponseEntity.notFound().build());
    }
}
