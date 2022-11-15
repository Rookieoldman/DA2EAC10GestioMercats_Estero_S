/*
 * Interfície que implementarà qualsevol lloc que forma part de la gestió dels mercats
 */
package principal;

import java.util.Scanner;

/**
 *
 * @author fta
 */
public interface Lloc {

    public final static Scanner DADES = new Scanner(System.in);

    public void updateLloc() throws GestorMercatsException;

    public void showLloc();

}
