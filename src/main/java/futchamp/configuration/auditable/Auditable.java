package futchamp.configuration.auditable;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Calendar;

/**
 * Esta clase se encargara de agregar los campos de creado (createdAt) y actualizado (updatedAt) a cada entidad de
 * forma heredada.
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Calendar createdAt;  // Fecha de creacion del registro

    @LastModifiedDate
    @Column(nullable = false)
    private Calendar updatedAt; // Fecha de ultima actualizacion del registro


    //Builder
    public Auditable() {
    }

    // Setter and getter
    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }
}
