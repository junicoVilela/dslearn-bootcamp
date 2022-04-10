package com.devsuperior.dslearn.service;

import com.devsuperior.dslearn.dto.NotificationDTO;
import com.devsuperior.dslearn.entities.Deliver;
import com.devsuperior.dslearn.entities.Notification;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.observers.DeliverRevisionObserver;
import com.devsuperior.dslearn.repositories.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Service
public class NotificationService implements DeliverRevisionObserver {

    private static Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private NotificationRepository notificationRepository;

    private DeliverService deliverService;

    private AuthService authService;

    public NotificationService(NotificationRepository notificationRepository, DeliverService deliverService, AuthService authService) {
        this.notificationRepository = notificationRepository;
        this.deliverService = deliverService;
        this.authService = authService;
    }

    @PostConstruct
    private void initialize() {
        deliverService.subscribeDeliverRevisionObserver(this);
    }

    public Page<NotificationDTO> notificationForCurrentUser(boolean unreadOnly, Pageable pageable) {
        User user = authService.authenticated();

        Page<Notification> page = notificationRepository.find(user, unreadOnly, pageable);

        return page.map(n -> new NotificationDTO(n));
    }

    @Transactional
    public void saveDeliverNotification(Deliver deliver) {
        Long sectionId = deliver.getLesson().getSection().getId();
        Long resourceId = deliver.getLesson().getSection().getResource().getId();
        Long offerId = deliver.getLesson().getSection().getResource().getOffer().getId();

        String route = "/offers/" + offerId + "/resources/" + resourceId + "/sections/" + sectionId;
        String text = deliver.getFeedback();
        Instant moment = Instant.now();
        User user = deliver.getEnrollment().getStudent();

        Notification notification = new Notification(null, text, moment, false, route, user);
        notificationRepository.save(notification);
    }

    @Override
    public void onSaveRevision(Deliver deliver) {
        saveDeliverNotification(deliver);
    }
}
