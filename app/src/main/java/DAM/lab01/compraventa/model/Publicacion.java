package DAM.lab01.compraventa.model;

public class Publicacion {
    private String titulo;
    private String email;
    private String descripcion;
    private Integer precio;
    private String categoria;
    private Integer descuentoEnvio;
    private String direccionRetiroEnPersona;

    public Publicacion(String titulo, String email, String descripcion, Integer precio, String categoria, Integer descuentoEnvio, Object o, Object o1) {}

    public Publicacion(String titulo, String email, String descripcion, Integer precio, String categoria, Integer descuentoEnvio, String direccionRetiro) {
        this.titulo = titulo;
        this.email = email;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.descuentoEnvio = descuentoEnvio;
        this.direccionRetiroEnPersona = direccionRetiroEnPersona;
    }
}
