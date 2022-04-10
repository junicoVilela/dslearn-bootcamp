package com.devsuperior.dslearn.resources;

import com.devsuperior.dslearn.dto.DeliverRevisionDTO;
import com.devsuperior.dslearn.dto.NotificationDTO;
import com.devsuperior.dslearn.service.DeliverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliverResource {

    private DeliverService deliverService;

    public DeliverResource(DeliverService deliverService) {
        this.deliverService = deliverService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody DeliverRevisionDTO dto) {
        deliverService.saveRevision(id, dto);

        return ResponseEntity.noContent().build();
    }

}

