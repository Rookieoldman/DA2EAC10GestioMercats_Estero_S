package persistencia;

import principal.GestorMercatsException;
import model.Mercat;

/**
 *
 * @author fta
 */
public interface ProveedorPersistencia {
    public void desarMercat(String nomFitxer, Mercat mercat)throws GestorMercatsException;
    public void carregarMercat(String nomFitxer)throws GestorMercatsException; 
}
