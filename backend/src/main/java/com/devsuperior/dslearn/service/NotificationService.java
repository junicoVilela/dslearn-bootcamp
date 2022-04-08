package com.devsuperior.dslearn.service;

import com.devsuperior.dslearn.dto.NotificationDTO;
import com.devsuperior.dslearn.entities.Notification;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.repositories.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private NotificationRepository notificationRepository;

    private AuthService authService;

    public NotificationService(NotificationRepository notificationRepository, AuthService authService) {
        this.notificationRepository = notificationRepository;
        this.authService = authService;
    }

    public Page<NotificationDTO> notificationForCurrentUser(boolean unreadOnly, Pageable pageable) {
        User user = authService.authenticated();

        Page<Notification> page = notificationRepository.find(user, unreadOnly, pageable);

        return page.map(n -> new NotificationDTO(n));
    }
}
