package v2.controllers;

import v2.models.*;
import v2.views.CastlePlacement;
import v2.views.DominoPlacement;
import v2.views.PositionArrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CastleController implements MovableInterface {

    private final GameController parentController;
    private final Kingdom kingdom;
    private CastlePlacement view;
    private final Tile castle;

    private int x;
    private int y;

    public CastleController(Kingdom kingdom, GameController parentController) {
        this.kingdom = kingdom;
        this.parentController = parentController;

        // Choix d'une position random pour le chateau
        Random rnd = new Random();
        x = rnd.nextInt(Kingdom.gridSize);
        y = rnd.nextInt(Kingdom.gridSize);

        // Création du chateau
        this.castle = new Tile(Lands.CASTLE, 0);

        // Création de la preview initiale
        updatePreview();

    }

    public void moveRight() {
        if (canGoRight()) {
            this.x++;
            updatePreview();
        }
    }

    public void moveLeft() {
        if (canGoLeft()) {
            this.x--;
            updatePreview();
        }
    }

    public void moveUp() {
        if (canGoUp()) {
            this.y--;
            updatePreview();
        }
    }

    public void moveDown() {
        if(canGoDown()) {
            this.y++;
            updatePreview();
        }
    }

    @Override
    public boolean canGoUp() {
        return y>0;
    }

    @Override
    public boolean canGoDown() {
        return y<Kingdom.gridSize-1;
    }

    @Override
    public boolean canGoRight() {
        return x<Kingdom.gridSize-1;
    }

    @Override
    public boolean canGoLeft() {
        return x>0;
    }

    public void updatePreview() {
        List<Preview> list = Arrays.asList(new Preview(this.castle, this.x, this.y));
        kingdom.setPreviews(list);
        if (view != null) {
            PositionArrows a = view.getArrows();
            a.downButton.setEnabled(canGoDown());
            a.upButton.setEnabled(canGoUp());
            a.leftButton.setEnabled(canGoLeft());
            a.rightButton.setEnabled(canGoRight());
        }
    }

    public void place() {
        kingdom.applyPreview();
        parentController.play();
    }

    public void setView(CastlePlacement view) {
        this.view = view;
    }

}
