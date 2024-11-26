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

}
