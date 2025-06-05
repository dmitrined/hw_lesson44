
import java.io.*;
import java.util.Arrays;

import static java.lang.System.*;

public class Task1 {
    public static void main(String[] args) {
        /*
        задача 1.
Пользователь вашей программы задает путь (название папки) после чего программа проверяет,
есть ли в этой папке файлы с расширением .jpg. Если такие файлы есть, программа должна создать папку foto
 и скопировать все файлы jpg в эту папку.
Если пользователь ввел несуществующую папку, программа должна выводить соответствующее сообщение.
Если в заданной папке нет файлов .jpg программа не должна создавать никаких дополнительных файлов или папок.

         */
        photoCopying("/Users/dmitrinedioglo/ait");

    }

    public static void photoCopying(String directory) {
        File file = new File(directory);

        if (!file.exists()) {
            System.out.println("Нет такой папки");
            return;
        }
        if (!file.isDirectory()) {
            System.out.println("Это не папка");
            return;
        }
        File[] list = file.listFiles(f -> f.isFile() && f.getName().endsWith("jpg"));
        if (list.length == 0) {
            System.out.println("В папке нету фоток");
            return;
        }


        File fotoDirectory = new File(file , "foto");
        if (fotoDirectory.mkdirs()) {
            for (var el : list) {
                
                try (FileInputStream fis = new FileInputStream(el);
                     FileOutputStream fos = new FileOutputStream(fotoDirectory)) {

                    byte[] buffer = new byte[4096];
                    int length;

                    while ((length = fis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }


    }

}



