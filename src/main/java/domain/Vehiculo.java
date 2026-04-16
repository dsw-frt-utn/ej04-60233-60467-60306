package domain;

public abstract class Vehiculo {
    protected String patente;
    protected Marca marca;
    protected String modelo;
    protected int anio;
    protected double capacidadCarga;
    protected Sucursal sucursal;
    private VehiculoTipo tipo;
    private Marca marcaVehiculo;

    public Vehiculo(VehiculoTipo tipo, String patente, Marca marca, String modelo, int anio, double capacidadCarga, Sucursal sucursal) {
        this.patente = patente;
        this.marcaVehiculo = marcaVehiculo;
        this.modelo = modelo;
        this.anio = anio;
        this.capacidadCarga = capacidadCarga;
        this.sucursal = sucursal;
        this.tipo = tipo;
    }

    public String getPatente() {
        return patente;
    }

    public Marca getMarca() {
        return marca;
    }
    
    public VehiculoTipo getTipo(){
        return tipo;
    }
    
    public double getCapacidadCarga(){
        return capacidadCarga;
    }
    
    public int getAnio(){
        return anio;
    }
    
    public String getCodigoSucursal(){
        return sucursal.getCodigo();
    }
    
   public abstract double calcularConsumo(double kilometros);

    public boolean esDe(VehiculoTipo tipo){
        return this.tipo == tipo;
    }
    @Override
    public String toString() {
        return marca.getNombre()+ " " + modelo+" - "+marca.getPais();
    }
}
