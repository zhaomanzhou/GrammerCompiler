package cn.edu.nwafu.admin.repository;

import cn.edu.nwafu.admin.domain.EmailSendHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSendHistoryRepository extends JpaRepository<EmailSendHistory, Long>
{
}
