package modelo;

public class Cliente {
    // 🔹 Atributos principales
    private int id;
    private String nombres;
    private String aPaterno;
    private String aMaterno;
    private String dni;

    // 🔹 Atributos para relación (llaves foráneas)
    private int tipoDocumentoId;
    private int generoId;

    // 🔹 Atributos descriptivos (texto para mostrar)
    private String tipoDocumento;
    private String genero;

    // 🔹 Constructores
    public Cliente() {
    }

    public Cliente(int id, String nombres, String aPaterno, String aMaterno, String dni,
                   int tipoDocumentoId, int generoId, String tipoDocumento, String genero) {
        this.id = id;
        this.nombres = nombres;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.dni = dni;
        this.tipoDocumentoId = tipoDocumentoId;
        this.generoId = generoId;
        this.tipoDocumento = tipoDocumento;
        this.genero = genero;
    }

    // 🔹 Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getaPaterno() { return aPaterno; }
    public void setaPaterno(String aPaterno) { this.aPaterno = aPaterno; }

    public String getaMaterno() { return aMaterno; }
    public void setaMaterno(String aMaterno) { this.aMaterno = aMaterno; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public int getTipoDocumentoId() { return tipoDocumentoId; }
    public void setTipoDocumentoId(int tipoDocumentoId) { this.tipoDocumentoId = tipoDocumentoId; }

    public int getGeneroId() { return generoId; }
    public void setGeneroId(int generoId) { this.generoId = generoId; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    // 🔹 Método auxiliar (opcional, útil para debug o logs)
    @Override
    public String toString() {
        return id + " - " + nombres + " " + aPaterno + " " + aMaterno + " (" + dni + ")";
    }
}
