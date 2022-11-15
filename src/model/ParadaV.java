/*
* Classe que defineix una parada de venda del mercat. Es defineixen pel seu codi, 
* nom i tipus de productes que ofereix.
 */
package model;


/**
 *
 * @author fta
 */
public class ParadaV extends Parada{

    /*
     TODO CONSTRUCTOR
    
     Paràmetres: valors per tots els atributs de la classe.
    
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres.
     */

    public ParadaV(String codi, String nom, String tipus) {
        super(codi, nom, tipus);
    }

    
    /*
    TODO
    
     Paràmetres: cap
    
     Accions:
     - Demanar a l'usuari les dades per consola per crear una nova parada de venda. Les dades a demanar 
       són les que necessita el constructor.
     - També heu de tenir en compte que el nom o el tipus de producte poden ser una frase, per exemple, 
       "Fresca" o "pasta fresca".
     
     Retorn: La nova parada de venda creada.
     */
    public static ParadaV addParadaV() {
        String codi, nom, tipus;
        
        boolean taula=false;

        System.out.println("\nCodi de la parada de venda:");
        codi = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nNom de la parada de venda:");
        nom = DADES.nextLine();
        System.out.println("\nTipus de productes que trobem en la parada de venda:");
        tipus = DADES.nextLine();        

        return new ParadaV(codi, nom, tipus);
    }
    
}
