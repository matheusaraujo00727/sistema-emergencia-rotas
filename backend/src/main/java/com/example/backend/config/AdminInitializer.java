package com.example.backend.config;

import com.example.backend.entity.Usuario;
import com.example.backend.enums.PerfilUsuario;
import com.example.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {

        String cpfAdmin =
                "000.000.000-00";

        if (usuarioRepository
                .findByCpf(cpfAdmin)
                .isEmpty()) {

            Usuario admin =
                    new Usuario();

            admin.setNome("adm");
            admin.setCpf(cpfAdmin);
            admin.setPerfil(
                    PerfilUsuario.ADMIN
            );

            usuarioRepository.save(admin);

            System.out.println(
                    "Administrador criado!"
            );
        }
    }
}
