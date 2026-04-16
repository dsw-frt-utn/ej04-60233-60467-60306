package data;

import java.util.ArrayList;
import java.util.Optional;

import domain.Marca;
import domain.Responsable;
import domain.Sucursal;
import domain.Vehiculo;
import domain.VehiculoCombustible;
import domain.VehiculoElectrico;

public class Persistencia {

    private static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private static ArrayList<Responsable> responsables = new ArrayList<>();
    private static ArrayList<Sucursal> sucursales = new ArrayList<>();
    private static ArrayList<Marca> marcas = new ArrayList<>(); //añadimos todos los arraylist como static ya que los manejaremos mediante controlador.

    private static void inicializarMarcas() {
        Marca m1 = new Marca("Ford", "Estados Unidos");
        Marca m2 = new Marca("Chevrolet", "Estados Unidos");
        Marca m3 = new Marca("Volkswagen", "Alemania");
        Marca m4 = new Marca("Mercedes", "Alemania");
        marcas.add(m1);
        marcas.add(m2);
        marcas.add(m3);
        marcas.add(m4);
    }

    private static void inicializarResponsables() {
        Responsable r1 = new Responsable("Carlos Gómez", "25444111", "3815551111");
        Responsable r2 = new Responsable("Laura Pérez", "30111222", "3815552222");
        Responsable r3 = new Responsable("Juan Carlos Poles", "23211252", "38143512253");
        responsables.add(r1);
        responsables.add(r2);
        responsables.add(r3);
    }

    private static void inicializarSucursales() {
        Sucursal s1 = new Sucursal("SUC01", "Av. Belgrano 1200", "Tucumán", responsables.get(0));
        Sucursal s2 = new Sucursal("SUC02", "San Martín 450", "Yerba Buena", responsables.get(1));
        Sucursal s3 = new Sucursal("SUC03", "Av.Rosales 1530", "Tucumán", responsables.get(1));

        sucursales.add(s1);
        sucursales.add(s2);
        sucursales.add(s3);
    }
public static Optional<Sucursal> getSucursal(String codigo) {
    return sucursales.stream()
            .filter(s -> s.getCodigo().equals(codigo))
            .findFirst();
}
public static Optional<Marca> getMarca (String nombre) {
    return marcas.stream()
            .filter(m -> m.getNombre().equalsIgnoreCase(nombre))
            .findFirst();
}
public static void agregarVehiculo(Vehiculo vehiculo) {
    vehiculos.add(vehiculo);
    System.out.println("Vehiculo agregado: "+ vehiculo.getPatente() + " info: "+vehiculo.getAnio() + " "+ vehiculo.getTipo());
    
    System.out.println("Vehiculos : "+ vehiculos.size());
}

    public static ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public static Optional<Vehiculo> getVehiculo(String patente) {
        return vehiculos.stream()
                .filter(v -> v.getPatente().equals(patente))
                .findFirst();
    }
    public static void eliminarVehiculo(String patente) {
    vehiculos.removeIf(v -> v.getPatente().equals(patente));
}

    public static void inicializar() {
        inicializarResponsables();
        inicializarSucursales();
        inicializarMarcas();
    }
}
