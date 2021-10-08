import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Searcher {
    private static String savePath = "D:\\!designBurg!\\Desktop\\From_order";;
    private static String searchPath = "D:\\!designBurg!\\Desktop\\ORDER";
    private static int folderName = 0;

    int a = 5;

    public static void main(String[] args) throws IOException {
        getFiles(new File(searchPath));
    }

    private static void getFiles(File rootFile) throws IOException {
        if (rootFile.isDirectory()) {

            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory() && !file.toString().contains("out")  && !file.toString().contains("print")
                            && !file.toString().contains("work")  && !file.toString().contains("utv")) {

                        getFiles(file);
                    } else {
                        if (file.getName().toLowerCase().contains("logo")) {
                            Path path = Paths.get(rootFile.getAbsolutePath());
                            String fileName = file.getName();
                            String newPath = savePath + "\\" + fileName;
                            String oldPath = file.getAbsolutePath();

                            if (!Files.exists(path)) {
                                Files.createDirectory(Path.of(savePath + "\\" + folderName));
                                System.out.println("Папка создана: " + Path.of(savePath + "\\" + folderName));
                            }
                            copy(newPath + folderName, oldPath);

                            folderName += 1;
                        }
                    }
                }
            }
        }
    }

    public static void copy (String newPath, String oldPath) throws IOException {
        System.out.println("Путь к файлу: " + oldPath);
        System.out.println("Путь сохранения: " + newPath);
        Path pToDest = Paths.get(oldPath);
        Path pToSave = Paths.get(newPath);

        Files.copy(pToDest, pToSave, StandardCopyOption.REPLACE_EXISTING);
    }
}

