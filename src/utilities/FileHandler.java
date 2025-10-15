package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import shapes.*;

/**
 * The {@code FileHandler} class is responsible for reading and parsing
 * text files containing shape data, and creating corresponding {@link Shape}
 * objects based on that data.
 * <p>
 * It supports multiple file path checks to ensure flexibility when running
 * from different working directories or from within a JAR.
 * <p>
 * Each input file is expected to follow this format:
 * <pre>
 * 7
 * Cylinder 12.0 3.0
 * Cone 9.5 4.0
 * Pyramid 10.0 5.0
 * ...
 * </pre>
 * The first line specifies how many shapes are listed below.
 */
public class FileHandler {

    /**
     * Parses a text file and constructs an array of {@link Shape} objects
     * based on the file contents.
     *
     * @param fileName the name (or path) of the input file
     * @return an array of {@link Shape} objects
     */
    public static Shape[] parse(String fileName) {
        // Validate file name
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Invalid file name.");
            return new Shape[0];
        }

        // Clean up possible quotes or spaces
        fileName = fileName.replace("\"", "").trim();
        File file = new File(fileName);

        // Try alternate paths if file not found in working directory
        if (!file.exists()) {
            File userDirRelative = new File(System.getProperty("user.dir"), fileName);
            if (userDirRelative.exists()) file = userDirRelative;

            File resRelative = new File(System.getProperty("user.dir") + File.separator + "res", fileName);
            if (resRelative.exists()) file = resRelative;

            // Attempt to locate relative to JAR execution path
            try {
                String jarDir = new File(FileHandler.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .toURI())
                        .getParent();
                File jarRelative = new File(jarDir, fileName);
                if (jarRelative.exists()) file = jarRelative;
            } catch (Exception e) {
                // Ignore if unable to locate jar path
            }
        }

        // If file still not found, display full attempted path
        if (!file.exists()) {
            System.out.println("❌ File not found: " + file.getAbsolutePath());
            return new Shape[0];
        }

        // Begin parsing
        try (Scanner input = new Scanner(file)) {
            if (!input.hasNextLine()) {
                System.out.println("⚠️ Empty file.");
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

            // Read each line and create appropriate shape objects
            while (input.hasNextLine() && index < numShapes) {
                String line = input.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                String shapeType = parts[0];

                try {
                    switch (shapeType) {
                        case "Cylinder":
                            shapes[index++] = new Cylinder(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        case "Cone":
                            shapes[index++] = new Cone(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        case "Pyramid":
                            shapes[index++] = new Pyramid(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        case "SquarePrism":
                            shapes[index++] = new SquarePrism(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        case "TriangularPrism":
                            shapes[index++] = new TriangularPrism(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        case "PentagonalPrism":
                            shapes[index++] = new PentagonalPrism(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        case "OctagonalPrism":
                            shapes[index++] = new OctagonalPrism(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        default:
                            System.out.println("Unknown shape type: " + shapeType);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid line: " + line);
                }
            }

            System.out.println("File loaded successfully: " + index + " shapes parsed.");
            return shapes;

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return new Shape[0];
        }
    }
}
