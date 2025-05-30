package com.project.academia_api.infrastructure.adapters.repository.jpa;

import com.project.academia_api.domain.model.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoSpringRepository extends JpaRepository<AlunoEntity, Long> {

    @Query("SELECT a FROM AlunoEntity a" +
            " WHERE (:id IS NULL OR a.id = :id)" +
            " AND (:cpf IS NULL OR a.cpf = :cpf)" +
            " AND (:nome IS NULL OR a.nome LIKE %:nome%)")
    AlunoEntity findByParams(Long id, String cpf, String nome);

    boolean existsByCpf(String cpf);
}
