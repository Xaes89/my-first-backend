public class EnvDemo {
    public static void main(String[] args) {
        // Leer variables de entorno
        String javaHome = System.getenv("JAVA_HOME");
        String path = System.getenv("PATH");
        String username = System.getenv("USERNAME");
        String os = System.getenv("OS");

        System.out.println("=== VARIABLES DE ENTORNO ===");
        System.out.println("JAVA_HOME: " + javaHome);
        System.out.println("USERNAME: " + username);
        System.out.println("OS: " + os);
        System.out.println("");

        System.out.println("=== ANÁLISIS DEL PATH ===");
        if (path != null) {
            String[] pathEntries = path.split(";");
            int javaCount = 0;
            for (String entry : pathEntries) {
                if (entry.toLowerCase().contains("java")) {
                    System.out.println("Entrada Java #" + (++javaCount) + ": " + entry);
                }
            }
        }

        System.out.println("");
        System.out.println("=== INFORMACIÓN DE JAVA ===");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Java Home: " + System.getProperty("java.home"));
        System.out.println("Java Vendor: " + System.getProperty("java.vendor"));
    }
}