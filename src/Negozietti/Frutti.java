package Negozietti;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
@XmlRootElement(name = "Frutti")
public class Frutti {

    ArrayList<Frutto> frutti = new ArrayList<>();
    @XmlElement(name = "Frutto")
    public void setFrutti(ArrayList<Frutto> frutti) {

        this.frutti = frutti;
    }

    public ArrayList<Frutto> getFrutti(){

        return frutti;
    }
}