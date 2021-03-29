package com.app.usuario.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.usuario.Model.UsuarioModel;
import com.app.usuario.Repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioModel salvar(String nome, Date dataNascimento, String path) throws Exception {
		
		UsuarioModel usuario = new UsuarioModel();
		
		usuario.setNome(nome);
		usuario.setDataNascimento(dataNascimento);
		usuario.setPathFoto(path);
		
		return usuarioRepository.save(usuario);
	}
	
	public List<UsuarioModel> getUsuarios(){
		
		return usuarioRepository.findAll();
	}
	
	public void validaNome(String nome) throws Exception {
		
		UsuarioModel usuario = usuarioRepository.findBynome(nome);
		
		if(usuario != null) {
			throw new Exception("Já existe um usuário cadastro com esse nome.");
		}

	}
}
