import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;
    private String path = "Games/savegames/save";
    private ArrayList<GameProgress> list;
    private String[] paths = {"Games/savegames/save1.dat", "Games/savegames/save2.dat", "Games/savegames/save3.dat"};

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public GameProgress(){
    }

    public void delete(){
        for (String s : paths) {
            File temp = new File(s);
            if (temp.delete()) {
                System.out.println(s + " deleted");
            } else {
                System.out.println(s + " NOT DELETED");
            }
        }
    }

    public void zip(int variable) {
        if (variable == 1) {
            zipFiles(paths);
            delete();
        }
        if (variable == 0){
            zipFiles(paths);
        }
    }

    public void saveGames(){
        createGameProgress();
        for (int i = 0; i < list.size(); i++){
            var temp = (path.concat(String.valueOf(i+1)).concat(".dat"));
            saveGame(temp, list.get(i));
        }
    }

    private void zipFiles(String[] paths) {
        String zipName = "Games/savegames/zipFiles.zip";
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName))) {
            for (String s : paths) {
                FileInputStream fis = null;
                ZipEntry entry = new ZipEntry(s);
                zos.putNextEntry(entry);
                fis = new FileInputStream(s);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zos.write(buffer);
                fis.close();
                zos.closeEntry();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    private void saveGame(String path, GameProgress gameProgress){
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(gameProgress);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void createGameProgress(){
        list = new ArrayList<>(Arrays.asList(
                new GameProgress(25, 25, 25, 25),
                new GameProgress(30, 30, 30, 30),
                new GameProgress(40, 40, 40, 40)));
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
}
