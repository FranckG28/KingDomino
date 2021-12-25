package v2.views;

import v2.models.Colors;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class KingDominoDesign {

    // COULEURS
    public static final Color BLUE = new Color(59,130,246);
    public static final Color GREEN = new Color(34,197,94);
    public static final Color YELLOW = new Color(227,178,60);
    public static final Color PINK = new Color(255,169,231);
    public static final Color RED = new Color(255,60,56);
    public static final Color PURPLE = new Color(102,44,145);
    public static final Color BLACK = new Color(15,17,8);

    // POLICES D'ECRITURES
    public final Font titleFont;
    public final Font textFont;

    // TAILLES D'ECRITURES
    public static final float textXl = 60F;
    public static final float textLg = 40F;
    public static final float textMd = 25F;
    public static final float textBase = 16F;
    public static final float textSm = 10F;

    // INSTANCE DE LA CLASSE
    private static KingDominoDesign instance;

    // CONSTRUCTEUR DE LA CLASSE : CHARGE LES RESSOURCES NECESSAIRES
    private KingDominoDesign() throws IOException, FontFormatException {
        
        // CHARGEMENT DES POLICES D'ECRITURES
        try {

            // Récupération des fichiers
            File titleFontFile = new File("rendu2/src/v2/Bagnard.ttf");
            File textFontFile = new File("rendu2/src/v2/Baloo2.ttf");

            // Création des fonts
            titleFont = Font.createFont(Font.TRUETYPE_FONT, titleFontFile).deriveFont(textBase);
            textFont = Font.createFont(Font.TRUETYPE_FONT, textFontFile).deriveFont(textBase);
            
            // Enregistrement des fonts
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(titleFont);
            ge.registerFont(textFont);

        } catch (IOException |FontFormatException e) {
            //Handle exception
            throw e;
        }

    }

    // FONCTION D'OBTENTION DE L'INSTANCE DU SIGLETON
    public static KingDominoDesign getInstance() {
        if (instance == null) {
            try {
                instance = new KingDominoDesign();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    // OBTENIR LA COULEUR CORRESPONDANTE A UNE VALEUR DE L'ENUM COLORS
    public static Color getColor(Colors color) {
        return switch (color) {
            case BLUE -> BLUE;
            case GREEN -> GREEN;
            case YELLOW -> YELLOW;
            case PINK -> PINK;
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };
    }

}
