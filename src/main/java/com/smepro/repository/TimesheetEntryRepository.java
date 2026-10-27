package com.smepro.repository;

import com.smepro.entity.TimesheetEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetEntryRepository extends JpaRepository<TimesheetEntry, Long> {

    /** ดึง timesheet ของ user ใน week ที่กำหนด */
    List<TimesheetEntry> findByUsernameAndWorkDateBetweenOrderByWorkDateAsc(
            String username, LocalDate startDate, LocalDate endDate);

    /** ดึง timesheet ทั้งหมดของ Task */
    List<TimesheetEntry> findByWbsElementIdOrderByWorkDateDesc(Long wbsId);

    /** ดึง timesheet รอ Approve */
    List<TimesheetEntry> findByApprovedByAndTsStatus(String approver, String tsStatus);

    /** รวมชั่วโมงจริงของ Task */
    @Query("SELECT SUM(t.hoursLogged) FROM TimesheetEntry t WHERE t.wbsElement.id = :wbsId")
    Double sumHoursByWbs(@Param("wbsId") Long wbsId);
}
