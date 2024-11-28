package lshh.pollservice.common.bean;

public class UserThreadManager {
    private static final ThreadLocal<String> ADMIN_ID = new ThreadLocal<>();

    public static String getAdminId(){
        return ADMIN_ID.get();
    }
    public static void setAdminId(String adminId){
        ADMIN_ID.set(adminId);
    }
}
