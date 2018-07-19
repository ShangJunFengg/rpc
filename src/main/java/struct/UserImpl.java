package struct;

public class UserImpl implements User {
    public String say(String name) {
        System.out.println("---调用了---");
        return name+"你好---";
    }
}
