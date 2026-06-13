package com.example.backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class AtendimentoLogger {

    @Value("${app.log.path}")
    private String basePath;

    private final DateTimeFormatter fileFormatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final DateTimeFormatter logFormatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private File getLogFile() {

        String fileName = "log-" + LocalDateTime.now().format(fileFormatter) + ".log";

        File dir = new File(basePath);

        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                throw new RuntimeException("Não foi possível criar diretório de logs: " + dir.getAbsolutePath());
            }
        }

        return new File(dir, fileName);
    }

    public void log(String message) {

        File file = getLogFile();

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(
                    "[" + LocalDateTime.now().format(logFormatter) + "] " +
                            message + System.lineSeparator()
            );
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever log", e);
        }
    }
}