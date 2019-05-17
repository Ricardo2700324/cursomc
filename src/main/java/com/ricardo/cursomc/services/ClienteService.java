package com.ricardo.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ricardo.cursomc.domain.Cliente;
import com.ricardo.cursomc.domain.Cliente;
import com.ricardo.cursomc.dto.ClienteDTO;
import com.ricardo.cursomc.repositories.ClienteRepository;
import com.ricardo.cursomc.services.exceptions.DataIntegrityException;
import com.ricardo.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName());
		}
		
		return obj;
	}
	
	//implementado metodo update
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	//metodo para realizar o delete
	public void delete(Integer id) {
		find(id);
		try {
		repo.delete(id);
		}catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas!");
		}
	}
	
	//metodo para listar categorias
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	//Função para controlar a paginação
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		//throw new UnsupportedOperationException();
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
