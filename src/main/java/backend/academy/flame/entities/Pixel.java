package backend.academy.flame.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pixel {
    private int red;
    private int green;
    private int blue;
    private boolean visited;
    private int counter;
    private double normal;

    public Pixel() {
        visited = false;
        counter = 0;
        normal = 0.0;
    }

    public void setColor(int red, int green, int blue) {
        this.blue = blue;
        this.green = green;
        this.red = red;
        visited = true;
    }

    public void setAvg(int red, int green, int blue) {
        setColor((this.red + red) / 2, (this.green + green) / 2, (this.blue + blue) / 2);
    }
}
