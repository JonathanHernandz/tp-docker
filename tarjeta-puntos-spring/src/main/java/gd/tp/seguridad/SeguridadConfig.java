package gd.tp.seguridad;

import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity // Activa la seguridad en métodos.
public class SeguridadConfig {
    @Autowired
    private final JwtUtil jwtUtil;

    public SeguridadConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity

                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Permite todas las rutas sin autentificacion
                .csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .cors(Customizer.withDefaults()) // Habilita cors
                .csrf(csrf -> csrf.disable()) // Desactivar CSRF paara APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/usuario/login", "/api/usuario/registro").permitAll()
                        .requestMatchers("/api/cliente/formulario", "/api/cliente/verificar", "/api/recuperacion/solicitar-codigo", "/api/recuperacion/verificar-codigo"
                        ,"/api/recuperacion/cambiar-password", "/api/tarjeta/*", "/api/recompensa").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Solo admin puede ver /api/admin/**
                        .requestMatchers("/api/cajero/**").hasAnyRole("ADMIN", "CAJERO") // Admin y cajero aquí
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
