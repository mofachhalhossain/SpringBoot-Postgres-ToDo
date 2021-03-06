package com.project.springbootpostgrestodo.Dao;

import com.project.springbootpostgrestodo.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    @Query("from ToDo as c where c.user.id=:id")
    public List<ToDo> findToDoByUser(@Param("id") int id);

}
