package v2.views;

import v2.models.Colors;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class KingDominoDesign {

    // COULEURS
    public static final Color BLUE = new Color(59,130,246);
    public static final Color GREEN = new Color(34,197,94);
    public static final Color YELLOW = new Color(227,178,60);
    public static final Color PINK = new Color(255,169,231);
    public static final Color RED = new Color(255,60,56);
    public static final Color PURPLE = new Color(102,44,145);
    public static final Color BLACK = new Color(15,17,8);
    public static final Color GRAY = new Color(63,65,67);

    // POLICES D'ÉCRITURES
    public final Font titleFont;
    public final Font textFont;

    // TAILLES D'ÉCRITURES
    public static final float textXl = 60F;
    public static final float textLg = 40F;
    public static final float textMd = 25F;
    public static final float textBase = 16F;
    public static final float textSm = 10F;

    // INSTANCE DE LA CLASSE
    private static KingDominoDesign instance;

    // CONSTRUCTEUR DE LA CLASSE : CHARGE LES RESSOURCES NECESSARIES
    private KingDominoDesign() throws IOException, FontFormatException, URISyntaxException {
        
        // CHARGEMENT DES POLICES D'ÉCRITURES

        // Récupération des fichiers
        InputStream titleFontFile = getClass().getResourceAsStream("/Bagnard.ttf");
        InputStream textFontFile = getClass().getResourceAsStream("/Baloo2.ttf");

        // Création des fonts
        titleFont = Font.createFont(Font.TRUETYPE_FONT, titleFontFile).deriveFont(textBase);
        textFont = Font.createFont(Font.TRUETYPE_FONT, textFontFile).deriveFont(textBase);

        // Enregistrement des fonts
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(titleFont);
        ge.registerFont(textFont);

    }

    // FONCTION D'OBTENTION DE L'INSTANCE DU SIGLETON
    public static KingDominoDesign getInstance() {
        if (instance == null) {
            try {
                instance = new KingDominoDesign();
            } catch (IOException | FontFormatException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    // OBTENIR LA COULEUR CORRESPONDANTE A UNE VALEUR DE L'ENUM COLORS
    public static Color getColor(Colors color) {
        Color result = null;
        switch (color) {
            case BLUE:
                result = BLUE;
                break;
            case GREEN:
                result = GREEN;
                break;
            case YELLOW:
                result = YELLOW;
                break;
            case PINK:
                result = PINK;
                break;
        }
        return result;
    }

}
