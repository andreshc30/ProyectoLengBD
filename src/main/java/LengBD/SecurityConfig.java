package LengBD;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/chat")
            )
                .authorizeHttpRequests(auth -> auth
                        
                .requestMatchers("/usuario/cambiarPassword").authenticated()

                // ---------- ESTATICOS ----------
                .requestMatchers("/css/**", "/js/**", "/img/**", "/webjars/**",
                                 "/logo/**", "/fonts/**", "/api/chat", "/error").permitAll()

                // ---------- PUBLICO (sin login) ----------
                .requestMatchers(
                    "/",
                    "/login",
                    "/audiciones/listado",
                    "/eventos/listado",
                    "/suscripcion/listado",
                    "/solicitudIngreso/nuevo",
                    "/solicitudIngreso/guardar"
                ).permitAll()

                // ---------- DIRECTOR / ADMIN ----------
                .requestMatchers(
                    "/banda/secciones/listadoDirector",
                    "/solicitudIngreso/listado",
                    "/solicitudIngreso/editar/**",
                    "/solicitudIngreso/editarDirector/**",
                    "/solicitudIngreso/guardarDirector",
                    "/solicitudIngreso/eliminar",
                    "/asignacionInstrumento/**"
                ).hasAnyRole("DIRECTOR", "ADMIN", "SUPER_ADMIN")

                // ---------- SOLO ADMIN ----------
                .requestMatchers(
                    "/banda/secciones/adminListado",
                    "/suscripcion/nuevo",
                    "/suscripcion/editar/**",
                    "/suscripcion/guardar",
                    "/suscripcion/eliminar"
                ).hasAnyRole("ADMIN", "SUPER_ADMIN")

                // ---------- BANDA: requiere login ----------
                .requestMatchers("/banda/**").authenticated()

                // ---------- TODO LO DEMAS ----------
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/procesar-login")
                .defaultSuccessUrl("/banda/listado", false)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .exceptionHandling(ex -> ex.accessDeniedPage("/acceso_denegado"))
            .sessionManagement(session -> session
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}