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

    private static void inicializarVehiculos() {
        Sucursal s1 = sucursales.get(0);
        Sucursal s2 = sucursales.get(1);
        
        Marca m1=new Marca("Fiat","Italia");
        Marca m2=new Marca("Ford","EEUU");
        Marca m3=new Marca("Toyota","Japon");
        Marca m4=new Marca("Audi","Alemania");
        
        VehiculoElectrico v1 = new VehiculoElectrico("AE123FG", m1, "Kangoo E-Tech", 2020, 1000, s1, 16);
        VehiculoElectrico v2 = new VehiculoElectrico("AF456HI", m2, "E-Transit", 2021, 1300, s2, 16);

        VehiculoCombustible v3 = new VehiculoCombustible("AC789JK",m3, "Daily", 2023, 1200, s1, 8, 1.5);
        VehiculoCombustible v4 = new VehiculoCombustible("AD321LM",m4, "Sprinter", 2020, 1200, s2, 7, 1);
        



        vehiculos.add(v1);
        vehiculos.add(v2);
        vehiculos.add(v3);
        vehiculos.add(v4);
    }

    public static ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public static Optional<Vehiculo> getVehiculo(String patente) {
        return vehiculos.stream()
                .filter(v -> v.getPatente().equals(patente))
                .findFirst();
    }
    
    public static ArrayList<Sucursal> getSucursales(){
        return sucursales;
    }
    
    public static void inicializar(){
        inicializarResponsables();
        inicializarSucursales();
        inicializarVehiculos();
        inicializarMarcas();

    }
    
    public static boolean registrarVehiculo(Vehiculo vel){
        return vehiculos.add(vel);
    }
    
    public static boolean eliminarVehiculo(Vehiculo vel){
        return vehiculos.removeIf(v->v.getPatente().equals(vel.getPatente())); 
    }
    
}
