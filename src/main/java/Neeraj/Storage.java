package Neeraj;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/neeraj.txt";

    public static void save(ArrayList<Task> tasks) {
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task t : tasks) {
                fw.write(t.toSaveFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return tasks;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                case "T":
                    Todo t = new Todo(parts[2]);
                    if (parts[1].equals("1")) t.markAsDone();
                    tasks.add(t);
                    break;
                case "D":
                    Deadline d = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) d.markAsDone();
                    tasks.add(d);
                    break;
                case "E":
                    Event e = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) e.markAsDone();
                    tasks.add(e);
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}