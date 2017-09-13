package app.alcaldiaitagui.Entidades;

public class Entidades {
    private String id;
    private String texto;
    private String titulo;
    private String urlimagen;
    private String urlNoticia;
    private String resumen;
    private String fecha;

    private String nombre;
    private String url;

    public String getNombre(){return nombre;}
    public String getUrl(){return url;}

    public String getId() {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto (String texto) {
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    public String getUrlNoticia() {return urlNoticia;}

    public void setUrlNoticia(String urlNoticia) {this.urlNoticia = urlNoticia;}



    public String getResumen() {return resumen;}

    public void setResumen(String resumen) {
       this.resumen = resumen;
    }

    public String getFecha() {
        return fecha;
   }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
