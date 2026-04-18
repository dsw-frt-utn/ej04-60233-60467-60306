package app;

import data.Persistencia;
import java.util.InvalidPropertiesFormatException;
import views.AgregarVehiculoView;
import views.ListarVehiculosView;

public class Program {
    public static void main(String[] args) throws IllegalArgumentException, InvalidPropertiesFormatException {
        Persistencia.inicializar();
        ListarVehiculosView view = new ListarVehiculosView();
        view.setVisible(true);
        AgregarVehiculoView vent2 = new AgregarVehiculoView();
        vent2.setVisible(true);
    }
}
