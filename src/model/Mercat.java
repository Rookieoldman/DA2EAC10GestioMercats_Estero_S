/*
 * Classe que defineix un mercat. Un mercat es defineix pel seu codi, nom, adreça, 
 * un array de punts d’informació, un array de parades gastronòmiques i un array de parades de venda.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import principal.GestorMercatsException;
import principal.Lloc;

/**
 *
 * @author fta
 */
public class Mercat implements Lloc {

    private int codi;
    private static int codiSeguent = 0;
    private String nom;
    private String adreca;
    private List<Lloc> locals = new ArrayList();

    /*
     TODO CONSTRUCTOR
    
     Paràmetres: valors per tots els atributs de la classe menys els arrays.
    
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres.
     - Al codi li hem d'assignar el valor de l'atribut codiSeguent, per després augmentar en 1 aquest atribut.
     */
    public Mercat(String nom, String adreca) {
        codi = codiSeguent;
        codiSeguent++;
        this.nom = nom;
        this.adreca = adreca;
    }

    //Nou constructor per poder crear un mercat a partir de la informació gurdada en el document XML
    public Mercat(int codi, String nom, String adreca) {
        this.codi = codi;
        this.nom = nom;
        this.adreca = adreca;
    }

    /*
     TODO Heu d'implementar tots els mètodes accessors possibles.
     */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public static int getCodiSeguent() {
        return codiSeguent;
    }

    public static void setCodiSeguent() {
        codiSeguent++;
    }

    public List<Lloc> getLocals() {
        return locals;
    }

    public void setLocals(List<Lloc> locals) {
        this.locals = locals;
    }

    /*
     TODO
    
     Paràmetres: cap
    
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou punt d'informació. Les dades
     a demanar són les que passem per paràmetre en el constructor.
     - També heu de tenir en compte que el nom i l'adreça, poden ser una frase, per exemple,
     "Mercat Nou" o "C/ Principal, 1".
    
     Retorn: El nou taller creat.
     */
    public static Mercat addMercat() {

        String nom, adreca;

        System.out.println("\nNom del mercat:");
        nom = DADES.nextLine();
        System.out.println("\nAdreça del mercat:");
        adreca = DADES.nextLine();

        return new Mercat(nom, adreca);
    }

    /*
     TODO
    
     Paràmetres: cap
    
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades del mercat i
       modificar els atributs corresponents d'aquest mercat. Els únics atributs que
       modificarem són els que hem inicialitzat en el constructor amb els paràmetres passats.
     - També heu de tenir en compte que el nom i l'adreça, poden ser una frase, per exemple,
     "Mercat Nou" o "C/ Principal, 1".
     - Li heu de mostrar a l'usuari els valors dels atributs abans de modificar-los.
     
    Retorn: cap
     */
    @Override
    public void updateLloc() {
        System.out.println("\nNom del mercat: " + nom);
        System.out.println("\nEntra el nou nom:");
        nom = DADES.nextLine();
        System.out.println("\nAdreça del mercat: " + adreca);
        System.out.println("\nEntra la nova adreça:");
        adreca = DADES.nextLine();
    }

    @Override
    public void showLloc() {
        System.out.println("\nLes dades del mercat amb codi " + codi + " són:");
        System.out.println("\nNom: " + nom);
        System.out.println("\nAdreça: " + adreca);
    }


    /*
     PUNT D'INFORMACIÓ
     */
 /*
     TODO
    
     Paràmetres: cap
    
     Accions:
     - Afegeix un nou punt d'informació a l'array de punts d'informació del mercat actual si aquest no existeix. 
       Per afegir-lo heu de fer servir el mètode de la classe PuntInformacio escaient i per comprovar la seva 
       existència el mètode d'aquesta classe que ens ajuda en aquesta tasca.
     - Actualitza la posició de l'array de punts d'informació si s'afegeix el punt d'informació.
     - Mostra el missatge "El punt d'informació ja existeix" si no s'ha afegit el punt d'informació.
    
     Retorn: cap
     */
    public void addPuntInformacio(PuntInformacio puntInformacio) throws GestorMercatsException {

        if (puntInformacio == null) {
            puntInformacio = PuntInformacio.addPuntInformacio();
        }

        if (selectLocal(1, puntInformacio.getCodi()) == -1) {
            locals.add(puntInformacio);
        } else {
            System.out.println("\nEl punt d'informació ja existeix");
        }
    }


