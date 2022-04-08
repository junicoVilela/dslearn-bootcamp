package com.devsuperior.dslearn.resources;

import com.devsuperior.dslearn.dto.NotificationDTO;
import com.devsuperior.dslearn.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationResource {

    private NotificationService notificationService;

    public NotificationResource(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<Page<NotificationDTO>> findAll(
            @RequestParam(value = "unreadOnly", defaultValue = "false") boolean unreadOnly,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<NotificationDTO> list = notificationService.notificationForCurrentUser(unreadOnly, pageRequest);

        return ResponseEntity.ok().body(list);
    }

}

