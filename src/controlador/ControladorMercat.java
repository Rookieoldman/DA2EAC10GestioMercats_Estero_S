package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;

import model.Mercat;
import persistencia.GestorPersistencia;
import persistencia.GestorXML;
import principal.GestorMercatsException;
import vista.MenuMercat;
import vista.MenuPrincipal;
import vista.MercatForm;
import vista.MercatLlista;

/**
 *
 * @author fta
 */
public class ControladorMercat implements ActionListener {

    private MenuMercat menuMercat;
    private MercatForm mercatForm = null;
    private MercatLlista mercatLlista = null;
    private int opcioSelec = 0;

    public ControladorMercat() {

        /*
        TODO
        
        S'inicialitza l'atribut menuMercat (això mostrarà el menú de mercats)
        Es crida a afegirListenersMenu
        
         */
        menuMercat = new MenuMercat();
        afegirListenersMenu();

    }

    //El controlador com a listener dels controls de les finestres que gestionen els mercats
    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DEL MENU
    private void afegirListenersMenu() {
        /*
        TODO
        
        A cada botó del menú de mercats, s'afegeix aquest mateix objecte (ControladorMercat) com a listener
        
         */

        JButton[] botons = menuMercat.getMenuButtons();
        for (JButton boto : botons){
            boto.addActionListener(this);
        }


    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DESAR i SORTIR DEL FORMULARI
    private void afegirListenersForm() {
        /*
        TODO
        
        A cada botó del formulari del mercat, s'afegeix aquest mateix objecte (ControladorMercat) com a listener
        
         */
        JButton sortir = mercatForm.getSortir();
        JButton desar = mercatForm.getDesar();
        if (sortir.equals(mercatForm.getSortir())){
            sortir.addActionListener(this);
        }
        if (desar.equals(mercatForm.getDesar())){
            desar.addActionListener(this);
        }
        
    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DEL BOTO SORTIR DE LA LLISTA
    private void afegirListenersLlista() {
        /*
        TODO
        
        Al botó de sortir de la llista de mercats, s'afegeix aquest mateix objecte (ControladorMercat) com a listener
         */
        JButton sortir = mercatLlista.getSortir();
        if (sortir.equals(mercatLlista.getSortir())){
            sortir.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        TODO
        
        Nota:
            Com ControladorMercat és listener del menú de mercats, del formulari i de la llista, llavors en aquest mètode
            actionPerformed heu de controlar si l'usuari ha premut algun botó de qualsevol dels esmentats frames.
            Ull! En el cas del formulari i de la llista, com provenen del menú (els llança el menú de mercat), heu de verificar
            primer que els objectes mercatForm o mercatLlista no són nulls, per tal de saber si podeu comparar-los amb
            alguns dels botons d'aquests frames.
        
        Accions per al menú:
            
            S'ha de cridar a seleccionarOpcio segons l'opció premuda. Penseu que l'opció es correspon amb la posició que el 
            botó ocupa a l'array de botons de menuMercats. També, heu d'actualitzar l'atribut opcioSelec (amb l'opció que ha 
            premut l'usuari).
        
        Accions per al formulari de Mercats:
            
            ---- DESAR ----
            Si el botó premut per l'usuari és el botó de desar del formulari de mercat, llavors:
                - Es crea un nou objecte Mercat amb les dades del formulari.
                - S'afegeix el mercat creat a l'array de mercats de la classe ControladorPrincipal i s'actualitza
                  l'atribut pMercats també de la classe ControladorPrincipal.
                - Aquest mercat passa a ser el mercatActual de la classe ControladorPrincipal i es canvia l'atribut
                  opcioSelec a 2 (seleccionar mercat)
            
            ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir del formulari de mercat, llavors:
                - Heu de tornar al menú de mercat (i amagar el formulari)
        
        Accions per a la llista de Mercats:
            
            ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir de la llista de mercat, llavors:
                - Heu de tornar al menú de mercats (i amagar la llista)
         
         */

        JButton botoPitjat = (JButton) e.getSource();
        JButton[] botons = menuMercat.getMenuButtons();

        for (int i = 0; i < botons.length; i++) {
            if (botoPitjat==botons[i]){
                seleccionarOpcio(i);
                menuMercat.getFrame().setVisible(false);
            }
        }

        if (mercatForm != null && e.getSource().equals(mercatForm.getSortir()) ) {
            mercatForm.getFrame().setVisible(false);
            menuMercat.getFrame().setVisible(true);
        }

        if (mercatForm != null && e.getSource().equals(mercatForm.getDesar())){
            String nom= mercatForm.gettNom().getText();
            String adreca = mercatForm.gettAdreca().getText();
            Mercat nouMercat = new Mercat(nom,adreca);
            Mercat[] mercats = ControladorPrincipal.getMercats();
            int posicio = ControladorPrincipal.getpMercats();
                mercats[posicio]= nouMercat;
                ControladorPrincipal.setpMercats();
                mercatForm.getFrame().setVisible(false);
                menuMercat.getFrame().setVisible(true);
        }

        if (mercatLlista != null && e.getSource().equals(mercatLlista.getSortir())){
            mercatLlista.getFrame().setVisible(false);
            menuMercat.getFrame().setVisible(true);
        }
        if (e.getSource().equals(menuMercat.getFrame().getContentPane())){
            menuMercat.getFrame().setVisible(true);
        }
    }

    private void seleccionarOpcio(Integer opcio) {

        GestorPersistencia gestor;

        switch (opcio) {

            case 0: //sortir
                ControladorPrincipal.getMenuPrincipal().getFrame().setVisible(true);
                break;

            case 1: // alta
                if (ControladorPrincipal.getpMercats() < ControladorPrincipal.getMAXMERCATS()) {
                    mercatForm = new MercatForm();
                    afegirListenersForm();
                } else {
                    menuMercat.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuMercat.getFrame(), "Màxim nombre de mercats assolit.");
                }
                break;

            case 2: //seleccionar

                if (ControladorPrincipal.getMercats()[0] != null) {
                    seleccionarMercat();
                    afegirListenersForm();
                } else {
                    menuMercat.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuMercat.getFrame(), "Abans s'ha de crear al menys un mercat");
                }
                break;

            case 3: // llistar
                if (ControladorPrincipal.getMercats()[0] != null) {
                    mercatLlista = new MercatLlista();
                    afegirListenersLlista();
                } else {
                    menuMercat.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuMercat.getFrame(), "Abans s'ha de crear al menys un mercat");
                }
                break;

            case 4: //carregar

                menuMercat.getFrame().setVisible(true);

                gestor = new GestorPersistencia();

                Mercat mercat;

                try {

                    String codi = JOptionPane.showInputDialog("Quin és el codi del mercat que vols carregar?");

                    gestor.carregarMercat(ControladorPrincipal.getMETODESPERSISTENCIA()[0], codi);

                    mercat = ((GestorXML) gestor.getGestor()).getMercat();

                    int pos = comprovarMercat(mercat.getCodi());

                    if (pos >= 0) {

                        Object[] options = {"OK", "Cancel·lar"};
                        int i = JOptionPane.showOptionDialog(null, "Premeu OK per substituir-lo.", "El mercat ja existeix",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                                null, options, options[0]);

                        if (i == 0) {
                            ControladorPrincipal.getMercats()[pos] = mercat;
                        }

                    } else {

                        ControladorPrincipal.getMercats()[ControladorPrincipal.getpMercats()] = mercat;
                        ControladorPrincipal.setpMercats();
                        JOptionPane.showMessageDialog(menuMercat.getFrame(), "Mercat afegit correctament");

                    }

                } catch (GestorMercatsException e) {
                    JOptionPane.showMessageDialog(menuMercat.getFrame(), e.getMessage());
                }

                break;

            case 5: //desar
                /*
                TODO                
                Es comprova si s'ha seleccionat el Mercat:
                  - Si no s'ha seleccionat es mostra un missatges d'error (JOptionPane.showMessageDialog)
                  - Si s'ha seleccionat el mercat, es desa el mercat cridant a desarMercat del gestor de persistència.
                
                NOTA: Si es produeix alguna excepció de tipus GestorMercatsException, s'ha de capturar
                i mostrar el missatge que retorna l'excepció (getMessage()) mitjançant JOptionPane.showMessageDialog.
                 */
        }

    }

    private void seleccionarMercat() {

        Integer[] codis = new Integer[ControladorPrincipal.getpMercats()];

        int i = 0;

        for (Mercat mercat : ControladorPrincipal.getMercats()) {

            if (mercat != null) {
                codis[i] = mercat.getCodi();
                menuMercat.getFrame().setVisible(true);


            }

            i++;

        }

        int messageType = JOptionPane.QUESTION_MESSAGE;
        int codi = JOptionPane.showOptionDialog(null, "Selecciona un mercat", "Selecció del mercat", 0, messageType, null, codis, "A");

        if (codi != JOptionPane.CLOSED_OPTION) {
            ControladorPrincipal.setMercatActual(ControladorPrincipal.getMercats()[codi]);
            menuMercat.getFrame().setVisible(true);
        }

    }

    private int comprovarMercat(int codi) {

        boolean trobat = false;

        int pos = -1;

        for (int i = 0; i < ControladorPrincipal.getMercats().length && !trobat; i++) {

            if (ControladorPrincipal.getMercats()[i] != null) {
                if (ControladorPrincipal.getMercats()[i].getCodi()==codi) {
                    pos = i;
                    trobat = true;
                }
            }
        }

        return pos;
    }

}
