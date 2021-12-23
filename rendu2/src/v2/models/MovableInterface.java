package v2.models;

public interface MovableInterface {

    public void moveUp();
    public void moveDown();
    public void moveRight();
    public void moveLeft();

    public boolean canGoUp();
    public boolean canGoDown();
    public boolean canGoRight();
    public boolean canGoLeft();

}
