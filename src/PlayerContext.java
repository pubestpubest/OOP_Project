import Interfaces.Player;

import java.util.HashMap;
public class PlayerContext implements Player {
    private String name;
    private HashMap<String,Integer> variables;


    public PlayerContext(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public HashMap<String, Integer> getVariables() {
        return variables;
    }
    public void setVariables(HashMap<String, Integer> variables) {
        this.variables = variables;
    }
    @Override
    public void done() {

    }
    @Override
    public void relocate() {

    }
    @Override
    public void move() {

    }
    @Override
    public void invest() {

    }
    @Override
    public void collect() {

    }
    @Override
    public void shoot() {

    }

    @Override
    public String toString() {
        return "PlayerContext{" +
                "name='" + name + '\'' +
                ", variables=" + variables +
                '}';
    }
}
