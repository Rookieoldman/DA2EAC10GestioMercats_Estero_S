package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author fta
 */
public class MenuPrincipal {
    
    private JFrame frame;

    private JButton[] menuButtons = new JButton[3];

    private final int AMPLADA = 800;
    private final int ALCADA = 600;

    public MenuPrincipal(){
        
        /*
        TODO
        
        Amb els atributs d'aquesta classe, heu de fer el següent (no afegiu cap listener a cap control):
            
            - Heu de crear l'objecte JFrame amb títol "Menú Principal" i layout Grid d'una columna
            
            - Heu de crear els botons del formulari. Cada botó serà un element de l'array de botons amb les següents etiquetes:
                        "0. Sortir"
                        "1. Menú Mercat"
                        "2. Menú Punts d'informació"
        
            - Heu d'afegir-ho tot al frame
        
            - Heu de fer visible el frame amb l'amplada i alçada de les constants AMPLADA i ALCADA, i fer que la finestra es tanqui 
            quan l'usuari ho fa amb el control "X" de la finestra. Per fer tot això, heu de cridar al mètode showFinestra() d'aquesta
            classe.
        
        */
        frame = new JFrame("Menu Principal");
        frame.setLayout(new GridLayout(3,1));

        JButton boto1 = new JButton("0. Sortir");
        JButton boto2 = new JButton("1. Menú Mercat");
        JButton boto3 = new JButton("2. Menú Punts d'informació");

        menuButtons[0]= boto1;
        menuButtons[1]= boto2;
        menuButtons[2]= boto3;


        for (int i = 0; i < menuButtons.length; i++) {
            frame.add(menuButtons[i]);


        }
        showFinestra();

    }
    
    private void showFinestra(){
        //Es mostra la finestra amb propietats per defecte
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton[] getMenuButtons() {
        return menuButtons;
    }

    public void setMenuButtons(JButton[] menuButtons) {
        this.menuButtons = menuButtons;
    }

}
