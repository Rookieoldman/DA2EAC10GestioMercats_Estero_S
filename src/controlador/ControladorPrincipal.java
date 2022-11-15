package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Mercat;
import persistencia.GestorPersistencia;
import vista.MenuPrincipal;

/**
 *
 * @author fta
 */
public class ControladorPrincipal implements ActionListener {

    static private MenuPrincipal menuPrincipal;
    static private final int MAXMERCATS = 4;
    static private Mercat[] mercats = new Mercat[MAXMERCATS];
    static private int pMercats = 0; //Priemra posició buida del vector mercats
    static private Mercat mercatActual = null;
    static private GestorPersistencia gp = new GestorPersistencia();
    static private final String[] METODESPERSISTENCIA = {"XML"};

    public ControladorPrincipal() {
        /*
        TODO
        
        S'inicialitza l'atribut menuPrincipal (això mostrarà el menú principal)
        A cada botó del menú, s'afegeix aquest mateix objecte (ControladorPrincipal) com a listener
        
         */

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        TODO

        S'ha de cridar a seleccionarOpcio segons l'opció premuda. Penseu que l'opció es 
        correspon amb la posició que el botó ocupa a l'array de botons de menuPrincipal.
        
         */

    }

    private void seleccionarOpcio(int opcio) {

        switch (opcio) {
            case 0:
                System.exit(0);
                break;
            case 1:
                menuPrincipal.getFrame().setVisible(false);
                ControladorMercat controladorMercat = new ControladorMercat();
                break;
            case 2:
                JOptionPane.showMessageDialog(menuPrincipal.getFrame(), "No heu d'implementar aquest menú");
                break;
        }

    }
    

    public static MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }

    public static void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        ControladorPrincipal.menuPrincipal = menuPrincipal;
    }

    public static GestorPersistencia getGp() {
        return gp;
    }

    public static void setGp(GestorPersistencia gp) {
        ControladorPrincipal.gp = gp;
    }

    public static Mercat[] getMercats() {
        return mercats;
    }

    public static void setMercats(Mercat[] mercats) {
        ControladorPrincipal.mercats = mercats;
    }

    public static int getpMercats() {
        return pMercats;
    }

    public static void setpMercats() {
        ControladorPrincipal.pMercats++;
    }

    public static Mercat getMercatActual() {
        return mercatActual;
    }

    public static void setMercatActual(Mercat mercatActual) {
        ControladorPrincipal.mercatActual = mercatActual;
    }

    public static int getMAXMERCATS() {
        return MAXMERCATS;
    }

    public static String[] getMETODESPERSISTENCIA() {
        return METODESPERSISTENCIA;
    }
      
}
