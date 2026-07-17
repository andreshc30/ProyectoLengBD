package LengBD.service;

import LengBD.domain.UsuarioLoginDTO;
import LengBD.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

        System.out.println(">>> loadUserByUsername: [" + correo + "]");

        UsuarioLoginDTO usuario = usuarioRepository.buscarPorCorreo(correo);

        System.out.println(">>> encontrado: " + (usuario != null));

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + correo);
        }
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new UsernameNotFoundException("El usuario no tiene contraseña configurada");
        }

        List<String> roles = usuarioRepository.buscarRolesPorCedula(usuario.getCedula());
        System.out.println(">>> roles: " + roles);

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String rol : roles) {
            authorities.add(new SimpleGrantedAuthority(
                "ROLE_" + rol.trim().toUpperCase().replace(" ", "_").replace("-", "_")
            ));
        }

        boolean activo = usuario.getIdEstado() != null && usuario.getIdEstado() == 1;

        return User.withUsername(usuario.getCorreo())
                .password(usuario.getPassword())
                .authorities(authorities)
                .disabled(!activo)
                .build();
    }
}