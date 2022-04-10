package com.devsuperior.dslearn.service;

import com.devsuperior.dslearn.dto.DeliverRevisionDTO;
import com.devsuperior.dslearn.entities.Deliver;
import com.devsuperior.dslearn.observers.DeliverRevisionObserver;
import com.devsuperior.dslearn.repositories.DeliverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class DeliverService {

    private DeliverRepository deliverRepository;

    private Set<DeliverRevisionObserver> deliverRevisionObservers = new LinkedHashSet<>();

    public DeliverService(DeliverRepository deliverRepository) {
        this.deliverRepository = deliverRepository;
    }

    @Transactional
    public void saveRevision(Long id, DeliverRevisionDTO dto) {
        Deliver deliver = deliverRepository.getOne(id);
        deliver.setStatus(dto.getStatus());
        deliver.setFeedback(dto.getFeedback());
        deliver.setCorrectCount(dto.getCorrectCount());

        deliverRepository.save(deliver);
        for (DeliverRevisionObserver observer : deliverRevisionObservers) {
            observer.onSaveRevision(deliver);
        }
    }


    public void subscribeDeliverRevisionObserver(DeliverRevisionObserver observer) {
        deliverRevisionObservers.add(observer);
    }
}
