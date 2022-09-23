package com.apulbere;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) throws Exception {
        String sql = Files.readString(Path.of(App.class.getResource("/query.sql").toURI()));
        List<String> paramLines = Files.readAllLines(Path.of(App.class.getResource("/params.log").toURI()));

        List<String> params = new ArrayList<>(paramLines.size());
        for(String line: paramLines) {
            int i = line.lastIndexOf("[");
            String param = line.substring(i + 1, line.length() - 1);
            if(param.equals("true") || param.equals("false") || param.equals("null")) {
                params.add(param);
            } else if (isNumeric(param)) {
                params.add(param);
            } else {
                params.add("'" + param + "'");
            }
        }
        StringBuilder str = new StringBuilder();
        for(int i = 0, ii = 1; i < sql.length(); i++) {
            if(sql.charAt(i) == '?') {
                str.append(params.get(ii++ - 1));
            } else {
                str.append(sql.charAt(i));
            }
        }

        System.out.println(str);
    }

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

}
