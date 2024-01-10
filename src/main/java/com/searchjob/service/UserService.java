package com.searchjob.service;

import com.searchjob.model.UserModel;
import com.searchjob.model.UsuarioLogin;
import com.searchjob.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserModel> cadastrarUsuario(UserModel email) {
        if (userRepository.findByEmail(email.getEmail()).isPresent())
            return Optional.empty();
        email.setPassword(criptografarSenha(email.getPassword()));
        return Optional.of(userRepository.save(email));
    }

    public Optional<UserModel> atualizarUsuario(UserModel usuario) {
        if (userRepository.findById(usuario.getId()).isPresent()) {
            Optional<UserModel> buscaUsuario = userRepository.findByEmail(usuario.getEmail());
            if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
            usuario.setPassword(criptografarSenha(usuario.getPassword()));
            return Optional.ofNullable(userRepository.save(usuario));
        }
        return Optional.empty();
    }

    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
        Optional<UserModel> email = userRepository.findByEmail(usuarioLogin.get().getEmail());
        if (email.isPresent()) {
            if (compararSenhas(usuarioLogin.get().getPassword(), email.get().getPassword())) {
                usuarioLogin.get().setId(email.get().getId());
                usuarioLogin.get().setName(email.get().getName());
                usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getPassword()));
                usuarioLogin.get().setPassword(email.get().getPassword());
                usuarioLogin.get().setTipo(email.get().getTipo());
                return usuarioLogin;
            }
        }
        return Optional.empty();
    }

    private String criptografarSenha(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaDigitada, senhaBanco);
    }

    private String gerarBasicToken(String usuario, String senha) {
        String token = usuario + ":" + senha;
        String tokenBase64 = Base64.encodeBase64String(token.getBytes(StandardCharsets.UTF_8));
        return "Basic " + tokenBase64;
    }

}
