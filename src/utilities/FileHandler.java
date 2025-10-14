package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import shapes.*;

public class FileHandler {

    public static Shape[] parse(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("❌ Invalid file name.");
            return new Shape[0];
        }

        // Clean file name (remove quotes and whitespace)
        fileName = fileName.replace("\"", "").trim();

        File file = new File(fileName);

        // If file doesn't exist, try additional locations
        if (!file.exists()) {
            // Relative to working directory
            File userDirRelative = new File(System.getProperty("user.dir"), fileName);
            if (userDirRelative.exists()) file = userDirRelative;

            // Relative to user.dir/res
            File resRelative = new File(System.getProperty("user.dir") + File.separator + "res", fileName);
            if (resRelative.exists()) file = resRelative;

            // Relative to JAR directory
            try {
                String jarDir = new File(FileHandler.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .toURI())
                        .getParent();
                File jarRelative = new File(jarDir, fileName);
                if (jarRelative.exists()) file = jarRelative;
            } catch (Exception e) {
                // ignore
            }
        }

        if (!file.exists()) {
            System.out.println("❌ File not found: " + file.getAbsolutePath());
            return new Shape[0];
        }

        // Parse shapes
        try (Scanner input = new Scanner(file)) {
            if (!input.hasNextLine()) {
                System.out.println("⚠️ Empty file.");
                return new Shape[0];
            }

            int numShapes;
            try {
                numShapes = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid number of shapes on first line.");
                return new Shape[0];
            }

            Shape[] shapes = new Shape[numShapes];
            int index = 0;

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
<<<<<<< HEAD
                            shapes[index++] = new Pyramid(
                                    Double.parseDouble(parts[1]), 
                                    Double.parseDouble(parts[2])  
=======
                            // Now height first, side second
                            shapes[index++] = new Pyramid(
                                Double.parseDouble(parts[1]), // height
                                Double.parseDouble(parts[2])  // side
>>>>>>> 72e3c07810550c3971e6d4f87b73065a649b38a2
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
                            System.out.println("⚠️ Unknown shape type: " + shapeType);
                    }
                } catch (Exception e) {
                    System.out.println("⚠️ Invalid line: " + line);
                }
            }

            System.out.println("✅ File loaded successfully: " + index + " shapes parsed.");

            return shapes;

        } catch (FileNotFoundException e) {
            System.out.println("❌ File not found: " + file.getAbsolutePath());
            return new Shape[0];
        }
    }
}
