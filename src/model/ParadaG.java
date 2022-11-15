/*
* Classe que defineix una parada gastronòmica del mercat. Es defineixen pel seu 
* codi, nom, tipus de productes que ofereix i si té taules per menjar o no.
 */
package model;

import principal.GestorMercatsException;

/**
 *
 * @author fta
 */
public class ParadaG extends Parada{ 
    
    private boolean teTaula;

    /*
     TODO CONSTRUCTOR
    
     Paràmetres: valors per tots els atributs de la classe.
    
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres.
     */

    public ParadaG(String codi, String nom, String tipus, boolean teTaula) {
        super(codi, nom, tipus);
        this.teTaula = teTaula;
    }

    /*
     TODO Heu d'implementar tots els mètodes accessors possibles.  
     */
    public boolean isTeTaula() {
        return teTaula;
    }

    public void setTeTaula(boolean teTaula) {
        this.teTaula = teTaula;
    }
    
    /*
    TODO
    
     Paràmetres: cap
    
     Accions:
     - Demanar a l'usuari les dades per consola per crear una nova parada gastronòmica. Les dades a demanar 
       són les que necessita el constructor.
     - També heu de tenir en compte que el nom o el tipus de producte poden ser una frase, per exemple, 
       "Can Tomàs" o "Formatges i embotits".
     - En el cas de les taules, a l'usuari li hem de demanar que contesti "Sí" (Verdader) o "No" (Fals).
       De moment, en el cas d'aquest EAC, suposarem que sempre contesta "Sí" o "No".
     
     Retorn: La nova parada gastronòmica creada.
     */
    public static ParadaG addParadaG() throws GestorMercatsException{

        String codi, nom, tipus, teTaula;
        
        boolean taula=false;

        System.out.println("\nCodi de la parada gastronòmica:");
        codi = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nNom de la parada gastronòmica:");
        nom = DADES.nextLine();
        System.out.println("\nTipus de productes que trobem en la parada gastronòmica:");
        tipus = DADES.nextLine();

        System.out.println("\nLa parada gastronòmica té taules?:(Sí o No)");
        
        switch (DADES.next()) {
            case "Sí":
                taula=true;
                break;
            case "No":
                taula=false;
                break;
            default:
                throw new GestorMercatsException("5");
        }
        

        return new ParadaG(codi, nom, tipus, taula);
    }
    
    /*
     TODO
    
     Paràmetres: cap
    
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de la parada gastronòmica actual i
       modificar els atributs corresponents d'aquesta parada.
     - També heu de tenir en compte que el nom o el tipus de producte poden ser una frase, per exemple, 
       "Can Tomàs" o "Formatges i embotits".
     - En el cas de les taules, a l'usuari li hem de mostrar "Sí" en cas que el valor sigui verdader i 
       "No" en cas contrari. De la mateixa manera, a l'usuari li hem de demanar que contesti "Sí" (Verdader) 
       o "No" (Fals). De moment, en el cas d'aquest EAC, suposarem que sempre contesta "Sí" o "No".
     - Li heu de mostrar a l'usuari els valors dels atributs abans de modificar-los.
     
     Retorn: cap
     */
    
    @Override
    public void updateLloc() throws GestorMercatsException {
        
        super.updateLloc();
        
        System.out.println("\nLa parada gastronòmica té taules: ");
        
        if(this.teTaula){
            System.out.print("Sí");
        }else{
            System.out.print("No");
        }
        
        System.out.println("\nLa parada gastronòmica té taules?:(Sí o No)");
        
        switch (DADES.next()) {
            case "Sí":
                this.teTaula=true;
                break;
            case "No":
                this.teTaula=false;
                break;
            default:
                throw new GestorMercatsException("5");
        }
    }
    
    @Override
    public void showLloc() {
        
        super.showLloc();
        
        System.out.print("\nLa parada gastronòmica ");
        
        if(teTaula){
            System.out.print("sí ");
        }else{
            System.out.print("no ");
        }
        
        System.out.print("té taules.");
    }
}
