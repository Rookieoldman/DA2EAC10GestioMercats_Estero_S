package principal;

/**
 *
 * @author fta
 */
public class GestorMercatsException extends Exception {

    private String codiCausa = "desconegut";
    private String missatge;

    public GestorMercatsException(String codiCausa) {
        this.codiCausa = codiCausa;
        switch (codiCausa) {
            case "1":
                missatge = "L'opció introduïda no és numèrica";
                break;
            case "2":
                missatge = "El valor introduït no és correcte";
                break;
            case "3":
                missatge = "Ja no hi caben més locals";
                break;
            case "4":
                missatge = "Aquest mercat ja existeix";
                break;
            case "5":
                missatge = "Has d'introduir Sí o NO";
                break;
            case "6":
                missatge = "El valor ha de ser positiu";
                break;
            case "GestorXML.model":
                missatge = "No s'ha pogut crear el model XML per desar el mercat";
                break;
            case "GestorXML.desar":
                missatge = "No s'ha pogut desar el mercat a causa d'error d'entrada/sortida";
                break;
            case "GestorXML.carrega":
                missatge = "No s'ha pogut carregar el mercat a causa d'error d'entrada/sortida";
                break;
            default:
                missatge = "Error desconegut";
                break;
        }
    }

    @Override
    public String getMessage() {
        return "Amb el codi de causa:  " + codiCausa + ", s'ha generat el següent missatge: " + missatge;
    }
}