    /*
     PARADA DE VENDA
     */
 /*
     TODO
    
     Paràmetres: cap
    
     Accions:
     - Afegeix una nova parada de venda a l'array de parades de venda del mercat actual si aquesta no existeix. 
       Per afegir-la heu de fer servir el mètode de la classe ParadaV escaient i per comprovar la seva 
       existència el mètode d'aquesta classe que ens ajuda en aquesta tasca.
     - Actualitza la posició de l'array de parades de venda si s'afegeix la parada de venda.
     - Mostra el missatge "La parada de venda ja existeix" si no s'ha afegit la parada de venda.
    
     Retorn: cap
     */
    public void addParadaV(ParadaV paradaV) {

        if (paradaV == null) {
            paradaV = ParadaV.addParadaV();
        }

        if (selectLocal(1, paradaV.getCodi()) == -1) {
            locals.add(paradaV);
        } else {
            System.out.println("\nLa parda de venda ja existeix");
        }
    }

    /*
     PARADA GASTRONÒMICA
     */
 /*
     TODO
    
     Paràmetres: cap
    
     Accions:
     - Afegeix una nova parada gastronòmica a l'array de parades gastronòmiques del mercat actual si aquesta no existeix. 
       Per afegir-la heu de fer servir el mètode de la classe ParadaG escaient i per comprovar la seva 
       existència el mètode d'aquesta classe que ens ajuda en aquesta tasca.
     - Actualitza la posició de l'array de parades parada gastronòmiques si s'afegeix la parada parada gastronòmica.
     - Mostra el missatge "La parada parada gastronòmica ja existeix" si no s'ha afegit la parada parada gastronòmica.
    
     Retorn: cap
     */
    public void addParadaG(ParadaG paradaG) throws GestorMercatsException {

        if (paradaG == null) {
            paradaG = ParadaG.addParadaG();
        }

        if (selectLocal(1, paradaG.getCodi()) == -1) {
            locals.add(paradaG);
        } else {
            System.out.println("\nLa parda de venda ja existeix");
        }
    }

    public int selectLocal(int tipusLocal, String codi) {

        if (codi == null) {
            //Demanem quin tipus de local vol seleccionar i el seu codi
            switch (tipusLocal) {
                case 1:
                    System.out.println("Codi del punt d'informació?:");
                    break;
                case 2:
                    System.out.println("Codi de la parada de venda?:");
                    break;
                case 3:
                    System.out.println("Codi de la parada gastronòmica?:");
                    break;
            }

            codi = DADES.next();
        }

        int posLocal = -1; //Posició que ocupa el local seleccionat dins l'array de locals del mercat

        //Seleccionem la posició que ocupa el local dins l'array de locals del mercat
        for (int i = 0; i < locals.size(); i++) {
            if (locals.get(i) instanceof PuntInformacio && tipusLocal == 1) {
                if (((PuntInformacio) locals.get(i)).getCodi().equals(codi)) {
                    return i;
                }
            } else if (locals.get(i) instanceof ParadaV && tipusLocal == 2) {
                if (((ParadaV) locals.get(i)).getCodi().equals(codi)) {
                    return i;
                }
            } else if (locals.get(i) instanceof ParadaG && tipusLocal == 3) {
                if (((ParadaG) locals.get(i)).getCodi().equals(codi)) {
                    return i;
                }
            }
        }

        return posLocal;
    }
}
