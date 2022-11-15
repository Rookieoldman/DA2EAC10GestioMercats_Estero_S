/*
 *Classe que defineix un punt d’informació del mercat. Es defineixen pel seu codi, 
 *nom de la persona responsable, any actual i quantitat de persones que han demanat
 *informació des de l'inici de l'any actual.
 */
package model;

import principal.GestorMercatsException;
import principal.Lloc;

/**
 *
 * @author fta
 */
public class PuntInformacio implements Lloc {

    private String codi;
    private String nomResponsable;
    private int anyActual;
    private int quantitatPersones;

    /*
     TODO CONSTRUCTOR
    
     Paràmetres: valors per tots els atributs de la classe, menys els arrays
    
     Accions:
     - Assignar als atributs els valors passats com a paràmetres.    
     */
    public PuntInformacio(String codi, String nomResponsable, int anyActual, int quantitatPersones) {
        this.codi = codi;
        this.nomResponsable = nomResponsable;
        this.anyActual = anyActual;
        this.quantitatPersones = quantitatPersones;
    }

    /*
     TODO Heu d'implementar tots els mètodes accessors possibles.  
     */
    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNomResponsable() {
        return nomResponsable;
    }

    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
    }

    public int getQuantitatPersones() {
        return quantitatPersones;
    }

    public void setQuantitatPersones(int quantitatPersones) {
        this.quantitatPersones = quantitatPersones;
    }

    public int getAnyActual() {
        return anyActual;
    }

    public void setAnyActual(int anyActual) {
        this.anyActual = anyActual;
    }


    /*
    TODO
    
     Paràmetres: cap
    
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou punt d'informació. Les dades a demanar 
       són les que necessita el constructor.
     - També heu de tenir en compte que el nom del responsable seran el nom i cognoms, per tant,
       serà una frase com per exemple, "Biel Bestard".
     
     Retorn: El nou punt d'informació creat.
     */
    public static PuntInformacio addPuntInformacio() throws GestorMercatsException {
        String codi, nom;
        int any, quantitat;

        System.out.println("\nCodi del punt d'informació:");
        codi = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nNom de la persona responsable del punt d'informació:");
        nom = DADES.nextLine();
        System.out.println("\nAny actual:");
        any = DADES.nextInt();

        if (any < 0) {
            throw new GestorMercatsException("6");
        }

        System.out.println("\nQuantitat de persones que han demanat informació:");
        quantitat = DADES.nextInt();

        if (quantitat < 0) {
            throw new GestorMercatsException("6");
        }

        return new PuntInformacio(codi, nom, any, quantitat);
    }

    /*
     TODO
    
     Paràmetres: cap
    
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades del punt d'informació i
       modificar els atributs corresponents a aquest punt d'informació.
     - També heu de tenir en compte que el nom del responsable seran el nom i cognoms, per tant,
       serà una frase com per exemple, "Biel Bestard".
     - Li heu de mostrar a l'usuari els valors dels atributs abans de modificar-los.
     
    Retorn: cap
     */
    @Override
    public void updateLloc() throws GestorMercatsException {

        int valor;

        System.out.println("\nCodi del punt d'informació: " + codi);
        System.out.println("\nEntra el nou codi:");
        codi = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nNom de la persona responsable del punt d'informació: " + nomResponsable);
        System.out.println("\nEntra el nou nom:");
        nomResponsable = DADES.nextLine();
        System.out.println("\nAny actual: " + anyActual);
        System.out.println("\nEntra el nou any:");
        valor = DADES.nextInt();

        if (valor < 0) {
            throw new GestorMercatsException("6");
        }

        anyActual = valor;

        System.out.println("\nQuantitat de persones que han demanat informació: " + quantitatPersones);
        System.out.println("\nEntra la nova quantitat de persones:");
        valor = DADES.nextInt();

        if (valor < 0) {
            throw new GestorMercatsException("6");
        }
       
        quantitatPersones = valor;

    }

    @Override
    public void showLloc() {
        System.out.println("\nLes dades del punt d'informació " + codi + " són:");
        System.out.println("\nNom: " + nomResponsable);
        System.out.println("\nAnya actual: " + anyActual);
        System.out.println("\nQuantitat de persones que han demanat informació: " + quantitatPersones);
    }
}
