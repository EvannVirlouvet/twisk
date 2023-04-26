package twisk.outils;

public class TailleComposants {
    private int wVBox;
    private int hVBox;
    private int wHBox;
    private int hHBox;
    private int wVBoxGuichet;
    private int hVBoxGuichet;
    private int wHBoxGuichet;
    private int hHBoxGuichet;
    private static TailleComposants instance = new TailleComposants();
    public static TailleComposants getInstance(){
        return instance;
    }

    private TailleComposants(){
        this.wVBox = 200;
        this.hVBox = 100;
        this.wHBox = 65 ;
        this.hHBox = 130;
        this.wVBoxGuichet = 250;
        this.hVBoxGuichet = 125;
        this.wHBoxGuichet = 40;
        this.hHBoxGuichet = 160;
    }

    public int getwVBoxGuichet() {
        return wVBoxGuichet;
    }

    public int gethVBoxGuichet() {
        return hVBoxGuichet;
    }

    public int getwHBoxGuichet() {
        return wHBoxGuichet;
    }

    public int gethHBoxGuichet() {
        return hHBoxGuichet;
    }

    public int getwVBox() {
        return wVBox;
    }

    public int gethVBox() {
        return hVBox;
    }

    public int getwHBox() {
        return wHBox;
    }

    public int gethHBox() {
        return hHBox;
    }
}
