// Interface Drawable
interface Drawable {
    void draw();
}

// Abstract class Shape
abstract class Shape implements Drawable {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    // Method abstrak untuk menghitung luas
    abstract double area();
}

// Class Circle turunan dari Shape
class Circle extends Shape {
    private double radius;

    public Circle(double radius, String color) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    // Overloading method area dengan parameter diameter
    public double area(double diameter) {
        double r = diameter / 2;
        return Math.PI * r * r;
    }

    @Override
    public void draw() {
        System.out.println("Menggambar lingkaran berwarna " + color);
    }
}

// Class Square turunan dari Shape
class Square extends Shape {
    private double side;

    public Square(double side, String color) {
        super(color);
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }

    // Overloading method area dengan panjang dan lebar
    public double area(double panjang, double lebar) {
        return panjang * lebar;
    }

    @Override
    public void draw() {
        System.out.println("Menggambar persegi berwarna " + color);
    }
}

// Kelas Main
public class KuisEmpat {
    public static void main(String[] args) {
        // Objek Circle
        Circle lingkaran = new Circle(7, "Merah");
        lingkaran.draw();
        System.out.println("Luas lingkaran: " + lingkaran.area());
        System.out.println("Luas lingkaran dari diameter 14: " + lingkaran.area(14));

        System.out.println();

        // Objek Square
        Square persegi = new Square(5, "Biru");
        persegi.draw();
        System.out.println("Luas persegi: " + persegi.area());
        System.out.println("Luas persegi panjang 5x6: " + persegi.area(5, 6));
    }
}
