public class Main {

    public static void main(String[] args) {
        firstTask(); // ДЗ 1
        secondTask(); // ДЗ 2
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
}
