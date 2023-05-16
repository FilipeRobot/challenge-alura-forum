package com.filiperobot.aluraforumapi.domain.user;

import com.filiperobot.aluraforumapi.domain.user.DTO.DadosCadastroUsuario;
import com.filiperobot.aluraforumapi.domain.user.DTO.DadosUsuarioAtualizar;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    public Usuario(DadosCadastroUsuario dadosUsuario) {
        this.nome = dadosUsuario.nome();
        this.email = dadosUsuario.email();
        this.senha = dadosUsuario.senha();
    }

    public void atualizar(DadosUsuarioAtualizar dadosUsuario) {
        if (dadosUsuario.nome() != null) {
            this.nome = dadosUsuario.nome();
        }
        if (dadosUsuario.senha() != null) {
            this.senha = dadosUsuario.senha();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
