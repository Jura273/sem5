package M.T;
/*
Домашнее задание:

        1. Написать функцию, создающую резервную копию всех файлов в директории во вновь созданную папку ./backup
        2. Доработайте класс Tree и метод print который мы разработали на семинаре.
         Ваш метод должен распечатать полноценное дерево директорий и файлов относительно корневой директории.

        Данная промежуточная аттестация оценивается по системе "зачет" / "не зачет".

*/

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) {
        displayDirRecursively("src", 0);
        String sourceDirectory = "./";
        String backupDirectory = "./backup";

        try {
            createBackup(sourceDirectory, backupDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void displayDirRecursively(String directory, int depth) {
        File file = new File(directory);
        if (depth > 0) {
            for (int i = 0; i < depth; i++) {
                System.out.print("   ");
            }
            System.out.print("|__");
        }
        System.out.println(file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                displayDirRecursively(files[i].getPath(), depth + 1);
            }
        }
    }


    public static void createBackup(String sourceDirectory, String backupDirectory) throws IOException {
        // Создаем папку для резервных копий, если ее нет
        File backupDir = new File(backupDirectory);
        if (!backupDir.exists()) {
            backupDir.mkdir();
        }

        // Получаем список файлов в директории
        File sourceDir = new File(sourceDirectory);
        File[] filesToBackup = sourceDir.listFiles();

        // Копируем каждый файл в папку с резервными копиями
        for (File file : filesToBackup) {
            if (file.isFile()) {
                Files.copy(file.toPath(), new File(backupDir.getPath() + "/" + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }


}