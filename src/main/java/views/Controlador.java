package views;

import data.Persistencia;
import domain.Vehiculo;
import domain.VehiculoTipo;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class Controlador {
    
    public static ArrayList<VehiculoViewModel> getVehiculos(){
        ArrayList<VehiculoViewModel> vehiculos = new ArrayList<>();
        for(Vehiculo vehiculo : Persistencia.getVehiculos()) {
            vehiculos.add(new VehiculoViewModel(vehiculo));
        }
        return vehiculos;
    }
    
    public static double[] calcularConsumos(Map<String, Double> vehiculos){
        double consumoElectricos = 0;
        double consumoCombustible= 0;
        for(Map.Entry<String, Double> entry : vehiculos.entrySet()){
           double consumo = 0;
           Optional<Vehiculo> vehiculo = Persistencia.getVehiculo(entry.getKey());
           if(vehiculo.isPresent()){
               consumo = vehiculo.get().calcularConsumo(entry.getValue());
               consumoElectricos += vehiculo.get().esDe(VehiculoTipo.ELECTRICO) ? consumo : 0;
               consumoCombustible += vehiculo.get().esDe(VehiculoTipo.COMBUSTIBLE) ? consumo : 0;
           }
        }
        return new double[] {consumoElectricos, consumoCombustible};
    }
    
    
    public static boolean registrarVehiculoCombustible(String patente, String marcaStr, String modelo,String pais, int anio, double capCarga, int sucursalIdx, double kmPorLitro, double litrosExtra) {
        if (Persistencia.getVehiculo(patente).isPresent()) {
            return false; // Si la patente ya existe
        }
        
        domain.Marca marca = new domain.Marca(marcaStr, pais);
        domain.Sucursal sucursal = Persistencia.getSucursales().get(sucursalIdx);
        
        domain.VehiculoCombustible v = new domain.VehiculoCombustible(patente, marca, modelo, anio, capCarga, sucursal, kmPorLitro, litrosExtra);
        return Persistencia.registrarVehiculo(v);
    }

    public static boolean registrarVehiculoElectrico(String patente, String marcaStr, String modelo,String pais, int anio, double capCarga, int sucursalIdx, double kwhBase) {
        if (Persistencia.getVehiculo(patente).isPresent()) {
            return false; // La patente ya existe
        }
        domain.Marca marca = new domain.Marca(marcaStr, pais);
        domain.Sucursal sucursal = Persistencia.getSucursales().get(sucursalIdx);
        
        domain.VehiculoElectrico v = new domain.VehiculoElectrico(patente, marca, modelo, anio, capCarga, sucursal, kwhBase);
        return Persistencia.registrarVehiculo(v);
    }

    public static void eliminarVehiculo(String patente) {
        Optional<Vehiculo> v = Persistencia.getVehiculo(patente);
        v.ifPresent(vehiculo -> Persistencia.eliminarVehiculo(vehiculo));
    }
}
