package controllers;

import models.Domino;
import models.Lands;
import models.Tile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CSVReader {

    private static ArrayList<String> csvToListString() {
        
        String path = "rendu1/Dominos.csv";
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

    private static Stack<Domino> setDominos(ArrayList<String> listStringDomino) {
        Stack<Domino> listDomino = new Stack<>();
        for(int i = 1; i < listStringDomino.size(); i++) {
            ArrayList<String> line = splitString(listStringDomino.get(i));
            int numTuile = Integer.parseInt(line.get(0));
            Tile tuile1 = new Tile(Lands.valueOf(line.get(1)),Integer.parseInt(line.get(3)));
            Tile tuile2 = new Tile(Lands.valueOf(line.get(2)),Integer.parseInt(line.get(4)));
            listDomino.add(new Domino(numTuile, tuile1, tuile2));
        }
        return listDomino;
    }

    public static Stack<Domino> getDominos() {
        return setDominos(csvToListString());
    }
}

