package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import shapes.*;

public class FileHandler {

    public static Shape[] parse(String fileName) {
        // Clean file name
        fileName = fileName.replace("\"", "").trim();

        // Try direct path first
        File file = new File(fileName);

        // If not found, try relative to JAR location
        if (!file.exists()) {
            try {
                String jarDir = new File(FileHandler.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .toURI())
                        .getParent();
                file = new File(jarDir, fileName);
            } catch (Exception e) {
                // Ignore if fails
            }
        }

        System.out.println("Trying to open file: " + file.getAbsolutePath());

        // If still not found, return empty array
        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return new Shape[0];
        }

        try (Scanner input = new Scanner(file)) {
            // Read the number of shapes
            if (!input.hasNextLine()) {
                System.out.println("Empty file.");
                return new Shape[0];
            }

            int numShapes;
            try {
                numShapes = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number of shapes on first line.");
                return new Shape[0];
            }

            Shape[] shapes = new Shape[numShapes];
            int index = 0;

            // Parse each shape line
            while (input.hasNextLine() && index < numShapes) {
                String line = input.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                String shapeType = parts[0];

                try {
                    switch (shapeType) {
                        case "Cylinder":
                            shapes[index++] = new Cylinder(
                                    Double.parseDouble(parts[1]),
                                    Double.parseDouble(parts[2])
                            );
                            break;
                        case "Cone":
                            shapes[index++] = new Cone(
                                    Double.parseDouble(parts[1]),
                                    Double.parseDouble(parts[2])
                            );
                            break;
                        case "Pyramid":
                            shapes[index++] = new Pyramid(
                                    Double.parseDouble(parts[1])
                            );
                            break;
                        case "SquarePrism":
                            shapes[index++] = new SquarePrism(
                                    Double.parseDouble(parts[1]),
                                    Double.parseDouble(parts[2])
                            );
                            break;
                        case "TriangularPrism":
                            shapes[index++] = new TriangularPrism(
                                    Double.parseDouble(parts[1]),
                                    Double.parseDouble(parts[2])
                            );
                            break;
                        case "PentagonalPrism":
                            shapes[index++] = new PentagonalPrism(
                                    Double.parseDouble(parts[1]),
                                    Double.parseDouble(parts[2])
                            );
                            break;
                        case "OctagonalPrism":
                            shapes[index++] = new OctagonalPrism(
                                    Double.parseDouble(parts[1]),
                                    Double.parseDouble(parts[2])
                            );
                            break;
                        default:
                            System.out.println("Unknown shape type: " + shapeType);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid line: " + line);
                }
            }

            System.out.println("File loaded successfully: " + index + " shapes parsed.");

            // ✅ Print all parsed shapes
            for (int i = 0; i < index; i++) {
                if (shapes[i] != null)
                    System.out.println("[" + i + "] " + shapes[i].getClass().getSimpleName() + " → " + shapes[i]);
            }

            return shapes; // returns the array

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return new Shape[0];
        }
    }
}
