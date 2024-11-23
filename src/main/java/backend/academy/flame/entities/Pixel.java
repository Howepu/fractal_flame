package backend.academy.flame.entities;

public record Pixel(int r, int g, int b, int hitCount) {
    private static final int D = 255;

    public Pixel addColor(int r, int g, int b) {
        return new Pixel(
            Math.min(D, Math.max(0, this.r + r)),
            Math.min(D, Math.max(0, this.g + g)),
            Math.min(D, Math.max(0, this.b + b)),
            this.hitCount + 1
        );
    }

    public Pixel enhance(double factor) {
        return new Pixel(
            Math.min(D, (int) (r * factor)),
            Math.min(D, (int) (g * factor)),
            Math.min(D, (int) (b * factor)),
            hitCount
        );
    }
}
