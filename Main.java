public class Main {
    public static void main(String[] args) {
        Display display = new Display(800, 600, "Software Rendering");
        //System.out.println("Hello.");

        while(true) {
            display.SwapBuffers();
        }
    }
}
