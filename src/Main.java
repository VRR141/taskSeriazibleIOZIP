import java.io.File;

public class Main {

    public static void main(String[] args) {
        initGames(); //init Games directory
        firstTask(); // HW 1
        secondTask(); // HW 2
        thirdTask(); // HW 3
    }

    private static void firstTask(){
        DirectoriesFiles df = new DirectoriesFiles();
        df.createDirectories();
        df.createFiles();
        df.writeTemp();
    }

    private static void secondTask() {
        GameProgress gp = new GameProgress();
        gp.saveGames();
        gp.zip(1); // variable == 1 with delete, == 0 w/o delete
    }

    private static void thirdTask(){
        GameProgress gp = new GameProgress();
        gp.uNzip();
        System.out.println(gp.openProgress("Games/unZip/save1.dat"));
    }

    private static boolean initGames(){
        File file = new File("Games");
        return file.mkdir();
    }
}
