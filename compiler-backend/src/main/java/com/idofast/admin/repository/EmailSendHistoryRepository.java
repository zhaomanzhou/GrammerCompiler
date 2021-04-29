package com.idofast.admin.repository;

import com.idofast.admin.domain.EmailSendHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSendHistoryRepository extends JpaRepository<EmailSendHistory, Long>
{
}
