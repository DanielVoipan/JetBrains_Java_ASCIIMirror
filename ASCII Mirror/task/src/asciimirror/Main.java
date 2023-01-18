package asciimirror;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the file path:");
        String text = scanner.nextLine();
        int[] max = new int[]{0};
        try {
            List<StringBuilder> str = animalContent(text, max);
            StringBuilder s1 = str.get(0);
            for (String s : s1.toString().split("\\n")) {
                System.out.println(addWhiteSpaces(s, max[0]) + " | "+ mirrorAnimalContent(addWhiteSpaces(s,max[0])));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // return animal in mirror
    static String mirrorAnimalContent(String f) {
        StringBuilder str = new StringBuilder();
        for (int i = f.length() - 1; i >= 0; i--) {
            char c = f.charAt(i);
            str.append(mirrorCharacters(c));
        }
        return str.toString();
    }

    // return characters that aren't horizontaly mirrored
    static char mirrorCharacters(char c) {
        char r = c;
        switch (c) {
            case '<' :
                r = '>';
                break;
            case '>' :
                r = '<';
                break;
            case '[' :
                r = ']';
                break;
            case ']' :
                r = '[';
                break;
            case '(' :
                r = ')';
                break;
            case ')' :
                r = '(';
                break;
            case '{' :
                r = '}';
                break;
            case '}' :
                r = '{';
                break;
            case '/' :
                r = '\\';
                break;
            case '\\' :
                r = '/';
                break;
        }
        return r;
    }

    // get content from animal file
    static List<StringBuilder> animalContent(String f, int[] maxLength) throws Exception {
        List<StringBuilder> str = new ArrayList<>();
        StringBuilder s1 = new StringBuilder();
        File file = new File(f);
        if (!file.isFile()) {
            throw new Exception("File not found!");
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.length() > maxLength[0]) {
                    maxLength[0] = line.length();
                }
                s1.append(line).append("\n");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        str.add(s1);
        return str;
    }

    // add white spaces based on maxvalue of lines
    // foreach line in order to output in mirror
    static String addWhiteSpaces(String l, int maxLength) {
        StringBuilder str = new StringBuilder(l);
        int length = l.length();
        int dif = maxLength - length;
        for (int i = 0; i < dif; i++) {
            str.append("\s");
        }
        return str.toString();
    }
}