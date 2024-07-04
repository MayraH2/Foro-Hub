package com.company.ForoHub.infra.seguridad;

import com.company.ForoHub.repositorio.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSeguridad extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtener el token del header
        var authHeader = request.getHeader("Authorization");
        if( authHeader != null){
            var token = authHeader.replace("Bearer ","");
            var nombreUsuario = tokenService.getSubject(token);
            if ( nombreUsuario!= null){

            }
        }
        filterChain.doFilter(request,response);
    }
}
