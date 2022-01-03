package v2.models;

import java.util.Comparator;

public class DominoComparator implements Comparator<Domino> {
    @Override
    public int compare(Domino d1, Domino d2) {
        return d1.getNumber().compareTo(d2.getNumber());
    }
}