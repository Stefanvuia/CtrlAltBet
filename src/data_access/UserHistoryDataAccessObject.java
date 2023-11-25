package data_access;

//import entity.account.Account;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserHistoryDataAccessObject {
//    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, List<Integer>> accounts = new HashMap<String, List<Integer>>();
}
