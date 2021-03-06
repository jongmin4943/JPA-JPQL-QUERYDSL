package org.zerock.j07.todo.repository.dynamic;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j07.todo.entity.QTodo;
import org.zerock.j07.todo.entity.Todo;

import java.util.List;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch{

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Todo doA() {

       log.warn("doA....");
       log.warn("doA....");
       log.warn("doA....");
       log.warn("doA....");
       QTodo todo = QTodo.todo;
       JPQLQuery<Todo> query = from(todo);

       //where content like '%9%' and tno>170
       //where todo.tno > 170
       query.where(todo.tno.gt(0L));
       //content like '%9%';
//       query.where(todo.content.like("%9%"));
       query.orderBy(todo.tno.desc());
       query.offset(0);
       query.limit(10);


       List<Todo> result = query.fetch();
       long count = query.fetchCount();

       log.warn(count);
       log.warn(result);
       return null;
    }


    @Override
    public Page<Todo> listWithSearch(String keyword, Pageable pageable) {
        QTodo todo = QTodo.todo;
        JPQLQuery<Todo> query = from(todo);
        if(keyword != null && keyword.trim().length() != 0) {
            query.where(todo.content.like("%"+keyword+"%"));
        }

        query.where(todo.tno.gt(0L));
        query.orderBy(todo.tno.desc());

        //paging
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        List<Todo> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }
}
