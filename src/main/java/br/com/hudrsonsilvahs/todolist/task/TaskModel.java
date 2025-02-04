package br.com.hudrsonsilvahs.todolist.task;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "TB_tasks")
public class TaskModel {
    /**
     * id
     * usuario (id_usuario
     * descrição
     * data de inicio
     * de termino
     * titulo
     * prioridade
     *
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(length = 50)
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
