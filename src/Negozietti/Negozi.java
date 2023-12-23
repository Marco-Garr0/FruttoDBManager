package Negozietti;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
@XmlRootElement(name = "Negozi")
public class Negozi {

    private ArrayList<Negozio> negozi = new ArrayList<>();

    public ArrayList<Negozio> getNegozi() {
        return negozi;
    }
    @XmlElement(name = "Negozio")
    public void setNegozi(ArrayList<Negozio> negozi) {
        this.negozi = negozi;
    }
}
