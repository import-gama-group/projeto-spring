package com.example.app.controller;

import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.LoginDTO;
import com.example.app.dto.Sessao;
import com.example.app.model.Usuario;
import com.example.app.repository.UsuarioRepository;
import com.example.app.security.jwt.JWTConstants;
import com.example.app.service.LoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping
	public Sessao logar(@RequestBody LoginDTO login) throws Exception {

		if (login == null || login.getUsuario().isEmpty() || login.getSenha().isEmpty()) {
			throw new RuntimeException("Login e senha são requeridos");
		}

		Optional<Usuario> optuser = repository.findByLogin(login.getUsuario());
		System.out.println(repository.findByLogin(login.getUsuario()));
		Usuario usuario = optuser.get();

		boolean senhaOk = encoder.matches(login.getSenha(),usuario.getSenha());

		if (!senhaOk) {
			//throw new RuntimeException("Senha inválida para o login: " + login.getUsuario());
		}

		
		long tempoToken = 1 * 60 * 60 * 1000;
		Sessao sessao = new Sessao();
		sessao.setDataInicio(new Date(System.currentTimeMillis()));
		sessao.setDataFim(new Date(System.currentTimeMillis() + tempoToken));
		
		sessao.setLogin(usuario.getLogin());

		sessao.setToken(JWTConstants.PREFIX + getJWTToken(sessao));

		return sessao;
	}
	
	//como vc gerenciaria a nivel de banco o role de um usuario
	private String getJWTToken(Sessao sessao) {
		String role = "ROLE_ADMIN";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

		String token = Jwts.builder().setSubject(sessao.getLogin())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(sessao.getDataInicio()).setExpiration(sessao.getDataFim())
				.signWith(SignatureAlgorithm.HS512, JWTConstants.KEY.getBytes()).compact();

		return token;
	}
	
	@PostMapping("/nova-senha")
	
	  public @ResponseBody String solicitarNovaSenha(@RequestBody Usuario usuario){
		
		return service.solicitarNovaSenha(usuario);
	  }
	
	@PostMapping("/altera-senha")
	@ResponseBody
	  public void alterarSenha(@RequestParam String login, @RequestParam String senhaTemporaria, @RequestParam String novaSenha ){
		
		service.alterarSenha(login, senhaTemporaria, novaSenha);
	  }
	
}
