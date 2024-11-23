package backend.academy.flame.image;

import backend.academy.flame.entities.Pixel;

public record FractalImage(Pixel[] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[] pixels = new Pixel[width * height];
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = new Pixel(0, 0, 0, 0);  // Инициализация пикселей черным цветом
        }
        return new FractalImage(pixels, width, height);
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Pixel pixel(int x, int y) {
        return data[y * width + x];
    }

    public void setPixel(int x, int y, Pixel pixel) {
        data[y * width + x] = pixel;
    }
}



