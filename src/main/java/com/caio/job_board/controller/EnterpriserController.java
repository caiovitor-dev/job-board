package com.caio.job_board.controller;

import com.caio.job_board.dto.EnterpriseCreateDTO;
import com.caio.job_board.dto.EnterprisePatchDTO;
import com.caio.job_board.dto.EnterpriseResponseDTO;
import com.caio.job_board.dto.EnterpriseUpdateDTO;
import com.caio.job_board.entity.Enterprise;
import com.caio.job_board.mapper.EnterpriseMapper;
import com.caio.job_board.service.EnterpriseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("enterprises")
@PreAuthorize("hasAnyRole('ADMIN','ENTERPRISE')")
public class EnterpriserController {

    private final EnterpriseService enterpriseService;
    private final EnterpriseMapper enterpriseMapper;

    @PostMapping
    public ResponseEntity<Void> createEnterprise(@RequestBody @Valid EnterpriseCreateDTO dto){


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


    @GetMapping("/cu")
    public ResponseEntity<List<EnterpriseResponseDTO>> listEnterprise(
            @RequestParam(value = "numberPage",defaultValue = "1") int numberPage,
            @RequestParam(value = "numberSize",defaultValue = "0") int numberSize){

        Page<Enterprise> page = enterpriseService.searchPaginated(numberPage, numberPage);

        List<EnterpriseResponseDTO> enterprises = page.stream().map(enterpriseMapper::toDTO).toList();

        return ResponseEntity.ok(enterprises);
    }

    @PutMapping("/{id}")
    public ResponseEntity <?> replaceEnterprise(@PathVariable UUID id, @RequestBody EnterpriseUpdateDTO dto){

        return enterpriseService.updateEnterprise(dto,id)
                .map(enterprise ->ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());

    }
    @PatchMapping("/{id}")
    public ResponseEntity <?>  partialUpdateEnterprise(@PathVariable UUID id, @RequestBody EnterprisePatchDTO dto){

        return  enterpriseService.updatePartial(id,dto)
                .map(enterprise -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnterprise(@PathVariable UUID id){
        return enterpriseService.getEnterprise(id).map(enterprise -> {

            enterpriseService.deleteEnterprise(enterprise);
            return ResponseEntity.noContent().build();

        }).orElse(ResponseEntity.notFound().build());
    }
}
