package org.msse640.triangle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TriangleMiddlewareApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(TriangleMiddlewareApplication.class, args);

        try {
            openHomePage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openHomePage() throws IOException {
        String url = "http://localhost:8080";
        Runtime rt = Runtime.getRuntime();
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            rt.exec(new String[] { "rundll32", "url.dll,FileProtocolHandler", url });
        } else if (os.contains("mac")) {
            rt.exec(new String[] { "open", url });
        } else if (os.contains("nix") || os.contains("nux")) {
            rt.exec(new String[] { "xdg-open", url });
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + os);
        }
    }
}