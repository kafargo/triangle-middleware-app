package org.msse640.triangle;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

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
            // Windows
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } else if (os.contains("mac")) {
            // macOS
            rt.exec("open " + url);
        } else if (os.contains("nix") || os.contains("nux")) {
            // Unix or Linux
            rt.exec("xdg-open " + url);
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + os);
        }
    }

}