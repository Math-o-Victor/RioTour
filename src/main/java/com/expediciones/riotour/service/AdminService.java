package com.expediciones.riotour.service;

import com.expediciones.riotour.models.AdminLoginModel;
import com.expediciones.riotour.models.AdminModel;
import com.expediciones.riotour.repository.AdminRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Optional<AdminModel> cadastrarUsuario(AdminModel usuario) {

        if (adminRepository.findByEmail(usuario.getEmail()).isPresent())
            return Optional.empty();

        usuario.setSenha(criptografarSenha(usuario.getSenha()));


        return Optional.of(adminRepository.save(usuario));

    }

    public Optional<AdminModel> atualizarUsuario(AdminModel usuario) {

        if(adminRepository.findById(usuario.getId()).isPresent()) {

            Optional<AdminModel> buscaUsuario = adminRepository.findByEmail(usuario.getEmail());

            if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != usuario.getId()))
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

            usuario.setSenha(criptografarSenha(usuario.getSenha()));

            return Optional.ofNullable(adminRepository.save(usuario));

        }

        return Optional.empty();

    }

    public Optional<AdminLoginModel> autenticarUsuario(Optional<AdminLoginModel> usuarioLogin) {

        Optional<AdminModel> usuario = adminRepository.findByEmail(usuarioLogin.get().getEmail());

        if (usuario.isPresent()) {

            if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

                usuarioLogin.get().setEmail(usuario.get().getEmail());
                usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getSenha()));
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
