package v2.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Draw {

    private Queue<Domino> content = new LinkedList<>();;
    private final List<DrawObserver> observers = new ArrayList<>();
    private final int size;

    public Draw(int size) {
        this.size = size;
    }

    public Queue<Domino> getContent() {
        return this.content;
    }

    public void setContent(Queue<Domino> newContent) {
        this.content = newContent;
        notifyObservers();
    }

    public Domino pickDomino() {
        Domino result = content.poll();
        notifyObservers();
        return result;
    }

    public Domino getDomino(int index) {
        if (index >= 0 && index < content.size()) {
            LinkedList<Domino> dominos = (LinkedList<Domino>)content;
            return dominos.get(index);
        } else {
            return null;
        }
    }

    public int getSize() {
        return this.size;
    }

    public void addObserver(DrawObserver observer) {
        this.observers.add(observer);
    }

    public void notifyObservers() {
        for (DrawObserver o:this.observers) {
            o.updateDraw(this);
        }
    }

}
