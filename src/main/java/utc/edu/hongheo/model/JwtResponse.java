package utc.edu.hongheo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private Long id;

    private String token;

    private String type = "Bearer";

    private String username;

    private Collection<? extends GrantedAuthority> roles;
}
