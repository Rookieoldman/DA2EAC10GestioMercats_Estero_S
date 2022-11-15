package vista;

import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;
import model.Mercat;

/**
 *
 * @author fta
 */
public class MercatTableModel extends AbstractTableModel{
    
    private final String[] columnNames = {"Codi", "Nom", "Adreça"};

    String[][] data = new String[ControladorPrincipal.getMAXMERCATS()][3];

    public MercatTableModel() {
        int i = 0;
        for (Mercat mercat : ControladorPrincipal.getMercats()) {
            if (mercat != null) {
                data[i][0] = String.valueOf(mercat.getCodi());
                data[i][1] = mercat.getNom();
                data[i][2] = mercat.getAdreca();
                i++;
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }
    
}
