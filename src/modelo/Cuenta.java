package modelo;

public class Cuenta {

    private int id;
    private String numeroCuenta;
    private double saldo;

    private int clienteId;
    private int tipoCuentaId;
    private int monedaId;
    private int estadoId;

    private String clienteNombre;
    private String tipoCuenta;
    private String moneda;
    private String estado;

    // 🔹 Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public int getTipoCuentaId() { return tipoCuentaId; }
    public void setTipoCuentaId(int tipoCuentaId) { this.tipoCuentaId = tipoCuentaId; }

    public int getMonedaId() { return monedaId; }
    public void setMonedaId(int monedaId) { this.monedaId = monedaId; }

    public int getEstadoId() { return estadoId; }
    public void setEstadoId(int estadoId) { this.estadoId = estadoId; }

    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public String getTipoCuenta() { return tipoCuenta; }
    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }

    public String getMoneda() { return moneda; }
    public void setMoneda(String moneda) { this.moneda = moneda; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return numeroCuenta + " - " + clienteNombre + " (" + tipoCuenta + ")";
    }
}
