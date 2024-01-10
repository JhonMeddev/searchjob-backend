package com.searchjob.repository;

import com.searchjob.model.VagaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaRepository extends JpaRepository<VagaModel, Long> {

    public List<VagaModel> findAllByTituloContainingIgnoreCase(String titulo);

    public List<VagaModel> findAllByDescricaoContainingIgnoreCase(String descricao);
}
