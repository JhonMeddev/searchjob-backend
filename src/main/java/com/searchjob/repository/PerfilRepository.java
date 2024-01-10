package com.searchjob.repository;

import com.searchjob.model.PerfilModel;
import com.searchjob.model.utils.AreasEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfilRepository extends JpaRepository<PerfilModel, Long> {

    public List<PerfilModel> findAllByArea(AreasEnum area);

}
