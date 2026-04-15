package domain;

public class VehiculoCombustible extends Vehiculo {
    private double kilometrosPorLitro;
    private double litrosExtra;

    public VehiculoCombustible(String patente, Marca marca, String modelo, int anio, double capacidadCarga,
                               Sucursal sucursal, double kilometrosPorLitro, double litrosExtra) {
        super(VehiculoTipo.COMBUSTIBLE, patente, marca, modelo, anio, capacidadCarga, sucursal);
        this.kilometrosPorLitro = kilometrosPorLitro;
        this.litrosExtra = litrosExtra;
    }
    
     public double getKilometrosPorLitro() {
        return kilometrosPorLitro;
    }

    public double getLitrosExtra() {
        return litrosExtra;
    }

    @Override
    public double calcularConsumo(double kilometros) {
        double litrosExtraAplicables = (anio < 2021) ? litrosExtra : 0;
        double calculoLitrosExtra = (kilometros / 15) * litrosExtraAplicables; //Calcula los litros extra basados en la distancia recorrida y el consumo adicional por cada 100 km
        double total = (kilometros / kilometrosPorLitro) + calculoLitrosExtra; //Calcula el consumo total en litros basado en la distancia recorrida y el rendimiento del vehículo
        return total;
    }
}
