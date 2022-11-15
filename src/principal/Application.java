package principal;

import model.Mercat;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.ParadaG;
import model.ParadaV;
import model.PuntInformacio;
import persistencia.GestorPersistencia;

/**
 *
 * @author fta
 */
public class Application {

    private final static Scanner DADES = new Scanner(System.in);

    private static Mercat[] mercats = new Mercat[15];
    private static int pMercats = 0; //Priemra posició buida del vector mercats
    private static Mercat mercatActual = null;
    static private String FITXER = "mercat";
    static private GestorPersistencia gp = new GestorPersistencia();

    public static void main(String[] args) {
        try {
            menuPrincipal();
        } catch (GestorMercatsException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    private static void menuPrincipal() throws GestorMercatsException {
        int opcio = 0;

        do {

            try {

                System.out.println("\nSelecciona una opció");
                System.out.println("\n0. Sortir");
                System.out.println("\n1. Gestió de mercats");
                System.out.println("\n2. Gestió de punts d'informació");
                System.out.println("\n3. Gestió de parades gastronòmiques");
                System.out.println("\n4. Gestió de parades de venda");
                opcio = DADES.nextInt();

                switch (opcio) {
                    case 0:
                        break;
                    case 1:
                        menuMercats();
                        break;
                    case 2:
                        if (mercatActual != null) {
                            menuLocals(1);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar el mercat al menú Gestió de mercats");
                        }
                        break;
                    case 3:
                        if (mercatActual != null) {
                            menuLocals(3);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar el mercat al menú Gestió de mercats");
                        }
                        break;
                    case 4:
                        if (mercatActual != null) {
                            menuLocals(2);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar el mercat al menú Gestió de mercats");
                        }
                        break;
                    default:
                        System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                        break;
                }
            } catch (InputMismatchException e) {

                throw new GestorMercatsException("1");

            } catch (ArrayIndexOutOfBoundsException e) {

                throw new GestorMercatsException("3");

            }
        } while (opcio != 0);
    }

    public static void menuMercats() throws GestorMercatsException, InputMismatchException, ArrayIndexOutOfBoundsException {
        int opcio;

        do {
            int indexSel;
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Seleccionar");
            System.out.println("\n3. Modificar");
            System.out.println("\n4. Llista de mercats");
            System.out.println("\n5. Carregar mercat");
            System.out.println("\n6. Desar mercat");
            opcio = DADES.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    mercats[pMercats] = Mercat.addMercat();
                    pMercats++;
                    break;
                case 2:
                    indexSel = selectMercat();
                    if (indexSel >= 0) {
                        mercatActual = mercats[indexSel];
                    } else {
                        System.out.println("\nNo existeix aquest mercat");
                    }
                    break;
                case 3:
                    indexSel = selectMercat();
                    if (indexSel >= 0) {
                        mercats[indexSel].updateLloc();
                    } else {
                        System.out.println("\nNo existeix aquest mercat");
                    }
                    break;
                case 4:
                    for (int i = 0; i < pMercats; i++) {
                        mercats[i].showLloc();
                    }
                    break;

                case 5: //Carregar mercat
                    pMercats = 0;
                    mercats = new Mercat[1]; //Per facilitar la feina, només podem carregar un mercat
                    gp.carregarMercat("XML", FITXER);
                    mercats[pMercats] = (gp.getGestor()).getMercat();
                    break;

                case 6: //Desar mercat
                    int pos = selectMercat();
                    if (pos >= 0) {
                        gp.desarMercat("XML", FITXER, mercats[pos]);
                    } else {
                        System.out.println("\nNo existeix aquest mercat");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }


    /*
     TODO Heu de desenvolupar el menuParadesVenda amb les opcions que podeu veure.
     Nota: penseu que quan arribem aquí, l'atribut mercatActual no és null
       
     Opció 0. Sortir -->       Surt del menú i retorna al menú principal
     Opció 1. Alta -->         Crea una parada de venda del mercat actual. Penseu que Mercat sap crear parades de venda        
     Opció 2. Modificar -->    Permet modificar una parada de venda del mercat actual
     (per comprovar l'existència de la parada de venda tenim un mètode en la classe Mercat que ens ajuda)
     Opció 3. Llista de parades de venda--> Imprimeix les dades de les parades de venda del mercat actual
        
     A més, heu de fer una estructura iterativa per tornar a mostrar el menú sempre que no es premi l'opció 0 de sortida
     Recomanacions:
     - estructura de control switch-case per bifurcar les opcions
     - si no s'ha introduït cap opció de les de la llista, s'ha de mostrar el missatge
     "S'ha de seleccionar una opció correcta del menú."
     - definiu una variable opcio de tipus enter
     */
    public static void menuLocals(int tipus) throws GestorMercatsException, InputMismatchException, ArrayIndexOutOfBoundsException {
        int opcio;

        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Llista");
            opcio = DADES.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipus) {
                        case 1:
                            mercatActual.addPuntInformacio(null);
                            break;
                        case 2:
                            mercatActual.addParadaV(null);
                            break;
                        case 3:
                            mercatActual.addParadaG(null);
                            break;
                    }
                    break;
                case 2:
                    int indexSel = mercatActual.selectLocal(tipus, null);
                    if (indexSel >= 0) {
                        mercatActual.getLocals().get(indexSel).updateLloc();
                    } else {
                        System.out.println("\nNo existeix aquest local");
                    }
                    break;
                case 3:
                    for (int i = 0; i < mercatActual.getLocals().size(); i++) {
                        if (mercatActual.getLocals().get(i) instanceof PuntInformacio && tipus == 1) {
                            mercatActual.getLocals().get(i).showLloc();
                        } else if (mercatActual.getLocals().get(i) instanceof ParadaV && tipus == 2) {
                            mercatActual.getLocals().get(i).showLloc();
                        } else if (mercatActual.getLocals().get(i) instanceof ParadaG && tipus == 3) {
                            mercatActual.getLocals().get(i).showLloc();
                        }
                    }

                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static Integer selectMercat() {

        System.out.println("\nCodi del mercat?:");
        int codi = DADES.nextInt();

        for (int i = 0; i < pMercats; i++) {
            if (mercats[i].getCodi() == codi) {
                return i;
            }
        }
        return -1;
    }
}
