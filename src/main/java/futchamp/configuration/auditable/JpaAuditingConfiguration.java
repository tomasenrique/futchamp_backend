package futchamp.configuration.auditable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * Esta clase se encargara de auditar las fechas de creado (createdAt) y actualizado (updatedAt)  rellenando de forma
 * automatica los campos junto con la clase Auditable que sera la que agrege los campos a cada entidad de forma heredada.
 */

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable("futchamp");
    }
}
