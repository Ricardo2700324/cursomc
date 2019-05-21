package com.ricardo.cursomc.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricardo.cursomc.domain.Categoria;
import com.ricardo.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	//@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	//Page<Produto> search(@Param("nome") String nome,@Param("categorias") List<Categoria> categorias,Pageable pageRequest);
	Page<Produto> findDistnctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias,Pageable pageRequest);
}
