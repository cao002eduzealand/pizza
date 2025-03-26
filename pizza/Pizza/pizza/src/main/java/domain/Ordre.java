package domain;

import java.time.LocalDate;
import java.util.List;

public class Ordre {
    private int id;
    private Bruger bruger;
    private LocalDate dato;
    private double pris;
    private List<PizzaOrdre> pizzaer;

    public Ordre() {}

    public Ordre(int id, Bruger bruger, LocalDate dato, double pris, List<PizzaOrdre> pizzaer) {
        this.id = id;
        this.bruger = bruger;
        this.dato = dato;
        this.pris = pris;
        this.pizzaer = pizzaer;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Bruger getBruger() { return bruger; }
    public void setBruger(Bruger bruger) { this.bruger = bruger; }

    public LocalDate getDato() { return dato; }
    public void setDato(LocalDate dato) { this.dato = dato; }

    public double getPris() { return pris; }
    public void setPris(double pris) { this.pris = pris; }

    public List<PizzaOrdre> getPizzaer() { return pizzaer; }
    public void setPizzaer(List<PizzaOrdre> pizzaer) { this.pizzaer = pizzaer; }
}
