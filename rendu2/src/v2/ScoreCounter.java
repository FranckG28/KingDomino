package v2;

import v2.models.*;

import java.util.ArrayList;
import java.util.List;

public class ScoreCounter {

    private final Player player;
    private List<List<Tile>> domains = new ArrayList<>();

    public ScoreCounter(Player player) {
        this.player = player;
    }

    public void calculate() {

        // On commence par remplir la liste des domaines :
        for(int x = 0; x<Kingdom.gridSize; x++) {

            for (int y = 0; y < Kingdom.gridSize; y++) {

                // On vérifie chaque case :
                checkTile(x, y, null);

            }

        }

        // On compte les points :
        int score = 0;
        for (List<Tile> l: domains) {
            // On compte le nombre de couronne :
            score += l.size() * getCrownCount(l);
        }

        player.score = score;

    }

    private void checkTile(int x, int y, List<Tile> domain) {

        // Vérification des coordonnées :
        if (x >= 0 && y >= 0 && x<Kingdom.gridSize && y<Kingdom.gridSize) {

            Tile tile = player.getKingdom().getKingdom()[y][x];

            if (tile != null                        // Si la case n'est pas vide
                    && tile.getLand() != Lands.CASTLE   // Si la case n'est pas le chateau
                    && !tileAlreadyInDomain(tile)       // Et si la case n'est pas déjà dans un domaine
            ) {

                if (domain == null) {
                    // Si le domaine n'existe pas encore, on le crée
                    domain = new ArrayList<>();
                    domains.add(domain);
                }

                if (domain.isEmpty() || domain.get(0).getLand() == tile.getLand()) {
                    // Si le domaine est nouveau ou que le terrain est le même, on l'ajoute au domaine
                    domain.add(tile);

                    // Et on vérifie ses voisins

                    // haut
                    checkTile(x, y+1, domain);

                    // bas
                    checkTile(x, y-1, domain);

                    // gauche
                    checkTile(x-1, y, domain);

                    // droite
                    checkTile(x+1, y, domain);

                }

            }

        }

    }

    private boolean tileAlreadyInDomain(Tile tile) {
        for (List<Tile> l:domains) {
            for (Tile t:l) {
                // Si la tuile est dans le domaine, retourner vrai
                if (t.equals(tile)) return true;
            }
        }

        // Si aucun résultat, elle n'est pas déjà dans un domaine
        return false;
    }

    private int getCrownCount(List<Tile> domain) {

        int sum = 0;
        for (Tile t:domain) {
            sum += t.getCrowns();
        }
        return sum;

    }

}
