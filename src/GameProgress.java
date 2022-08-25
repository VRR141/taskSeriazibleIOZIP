import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * Class parameters
     */
    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    /**
     * path - path for Save GameProgress
     */
    private String path = "Games/savegames/save";
    private ArrayList<GameProgress> list;

    /**
     * Saved paths
     */
    private String[] paths = {"Games/savegames/save1.dat", "Games/savegames/save2.dat", "Games/savegames/save3.dat"};

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public GameProgress(){
    }

    /**
     * Delete files from paths
     */
    public void delete(){
        for (String s : paths) {
            File temp = new File(s);
            if (temp.delete()) {
                //System.out.println(s + "DELETED");
            } else {
                System.out.println(s + " NOT DELETED");
            }
        }
    }

    /**
     * zip files to Games/savegames/zipFiles.zip from paths
     */
    public void zip(int variable) {
        if (variable == 1) {
            zipFiles("Games/savegames/zipFiles.zip", paths);
            delete();
        }
        if (variable == 0){
            zipFiles("Games/savegames/zipFiles.zip", paths);
        }
    }

    /**
     * unZip files from Games/savegames/zipFiles.zip -> Games/unZip
     */
    public void uNzip(){
        openZip("Games/savegames/zipFiles.zip", "unZip");
    }

    /**
     * save Serializable GameProgress objects from List to path
     */
    public void saveGames(){
        createGameProgress();
        for (int i = 0; i < list.size(); i++){
            var temp = (path.concat(String.valueOf(i+1)).concat(".dat"));
            saveGame(temp, list.get(i));
        }
    }

    /**
     * return GameProgress from saved files;
     */
    public GameProgress openProgress(String path){
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            var gp = (GameProgress) ois.readObject();
            return gp;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * open zip (pathZip), unZip -> pathUnZip
     */
    private void openZip(String pathZip, String pathUnZip) {
        File tempFile = new File("Games/".concat(pathUnZip));
        tempFile.mkdir();
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(pathZip)))
        {
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName();
                name = name.replaceAll("savegames", pathUnZip);
                FileOutputStream fos = new FileOutputStream(name);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fos.write(c);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * zip files -> zipName.zip from paths
     */
    private void zipFiles(String zipName, String[] paths) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName))) {
            for (String s : paths) {
                FileInputStream fis;
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

    /**
     * save Serializable GameProgress object to path
     */
    private void saveGame(String path, GameProgress gameProgress){
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(gameProgress);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * create new GameProgressObjects
     */
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
