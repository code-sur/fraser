package CodeRaguet.fraser.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HerokuDBURLParser {

    public static String parse(String database_url) {
        String regex = "postgres://(\\w+):(\\w+)@(\\w+):(\\d+)/(\\w+)";
        Matcher matcher = Pattern.compile(regex).matcher(database_url);
        matcher.find();
        String user = matcher.group(1);
        String password = matcher.group(2);
        String host = matcher.group(3);
        String port = matcher.group(4);
        String db = matcher.group(5);
        return String.format("jdbc:postgresql://%s:%s/%s?user=%s&password=%s", host, port, db, user, password);
    }

}
