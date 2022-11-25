package org.datn.service;

import org.datn.entity.Cours;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CoursService {
    List<Cours> findAll();

    List<Cours> pagination();

    Cours createCours(Cours co);

    void deleteCours(Long id);
    Cours updateCours(Cours co);

    Cours findByIDCours(Long id);

    Page<Cours> getCoursPaging(Optional<Integer> pageNumber, Integer pageSize);
}
