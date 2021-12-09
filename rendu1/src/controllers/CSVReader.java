package controllers;

import models.Domino;
import models.Lands;
import models.Tile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVReader {

    private static ArrayList<String> CSVtoListString() {
        String path = "C:\\Users\\jeans\\a31-kingdomino\\rendu1\\src\\Dominos.csv";
        String line = "";

        ArrayList<String> res = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while((line = br.readLine()) != null) {
                res.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private static ArrayList<String> splitString(String line) {
        String[] sep = line.split(",");
        return new ArrayList<>(Arrays.asList(sep));
    }

    private static ArrayList<Domino> setDominos(ArrayList<String> listStringDomino) {
        ArrayList<Domino> listDomino = new ArrayList<>();
        for(int i = 1; i < listStringDomino.size(); i++) {
            ArrayList<String> line = splitString(listStringDomino.get(i));
            int numTuile = Integer.parseInt(line.get(0));
            Tile tuile1 = new Tile(Lands.valueOf(line.get(1)),Integer.parseInt(line.get(3)));
            Tile tuile2 = new Tile(Lands.valueOf(line.get(2)),Integer.parseInt(line.get(4)));
            listDomino.add(new Domino(numTuile, tuile1, tuile2));
        }
        return listDomino;
    }

    public static ArrayList<Domino> getDominos() {
        return setDominos(CSVtoListString());
    }
}