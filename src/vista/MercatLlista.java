package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author fta
 */
public final class MercatLlista {
    
    private JFrame frame;
    
    private final int AMPLADA = 600;
    private final int ALCADA = 200;
    
    private JTable tMercats;

    private JButton sortir;   
    

    public MercatLlista() {
        frame = new JFrame("Llista de Mercats");
        frame.setLayout(new GridLayout(0, 1));

        tMercats = new JTable(new MercatTableModel());
        
        sortir = new JButton("Sortir");

        frame.add(new JScrollPane(tMercats));  
        frame.add(sortir);

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

    public JTable gettMercats() {
        return tMercats;
    }

    public void settMercats(JTable tMercats) {
        this.tMercats = tMercats;
    }

    public JButton getSortir() {
        return sortir;
    }

    public void setSortir(JButton sortir) {
        this.sortir = sortir;
    }
}