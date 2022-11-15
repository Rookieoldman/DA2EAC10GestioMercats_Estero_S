package model;

import principal.GestorMercatsException;
import principal.Lloc;

/**
 *
 * @author fta
 */
public abstract class Parada implements Lloc{
    
    private String codi;
    private String nom;
    private String tipus;

    //Mètode constructor
    public Parada(String codi, String nom, String tipus) {
        this.codi = codi;
        this.nom = nom;
        this.tipus = tipus;
    }

    //Mètodes accessors
    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
    
    @Override
     public void updateLloc()throws GestorMercatsException {
        System.out.println("\nCodi de la parada: " + codi);
        System.out.println("\nEntra el nou codi:");
        codi = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nNom de la parada: " + nom);
        System.out.println("\nEntra el nou nom:");
        nom = DADES.nextLine();
        System.out.println("\nTipus de productes que trobem en la parada: " + tipus);
        System.out.println("\nEntra el nou tipus de productes:");
        tipus = DADES.nextLine();
    }
    
    
    @Override
    public void showLloc() {
        System.out.println("\nLes dades de la parada amb codi " + codi + " són:");
        System.out.println("\nNom: " + nom);
        System.out.println("\nTipus de productes: " + tipus);
    }
}
