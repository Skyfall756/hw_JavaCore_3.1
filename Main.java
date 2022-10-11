import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Main {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        File game = new File("C://Game");

        File src = new File(game, "src");
        makeDir(src, sb);

        File res = new File(game, "res");
        makeDir(res, sb);

        File savegames = new File(game, "savegames");
        makeDir(savegames, sb);

        File temp = new File(game, "temp");
        makeDir(temp, sb);

        File main = new File(src, "Main");
        makeDir(main, sb);

        File test = new File(src, "Test");
        makeDir(test, sb);

        File mainJava = new File(main, "Main.java");
        makeFile(mainJava, sb);

        File utils = new File(main, "Utils.java");
        makeFile(utils, sb);

        File dravables = new File(res, "Drawables");
        makeDir(dravables, sb);

        File vectors = new File(res, "Vectors");
        makeDir(vectors, sb);

        File icons = new File(res, "Icons");
        makeDir(icons, sb);

        File temp1 = new File(temp, "Temp.txt");
        makeFile(temp1, sb);


        List<GameProgress> gp = Arrays.asList(
                new GameProgress(100, 56, 12, 30),
                new GameProgress(79, 115, 24, 10),
                new GameProgress(50,20,35,80));



        for (GameProgress gameProgress : gp) {
            String path = savegames.getPath();
            gameProgress.saveGame(path, gameProgress, sb);
        }
        logWriter(sb, temp1);

        zipFiles(savegames.getAbsolutePath(), savesPaths(savegames));

     //   deleteSaves(savegames);




    }


    public static void logWriter(StringBuilder sb, File temp1) {
        try (FileWriter log = new FileWriter(temp1, true)) {
            log.write(sb.toString());
            log.flush();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void makeDir (File file, StringBuilder sb) {
        if (file.mkdir()) {
            sb.append(file.getName() + " создан\n");
        } else {
            sb.append(file.getName() + " НЕ создан\n");
        }

    }

    public static void makeFile (File file, StringBuilder sb) {
        try {
            if (file.createNewFile()) {
                sb.append(file.getName() + " создан\n");
            }
        } catch (IOException ex) {
            sb.append(ex.getMessage() + ":" + file.getName() + "\n");
        }
    }

    public static void zipFiles(String path, List<String> saves) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path + "/zip.zip"))) {
            for (String str : saves) {
                try (FileInputStream fis = new FileInputStream(str)) {
                    ZipEntry entry = new ZipEntry(new File(str).getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static List <String> savesPaths(File saves) {
        List<String> paths = new ArrayList<>();
        File[] file = saves.listFiles();
        for (int i = 0; i < file.length; i++) {
            String path = file[i].getAbsolutePath();
            paths.add(path);
        }
        return paths;
    }

    public static void deleteSaves (File savegame) {
        for (File file : savegame.listFiles()) {
            if (file.isFile())
            file.delete();
        }
    }
}
