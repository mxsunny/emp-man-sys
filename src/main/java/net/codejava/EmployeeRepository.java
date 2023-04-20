package net.codejava;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
//	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
//			+ " OR p.brand LIKE %?1%"
//			+ " OR p.madein LIKE %?1%"
//			+ " OR CONCAT(p.price, '') LIKE %?1%")
	@Query("SELECT e FROM Employee e WHERE CONCAT(e.id, ' ',e.firstname, ' ', e.lastname, ' ', e.email, ' ', e.address, ' ', e.contact, ' ',e.salary, ' ') LIKE %?1%")
	public List<Employee> search(String keyword);
}
