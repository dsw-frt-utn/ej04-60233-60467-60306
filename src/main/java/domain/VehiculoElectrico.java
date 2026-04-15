package domain;

public class VehiculoElectrico extends Vehiculo {
    private double kwhBase;

    public VehiculoElectrico(String patente, Marca marca, String modelo, int anio, double capacidadCarga,
                             Sucursal sucursal, double kwhBase) {
        super(VehiculoTipo.ELECTRICO, patente, marca, modelo, anio, capacidadCarga, sucursal);
        this.kwhBase = kwhBase;
    }
    

    @Override
    public double calcularConsumo(double kilometros) //agregamos el parámetro de kilómetros para calcular el consumo total en función de la distancia recorrida
    {
        double total = (kilometros/100)*kwhBase; //correccion: El consumo se calcula en función de los kilómetros recorridos y el consumo base por cada 100 km

        if (capacidadCarga >= 1200) {
            total = total * 1.15; //Correccion:Aumenta un 15% para vehículos con capacidad de carga mayor o igual a 1200 kg
        }

        return total;
    }

    public double getKwhBase() {
        return kwhBase;
    }
}
