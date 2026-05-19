package com.internshipproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saplings")
public class Sapling {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String name;
    private String location;
    private String species;
    private String status;
    private String lastUpdate;
    private String imagePath; // Optional: to store path to captured image

    public Sapling(String name, String location, String species, String status, String lastUpdate) {
        this.name = name;
        this.location = location;
        this.species = species;
        this.status = status;
        this.lastUpdate = lastUpdate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(String lastUpdate) { this.lastUpdate = lastUpdate; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
