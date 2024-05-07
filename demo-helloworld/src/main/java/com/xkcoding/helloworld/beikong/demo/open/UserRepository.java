package com.xkcoding.helloworld.beikong.demo.open;

public class UserRepository implements DataRepository<User> {
    @Override
    public boolean saveOrUpdate(User user) {
        // 假设这里是实际的保存或更新逻辑
        // 返回 true 表示操作成功，返回 false 表示操作失败
        return true;
    }
}