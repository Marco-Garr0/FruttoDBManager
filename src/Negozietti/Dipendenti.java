package Negozietti;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
@XmlRootElement(name = "Dipedenti")
public class Dipendenti {

    ArrayList<Dipendente> Dipendenti = new ArrayList<>();

    public ArrayList<Dipendente> getDipendenti() {
        return Dipendenti;
    }
    @XmlElement(name = "Dipendente")
    public void setDipendenti(ArrayList<Dipendente> Dipendenti) {
        this.Dipendenti = Dipendenti;
    }
}
