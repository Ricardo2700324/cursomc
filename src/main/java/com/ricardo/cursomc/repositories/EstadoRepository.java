package com.ricardo.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricardo.cursomc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();

}