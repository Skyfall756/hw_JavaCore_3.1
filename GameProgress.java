import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    public void saveGame(String path, GameProgress gp, StringBuilder sb) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss_SSS");
        path = path + "/save" + sdf.format(new Date()) + ".dat";
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
            sb.append(gp + " сохранен\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
