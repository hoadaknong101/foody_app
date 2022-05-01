package hcmute.edu.vn.phamdinhquochoa.foodyapp.beans;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Arrays;

public class Food implements Serializable {

    private Integer id;
    private String name;
    private String type;
    private byte[] image;
    private String description;

    public Food(){

    }

    public Food(Integer id, String name, String type, byte[] image, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
        this.description = description;
    }

    public Food(Integer id, String name, String type, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", image=" + Arrays.toString(image) +
                ", description='" + description + '\'' +
                '}';
    }
}
