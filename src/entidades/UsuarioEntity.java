package entidades;

import javax.persistence.*;

/**
 * Created by osocron on 14/10/15.
 */
@Entity
@Table(name = "Usuario", schema = "", catalog = "taller_mecanico_lara")
public class UsuarioEntity {
    private String nombre;
    private String contrasena;

    @Id
    @Column(name = "Nombre", nullable = false, insertable = true, updatable = true, length = 45)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "Contrasena", nullable = false, insertable = true, updatable = true, length = 45)
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioEntity that = (UsuarioEntity) o;

        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (contrasena != null ? !contrasena.equals(that.contrasena) : that.contrasena != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (contrasena != null ? contrasena.hashCode() : 0);
        return result;
    }
}
