package com.engg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.engg.madel.Subject;

@Repository
@Transactional
public interface UploadRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {

	@Modifying
	@Query(value = "DELETE FROM Subject s WHERE s.year=:year AND s.orderForDisplay=:orderForDisplay AND s.courseName=:courseName")
	void deleteDataIfAvailable(@Param("year") String year, @Param("orderForDisplay") int orderForDisplay,
			@Param("courseName") String courseName);

	@Modifying
	@Query(value = "SELECT DISTINCT s.courseName FROM Subject s where s.year=:year ORDER BY orderForDisplay")
	List<String> getFirstYearCourseName(String year);

	@Modifying
	@Query(value = "SELECT DISTINCT s.year,s.courseName FROM Subject s where s.year<>:year ORDER BY orderForDisplay")
	List<String> getOtherYearCourseName(String year);

	@Modifying
	@Query(value = "SELECT s FROM Subject s WHERE s.courseName=:courseName AND s.year=:year")
	List<Subject> retriveSubjectExcelData(String courseName, String year);

}
