package com.expediciones.riotour.service;

import com.expediciones.riotour.models.UsuarioLoginModel;
import com.expediciones.riotour.models.UsuarioModel;
import com.expediciones.riotour.repository.UsuarioRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    public UsuarioRepository usuarioRepository;

    public Optional<UsuarioModel> cadastrarUsuario(UsuarioModel usuario) {

        if (usuarioRepository.findByNomeUsuario(usuario.getNomeUsuario()).isPresent())
            return Optional.empty();

        usuario.setSenha(criptografarSenha(usuario.getSenha()));


        return Optional.of(usuarioRepository.save(usuario));

    }

    public Optional<UsuarioModel> atualizarUsuario(UsuarioModel usuario) {

        if(usuarioRepository.findById(usuario.getId()).isPresent()) {

            Optional<UsuarioModel> buscaUsuario = usuarioRepository.findByNomeUsuario(usuario.getNomeUsuario());

            if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != usuario.getId()))
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

            usuario.setSenha(criptografarSenha(usuario.getSenha()));

            return Optional.ofNullable(usuarioRepository.save(usuario));

        }

        return Optional.empty();

    }

    public Optional<UsuarioLoginModel> autenticarUsuario(Optional<UsuarioLoginModel> usuarioLogin) {

        Optional<UsuarioModel> usuario = usuarioRepository.findByNomeUsuario(usuarioLogin.get().getNomeUsuario());

        if (usuario.isPresent()) {

            if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

                usuarioLogin.get().setNomeUsuario(usuario.get().getNome());
                usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getNomeUsuario(), usuarioLogin.get().getSenha()));
                usuarioLogin.get().setSenha(usuario.get().getSenha());

                return usuarioLogin;

            }
        }

        return Optional.empty();

    }

    private String criptografarSenha(String senha) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(senha);

    }

    private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(senhaDigitada, senhaBanco);

    }

    private String gerarBasicToken(String usuario, String senha) {

        String token = usuario + ":" + senha;
        byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(tokenBase64);

    }
}
