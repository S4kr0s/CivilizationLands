package de.sakros.civilizationlands.commands;

public class Team {

    public String name;
    public int value;

    public Team (String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
