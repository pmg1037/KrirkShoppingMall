package piyathep.krirk.ac.th.krirkshoppingmall.utility;

/**
 * Created by Piyathep on 27/3/2561.
 */

public class MyConstant {

    //    About URL
    private String urlAddUserString = "http://androidthai.in.th/kir/addDataUng.php";
    private String urlGetAllUserString = "http://androidthai.in.th/kir/getAllUserGame.php";


    //    About Array
    private String[] columnUser = new String[]{"id", "Name", "User", "Password", "Mode"};

    public String[] getColumnUser() {
        return columnUser;
    }

    public String getUrlGetAllUserString() {
        return urlGetAllUserString;
    }

    public String getUrlAddUserString() {
        return urlAddUserString;
    }
}   //  Main Class
