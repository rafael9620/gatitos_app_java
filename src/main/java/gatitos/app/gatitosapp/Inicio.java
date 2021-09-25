package gatitos.app.gatitosapp;

import javax.swing.JOptionPane;

/**
 *
 * @author FranciscoR
 */
public class Inicio {

    public static void main(String[] args) {
        int opcion_menu = -1;
        String[] botones = {"1. Ver Gatos", "2. Salir"};

        do {
            String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos Java", "Menu Principal", JOptionPane.INFORMATION_MESSAGE,
                    null, botones, botones[0]);

            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    opcion_menu = i;
                }
            }
            
            switch(opcion_menu){
                case 0:
                    break;
                default:
                    break;
            }
        } while (opcion_menu != 1);
    }
}
