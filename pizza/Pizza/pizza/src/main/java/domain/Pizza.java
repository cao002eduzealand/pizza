package domain;

import java.util.List;

public class Pizza {
    private int id;
    private String navn;
    private String beskrivelse;
    private Sauce sauce;
    private Bund bund;
    private double pris;
    private String imageUrl;
    private List<Topping> toppings;



    public Pizza(){}
    public Pizza(int id, String navn, String beskrivelse, Sauce sauce, Bund bund, double pris, String imageUrl, List<Topping> toppings) {
        this.id = id;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.sauce = sauce;
        this.bund = bund;
        this.pris = pris;
        this.imageUrl = imageUrl;
        this.toppings = toppings;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public String getBeskrivelse() { return beskrivelse; }
    public void setBeskrivelse(String beskrivelse) { this.beskrivelse = beskrivelse; }

    public Sauce getSauce() { return sauce; }
    public void setSauce(Sauce sauce) { this.sauce = sauce; }

    public Bund getBund() { return bund; }
    public void setBund(Bund bund) { this.bund = bund; }

    public double getPris() { return pris; }
    public void setPris(double pris) { this.pris = pris; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<Topping> getToppings() { return toppings; }
    public void setToppings(List<Topping> toppings) { this.toppings = toppings; }
}

