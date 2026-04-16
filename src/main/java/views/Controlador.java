package views;

import data.Persistencia;
import domain.Marca;
import domain.Sucursal;
import domain.Vehiculo;
import domain.VehiculoCombustible;
import domain.VehiculoElectrico;
import domain.VehiculoTipo;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import javax.swing.JOptionPane;

public class Controlador {

    public static ArrayList<VehiculoViewModel> getVehiculos() {
        ArrayList<VehiculoViewModel> vehiculos = new ArrayList<>();
        for (Vehiculo vehiculo : Persistencia.getVehiculos()) {
            vehiculos.add(new VehiculoViewModel(vehiculo));
        }
        return vehiculos;
    }

    public static void altaVehiculos(AgregarVehiculo agregarVehiculo) { //chequeo que todos los campos esten completos. 
        System.out.println("prueba");
        String tipoVehiculo = agregarVehiculo.getComboTipoVehiculo().getSelectedItem().toString();
        boolean litrosExtraValido = tipoVehiculo.equalsIgnoreCase("Eléctrico")
                || !agregarVehiculo.getTextoLitrosExtra().getText().isBlank();

        if (!agregarVehiculo.getTextoAnio().getText().isBlank()
                && !agregarVehiculo.getTextoCapacidad().getText().isBlank()
                && !agregarVehiculo.getTextoKmPorLitro().getText().isBlank()
                && litrosExtraValido
                && !agregarVehiculo.getTextoModelo().getText().isBlank()
                && !agregarVehiculo.getTextoPatente().getText().isBlank()) {
            System.out.println("prueba");
            //---------------------------------------------------------------------------\\
            String sucursalSeleccionada = agregarVehiculo.getComboSucursal().getSelectedItem().toString();
            String marcaSeleccionada = agregarVehiculo.getComboMarcaVehiculo().getSelectedItem().toString();
            //---------------------------------------------------------------------------\\            

            Optional<Sucursal> sucursal = Persistencia.getSucursal(sucursalSeleccionada);
            Optional<Marca> marca = Persistencia.getMarca(marcaSeleccionada);
            // Validar patente duplicada
            String patente = agregarVehiculo.getTextoPatente().getText();
            if (Persistencia.getVehiculo(patente).isPresent()) {
                JOptionPane.showMessageDialog(agregarVehiculo, "Ya existe un vehículo con esa patente.", "Error: Patente duplicada.", JOptionPane.ERROR_MESSAGE);
                return;
            }

// Validar números negativos
            try {
                if (Double.parseDouble(agregarVehiculo.getTextoCapacidad().getText()) < 0
                        || Double.parseDouble(agregarVehiculo.getTextoKmPorLitro().getText()) < 0
                        || Integer.parseInt(agregarVehiculo.getTextoAnio().getText()) < 0) {
                    JOptionPane.showMessageDialog(agregarVehiculo, "No se permiten valores negativos.", "Error: Valores inválidos.", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (tipoVehiculo.equalsIgnoreCase("Combustible")
                        && Double.parseDouble(agregarVehiculo.getTextoLitrosExtra().getText()) < 0) {
                    JOptionPane.showMessageDialog(agregarVehiculo, "No se permiten valores negativos.", "Error: Valores inválidos.", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(agregarVehiculo, "Ingrese valores numéricos válidos.", "Error: Formato inválido.", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tipoVehiculo.equalsIgnoreCase("Combustible")) {
                VehiculoCombustible v = new VehiculoCombustible(agregarVehiculo.getTextoPatente().getText(),
                        marca.get(),
                        agregarVehiculo.getTextoModelo().getText(),
                        Integer.parseInt(agregarVehiculo.getTextoAnio().getText()),
                        Double.parseDouble(agregarVehiculo.getTextoCapacidad().getText()),
                        sucursal.get(),
                        Double.parseDouble(agregarVehiculo.getTextoKmPorLitro().getText()),
                        Double.parseDouble(agregarVehiculo.getTextoLitrosExtra().getText())
                );
                Persistencia.agregarVehiculo(v);
                agregarVehiculo.refrescar();

            } //---------------------------------------------------------------------------\\                            
            else if (tipoVehiculo.equalsIgnoreCase("Eléctrico")) {

                VehiculoElectrico v = new VehiculoElectrico(agregarVehiculo.getTextoPatente().getText(),
                        marca.get(),
                        agregarVehiculo.getTextoModelo().getText(),
                        Integer.parseInt(agregarVehiculo.getTextoAnio().getText()),
                        Double.parseDouble(agregarVehiculo.getTextoCapacidad().getText()),
                        sucursal.get(),
                        Double.parseDouble(agregarVehiculo.getTextoKmPorLitro().getText()));
                Persistencia.agregarVehiculo(v);
                agregarVehiculo.refrescar();
            }
            //---------------------------------------------------------------------------\\            
        } else {
            JOptionPane.showMessageDialog(agregarVehiculo, "debes completar todos los campos.", "Error: Campos en blanco.", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void bajaVehiculo(String patente) {
        Persistencia.eliminarVehiculo(patente);
    }

    public static double[] calcularConsumos(Map<String, Double> vehiculos) {
        double consumoElectricos = 0;
        double consumoCombustible = 0;
        for (Map.Entry<String, Double> entry : vehiculos.entrySet()) {
            double consumo = 0;
            Optional<Vehiculo> vehiculo = Persistencia.getVehiculo(entry.getKey());
            if (vehiculo.isPresent()) {
                consumo = vehiculo.get().calcularConsumo(entry.getValue());
                consumoElectricos += vehiculo.get().esDe(VehiculoTipo.ELECTRICO) ? consumo : 0;
                consumoCombustible += vehiculo.get().esDe(VehiculoTipo.COMBUSTIBLE) ? consumo : 0;
            }
        }
        return new double[]{consumoElectricos, consumoCombustible};
    }
}
