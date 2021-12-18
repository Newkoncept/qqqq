package Controller;

import Model.*;

import java.io.*;
import java.util.ArrayList;

public class SerializedDataCollection {
    ArrayList<Item> productArray = new ArrayList<>();
    ArrayList<Person> usersArray = new ArrayList<>();
    ArrayList<Sale> salesArray = new ArrayList<>();
    ArrayList<ShoppingCart> cartArray = new ArrayList<>();

    // This controls auto generation of the details
    // index 0 is for product
    // index 1 is for user
    int[] idGenerator = { 1, 1 };

    FileOutputStream fileOut;

    public SerializedDataCollection() {
        usersArray.add(new Person("admin", "adminUser",
                "admin Last", "admin@sample.com", "12345", "2021-01-14"));

        fileChecker();
    }

    // Method to check serialized files existence
    public void fileChecker() {
        File productsTempFile = new File("src/serializedData/productsDetails.ser");
        File usersTempFile = new File("src/serializedData/usersDetails.ser");
        File salesTempFile = new File("src/serializedData/salesDetails.ser");

        File idGeneratorTempFile = new File("src/serializedData/idGenerator.txt");

        boolean productsTempFileExists = productsTempFile.exists();
        boolean usersTempFileExists = usersTempFile.exists();
        boolean salesTempFileExists = salesTempFile.exists();

        boolean idGeneratorTempFileExists = idGeneratorTempFile.exists();

        if (productsTempFileExists == false || usersTempFileExists == false || salesTempFileExists == false) {
            serializeDetails(productArray);
            serializeDetails(usersArray);
            serializeDetails(salesArray);
        }

        if (idGeneratorTempFileExists == false) {
            serializeIdGenerator();
        }

    }

    // Serialize method for Models
    public void serializeDetails(ArrayList<?> arrayName) {
        try {
            if (arrayName == productArray) {
                fileOut = new FileOutputStream("src/serializedData/productsDetails.ser");
            } else if (arrayName == usersArray) {
                fileOut = new FileOutputStream("src/serializedData/usersDetails.ser");
            } else if (arrayName == salesArray) {
                fileOut = new FileOutputStream("src/serializedData/salesDetails.ser");
            }

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(arrayName);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialize method for models
    public void deSerializeDetails(ArrayList<?> arrayName, String storedFileName) {
        try {
            FileInputStream fis = new FileInputStream("src/serializedData/" + storedFileName + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            if (arrayName == productArray) {
                productArray = castToAnything(ois.readObject());
            } else if (arrayName == usersArray) {
                usersArray = castToAnything(ois.readObject());
            } else if (arrayName == salesArray) {
                salesArray = castToAnything(ois.readObject());
            }

            ois.close();
            fis.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    // Serialize method for user id tracker
    public void serializeIdGenerator() {
        try {
            FileOutputStream fos = new FileOutputStream("src/serializedData/idGenerator.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(idGenerator);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialize method for user id tracker
    public void deSerializeIdGenerator() {
        try {
            FileInputStream fis = new FileInputStream("src/serializedData/idGenerator.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            idGenerator = castToAnything(ois.readObject());

            ois.close();
            fis.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T castToAnything(Object obj) {
        return (T) obj;
    }

}
