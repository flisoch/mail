package ru.itis.flisoch.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flisoch.mail.domain.Filter;

public interface FilterRepository extends JpaRepository<Filter, Long> {
}
