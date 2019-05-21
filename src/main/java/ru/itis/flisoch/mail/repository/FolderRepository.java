package ru.itis.flisoch.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.flisoch.mail.domain.Folder;
import ru.itis.flisoch.mail.domain.User;

import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    @Query("Select f from Folder f where f.name = :folderName and f.owner = :owner")
    Optional<Folder> findByNameAndOwner(@Param("folderName") String folderName,
                                           @Param("owner") User owner);
}
