package domain;

public class Bruger {
    private int id;
    private String navn;
    private String password;
    private String adresse;
    private String email;
    private double bonuspoint;

    public Bruger() {}

    public Bruger(int id, String navn, String password, String adresse, String email, double bonuspoint){
        this.id=id;
        this.navn=navn;
        this.adresse=adresse;
        this.email=email;
        this.bonuspoint=bonuspoint;

    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getBonuspoint() { return bonuspoint; }
    public void setBonuspoint(double bonuspoint) { this.bonuspoint = bonuspoint; }




}






