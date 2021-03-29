package com.app.usuario.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.usuario.Model.UsuarioModel;
import com.app.usuario.Service.FileService;
import com.app.usuario.Service.UsuarioService;


@RestController
@RequestMapping(value="/api")
public class UsuarioController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value="/cadastrar", consumes="multipart/form-data", method=RequestMethod.POST)
	public UsuarioModel cadastrar(@RequestParam(value="foto") MultipartFile foto, @RequestParam(value="nome") String nome, 
			@RequestParam(value="dataNascimento") @DateTimeFormat(pattern="yyyy-MM-dd") Date dataNascimento) throws Exception{
		
		verificarCampos(foto, nome, dataNascimento);
		usuarioService.validaNome(nome);
		
		String path = fileService.salvar(foto, nome);
		
		return usuarioService.salvar(nome, dataNascimento, path);
		
	}
	
	@RequestMapping(value="/usuarios", method=RequestMethod.GET)
	public List<UsuarioModel> usuarios() throws Exception{
		
		return usuarioService.getUsuarios();
	}
	
	public void verificarCampos(MultipartFile foto, String nome, Date dataNascimento) throws Exception {
		
		if(foto == null || foto.isEmpty()) {
			throw new Exception("O campo foto é obrigatorio.");
		}
		if(nome == null || nome.equals("")) {
			throw new Exception("O campo nome é obrigatorio.");
		}
		if(dataNascimento == null) {
			throw new Exception("O campo dataNascimento é obrigatorio.");
		}
	}
	
}
