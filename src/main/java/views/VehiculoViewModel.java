package views;

import views.AgregarVehiculo;
import domain.*;
import javax.swing.JOptionPane;

public class VehiculoViewModel {

    private String patente;
    private String vehiculo;
    private String tipo;
    private String sucursal;
    private double capacidadCarga;
    private double kmPorLitro;
    private int anio;
    private double litrosExtra;
    private double kmARecorrer;
    private static Double kmGlobal = null;

    public VehiculoViewModel(Vehiculo vehiculo) {
        if (vehiculo == null) {
            return;
        }
        patente = vehiculo.getPatente();
        this.vehiculo = vehiculo.toString();
        tipo = vehiculo.getTipo().name();
        sucursal = vehiculo.getCodigoSucursal();
        capacidadCarga = vehiculo.getCapacidadCarga();
        anio = vehiculo.getAnio();
        kmPorLitro = vehiculo instanceof VehiculoCombustible
                ? ((VehiculoCombustible) vehiculo).getKilometrosPorLitro()
                : vehiculo instanceof VehiculoElectrico
                        ? ((VehiculoElectrico) vehiculo).getKwhBase()
                        : 0;
        litrosExtra = vehiculo instanceof VehiculoCombustible ? ((VehiculoCombustible) vehiculo).getLitrosExtra() : 0;
        if (kmGlobal == null) {
            String input = JOptionPane.showInputDialog(null,
                    "Ingrese los kilómetros a recorrer para TODOS los vehículos:",
                    "Kilómetros a recorrer",
                    JOptionPane.QUESTION_MESSAGE);
            try {
                kmGlobal = Double.parseDouble(input);
            } catch (Exception e) {
                kmGlobal = 100.0;
            }
        }
        kmARecorrer = kmGlobal;
    }

    public String getPatente() {
        return patente;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public double getKmPorLitro() {
        return kmPorLitro;
    }

    public int getAnio() {
        return anio;
    }

    public double getLitrosExtra() {
        return litrosExtra;
    }

    public double getKmARecorrer() {
        return kmARecorrer;
    }

    public String getSucursal() {
        return sucursal;
    }
}
