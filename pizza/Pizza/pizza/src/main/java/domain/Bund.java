package domain;

public class Bund {
    private int id;
    private String navn;
    private double pris;

    public Bund(int id, String navn, double pris) {
        this.id = id;
        this.navn = navn;
        this.pris = pris;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public double getPris() { return pris; }
    public void setPris(double pris) { this.pris = pris; }
}

