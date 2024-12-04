package backend.academy.flame.image;

import backend.academy.flame.entities.Pixel;

public record FractalImage(Pixel[] data, int width, int height) {


    public Pixel pixel(int x, int y) {
        return data[y * width + x];
    }

    public void setPixel(int x, int y, Pixel pixel) {
        data[y * width + x] = pixel;
    }
}



