import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Searcher {
    private static String savePath = "D:\\!designBurg!\\Desktop\\From_order";
    private static String searchPath = "Z:\\For_office\\Logos";
    private static int folderName = 0;

    public static void main(String[] args) throws IOException {

        ArrayList<File> fileList = new ArrayList<>();
        getFiles(new File(searchPath), fileList);
        for(File file: fileList) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private static void getFiles(File rootFile, List<File> fileList) throws IOException {
        if (rootFile.isDirectory()) {

            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {

                    if (file.isDirectory() && !file.toString().contains("out")  && !file.toString().contains("print")
                            && !file.toString().contains("work")  && !file.toString().contains("utv")) {
                        System.out.println("Ищу в: " + file);
                        getFiles(file, fileList);
                    } else {
                        if (file.getName().toLowerCase().contains("logo")) {
                            fileList.add(file);

                            Path path = Paths.get( savePath + "\\" + folderName);
                            String fileName = file.getName();
                            String newPath = savePath;
                            String oldPath = file.getAbsolutePath();

                            if (Files.exists(path)){
                                System.out.println("Папка уже существует");
                            }
                            else {
                                Files.createDirectory(Path.of(savePath + "\\" + folderName));
                                System.out.println("Папка создана");

                            }

                            copy(newPath + "\\" + folderName +  "\\" + fileName, oldPath);

                        }
                    }
                }
                folderName += 1;
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

