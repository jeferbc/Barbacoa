package jeffersonbernalcardona.barbacoa;

/**
 * Created by Aldebarantech on 03/10/2016.
 */
public class Promocion {

    int idImagen,precio;
    String nombre,descripcion;

    public Promocion(int idImagen, int precio, String nombre, String descripcion) {
        this.idImagen = idImagen;
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public int getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}