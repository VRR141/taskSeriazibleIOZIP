import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DirectoriesFiles {

    /**
     * DIRECTORIES FOR CREATE
     */
    private String[] directories = {
            "Games/src", "Games/res", "Games/savegames", "Games/temp",
            "Games/src/main", "Games/src/test",
            "Games/res/drawables", "Games/res/vectors", "Games/res/icons"};

    /**
     * FILES FOR CREATE
     */

    private String[] files = {"Main.java", "Utils.java"};
    /**
     * text file for logs
     */

    private String tmp = "temp.txt";

    private StringBuffer sb = new StringBuffer();

    /**
     * create files
     */
    protected void createFiles() {
        String p1 = "Games/src/main/";
        String p2 = "Games/temp/";
        for (String s : files) {
            String tempS = p1.concat(s);
            File temp = new File(tempS);
            try {
                if (temp.createNewFile()) {
                    sb.append(tempS).append(" file created \n");
                } else {
                    sb.append(tempS).append(" file NOT created \n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String tempS = p2.concat(tmp);
        File temp = new File(tempS);
        try {
            if (temp.createNewFile()) {
                sb.append(tempS).append(" file created \n");
            } else {
                sb.append(tempS).append(" file NOT created \n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * create directories
     */

    protected void createDirectories() {
        for (String s : directories) {
            File temp = new File(s);
            if (temp.mkdir()) {
                sb.append(s).append(" directory created \n");
            } else {
                sb.append(s).append(" directory NOT created ERROR \n");
            }
        }
    }

    /**
     * logs writer
     */
    protected StringBuffer writeTemp() {
        String temp = directories[3].concat("/").concat(tmp);
        sb.append("__________________________________");
        try (FileWriter fw = new FileWriter(temp, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }
}
