package com.postgre.springapipostgre.Repositories;

import com.postgre.springapipostgre.models.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
