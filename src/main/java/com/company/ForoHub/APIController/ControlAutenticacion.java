package com.company.ForoHub.APIController;

import com.company.ForoHub.Usuario.Usuario;
import com.company.ForoHub.infra.seguridad.DatosJWTToken;
import com.company.ForoHub.infra.seguridad.TokenService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class ControlAutenticacion {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity realizarLogeo(@RequestBody DatosAutenticacion datos){
        var autenticacionToken = new UsernamePasswordAuthenticationToken(datos.usuario(),
                datos.clave());
        var  usuarioAutenticado = manager.authenticate(autenticacionToken);
        var JWTToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTToken));
    }
}
