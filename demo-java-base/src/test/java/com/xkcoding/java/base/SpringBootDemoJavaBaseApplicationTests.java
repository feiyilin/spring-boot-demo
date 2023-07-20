package com.xkcoding.java.base;

import com.xkcoding.java.base.functional.MyFunction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoJavaBaseApplicationTests {

    @Test
    public void contextLoads() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int count = 0;
        Integer a = 1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //executorService.submit(new MyRunnable());
//            count++;
//            Future<String> submit = executorService.submit(new MyRunnable(), "test" + count);
//            String s = submit.get();
//            System.out.println(s);
            executorService.submit(() -> {
                System.out.println("MyFunctionImpl.run" + Thread.currentThread().getName());
                list.add(1);
            });
        }
        Integer integer = test1((t) -> {
            System.out.println("MyFunctionImpl.run" + Thread.currentThread().getName());
            list.add(1);
            return t;
        }, a);
        test1(new MyFunction("test", 1), "test");
    }

    private <T, R> R test1(Function<T, R> function, T t) {
        return function.apply(t);
    }

}
