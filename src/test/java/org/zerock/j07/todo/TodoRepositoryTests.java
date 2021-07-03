package org.zerock.j07.todo;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j07.todo.entity.Todo;
import org.zerock.j07.todo.repository.TodoRepository;

import java.util.*;
import java.util.stream.IntStream;

@ActiveProfiles("dev")
@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1() {
        log.info(todoRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,300).forEach(i -> {
            Todo todo = Todo.builder()
                    .content("내용..."+i)
                    .build();
            todoRepository.save(todo);
        });//loop
    }

    @Test
    public void testSelect() {
        Optional<Todo> result = todoRepository.findById(1L);
        //log.info(result.get());
        result.ifPresent(todo->log.info(todo));
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(pageable);
        log.info(result);
        result.getContent().forEach(todo->log.info(todo));
    }

    @Test
    public void testUpdate() {
        Optional<Todo> result = todoRepository.findById(300L);

        result.ifPresent(todo->{
            todo.changeTitle("300번 내용 수정");
            todoRepository.save(todo);
        });
    }

    @Test
    public void testDelete() {
        todoRepository.delete(Todo.builder().tno(300L).build());
    }

    @Test
    public void testList1(){
        String keyword = "15";
        Pageable pageable = PageRequest.of(0,10);
        Page<Todo> result = todoRepository.getList(keyword,pageable);
        log.info(result.getTotalElements());
        result.getContent().forEach(todo->{
            log.info(todo);
        });
    }

    @Test
    public void testDoA(){
        todoRepository.doA();
    }

    @Test
    public void testListWithSearch() {
        Pageable pageable = PageRequest.of(0, 10);
        String keyword = "10";
        Page<Todo> result = todoRepository.listWithSearch(keyword, pageable);
        log.info(result.getTotalElements());
        result.getContent().forEach(todo ->{
            log.info(todo);
        });
    }

    @Test
    public void testRecusion() {
        System.out.println(palindromeCheck("sitonapanotis"));
//        System.out.println(recursionOperator(1,10,"*"));
//        System.out.println(fibonacci(13,0,1));
//        System.out.println(fibonacci2(15));
//        System.out.println(plus(10));
//        System.out.println(multi(10));
    }
    public int multi(int a) {
        if(a == 1) {
            return a;
        } else {
            return multi(a-1)*a;
        }
    }
    public int plus(int a) {
        if(a == 0) {
            return a;
        } else {
            return plus(a-1)+a;
        }
    }
    public int fibonacci(int count, int prevNum, int currNum) {
        System.out.print(currNum+ " ");
        if(count > 1) {
            return prevNum+fibonacci(count-1,currNum,currNum+prevNum);
        } else {
            return prevNum+1;
        }
    }
    public int fibonacci2(int count) {
        if(count == 1 || count == 2) {
            return 1;
        }
        return fibonacci2(count-2) + fibonacci2(count-1);
    }
    public int recursionOperator(int start, int end, String operator) {
        if(start < end) {
            if(operator.equals("+")) {
                return start + recursionOperator(start+1,end,operator);
            } else if (operator.equals("-")) {
                return -1*start + recursionOperator(start+1,end,operator);
            } else if (operator.equals("*")) {
                return start * recursionOperator(start+1,end,operator);
            } else if (operator.equals("/")) {
                if(start > 0) {
                    return start / recursionOperator(start+1,end,operator);
                } else {
                    return 0;
                }
            }
        }
        if(operator.equals("-")) {
            return start*-1;
        }
        return start;
    }

    public boolean palindromeCheck(String s){
        if(s.length() == 0 || s.length() == 1) {
            return true;
        }
        if(s.charAt(0) == s.charAt(s.length()-1)) {
            return palindromeCheck(s.substring(1, s.length()-1));
        }
        return false;
    }
}
