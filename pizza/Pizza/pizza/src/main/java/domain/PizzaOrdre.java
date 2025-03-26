package domain;

public class PizzaOrdre {
    private Pizza pizza;
    private int antal;

    public PizzaOrdre(Pizza pizza, int antal) {
        this.pizza = pizza;
        this.antal = antal;
    }

    public Pizza getPizza() { return pizza; }
    public void setPizza(Pizza pizza) { this.pizza = pizza; }

    public int getAntal() { return antal; }
    public void setAntal(int antal) { this.antal = antal; }
}
