package DAM.lab01.compraventa.model;

public class Publicacion {
    private String titulo;
    private String email;
    private String descripcion;
    private Integer precio;
    private String categoria;
    private Integer descuentoEnvio;
    private Boolean retiroEnPersona;
    private String direccionRetiroEnPersona;

    public Publicacion(String titulo, String email, String descripcion, Integer precio, String categoria, Integer descuentoEnvio, Object o, Object o1) {}

    public Publicacion(String titulo, String email, String descripcion, String precio, String categoria, Integer descuentoEnvio, Integer costoEnvio, Boolean retiroEnPersona, String direccionRetiroEnPersona) {
        this.titulo = titulo;
        this.email = email;
        this.descripcion = descripcion;
        this.precio = Integer.valueOf(precio);
        this.categoria = categoria;
        this.descuentoEnvio = descuentoEnvio;
        this.direccionRetiroEnPersona = direccionRetiroEnPersona;
    }
}
