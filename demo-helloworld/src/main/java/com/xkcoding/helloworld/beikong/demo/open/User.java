package com.xkcoding.helloworld.beikong.demo.open;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private Long id;
    private String username;
    private String email;

    public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static void main(String[] args) {
        // 创建一个用户列表
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "user1", "user1@example.com"));
        userList.add(new User(2L, "", "user2@example.com")); // 无效数据，缺少用户名
        userList.add(new User(3L, "user3", "")); // 无效数据，缺少邮箱

        // 创建一个批量操作服务实例
        BatchOperationService<User> service = new BatchOperationService<>(new UserValidator(), new UserRepository());

        // 调用批量操作方法
        BatchOperationResult<User> result = service.batchOperation(userList);

        // 处理结果
        List<User> successList = result.getSuccessList();
        List<BatchOperationFailure<User>> failureList = result.getFailureList();

        System.out.println("成功保存或更新的数据：");
        for (User user : successList) {
            System.out.println(user);
        }

        System.out.println("\n保存或更新失败的数据：");
        for (BatchOperationFailure<User> failure : failureList) {
            System.out.println("索引：" + failure.getIndex() + ", 数据：" + failure.getData() + ", 失败原因：" + failure.getReason());
        }
    }

}
