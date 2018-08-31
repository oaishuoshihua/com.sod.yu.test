package com.sodyu.test.guavaOrjava8;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created  on 2018/3/4.
 */
public class OptionalTest {

    public static void main(String[] args) {
        new OptionalTest().whenMap_thenOk();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            User user =new User("person"+i,""+i);
            users.add(user);
        }

        User currentUser = users.stream().findFirst().orElse(new User("oaishuoshihua@163.com","1"));
        currentUser = users.stream().findFirst().orElseGet(() ->new User());//默认是给一个方法
        Optional optional = Optional.of(new User());//of创建时对象必须不为null
        System.out.println("id: "+currentUser.getId() + " email: "+currentUser.getEmail());
    }


    public  void whenMap_thenOk() {
        User user = new User("person","28");
        //map使用传入的函数返回值构建optional对象
        String email = Optional.ofNullable(user).map(u -> u.getEmail()).orElse("default");//可以获取一个对象的某个值，不用判断是否为空，并可以赋值一个默认值
        System.out.println(email);
        user = null;
        email = Optional.ofNullable(user).map(p->p.getEmail()).orElseGet(()-> { return new String("defalut");});
        email = Optional.ofNullable(user).map(p->p.getEmail()).orElseGet(()->  new String("defalut"));
        System.out.println(email);
    }



}

class User {
    private String email;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    User(String email,String id){
        this.email = email;
        this.id = id;
    }

    User (){}
}