package org.generation.italy.main.repo;

import org.generation.italy.main.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{

}
