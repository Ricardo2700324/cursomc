package com.ricardo.cursomc.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ricardo.cursomc.domain.Categoria;
import com.ricardo.cursomc.domain.Cliente;
import com.ricardo.cursomc.dto.CategoriaDTO;
import com.ricardo.cursomc.repositories.CategoriaRepository;
import com.ricardo.cursomc.services.exceptions.DataIntegrityException;
import com.ricardo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName());
		}

		return obj;
	}

	// metodo para inserir a gategoria usando o repo.
	@Transactional
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	// implementado metodo update
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

	}

	// metodo para realizar o delete
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos!");
		}
	}

	// metodo para listar categorias
	public List<Categoria> findAll() {
		return repo.findAll();
	}

	// Função para controlar a paginação
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());

	}
}
